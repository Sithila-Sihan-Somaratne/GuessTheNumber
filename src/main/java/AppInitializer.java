import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        try {
            System.err.close();
            System.setErr(System.out);
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/guessNumberForm.fxml")))));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
