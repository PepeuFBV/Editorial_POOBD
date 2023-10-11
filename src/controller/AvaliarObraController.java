package controller;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DAO.ObraDAO;
import model.VO.ObraVO;

public class AvaliarObraController {
	
	@FXML
	private TextField showFile;
	
	@FXML
	private Button buttonarquivo;
	
	@FXML
	private Label erroAut;
	
	@FXML
	private RadioButton buttonaceita;
	
	@FXML
	private RadioButton buttonrejeitada;
	
	@FXML
	private ToggleGroup radioavaliar;
	
	@FXML
	public void handleBtnOpenFile(ActionEvent event) {
		final FileChooser fc = new FileChooser();
		fc.setTitle("Seleção do relatório");
		fc.setInitialDirectory(new File(System.getProperty("user.home")));
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("pdf", "*.*"));
		File file = fc.showOpenDialog(null);
		if (file != null) {
			showFile.appendText(file.getAbsolutePath() + "\n");
		} else {
			System.out.println("Você deve selecionar um arquivo");
		}
	}
	
	public void enviar(ActionEvent event) {
	    try {
	        String caminhoArquivo = showFile.getText();
	        
	        if (!caminhoArquivo.isEmpty()) {
	            obra.setPdfAvaliacao(caminhoArquivo);
	            
	            ObraDAO obraDAO = new ObraDAO();
	            obraDAO.atualizar(obra);
	            
	            System.out.println("Caminho do arquivo salvo no banco de dados com sucesso.");
	        } else {
	            erroAut.setText("Você deve selecionar um arquivo antes de enviar.");
	            erroAut.setVisible(true);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	}
	
	public void voltarTelaOBrasAvaliador(ActionEvent event) {
	    Stage stage = (Stage) showFile.getScene().getWindow();
	    stage.close();
	}
	
}
