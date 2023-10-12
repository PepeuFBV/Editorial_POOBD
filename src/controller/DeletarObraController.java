package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.DAO.ObraDAO;
import view.Telas;

public class DeletarObraController {
	
	@FXML 
	private Label label1;
	
	@FXML 
	private Label label2;
	
	@FXML
	private Label erroDeletarObra;
	
    @FXML
    private Button buttonSim;

    @FXML
    private Button buttonNao;
    
    @FXML
    private Button botao;

    @FXML
    private void handleSimButtonAction(ActionEvent event) {
    	ObraDAO obraDAO = new ObraDAO();
    	obraDAO.excluir(obraVO);
    	System.out.println("Obra excluída com sucesso.");
    	label1.setVisible(false);
    	label2.setVisible(false);
    	buttonSim.setVisible(false);
    	buttonSim.setVisible(false);
    	erroDeletarObra.setText("Obra excluída com sucesso.");
    	erroDeletarObra.setVisible(true);
    	botao.setVisible(true);
    	try {
			Telas.telaPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    } 
    
    @FXML
    private void voltar(ActionEvent event) {
	    Stage stage = (Stage) erroDeletarObra.getScene().getWindow();
	    stage.close();
    }
}


