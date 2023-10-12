package controller;

import exceptions.InsertException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.BO.UserBO;
import model.VO.AutorVO;
import model.VO.UsuarioVO;
import view.Telas;

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
	
	public void adicionar(ActionEvent event) {
	    String cpfText = cpf.getText();
	    String nomeText = nome.getText();
	    String enderecoText = endereco.getText();
	    String senhaText = senha.getText();
	    String emailText = email.getText();

	    if (cpfText.isEmpty() ||senhaText.isEmpty() ||emailText.isEmpty() || nomeText.isEmpty() || enderecoText.isEmpty()) {
	    	erroNovoAvaliadorGerente.setText("Por favor, preencha todos os campos.");
	    	erroNovoAvaliadorGerente.setVisible(true);
	    } else {
	    	UsuarioVO autorVO = new AutorVO();
	    	autorVO.setCpf(cpfText);
	    	autorVO.setNome(nomeText);
	    	autorVO.setEndereco(enderecoText);
	    	autorVO.setEmail(emailText);
	    	autorVO.setSenha(senhaText);
	    	autorVO.setTipo("Avaliador");
            try {
            	UserBO<AutorVO> userBO = new UserBO<AutorVO>();
                userBO.cadastrar(autorVO);
                try {
					Telas.telaLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
            } catch (InsertException e) {
            	erroNovoAvaliadorGerente.setText("Erro ao cadastrar: " + e.getMessage());
            	erroNovoAvaliadorGerente.setVisible(true);                      
            }
            erroNovoAvaliadorGerente.setText("Avaliador adicionado com sucesso");
            erroNovoAvaliadorGerente.setVisible(true);
	        btncancelar.setText("Fechar");
	    }
	}
	
	public void cancelar(ActionEvent event) {
		Stage stage = (Stage) erroNovoAvaliadorGerente.getScene().getWindow();
	    stage.close();
	}
	
}
