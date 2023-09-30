package br.edu.ufersa.EditorialdoPaulao.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NovaObraAutorController {
	
	ObservableList<String> obras = FXCollections.observableArrayList("As Crônicas de Nárnia");
	
	@FXML
	private ChoiceBox<String> obra; //titulos
	
	@FXML
	private TextField titulo;
	
	@FXML
	private TextField genero;
	
	@FXML
	private TextField ano;
	
	@FXML
	private Label erroNovaObraAutor;
	
	@FXML
	private Button btncancelar;
	
	
	public void adicionar(ActionEvent event) {
		
        String selecao = (String) obra.getValue(); 
        if (selecao != null) {
            // adicionar obra 
        } else {
        	erroNovaObraAutor.setText("Você deve selecionar uma obra.");
        	erroNovaObraAutor.setVisible(true);
            return;
        }
        
	    String tituloText = titulo.getText();
	    String generoText = genero.getText();
	    String anoText = ano.getText();
	    if (tituloText.isEmpty() || generoText.isEmpty() || anoText.isEmpty()) {
	    	erroNovaObraAutor.setText("Por favor, preencha todos os campos.");
	    	erroNovaObraAutor.setVisible(true);
	        return;
	    } else {
	        System.out.println("Obra adicionada com sucesso.");
	        erroNovaObraAutor.setText("Obra adicionada com sucesso.");
	        erroNovaObraAutor.setVisible(true);
	        btncancelar.setText("Fechar");
	    }
	}
	
	public void cancelar(ActionEvent event) {
		Stage stage = (Stage) erroNovaObraAutor.getScene().getWindow();
	    stage.close();
	}
}


