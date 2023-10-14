package controller;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DAO.AutorDAO;
import model.VO.AutorVO;

public class EditarAutorGerenteController {

	@FXML
	private TextField cpf;
	
	@FXML
	private TextField nome;
	
	@FXML
	private TextField endereco;
	
	@FXML
	private TextField email;
	
	@FXML
	private PasswordField senha;
	
	@FXML
	private ChoiceBox<String> autor;

    @FXML
    private Label erroEditarAutorGerente;

    @FXML
    private Button btncancelar;

    @FXML
    private void initialize() {
        carregarAutoresEmail();
    }

    private void carregarAutoresEmail() {
        AutorDAO autorDAO = new AutorDAO();
        ArrayList<AutorVO> autores = autorDAO.listar();

        ObservableList<String> emailList = FXCollections.observableArrayList();
        for (AutorVO autor : autores) {
        	emailList.add(autor.getEmail());
        }

        autor.setItems(emailList);
    }

    public void concluir(ActionEvent event) {
	    String emailSelecionado = autor.getValue();
	    String cpfText = cpf.getText();
	    String nomeText = nome.getText();
	    String enderecoText = endereco.getText();
	    String senhaText = senha.getText();
	    String emailText = email.getText();

        if (emailSelecionado == null || emailSelecionado.isEmpty() || cpfText.isEmpty() || nomeText.isEmpty() || enderecoText.isEmpty()|| senhaText.isEmpty()|| emailText.isEmpty()) {
            erroEditarAutorGerente.setText("Por favor, preencha todos os campos e selecione um autor.");
            erroEditarAutorGerente.setVisible(true);
        } else {
            AutorDAO autorDAO = new AutorDAO();
            AutorVO autorVO = new AutorVO();
            autorVO.setEmail(emailSelecionado);

            ArrayList<AutorVO> autores = autorDAO.buscarPorEmail(autorVO);

            if (!autores.isEmpty()) {
                AutorVO autorEncontrado = autores.get(0);

                autorEncontrado.setSenha(senhaText);
	            autorEncontrado.setIDAutor(autores.get(0).getIDAutor());
	            autorEncontrado.setTipo("Autor");
	            autorEncontrado.setEmail(emailText);
	            autorEncontrado.setCpf(cpfText);
	            autorEncontrado.setNome(nomeText);
	            autorEncontrado.setEndereco(enderecoText);

                autorDAO.atualizar(autorEncontrado);

                System.out.println("Edição bem-sucedida.");
                erroEditarAutorGerente.setText("Edição bem-sucedida.");
                erroEditarAutorGerente.setVisible(true);
                btncancelar.setText("Fechar");
            } else {
                erroEditarAutorGerente.setText("Autor não encontrado.");
                erroEditarAutorGerente.setVisible(true);
            }
        }
    }

    public void cancelar(ActionEvent event) {
        Stage stage = (Stage) erroEditarAutorGerente.getScene().getWindow();
        stage.close();
    }
}
