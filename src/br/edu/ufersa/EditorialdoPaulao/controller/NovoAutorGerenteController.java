package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NovoAutorGerenteController {

	@FXML
	private TextField cpf;
	
	@FXML
	private TextField nome;
	
	@FXML
	private TextField endereco;
	
	@FXML
	private Label erroNovoAutorGerente;
	
	@FXML
	private Button btncancelar;
	
	public void adicionar(ActionEvent event) {
	    String cpfText = cpf.getText();
	    String nomeText = nome.getText();
	    String enderecoText = endereco.getText();
	    if (cpfText.isEmpty() || nomeText.isEmpty() || enderecoText.isEmpty()) {
	    	erroNovoAutorGerente.setText("Por favor, preencha todos os campos.");
	        erroNovoAutorGerente.setVisible(true);
	    } else {
	        System.out.println("Autor adicionado com sucesso");
	        erroNovoAutorGerente.setText("Autor adicionado com sucesso");
	        erroNovoAutorGerente.setVisible(true);
	        btncancelar.setText("Fechar");
	    }
	}
	
	public void cancelar(ActionEvent event) {
		Stage stage = (Stage) erroNovoAutorGerente.getScene().getWindow();
	    stage.close();
	}
	
}
