package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarAvaliadorGerenteController {

	@FXML
	private TextField cpf;
	
	@FXML
	private TextField nome;
	
	@FXML
	private TextField endereco;
	
	@FXML
	private Label erroEditarAvaliadorGerente;
	
	@FXML
	private Button btncancelar;
	
	public void concluir(ActionEvent event) {
	    String cpfText = cpf.getText();
	    String nomeText = nome.getText();
	    String enderecoText = endereco.getText();
	    if (cpfText.isEmpty() || nomeText.isEmpty() || enderecoText.isEmpty()) {
	    	erroEditarAvaliadorGerente.setText("Por favor, preencha todos os campos.");
	    	erroEditarAvaliadorGerente.setVisible(true);
	    } else {
	        System.out.println("Edição bem-sucedida.");
	        erroEditarAvaliadorGerente.setText("Edição bem-sucedida.");
	        erroEditarAvaliadorGerente.setVisible(true);
	        btncancelar.setText("Fechar");
	    }
	}
	
	public void cancelar(ActionEvent event) {
		Stage stage = (Stage) erroEditarAvaliadorGerente.getScene().getWindow();
	    stage.close();
	}
	
}
