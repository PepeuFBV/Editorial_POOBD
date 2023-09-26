package view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TelaPrincipalController {
    
    @FXML
    private Button btnSair;

    public void initialize() {
        
        //checar qual a class de user logado
    }

    @FXML
    public void sair(ActionEvent event) throws Exception { //checar se método funciona
        HelloFX.telaLogin();
    }

    @FXML
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
