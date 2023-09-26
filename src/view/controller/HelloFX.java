package view.controller;


import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class HelloFX extends Application {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage primaryStage) {
        HelloFX.primaryStage = primaryStage;
    }

    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        primaryStage.setTitle("Editorial do Paulão - Tela de Login");
        primaryStage.show();
        telaLogin();
    }  

    public static void telaLogin() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("login-page.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }
    
    public static void telaPrincipal() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("principal-page.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaAvaliarObra() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("avaliar-obra.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }
    
    public static void telaCadastrar() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("cadastrar.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaDeletarAutor() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("deletar-autor.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaDeletarAvaliador() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("deletar-avaliador.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaDeletarObra() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("deletar-obra.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }
    
    public static void telaDownload() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("download.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }
    
    public static void telaEditarAvaliador() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("editar-avaliador-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }
    
    public static void telaEditarAutor() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("editar-autor-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }
    
    public static void telaEditarObraGerente() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("editar-obra-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaEditarObraAutor() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("editar-obra-autor.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaGerarRelatorio() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("gerar-relatorio.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaNovaObraAutor() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("editar-obra-autor.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaNovaObraGerente() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("nova-obra-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaNovoAutorGerente() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("editar-autor-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaNovoAvaliadorGerente() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("editar-avaliador-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

}