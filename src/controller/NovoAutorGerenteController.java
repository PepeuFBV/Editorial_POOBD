package controller;

import exceptions.InsertException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.BO.GerenteBO;

public class NovoAutorGerenteController {

    @FXML
    private TextField cpf;

    @FXML
    private TextField nome;

    @FXML
    private TextField endereco;

    @FXML
    private PasswordField senha;

    @FXML
    private TextField email;

    @FXML
    private Label erroNovoAutorGerente;

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

            gerenteBO.adicionarAutor(cpfText, nomeText, enderecoText, senhaText, emailText);

            erroNovoAutorGerente.setText("Autor adicionado com sucesso");
            erroNovoAutorGerente.setVisible(true);
            btncancelar.setText("Fechar");
        } catch (InsertException e) {
            erroNovoAutorGerente.setText("Erro ao cadastrar: " + e.getMessage());
            erroNovoAutorGerente.setVisible(true);
        }
    }

    public void cancelar(ActionEvent event) {
        Stage stage = (Stage) erroNovoAutorGerente.getScene().getWindow();
        stage.close();
    }
}
