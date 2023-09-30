package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.BO.UserBO;
import controller.TelaPrincipalController;
import javafx.fxml.FXMLLoader;

public class Telas {
    
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage primaryStage) {
        Telas.primaryStage = primaryStage;
    }

    public static void telaLogin() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/login-page.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Editorial do Paulão - Tela de Login");
        primaryStage.show();
    }
    
    public static void telaPrincipal(UserBO userBO) throws Exception {
        TelaPrincipalController tela = new TelaPrincipalController();
        tela.start(userBO);
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/tela-principal.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaAvaliarObra() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/avaliar-obra.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void telaCadastrar() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/cadastrar.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaDeletarAutor() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/deletar-autor.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaDeletarAvaliador() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/deletar-avaliador.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaDeletarObra() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/deletar-obra.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void telaDownload() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/download.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void telaEditarAvaliador() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/editar-avaliador-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void telaEditarAutor() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/editar-autor-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void telaEditarObraGerente() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/editar-obra-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaEditarObraAutor() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/editar-obra-autor.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaGerarRelatorio() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/gerar-relatorio.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaNovaObraAutor() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/editar-obra-autor.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaNovaObraGerente() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/nova-obra-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaNovoAutorGerente() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/editar-autor-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaNovoAvaliadorGerente() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/editar-avaliador-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaRedefinirSenha() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("VE/redefinir-senha.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}