package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
	private Label erroEditarObraGerente;
	
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
        	erroEditarObraGerente.setText("Você deve selecionar uma obra.");
        	erroEditarObraGerente.setVisible(true);
            return;
        }
        
        String tituloText = titulo.getText();
	    String generoText = genero.getText();
	    String anoText = ano.getText();
	    if (tituloText.isEmpty() || generoText.isEmpty() || anoText.isEmpty()) {
	    	erroEditarObraGerente.setText("Por favor, preencha todos os campos.");
	    	erroEditarObraGerente.setVisible(true);
	    } else {
	        selecao = (String) autor.getValue(); 
	        if (selecao != null) {
	            // editar autor 
	        } else {
	        	erroEditarObraGerente.setText("Você deve selecionar um autor.");
	        	erroEditarObraGerente.setVisible(true);
	            return;
	        }
	        
	        selecao = (String) avaliador.getValue(); 
	        if (selecao != null) {
	            // editar avaliador 
	        } else {
	        	erroEditarObraGerente.setText("Você deve selecionar um avaliador.");
	        	erroEditarObraGerente.setVisible(true);
	            return;
	        }
	        
	        selecao = (String) stts.getValue(); 
	        if (selecao != null) {
	            // editar status 
	        } else {
	        	erroEditarObraGerente.setText("Você deve selecionar um status.");
	        	erroEditarObraGerente.setVisible(true);
	            return;
	        }
	        System.out.println("Edição bem-sucedida.");
	        erroEditarObraGerente.setText("Edição bem-sucedida.");
	        erroEditarObraGerente.setVisible(true);
	        btncancelar.setText("Fechar");
	    }
       
	}
	
	public void cancelar(ActionEvent event) {
		Stage stage = (Stage) erroEditarObraGerente.getScene().getWindow();
	    stage.close();
	}
}
