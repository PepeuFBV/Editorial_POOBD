package controller;

import java.io.IOException;
import java.sql.SQLException;

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
        int anoInicio = datainicio.getValue().getYear();
        int anoFinal = datafinal.getValue().getYear();

        try {
            gerenteBO.baixarRelatorios(anoInicio, anoFinal);

            mensagemLabel.setText("Relatórios baixados com sucesso.");
            mensagemLabel.setVisible(true);
            System.out.println("Relatórios baixados com sucesso.");
        } catch (IllegalArgumentException e) {
            mensagemLabel.setText(e.getMessage());
            mensagemLabel.setVisible(true);
            e.printStackTrace();
        } catch (IOException | SQLException e) {
            mensagemLabel.setText("Erro ao baixar relatórios.");
            mensagemLabel.setVisible(true);
            e.printStackTrace();
        }
    }

    public void fechar(ActionEvent event) {
        Stage stage = (Stage) datainicio.getScene().getWindow();
        stage.close();
    }
}
