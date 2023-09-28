package br.edu.ufersa.EditorialdoPaulao.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
    
    public static void telaPrincipal() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("principal-page.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaAvaliarObra() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("avaliar-obra.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void telaCadastrar() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("cadastrar.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaDeletarAutor() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("deletar-autor.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaDeletarAvaliador() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("deletar-avaliador.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaDeletarObra() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("deletar-obra.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void telaDownload() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("download.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void telaEditarAvaliador() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("editar-avaliador-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void telaEditarAutor() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("editar-autor-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void telaEditarObraGerente() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("editar-obra-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaEditarObraAutor() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("editar-obra-autor.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaGerarRelatorio() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("gerar-relatorio.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaNovaObraAutor() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("editar-obra-autor.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaNovaObraGerente() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("nova-obra-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaNovoAutorGerente() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("editar-autor-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaNovoAvaliadorGerente() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("editar-avaliador-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void telaRedefinirSenha() throws Exception {
        Parent root = FXMLLoader.load(Telas.class.getResource("redefinir-senha.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}