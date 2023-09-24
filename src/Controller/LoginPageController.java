package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.entities.Avaliador;

public class LoginPageController{

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtSenha;
    
    @FXML
    private Label usuarioNotFound;

    @FXML
    private Label senhaOuLoginInvalido;

    @FXML
    private Button btnLogin;
    
    @FXML
    void fazerLogin(ActionEvent event) throws ErroLoginException { //incorporar exception no método
        String login = txtLogin.getText();
        String senha = txtSenha.getText();

        if (login == null || login.isEmpty()) {
            usuarioNotFound.setVisible(true); //fazer controller de usuário not found
            return;
        } else if (senha == null || senha.isEmpty()) {
            senhaOuLoginInvalido.setVisible(true); //fazer controller de senha inválida
            return;
        } else {
            UserBO userBO = new UserBO();
            if (userBO.login(login)) { //retorna true se for existir tal usuário
                String tipo = userBO.getTipo();
                boolean loginValido = false;
                if (tipo.equals("Gerente")) { //se for gerente
                    GerenteBO gerenteBO = new GerenteBO();
                    gerenteBO.setLogin(login);
                    gerenteBO.setSenha(senha);
                    loginValido = gerenteBO.login();
                    if (loginValido) {
                        //chamar próxima tela
                    } else {
                        //abrir tela de erro, incorporar como exeption depois
                    }

                } else if (tipo.equals("Autor")) { //se for autor
                    AutorBO autorBO = new AutorBO();
                    autorBO.setLogin(login);
                    autorBO.setSenha(senha);
                    loginValido = autorBO.login();
                    if (loginValido) {
                        //chamar próxima tela
                    } else {
                        //abrir tela de erro, incorporar como exeption depois
                    }

                } else { //se for avaliador
                    AvaliadorBO avaliadorBO = new AvaliadorBO();
                    avaliadorBO.setLogin(login);
                    avaliadorBO.setSenha(senha);
                    loginValido = avaliadorBO.login();
                    if (loginValido) {
                        //chamar próxima tela
                    } else {
                        //abrir tela de erro, incorporar como exeption depois
                    }
                    
                }
            } else {
                //abrir tela de erro, incorporar como excption depois
            }

        }
        
    }

}