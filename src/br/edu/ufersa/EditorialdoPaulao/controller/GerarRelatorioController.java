package br.edu.ufersa.EditorialdoPaulao.controller;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class GerarRelatorioController {

	@FXML
	private DatePicker datainicio;
	
	@FXML
	private DatePicker datafinal;
	
	LocalDate dataInicio = (datainicio.getValue());
	LocalDate dataFinal = (datainicio.getValue());
	
	public void baixar(ActionEvent event) {
		//lógica para baixar o relatório
		System.out.println("Relatório baixado com sucesso.");
	}
	
	public void fechar(ActionEvent event) {
		//voltar para a tela das obras de avaliador
	}
}
