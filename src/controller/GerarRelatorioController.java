package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.DAO.ObraDAO;
import model.VO.ObraVO;

public class GerarRelatorioController{

	@FXML
	private DatePicker datainicio;
	
	@FXML
	private DatePicker datafinal;
	
	@FXML
	private Label mensagemLabel;
	
	public void baixar(ActionEvent event) {
	    int anoInicio = datainicio.getValue().getYear();
	    int anoFinal = datafinal.getValue().getYear();
	    
	    ArrayList<ObraVO> obras = new ArrayList<>(); 

	    if (anoInicio > anoFinal) {
	        mensagemLabel.setText("O ano inicial não pode ser maior que o ano final.");
	        mensagemLabel.setVisible(true);
	    } else {
	        ObraDAO obraDAO = new ObraDAO();
	        try {
				obras = obraDAO.buscarPorAno(anoInicio, anoFinal);
			} catch (SQLException e) {
				e.printStackTrace();
			}

	        if (!obras.isEmpty()) {
	            String diretorioSalvar = "C:\\Avaliações";
	            mensagemLabel.setText("Relatórios baixados com sucesso.");
	            mensagemLabel.setVisible(true);
	            System.out.println("Relatórios baixados com sucesso.");

	            for (ObraVO obra : obras) {
	                if (obra.getPdfObra() != null) {
	                    byte[] pdfObraBytes = obra.getPdfObra();
	                    String nomeArquivoObra = "obra_" + obra.getIDObra() + ".pdf";
	                    String caminhoArquivoObra = diretorioSalvar + nomeArquivoObra;

	                    try (FileOutputStream outputStream = new FileOutputStream(caminhoArquivoObra)) {
	                        outputStream.write(pdfObraBytes);
	                        mensagemLabel.setText("Relatório de obra baixado com sucesso.");
	                        mensagemLabel.setVisible(true);
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                        mensagemLabel.setText("Erro ao baixar o relatório de obra.");
	                        mensagemLabel.setVisible(true);
	                    }
	                }

	                if (obra.getPdfAvaliacao() != null) {
	                    byte[] pdfAvaliacaoBytes = obra.getPdfAvaliacao();
	                    String nomeArquivoAvaliacao = "avaliacao_" + obra.getIDObra() + ".pdf";
	                    String caminhoArquivoAvaliacao = diretorioSalvar + nomeArquivoAvaliacao;

	                    try (FileOutputStream outputStream = new FileOutputStream(caminhoArquivoAvaliacao)) {
	                        outputStream.write(pdfAvaliacaoBytes);
	                        mensagemLabel.setText("Relatório de avaliação baixado com sucesso.");
	                        mensagemLabel.setVisible(true);
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                        mensagemLabel.setText("Erro ao baixar o relatório de avaliação.");
	                        mensagemLabel.setVisible(true);
	                    }
	                }
	            }
	        }
	    }
	}

	public void fechar(ActionEvent event) {
	    Stage stage = (Stage) datainicio.getScene().getWindow();
	    stage.close();
	}

}
