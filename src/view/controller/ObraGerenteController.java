package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ObraGerenteController {
    
    @FXML
    private Button btnSair;

    @FXML
    public void sair(ActionEvent event) throws Exception { //checar se método funciona
        HelloFX.telaLogin();
    }

    public void paraAvaliadores(ActionEvent event) throws Exception {
        HelloFX.telaAvaliadoresGerente();
    }

    public void paraObras(ActionEvent event) throws Exception {
        HelloFX.telaObrasGerente();
    }

    public void adicionarNovaObra(ActionEvent event) {

    }

    public void removerObra(ActionEvent event) {

    }

    public void editarObra(ActionEvent event) {

    }

    public void gerarRelatorio(ActionEvent event) {

    }

}
