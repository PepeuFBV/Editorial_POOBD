package controller;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.BO.ObraBO;
import model.VO.ObraVO;

public class DeletarObraGerenteController {
	
	@FXML
	private Label erroDeletarObra;
	
	@FXML
	private ChoiceBox<String> obra;
	
    ObraBO obraBO = new ObraBO();
	
	@FXML
	private void initialize() {
	    carregarObras();
	}

	private void carregarObras() {
	    List<ObraVO> obras = obraBO.listar(); 

	    ObservableList<String> obrasList = FXCollections.observableArrayList();
	    for (ObraVO obra : obras) {
	        obrasList.add(obra.getTitulo());
	    }

	    obra.setItems(obrasList);
	}
	
	@FXML
	private void deletar(ActionEvent event) {
	    ObservableList<String> obrasList = obra.getItems();
	    if (!obrasList.isEmpty()) {
	        String tituloObra = obrasList.get(0);
	        ObraVO obraExcluir = new ObraVO();
	        obraExcluir.setTitulo(tituloObra);
	        
	        obraBO.excluir(obraExcluir);
	        
	        carregarObras();

	        erroDeletarObra.setText("Obra excluída com sucesso.");
	        erroDeletarObra.setVisible(true);

	    }
	}
   
    @FXML
    private void voltar(ActionEvent event) {
	    Stage stage = (Stage) erroDeletarObra.getScene().getWindow();
	    stage.close();
    }
}


