package controller;

import java.util.ArrayList;
import java.util.List;
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
import model.BO.AvaliadorBO;
import model.DAO.AvaliadorDAO;
import model.VO.AvaliadorVO;

public class EditarAvaliadorGerenteController {

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
	private ChoiceBox<String> avaliador;
	
	@FXML
	private Label erroEditarAvaliadorGerente;
	
	@FXML
	private Button btncancelar;
	
	@FXML
	private void initialize() {
		carregarAvaliadoresEmails();
	}

	private void carregarAvaliadoresEmails() {
	    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
	    ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.listar();

	    ObservableList<String> emailList = FXCollections.observableArrayList();
	    for (AvaliadorVO avaliador : avaliadores) {
	        emailList.add(avaliador.getEmail());
	    }

	    avaliador.setItems(emailList);
	}

	public void concluir(ActionEvent event) {
	    String emailSelecionado = avaliador.getValue();
	    String cpfText = cpf.getText();
	    String nomeText = nome.getText();
	    String enderecoText = endereco.getText();
	    String senhaText = senha.getText();
	    String emailText = email.getText();

	    if (emailSelecionado == null || emailSelecionado.isEmpty() || cpfText.isEmpty() || nomeText.isEmpty() || enderecoText.isEmpty() || senhaText.isEmpty()|| emailText.isEmpty()) {
	        erroEditarAvaliadorGerente.setText("Por favor, preencha todos os campos e selecione um avaliador.");
	        erroEditarAvaliadorGerente.setVisible(true);
	    } else {
	        AvaliadorBO avaliadorBO = new AvaliadorBO();
	        AvaliadorVO avaliadorVO = new AvaliadorVO();
	        avaliadorVO.setEmail(emailSelecionado);

	        List<AvaliadorVO> avaliadores = avaliadorBO.buscarPorEmail(avaliadorVO);

	        if (!avaliadores.isEmpty()) {
	            AvaliadorVO avaliadorEncontrado = avaliadores.get(0);
	            
	            avaliadorEncontrado.setSenha(senhaText);
	            avaliadorEncontrado.setIDAvaliador(avaliadores.get(0).getIDAvaliador());
	            avaliadorEncontrado.setTipo("Avaliador");
	            avaliadorEncontrado.setEmail(emailText);
	            avaliadorEncontrado.setCpf(cpfText);
	            avaliadorEncontrado.setNome(nomeText);
	            avaliadorEncontrado.setEndereco(enderecoText);

	            avaliadorBO.atualizar(avaliadorEncontrado);

	            System.out.println("Edição bem-sucedida.");
	            erroEditarAvaliadorGerente.setText("Edição bem-sucedida.");
	            erroEditarAvaliadorGerente.setVisible(true);
	            btncancelar.setText("Fechar");
	        } else {
	            erroEditarAvaliadorGerente.setText("Avaliador não encontrado.");
	            erroEditarAvaliadorGerente.setVisible(true);
	        }
	    }
	}

	
	public void cancelar(ActionEvent event) {
		Stage stage = (Stage) erroEditarAvaliadorGerente.getScene().getWindow();
	    stage.close();
	}
	
}
