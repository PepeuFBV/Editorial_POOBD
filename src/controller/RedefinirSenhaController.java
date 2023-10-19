package controller;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import exceptions.NotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.BO.UsuarioBO;
import model.VO.UsuarioVO;

public class RedefinirSenhaController {

	@FXML
	private TextField emailrecuperacao;
	
	@FXML
	private Label mensagemLabel;
	
	@FXML
	private Label label1;
	
	
	@FXML
	private Label label2;
	
	@FXML
	private Label label3;
	
	@FXML
	private Button botao;
	
    private void enviarEmail(String email, String senha) throws MessagingException {
        String host = "smtp.gmail.com";
        String username = "email do sr paulao";
        String password = "senha de acesso";
        int port = 465;

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Sua senha");
        message.setText("Sua senha é: " + senha); 

        Transport.send(message);
    }

    @FXML
    private void enviar(ActionEvent event) {
        String email = emailrecuperacao.getText();

        if (email.isEmpty()) {
            emailrecuperacao.setPromptText("Preencha o campo de e-mail.");
        } else {
            UsuarioVO usuarioVO = new UsuarioVO();
            usuarioVO.setEmail(email);
            UsuarioBO<UsuarioVO> usuarioBO = new UsuarioBO<UsuarioVO>();

            try {
                List<UsuarioVO> users = usuarioBO.buscarPorEmail(usuarioVO);

                if (!users.isEmpty()) {
                    UsuarioVO user = users.get(0); // pega o primeiro usuário da lista
                    String senha = user.getSenha(); // pega a senha do primeiro usuário

                    enviarEmail(email, senha);

                    label1.setVisible(false);
                    label2.setVisible(false);
                    label3.setVisible(false);
                    botao.setVisible(false);
                    emailrecuperacao.setVisible(false);
                    mensagemLabel.setText("E-mail enviado com sucesso.");
                    mensagemLabel.setVisible(true);
                } else {
                    throw new NotFoundException();
                }
            } catch (MessagingException | NotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void voltar(ActionEvent event) {
        Stage stage = (Stage) emailrecuperacao.getScene().getWindow();
        stage.close();
    }
}
