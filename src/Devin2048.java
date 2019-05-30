import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Devin2048 extends Application {

    @Override
    public void start(Stage ps) {

        // Establishing the background of the game.
        BorderPane background = new BorderPane();
        background.setStyle("-fx-background-color: #faf8ef");


        //Setting up the top bar of the game.
        HBox topBar = new HBox();
        GridPane topBarSplit = new GridPane(); //For splitting the name and scores.

        Pane name = new Pane(); //For "2048" at the top of the screen.
        topBarSplit.add(name, 0, 0);

        GridPane scoresSplit = new GridPane(); //Splitting scores.
        topBarSplit.add(scoresSplit, 1, 0);

        Pane score = new Pane();
        Pane bestScore = new Pane();
        scoresSplit.add(score, 0, 0);
        scoresSplit.add(bestScore, 1, 0);

        topBar.getChildren().add(topBarSplit);

        background.setTop(topBar);

        //Setting up the board of the game.
        Pane gameBoarder = new Pane();

        Scene scene = new Scene(background, 500, 500);
        ps.setTitle("Devin 2048");
        ps.setScene(scene);
        ps.show();
    }
}
