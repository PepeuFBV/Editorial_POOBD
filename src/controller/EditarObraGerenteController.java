package controller;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DAO.AutorDAO;
import model.DAO.AvaliadorDAO;

public class EditarObraGerenteController {

	@FXML
	private TextField showFileger;
	
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
	
    private Map<String, Integer> autorParaIDMap = new HashMap<>();
    private Map<String, Integer> avaliadorParaIDMap = new HashMap<>();
	
	@FXML
	public void handleBtnOpenFile(ActionEvent event) {
		final FileChooser fc = new FileChooser();
		fc.setTitle("Seleção da Obra");
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf", "*.*"));
		File file = fc.showOpenDialog(null);
		if (file != null) {
			showFileger.appendText(file.getAbsolutePath() + "\n");
		} else {
			System.out.println("Você deve selecionar um arquivo");
		}
	}
	
	@FXML
	private void initialize() {
		
		ObservableList<String> status = FXCollections.observableArrayList("Aceita", "Rejeitada", "Em avaliação", "Avaliador pendente");
	    ObservableList<String> autores = FXCollections.observableArrayList();
	    ObservableList<String> avaliadores = FXCollections.observableArrayList();

	    try {
	        AutorDAO autorDAO = new AutorDAO();
	        ResultSet autoresDoDB = autorDAO.listar();

	        while (autoresDoDB.next()) {
	            String nomeAutor = autoresDoDB.getString("nome");
	            int autorID = autoresDoDB.getInt("id_autor");
	            autores.add(nomeAutor);
	            autorParaIDMap.put(nomeAutor, autorID);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    try {
	        AvaliadorDAO avaDAO = new AvaliadorDAO();
	        ResultSet avaliadoresDoDB = avaDAO.listar();

	        while (avaliadoresDoDB.next()) {
	            String nomeAvaliador = avaliadoresDoDB.getString("nome");
	            int avaliadorID = avaliadoresDoDB.getInt("id_avaliador");
	            avaliadores.add(nomeAvaliador);
	            avaliadorParaIDMap.put(nomeAvaliador, avaliadorID);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

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
