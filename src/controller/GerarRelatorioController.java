package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.DAO.ObraDAO;

public class GerarRelatorioController{

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
	        int anoInicio = dataInicio.getYear();
	        int anoFinal = dataFinal.getYear();
	        
	        //para gerentes
	        ObraDAO obraDAO = new ObraDAO();
	        ResultSet rs = obraDAO.buscarPorAno(anoInicio, anoFinal);

	        try {
	            String diretorioSalvar = "C:\\Avaliações";

	            try {
					while (rs.next()) {
					    Blob pdfData = rs.getBlob("pdf_avaliacao");
					    if (pdfData != null) {
					        byte[] pdfBytes = pdfData.getBytes(1, (int) pdfData.length());

					        // Nome do arquivo de saída (pode ser baseado em alguma informação da obra)
					        String nomeArquivo = "avaliacao.pdf";

					        // Caminho completo para o arquivo
					        String caminhoArquivo = diretorioSalvar + nomeArquivo;

					        // Verifique se o arquivo já existe e, se sim, renomeie para evitar substituição
					        int contador = 1;
					        while (new File(caminhoArquivo).exists()) {
					            nomeArquivo = "avaliacao" + contador + ".pdf";
					            caminhoArquivo = diretorioSalvar + nomeArquivo;
					            contador++;
					        }

					        // Salvar o PDF em um arquivo no diretório especificado
					        FileOutputStream outputStream = new FileOutputStream(caminhoArquivo);
					        outputStream.write(pdfBytes);
					        outputStream.close();
					    }
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

	            mensagemLabel.setText("Relatórios baixados com sucesso.");
	            mensagemLabel.setVisible(true);
	            System.out.println("Relatórios baixados com sucesso.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}


	public void fechar(ActionEvent event) {
	    Stage stage = (Stage) datainicio.getScene().getWindow();
	    stage.close();
	}

}
