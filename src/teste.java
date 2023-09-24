import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.stage.*;

public class teste extends Application {
    public static void main(String[] args) {
        System.out.println("Hello World");
        launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label msg = new Label();
        msg.setText("Olá mnundo");

        Scene cena = new Scene(msg);
        primaryStage.setScene(cena);
        primaryStage.setTitle("teste");
        primaryStage.show();

    }
    
}