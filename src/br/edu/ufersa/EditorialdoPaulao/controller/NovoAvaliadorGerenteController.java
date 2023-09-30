package br.edu.ufersa.EditorialdoPaulao.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NovoAvaliadorGerenteController {

	@FXML
	private TextField cpf;
	
	@FXML
	private TextField nome;
	
	@FXML
	private TextField endereco;
	
	@FXML
	private Label erroNovoAvaliadorGerente;
	
	@FXML
	private Button btncancelar;
	
	public void adicionar(ActionEvent event) {
	    String cpfText = cpf.getText();
	    String nomeText = nome.getText();
	    String enderecoText = endereco.getText();
	    if (cpfText.isEmpty() || nomeText.isEmpty() || enderecoText.isEmpty()) {
	    	erroNovoAvaliadorGerente.setText("Por favor, preencha todos os campos.");
	        erroNovoAvaliadorGerente.setVisible(true);
	    } else {
	        System.out.println("Avaliador adicionado com sucesso");
	        erroNovoAvaliadorGerente.setText("Avaliador adicionado com sucesso");
	        erroNovoAvaliadorGerente.setVisible(true);
	        btncancelar.setText("Fechar");
	    }
	}
	
	public void cancelar(ActionEvent event) {
		Stage stage = (Stage) erroNovoAvaliadorGerente.getScene().getWindow();
	    stage.close();
	}
	
}
