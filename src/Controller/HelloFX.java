package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloFX extends Application {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage primaryStage) {
        HelloFX.primaryStage = primaryStage;
    }

    public static void main(String[] args) { //apagar depois
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        primaryStage.setTitle("Editorial do Paulão - Tela de Login");
        primaryStage.show();
        telaLogin();
    }  

    public static void telaLogin() throws Exception{
        Parent root = FXMLLoader.load(HelloFX.class.getResource("login-page.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaObrasGerente() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("obra-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaAutoresGerente() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("autor-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaAvaliadoresGerente() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("avaliador-gerente.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaObrasAvaliador() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("obra-avaliador.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaAutoresAvaliador() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("autor-avaliador.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaObrasAutor() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("obra-autor.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public static void telaAutoresAutor() throws Exception {
        Parent root = FXMLLoader.load(HelloFX.class.getResource("autor-autor.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }

}