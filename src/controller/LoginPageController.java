package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.BO.UsuarioBO;
import model.VO.AutorVO;
import model.VO.AvaliadorVO;
import model.VO.GerenteVO;
import model.VO.UsuarioVO;
import view.Telas;

public class LoginPageController {

    @FXML
    private Button entrarButton;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void fazerLogin(ActionEvent event) throws Exception { //fazer pop-up de erro caso login ou senha estejam errados
        String loginUser = txtLogin.getText();
        String senhaUser = txtPassword.getText();

        if (loginUser == null || loginUser.isEmpty() || senhaUser == null || senhaUser.isEmpty()) {
            //chamar pop-up campo vázio (pode ser exception também)
        } else {
            UsuarioBO<UsuarioVO> userBO = new UsuarioBO<UsuarioVO>();
            UsuarioVO usuarioVO = new UsuarioVO();
            usuarioVO.setEmail(loginUser);
            usuarioVO.setSenha(senhaUser);
            UsuarioVO user = userBO.autenticar(usuarioVO);
            if (userBO.isAutenticado()) { //retorna true se existir no BD e define o tipo de user lá dentro
                if (user.getTipo().equals("Gerente")) {
                    GerenteVO gerenteVO = new GerenteVO();
                    gerenteVO.setNome(user.getNome());
                    gerenteVO.setEmail(user.getEmail());
                    gerenteVO.setSenha(user.getSenha());
                    gerenteVO.setTipo("Gerente");
                    Telas.setUsuarioVOAtual(gerenteVO);
                    Telas.telaPrincipal(user);
                } else if (userBO.getTipo().equals("Avaliador")) {
                    AvaliadorVO avaliadorVO = new AvaliadorVO();
                    avaliadorVO.setNome(user.getNome());
                    avaliadorVO.setEmail(user.getEmail());
                    avaliadorVO.setSenha(user.getSenha());
                    avaliadorVO.setTipo("Avaliador");
                    Telas.setUsuarioVOAtual(avaliadorVO);
                    Telas.telaPrincipal(user);
                } else if (userBO.getTipo().equals("Autor")) {
                    AutorVO autorVO = new AutorVO();
                    autorVO.setNome(user.getNome());
                    autorVO.setEmail(user.getEmail());
                    autorVO.setSenha(user.getSenha());
                    autorVO.setTipo("Autor");
                    Telas.setUsuarioVOAtual(autorVO);
                    Telas.telaPrincipal(user);
                }
            } else {
                //pop-up de user não existe
            }

        }
        
    }
    
    @FXML
    public void registrar(ActionEvent event) {
    	try {
			Telas.telaCadastrar();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    public void redefinir (ActionEvent event) {
    	try {
			Telas.telaRedefinirSenha();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
