package controller;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.DAO.AutorDAO;
import model.VO.AutorVO;

public class DeletarAutorController {

    @FXML
    private Label erroDeletarAutor;

    @FXML
    private ChoiceBox<String> email;

    @FXML
    private void initialize() {
        carregarEmailsAutores();
    }

    private void carregarEmailsAutores() {
        AutorDAO autorDAO = new AutorDAO();
        ArrayList<AutorVO> autores = autorDAO.listar();

        ObservableList<String> emailsList = FXCollections.observableArrayList();
        for (AutorVO autor : autores) {
            emailsList.add(autor.getEmail());
        }

        email.setItems(emailsList);
    }

    @FXML
    private void deletar(ActionEvent event) {
        String emailSelecionado = email.getValue();

        if (emailSelecionado != null && !emailSelecionado.isEmpty()) {
            AutorDAO autorDAO = new AutorDAO();
            AutorVO autor = new AutorVO();
            autor.setEmail(emailSelecionado);

            ArrayList<AutorVO> autores = autorDAO.buscarPorEmail(autor);

            if (!autores.isEmpty()) {
                AutorVO autorParaExcluir = autores.get(0);

                autorDAO.excluir(autorParaExcluir);

                erroDeletarAutor.setText("Autor excluído com sucesso.");
                erroDeletarAutor.setVisible(true);
            } else {
                erroDeletarAutor.setText("Autor não encontrado.");
                erroDeletarAutor.setVisible(true);
            }
        } else {
            erroDeletarAutor.setText("Por favor, selecione um autor para excluir.");
            erroDeletarAutor.setVisible(true);
        }
    }

    @FXML
    private void voltar(ActionEvent event) {
        Stage stage = (Stage) erroDeletarAutor.getScene().getWindow();
        stage.close();
    }
}
