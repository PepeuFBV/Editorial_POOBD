package controller;

import exceptions.InsertException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.BO.GerenteBO;

public class NovoAvaliadorGerenteController {

    @FXML
    private TextField cpf;

    @FXML
    private TextField nome;

    @FXML
    private TextField endereco;

    @FXML
    private TextField senha;

    @FXML
    private TextField email;

    @FXML
    private Label erroNovoAvaliadorGerente;

    @FXML
    private Button btncancelar;

    private GerenteBO gerenteBO = new GerenteBO();

    public void adicionar(ActionEvent event) {
        try {
            String cpfText = cpf.getText();
            String nomeText = nome.getText();
            String enderecoText = endereco.getText();
            String senhaText = senha.getText();
            String emailText = email.getText();

            gerenteBO.adicionarAvaliador(cpfText, nomeText, enderecoText, senhaText, emailText);

            erroNovoAvaliadorGerente.setText("Avaliador adicionado com sucesso");
            erroNovoAvaliadorGerente.setVisible(true);
            btncancelar.setText("Fechar");
        } catch (InsertException e) {
            erroNovoAvaliadorGerente.setText("Erro ao cadastrar: " + e.getMessage());
            erroNovoAvaliadorGerente.setVisible(true);
        }
    }

    public void cancelar(ActionEvent event) {
        Stage stage = (Stage) erroNovoAvaliadorGerente.getScene().getWindow();
        stage.close();
    }
}
