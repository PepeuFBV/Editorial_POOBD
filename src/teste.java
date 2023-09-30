import br.edu.ufersa.EditorialdoPaulao.view.Telas;
import javafx.application.Application;
import javafx.stage.*;

public class teste extends Application {
    public static void main(String[] args) {
        System.out.println("Hello World");
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Telas.setPrimaryStage(primaryStage);
        Telas.telaLogin();
    }
    
}