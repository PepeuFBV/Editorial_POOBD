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

public class EditarObraAutorController {

	ObservableList<String> obras = FXCollections.observableArrayList("As Crônicas de Nárnia", "O Senhor dos Anéis", "Harry Potter e a Pedra Filosofal");
	
	@FXML
	private ChoiceBox<String> obra; //titulos
	
	@FXML
	private TextField titulo;
	
	@FXML
	private TextField genero;
	
	@FXML
	private TextField ano;
	
	@FXML
	private Label erroEditarObraAutor;
	
	@FXML
	private Button btncancelar;
	
	@FXML
	private void initialize() {
		obra.setItems(obras);
	}
	
	public void concluir(ActionEvent event) {
		
        String selecao = (String) obra.getValue(); 
        if (selecao != null) {
            // editar obra 
        } else {
        	erroEditarObraAutor.setText("Você deve selecionar uma obra.");
        	erroEditarObraAutor.setVisible(true);
            return;
        }
        
	    String tituloText = titulo.getText();
	    String generoText = genero.getText();
	    String anoText = ano.getText();
	    if (tituloText.isEmpty() || generoText.isEmpty() || anoText.isEmpty()) {
	    	erroEditarObraAutor.setText("Por favor, preencha todos os campos.");
	    	erroEditarObraAutor.setVisible(true);
	        return;
	    } else {
	        System.out.println("Edição bem-sucedida.");
	        erroEditarObraAutor.setText("Edição bem-sucedida.");
	        erroEditarObraAutor.setVisible(true);
	        btncancelar.setText("Fechar");
	    }
	}
	
	public void cancelar(ActionEvent event) {
		Stage stage = (Stage) erroEditarObraAutor.getScene().getWindow();
	    stage.close();
	}
	
	public void excluirObra(ActionEvent event) {
		
	    String obraSelecionada = obra.getValue();
	    if (obraSelecionada != null) { //criar pop up para confirmar a exclusão
	        obras.remove(obraSelecionada);
	        //lógica para excluir a obra no banco de dados
	        System.out.println("Obra excluída: " + obraSelecionada);
	        //atualiza o ChoiceBox após a exclusão
	        obra.setItems(obras);
	        obra.getSelectionModel().clearSelection();
	    } else {
	        erroEditarObraAutor.setText("Você deve selecionar uma obra para excluir.");
	        erroEditarObraAutor.setVisible(true);
	    }
	}
}
