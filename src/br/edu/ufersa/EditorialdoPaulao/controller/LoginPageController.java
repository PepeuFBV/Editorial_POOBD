package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.BO.AutorBO;
import model.BO.AvaliadorBO;
import model.BO.GerenteBO;
import model.BO.UserBO;
import view.Telas;

public class LoginPageController {

    @FXML
    private Button entrarButton;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void fazerLogin(ActionEvent event) throws Exception { //fazer pop-up de erro caso login ou senha estejam errados
        String loginUser = txtLogin.getText();
        String senhaUser = txtPassword.getText();

        if (loginUser == null || loginUser.isEmpty() || senhaUser == null || senhaUser.isEmpty()) {
            //chamar pop-up campo vázio (pode ser exception também)
        } else {
            UserBO userBO = new UserBO();
            
            if (userBO.login(loginUser, senhaUser)) { //retorna true se existir no BD e define o tipo de user lá dentro
                if (userBO.getTipo() == "Gerente") {
                    GerenteBO gerenteBO = new GerenteBO();
                    gerenteBO.setNome(userBO.getNome());
                    gerenteBO.setLogin(userBO.getLogin());
                    gerenteBO.setSenha(userBO.getSenha());
                    Telas.telaPrincipal(userBO);
                } else if (userBO.getTipo() == "Avaliador") {
                    AvaliadorBO avaliadorBO = new AvaliadorBO();
                    avaliadorBO.setNome(userBO.getNome());
                    avaliadorBO.setLogin(userBO.getLogin());
                    avaliadorBO.setSenha(userBO.getSenha());
                    Telas.telaPrincipal(userBO);
                } else if (userBO.getTipo() == "Autor") {
                    AutorBO autorBO = new AutorBO();
                    autorBO.setNome(userBO.getNome());
                    autorBO.setLogin(userBO.getLogin());
                    autorBO.setSenha(userBO.getSenha());
                    Telas.telaPrincipal(userBO);
                }
            } else {
                //pop-up de user não existe
            }

        }
        
    }

}
