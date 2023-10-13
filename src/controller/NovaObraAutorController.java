package controller;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.BO.AutorBO;

public class NovaObraAutorController {
	
	@FXML
	private TextField showFileautor;
	
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
	
	@FXML
	public void handleBtnOpenFile(ActionEvent event) {
		final FileChooser fc = new FileChooser();
		fc.setTitle("Seleção da Obra");
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf", "*.*"));
		File file = fc.showOpenDialog(null);
		if (file != null) {
			showFileautor.appendText(file.getAbsolutePath() + "\n");
		} else {
			System.out.println("Você deve selecionar um arquivo");
		}
	}
	
	public void adicionar(ActionEvent event) {
	    String obraSelecionada = showFileautor.getText();
	    String tituloText = titulo.getText();
	    String generoText = genero.getText();
	    String anoText = ano.getText();

	    try {
	        AutorBO autorBO = new AutorBO();
	        autorBO.adicionarObra(obraSelecionada, tituloText, generoText, anoText);

	        System.out.println("Obra adicionada com sucesso.");
	        erroNovaObraAutor.setText("Obra adicionada com sucesso.");
	        erroNovaObraAutor.setVisible(true);
	        btncancelar.setText("Fechar");
	    } catch (IOException e) {
	        e.printStackTrace();
	        erroNovaObraAutor.setText("Erro ao ler o arquivo PDF.");
	        erroNovaObraAutor.setVisible(true);
	    } catch (IllegalArgumentException e) {
	        erroNovaObraAutor.setText(e.getMessage());
	        erroNovaObraAutor.setVisible(true);
	    }
	}

	
	public void cancelar(ActionEvent event) {
		Stage stage = (Stage) erroNovaObraAutor.getScene().getWindow();
	    stage.close();
	}
}


