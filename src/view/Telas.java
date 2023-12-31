package view;

import java.io.IOException;

import controller.AvaliarObraController;
import controller.BaixarObraAvaliadorController;
import controller.DeletarObraAutorController;
import controller.EditarObraAutorController;
import controller.LoginPageController;
import controller.NovaObraAutorController;
import controller.TelaPrincipalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.VO.UsuarioVO;

public class Telas {
    
    private static UsuarioVO usuarioVOAtual;
    private static Stage primaryStage;
    private static Parent root;

    public static Parent getRoot() {
        return root;
    }

    public static void setRoot(Parent root) {
        Telas.root = root;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        Telas.primaryStage = primaryStage;
    }

    public static UsuarioVO getUsuarioVOAtual() {
        return usuarioVOAtual;
    }

    public static void setUsuarioVOAtual(UsuarioVO usuarioVOAtual) {
        if (usuarioVOAtual != null) {
            Telas.usuarioVOAtual = usuarioVOAtual;
        } else {
            throw new NullPointerException("O usuário não pode ser nulo");
        }

    }

    public static void telaLogin() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("/view/VE/login-page.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Editorial do Paulão - Tela de Login");
        primaryStage.show();
    }
    
    public static void telaPrincipal(UsuarioVO usuarioVO) throws Exception { //recebe um UsuarioVO para definir como será a tela principal
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource("/view/VE/tela-principal.fxml"));
        Parent root = loader.load();
        Telas.setRoot(root);
        TelaPrincipalController controller = loader.getController();
        controller.setApropriateScreen();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tela Principal");
        primaryStage.show();
    }

    public static void telaAvaliarObra(UsuarioVO usuarioVO) throws Exception { //mudar a classe
    	FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/avaliar-obra.fxml"));
        Parent root = fx.load();
        AvaliarObraController controller = fx.getController();
        controller.setUsuarioVO(usuarioVO);

        Stage popupStage = new Stage();
        Scene scene = new Scene(root);
        popupStage.setScene(scene);

        popupStage.setTitle("Tela Avaliar Obra");

        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(primaryStage); 

        popupStage.show();
    }
    
    public static void telaCadastrar() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("/view/VE/cadastrar.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Tela Cadastrar");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaDeletarAutor() throws Exception {
    	FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/deletar-autor.fxml"));
    	try {
			Scene scene = new Scene(fx.load());
			Stage stage = new Stage();
            stage.setScene(scene);
			stage.setTitle("Tela Deletar Autor");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void telaDeletarAvaliador() throws Exception {
    	FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/deletar-avaliador.fxml"));
    	try {
			Scene scene = new Scene(fx.load());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Tela Deletar Avaliador");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void telaDeletarObraGerente() throws Exception {
    	FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/deletar-obra-gerente.fxml"));
    	try {
			Scene scene = new Scene(fx.load());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Tela Deletar Obra Gerente");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void telaDeletarObraAutor(UsuarioVO usuarioVO) throws Exception { //mudar a classe
    	FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/deletar-obra-autor.fxml"));
        Parent root = fx.load();
        DeletarObraAutorController controller = fx.getController();
        controller.setUsuarioVO(usuarioVO);

        Stage popupStage = new Stage();
        Scene scene = new Scene(root);
        popupStage.setScene(scene);

        popupStage.setTitle("Tela Deletar Obra Autor");

        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(primaryStage); 

        popupStage.show();
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
			Scene scene = new Scene(fx.load());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Tela Editar Avaliador Gerente");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void telaEditarAutor() throws Exception {
    	FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/editar-autor-gerente.fxml"));
    	try {
			Scene scene = new Scene(fx.load());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Tela Editar Autor Gerente");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void telaEditarObraGerente() throws Exception {
        FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/editar-obra-gerente.fxml"));
        try {
            Scene scene = new Scene(fx.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Tela Editar Obra Gerente");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void telaEditarObraAutor(UsuarioVO usuarioVO) throws Exception {
        FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/editar-obra-autor.fxml"));
        Parent root = fx.load();
        EditarObraAutorController controller = fx.getController();
        controller.setUsuarioVO(usuarioVO);

        Stage popupStage = new Stage();
        Scene scene = new Scene(root);
        popupStage.setScene(scene);

        popupStage.setTitle("Tela Editar Obra Autor");

        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(primaryStage); 

        popupStage.show();
    }
    
    public static void telaBaixarObra(UsuarioVO usuarioVO) throws Exception {
        FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/baixar-obra-avaliador.fxml"));
        Parent root = fx.load();
        BaixarObraAvaliadorController controller = fx.getController();
        controller.setUsuarioVO(usuarioVO);

        Stage popupStage = new Stage();
        Scene scene = new Scene(root);
        popupStage.setScene(scene);

        popupStage.setTitle("Tela Baixar Obra Avaliador");

        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(primaryStage); 

        popupStage.show();
    }

    public static void telaGerarRelatorio() throws Exception {
    	FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/gerar-relatorio.fxml"));
    	try {
			Scene scene = new Scene(fx.load());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Tela Gerar Relatório");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void telaNovaObraAutor(UsuarioVO usuarioVO) throws Exception {
        FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/nova-obra-autor.fxml"));
        Parent root = fx.load();
        NovaObraAutorController controller = fx.getController();
        controller.setUsuarioVO(usuarioVO);

        Stage popupStage = new Stage();
        Scene scene = new Scene(root);
        popupStage.setScene(scene);

        popupStage.setTitle("Tela Nova Obra Autor");

        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(primaryStage); 

        popupStage.show();
    }

    public static void telaNovaObraGerente() throws Exception {
    	FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/nova-obra-gerente.fxml"));
    	try {
			Scene scene = new Scene(fx.load());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Tela Nova Obra Gerente");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void telaNovoAutorGerente() throws Exception {
        FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/novo-autor-gerente.fxml"));
        try {
            Scene scene = new Scene(fx.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Tela Novo Autor Gerente");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void telaNovoAvaliadorGerente() throws Exception {
        FXMLLoader fx = new FXMLLoader(TelaPrincipalController.class.getResource("/view/VE/novo-avaliador-gerente.fxml"));
        try {
            Scene scene = new Scene(fx.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Tela Novo Avaliador Gerente");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void telaRedefinirSenha() throws Exception {
        FXMLLoader fx = new FXMLLoader(LoginPageController.class.getResource("/view/VE/redefinir-senha.fxml"));
        try {
            Scene scene = new Scene(fx.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Tela Redefinir Senha");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void telaDefinirAvaliador() throws Exception {
        FXMLLoader fx = new FXMLLoader(LoginPageController.class.getResource("/view/VE/definir-avaliador.fxml"));
        try {
            Scene scene = new Scene(fx.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Tela Definir Avaliador");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void telaDeletarAvaliacao() {
        
    }

}