import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Devin2048 extends Application {

    @Override
    public void start(Stage ps) {
        BorderPane background = new BorderPane();
        background.setStyle("-fx-background-color: #F2E2D2");

        Scene scene = new Scene(background, 500, 500);
        ps.setTitle("Devin 2048");
        ps.setScene(scene);
        ps.show();
    }
}
