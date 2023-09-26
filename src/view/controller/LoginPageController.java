package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.entities.Avaliador;

public class LoginPageController {

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtSenha;
    
    @FXML
    private Label lbLoginOuSenhaInvalidos;

    @FXML
    private TextField txtLabel;

    @FXML
    private Button btnLogin;
    


    
    @FXML
    public void fazerLogin(ActionEvent event) throws ErroLoginException {
        String login = txtLogin.getText();
        String senha = txtSenha.getText();

        if (login == null || login.isEmpty()) {
            txtLabel.setText("Usuário não existe!");
            lbLoginOuSenhaInvalidos.setLabelFor(txtLabel); //checar se é assim que se insere o texto na label
            lbLoginOuSenhaInvalidos.setVisible(true);
            throw new ErroLoginException("Usuário não existe");
        } else if (senha == null || senha.isEmpty()) {
            txtLabel.setText("Senha inválida!");
            lbLoginOuSenhaInvalidos.setLabelFor(txtLabel);
            lbLoginOuSenhaInvalidos.setVisible(true);
            throw new ErroLoginException("Senha inválida");
        } else {
            UserBO userBO = new UserBO();
            if (userBO.login(login)) { //metodo login do UserBO retorna true se o login existir
                String tipo = userBO.getTipo();
                boolean loginValido = false;

                if (tipo.equals("Gerente")) {
                    GerenteBO gerenteBO = new GerenteBO();
                    gerenteBO.setLogin(login);
                    gerenteBO.setSenha(senha);
                    loginValido = gerenteBO.login();
                } else if (tipo.equals("Autor")) {
                    AutorBO autorBO = new AutorBO();
                    autorBO.setLogin(login);
                    autorBO.setSenha(senha);
                    loginValido = autorBO.login();
                } else {
                    AvaliadorBO avaliadorBO = new AvaliadorBO();
                    avaliadorBO.setLogin(login);
                    avaliadorBO.setSenha(senha);
                    loginValido = avaliadorBO.login();
                }
            } else {
                txtLabel.setText("Erro ao fazer login. Verifique suas credenciais.");
                lbLoginOuSenhaInvalidos.setLabelFor(txtLabel);
                lbLoginOuSenhaInvalidos.setVisible(true);
                throw new ErroLoginException("Usuário não existe");
            }
        }
    }

}