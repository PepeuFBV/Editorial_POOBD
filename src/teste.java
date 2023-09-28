import javafx.application.Application;
import javafx.stage.*;
import view.HelloFX;

public class teste extends Application {
    public static void main(String[] args) {
        System.out.println("Hello World");
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        HelloFX.setPrimaryStage(primaryStage);
        HelloFX.telaLogin();
    }
    
}