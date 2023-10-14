package controller;

import exceptions.InsertException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.BO.UsuarioBO;
import model.VO.AutorVO;
import model.VO.AvaliadorVO;
import model.VO.UsuarioVO;
import view.Telas;

public class CadastrarController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField cpfField;

    @FXML
    private TextField enderecoField;

    @FXML
    private PasswordField senhaField;

    @FXML
    private PasswordField confirmaSenhaField;

    @FXML
    private RadioButton autorRadioButton;

    @FXML
    private RadioButton avaliadorRadioButton;

    @FXML
    private ToggleGroup radiocadastro;

    @FXML
    private Label mensagemLabel;

    @FXML
    public void cadastrar(ActionEvent event) {
        String username = usernameField.getText();
        String email = emailField.getText();
        String cpf = cpfField.getText();
        String endereco = enderecoField.getText();
        String senha = senhaField.getText();
        String confirmaSenha = confirmaSenhaField.getText();

        RadioButton selectedRadioButton = (RadioButton) radiocadastro.getSelectedToggle();
        String tipoUsuario = (selectedRadioButton != null) ? selectedRadioButton.getText() : "";

        if (username.isEmpty() || email.isEmpty() || cpf.isEmpty() || endereco.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()) {
            mensagemLabel.setText("Por favor, preencha todos os campos.");
            mensagemLabel.setVisible(true);

        } else if (!senha.equals(confirmaSenha)) {
            mensagemLabel.setText("As senhas não coincidem.");
            mensagemLabel.setVisible(true);
            
        } else {
        	
            if (tipoUsuario.isEmpty()) {
            	mensagemLabel.setText("Você deve selecionar o tipo de usuário.");
            	mensagemLabel.setVisible(true);
            	return;
            	
            } else {
            	if(tipoUsuario.equals("Autor")) {
            		UsuarioVO autorVO = new AutorVO();
            		autorVO.setNome(username);
            		autorVO.setEmail(email);
            		autorVO.setCpf(cpf);
            		autorVO.setEndereco(endereco);
            		autorVO.setSenha(senha);
            		autorVO.setTipo(tipoUsuario);
                    try {
                    	UsuarioBO<AutorVO> userBO = new UsuarioBO<AutorVO>();
                        userBO.cadastrar(autorVO);
                        try {
							Telas.telaLogin();
						} catch (Exception e) {
							e.printStackTrace();
						}
                    } catch (InsertException e) {
                        mensagemLabel.setText("Erro ao cadastrar");
                        mensagemLabel.setVisible(true);                      
                    }
            		
            	} else{
            		UsuarioVO avaliadorVO = new AvaliadorVO();
            		avaliadorVO.setNome(username);
            		avaliadorVO.setEmail(email);
            		avaliadorVO.setCpf(cpf);
            		avaliadorVO.setEndereco(endereco);
            		avaliadorVO.setSenha(senha);
            		avaliadorVO.setTipo(tipoUsuario);
                    try {
                    	UsuarioBO<AvaliadorVO> userBO = new UsuarioBO<AvaliadorVO>();
                        userBO.cadastrar(avaliadorVO);
                        try {
							Telas.telaLogin();
						} catch (Exception e) {
							e.printStackTrace();
						}
                    } catch (InsertException e) {
                        mensagemLabel.setText("Erro ao cadastrar");
                        mensagemLabel.setVisible(true);                      
                    }
            	}
            } 
            
        	try {
				Telas.telaLogin();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
    
    @FXML
    public void voltar(ActionEvent event) {
    	try {
			Telas.telaLogin();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}

