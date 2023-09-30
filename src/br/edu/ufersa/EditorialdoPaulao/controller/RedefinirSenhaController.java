package br.edu.ufersa.EditorialdoPaulao.controller;

import br.edu.ufersa.EditorialdoPaulao.view.Telas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
	
    @FXML
    private void enviar(ActionEvent event) {
        String email = emailrecuperacao.getText();

        if (email.isEmpty()) {
            mensagemLabel.setText("Preencha o campo de e-mail.");
            mensagemLabel.setVisible(true);
        } else {
        	System.out.println("E-mail enviado com sucesso.");
            //lógica para enviar o e-mail de recuperação de senha aqui
        	label1.setVisible(false);
        	label2.setVisible(false);
        	label3.setVisible(false);
        	botao.setVisible(false);
        	emailrecuperacao.setVisible(false);
        	mensagemLabel.setText("E-mail enviado com sucesso.");
            mensagemLabel.setVisible(true);
        	
        }
    }
    
    @FXML
    private void voltar(ActionEvent event) {
    	try {
			Telas.telaLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
