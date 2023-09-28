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
    }  

    public static Scene telaLogin() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("login-page.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }
    
    public static Scene telaPrincipal() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("principal-page.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }

    public static Scene telaAvaliarObra() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("avaliar-obra.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }
    
    public static Scene telaCadastrar() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("cadastrar.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }

    public static Scene telaDeletarAutor() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("deletar-autor.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }

    public static Scene telaDeletarAvaliador() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("deletar-avaliador.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }

    public static Scene telaDeletarObra() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("deletar-obra.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }
    
    public static Scene telaDownload() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("download.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }
    
    public static Scene telaEditarAvaliador() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("editar-avaliador-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }
    
    public static Scene telaEditarAutor() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("editar-autor-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }
    
    public static Scene telaEditarObraGerente() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("editar-obra-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }

    public static Scene telaEditarObraAutor() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("editar-obra-autor.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }

    public static Scene telaGerarRelatorio() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("gerar-relatorio.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }

    public static Scene telaNovaObraAutor() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("editar-obra-autor.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }

    public static Scene telaNovaObraGerente() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("nova-obra-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }

    public static Scene telaNovoAutorGerente() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("editar-autor-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }

    public static Scene telaNovoAvaliadorGerente() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("editar-avaliador-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }

    public static Scene telaRedefinirSenha() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("redefinir-senha.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        return scene;
    }

}