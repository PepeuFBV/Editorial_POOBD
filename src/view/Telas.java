package view;

import java.io.IOException;

import controller.LoginPageController;
import controller.TelaPrincipalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.BO.UserBO;

public class Telas {
    
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage primaryStage) {
        Telas.primaryStage = primaryStage;
    }

    public static void telaLogin() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("/view/VE/login-page.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Editorial do Paulão - Tela de Login");
        primaryStage.show();
    }
    
    public static void telaPrincipal() throws Exception {
    //public static void telaPrincipal(UserBO userBO) throws Exception {
        TelaPrincipalController tela = new TelaPrincipalController();
        tela.start();
        //tela.start(userBO);
        Parent root = FXMLLoader.load(Telas.class.getResource("/view/VE/tela-principal.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaAvaliarObra() throws Exception { //mudar a classe
    	FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/avaliar-obra.fxml"));
    	try {
			Scene s = new Scene(fx.load());
			Stage st = new Stage();
			st.setScene(s);
			st.setTitle("Tela Avaliar Obra");
			st.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void telaCadastrar() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("/view/VE/cadastrar.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaDeletarAutor() throws Exception {
    	FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/deletar-autor.fxml"));
    	try {
			Scene s = new Scene(fx.load());
			Stage st = new Stage();
			st.setScene(s);
			st.setTitle("Tela Deletar Autor");
			st.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void telaDeletarAvaliador() throws Exception {
    	FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/deletar-avaliador.fxml"));
    	try {
			Scene s = new Scene(fx.load());
			Stage st = new Stage();
			st.setScene(s);
			st.setTitle("Tela Deletar Avaliador");
			st.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void telaDeletarObra() throws Exception {
    	FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/deletar-obra.fxml"));
    	try {
			Scene s = new Scene(fx.load());
			Stage st = new Stage();
			st.setScene(s);
			st.setTitle("Tela Deletar Obra");
			st.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void telaDownload() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("/view/VE/download.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void telaEditarAvaliador() throws Exception {
    	FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/editar-avaliador-gerente.fxml"));
    	try {
			Scene s = new Scene(fx.load());
			Stage st = new Stage();
			st.setScene(s);
			st.setTitle("Tela Editar Avaliador Gerente");
			st.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void telaEditarAutor() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("/viewVE/editar-autor-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void telaEditarObraGerente() throws Exception {
        FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/editar-obra-gerente.fxml"));
        try {
            Scene s = new Scene(fx.load());
            Stage st = new Stage();
            st.setScene(s);
            st.setTitle("Tela Editar Obra Gerente");
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void telaEditarObraAutor() throws Exception {
    	FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/editar-obra-autor.fxml"));
    	try {
			Scene s = new Scene(fx.load());
			Stage st = new Stage();
			st.setScene(s);
			st.setTitle("Tela Editar Obra Autor");
			st.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void telaGerarRelatorio() throws Exception {
    	FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/gerar-relatorio.fxml"));
    	try {
			Scene s = new Scene(fx.load());
			Stage st = new Stage();
			st.setScene(s);
			st.setTitle("Tela Gerar Relatório");
			st.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void telaNovaObraAutor() throws Exception {
        FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/nova-obra-autor.fxml"));
        try {
            Scene s = new Scene(fx.load());
            Stage st = new Stage();
            st.setScene(s);
            st.setTitle("Tela Nova Obra Autor");
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void telaNovaObraGerente() throws Exception {
    	FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/nova-obra-gerente.fxml"));
    	try {
			Scene s = new Scene(fx.load());
			Stage st = new Stage();
			st.setScene(s);
			st.setTitle("Tela Nova Obra Gerente");
			st.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void telaNovoAutorGerente() throws Exception {
        FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/editar-autor-gerente.fxml"));
        try {
            Scene s = new Scene(fx.load());
            Stage st = new Stage();
            st.setScene(s);
            st.setTitle("Tela Novo Autor Gerente");
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void telaNovoAvaliadorGerente() throws Exception {
        FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/editar-avaliador-gerente.fxml"));
        try {
            Scene s = new Scene(fx.load());
            Stage st = new Stage();
            st.setScene(s);
            st.setTitle("Tela Novo Avaliador Gerente");
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void telaRedefinirSenha() throws Exception {
        FXMLLoader fx = new FXMLLoader(LoginPageController.class.getResource("/view/VE/redefinir-senha.fxml"));
        try {
            Scene s = new Scene(fx.load());
            Stage st = new Stage();
            st.setScene(s);
            st.setTitle("Tela Redefinir Senha");
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}