package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.BO.GerenteBO;

public class GerarRelatorioController {

    @FXML
    private DatePicker datainicio;

    @FXML
    private DatePicker datafinal;

    @FXML
    private Label mensagemLabel;

    private GerenteBO gerenteBO = new GerenteBO();

    public void baixar(ActionEvent event) {
        LocalDate dataInicio = datainicio.getValue();
        LocalDate dataFinal = datafinal.getValue();

        if (dataInicio != null && dataFinal != null) {
            java.sql.Date sqlDataInicio = java.sql.Date.valueOf(dataInicio);
            java.sql.Date sqlDataFinal = java.sql.Date.valueOf(dataFinal);

            try {
                gerenteBO.baixarRelatorios(sqlDataInicio, sqlDataFinal);
             
            } catch (IllegalArgumentException e) {
                mensagemLabel.setText(e.getMessage());
                mensagemLabel.setVisible(true);
                e.printStackTrace();
            } catch (IOException | SQLException e) {
                mensagemLabel.setText("Erro ao baixar relatórios.");
                mensagemLabel.setVisible(true);
                e.printStackTrace();
            }
        } else {
            mensagemLabel.setText("Selecione as datas de início e final.");
            mensagemLabel.setVisible(true);
        }
    }

    public void fechar(ActionEvent event) {
        Stage stage = (Stage) datainicio.getScene().getWindow();
        stage.close();
    }
}
