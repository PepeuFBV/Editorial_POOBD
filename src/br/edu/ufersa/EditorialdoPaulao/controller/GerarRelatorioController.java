package br.edu.ufersa.EditorialdoPaulao.controller;

import java.time.LocalDate;

import br.edu.ufersa.EditorialdoPaulao.view.Telas;
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
	
	LocalDate dataInicio = (datainicio.getValue());
	LocalDate dataFinal = (datainicio.getValue());
	
	public void baixar(ActionEvent event) {
		//lógica para baixar o relatório
    	mensagemLabel.setText("Relatório baixado com sucesso.");
        mensagemLabel.setVisible(true);
		System.out.println("Relatório baixado com sucesso.");
	}
	
	public void fechar(ActionEvent event) {
		try {
			Telas.telaPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
