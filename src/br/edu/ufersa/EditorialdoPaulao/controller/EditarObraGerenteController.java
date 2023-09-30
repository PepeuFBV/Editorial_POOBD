package br.edu.ufersa.EditorialdoPaulao.controller;

import br.edu.ufersa.EditorialdoPaulao.view.Telas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditarObraGerenteController {

	ObservableList<String> obras = FXCollections.observableArrayList("As Crônicas de Nárnia", "O Senhor dos Anéis", "Harry Potter e a Pedra Filosofal");
	ObservableList<String> autores = FXCollections.observableArrayList("C.S. Lewis", "J. R. R. Tolkien", "J. K. Rowling");
	ObservableList<String> avaliadores = FXCollections.observableArrayList("Gabriel Gadelha", "Angélica Félix", "Dudu");
	ObservableList<String> status = FXCollections.observableArrayList("Aceita", "Rejeitada", "Em avaliação", "Avaliador pendente");
	
	@FXML
	private ChoiceBox<String> obra; //titulos
	
	@FXML
	private ChoiceBox<String> autor;
	
	@FXML
	private ChoiceBox<String> avaliador;
	
	@FXML
	private ChoiceBox<String> stts;
	
	@FXML
	private TextField titulo;
	
	@FXML
	private TextField genero;
	
	@FXML
	private TextField ano;
	
	@FXML
	private Label mensagemLabel;
	
	@FXML
	private Button btncancelar;
	
	@FXML
	private void initialize() {
		obra.setItems(obras);
		autor.setItems(autores);
		avaliador.setItems(avaliadores);
		stts.setItems(status);
	}

	
	public void concluir(ActionEvent event) {
		
        String selecao = (String) obra.getValue(); 
        if (selecao != null) {
            // editar obra 
        } else {
        	mensagemLabel.setText("Você deve selecionar uma obra.");
        	mensagemLabel.setVisible(true);
            return;
        }
        
        selecao = (String) autor.getValue(); 
        if (selecao != null) {
            // editar autor 
        } else {
        	mensagemLabel.setText("Você deve selecionar um autor.");
        	mensagemLabel.setVisible(true);
            return;
        }
        
        selecao = (String) avaliador.getValue(); 
        if (selecao != null) {
            // editar avaliador 
        } else {
        	mensagemLabel.setText("Você deve selecionar um avaliador.");
        	mensagemLabel.setVisible(true);
            return;
        }
        
        selecao = (String) stts.getValue(); 
        if (selecao != null) {
            // editar status 
        } else {
        	mensagemLabel.setText("Você deve selecionar um status.");
        	mensagemLabel.setVisible(true);
            return;
        }
        
	    String tituloText = titulo.getText();
	    String generoText = genero.getText();
	    String anoText = ano.getText();
	    if (tituloText.isEmpty() || generoText.isEmpty() || anoText.isEmpty()) {
	        mensagemLabel.setText("Por favor, preencha todos os campos.");
	        mensagemLabel.setVisible(true);
	        return;
	    } else {
	        System.out.println("Edição bem-sucedida.");
	        mensagemLabel.setText("Edição bem-sucedida.");
	        mensagemLabel.setVisible(true);
	        btncancelar.setText("Fechar");
	    }
	}
	
	public void cancelar(ActionEvent event) {
		try {
			Telas.telaPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
