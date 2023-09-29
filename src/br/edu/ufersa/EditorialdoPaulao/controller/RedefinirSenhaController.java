package br.edu.ufersa.EditorialdoPaulao.controller;

import br.edu.ufersa.EditorialdoPaulao.view.Telas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RedefinirSenhaController {

	@FXML
	private TextField emailrecuperacao;
	
	@FXML
	private Label mensagemLabel;
	
    @FXML
    private void enviar(ActionEvent event) {
        String email = emailrecuperacao.getText();

        if (email.isEmpty()) {
            mensagemLabel.setText("Preencha o campo de e-mail.");
            mensagemLabel.setVisible(true);
        } else {
            //lógica para enviar o e-mail de recuperação de senha aqui
        	System.out.println("E-mail enviado com sucesso.");
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
