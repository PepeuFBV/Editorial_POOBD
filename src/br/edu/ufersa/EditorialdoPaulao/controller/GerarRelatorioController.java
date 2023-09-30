package controller;

import java.time.LocalDate;

import view.Telas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class GerarRelatorioController {

	@FXML
	private DatePicker datainicio;
	
	@FXML
	private DatePicker datafinal;
	
	@FXML
	private Label mensagemLabel;
	
	public void baixar(ActionEvent event) {
	    LocalDate dataInicio = datainicio.getValue();
	    LocalDate dataFinal = datafinal.getValue();
	    
	    if (dataInicio == null || dataFinal == null) {
	        mensagemLabel.setText("Por favor, selecione as datas.");
	        mensagemLabel.setVisible(true);
	    } else if (dataFinal.isBefore(dataInicio)) {
	        mensagemLabel.setText("A data final deve ser maior ou igual à data inicial.");
	        mensagemLabel.setVisible(true);
	    } else {
	        // Lógica para baixar o relatório com as datas selecionadas
	        mensagemLabel.setText("Relatório baixado com sucesso.");
	        mensagemLabel.setVisible(true);
	        System.out.println("Relatório baixado com sucesso.");
	    }
	}


	
	public void fechar(ActionEvent event) {
		try {
			Telas.telaPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
