import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Emma2048 extends Application {

    @Override
    public void start(Stage ps) {

        // Establishing the background of the game.
        BorderPane background = new BorderPane();
        background.setStyle("-fx-background-color: #faf8ef");



//        //Setting up the top bar of the game.
//        HBox topBar = new HBox();
//        GridPane topBarSplit = new GridPane(); //For splitting the name and scores.
//
//        Pane name = new Pane(); //For "2048" at the top of the screen.
//        topBarSplit.add(name, 0, 0);
//
//        GridPane scoresSplit = new GridPane(); //Splitting scores.
//        topBarSplit.add(scoresSplit, 1, 0);
//
//        Pane score = new Pane();
//        Pane bestScore = new Pane();
//        scoresSplit.add(score, 0, 0);
//        scoresSplit.add(bestScore, 1, 0);
//
//        topBar.getChildren().add(topBarSplit);
//
//        background.setTop(topBar);

        //Setting up the board of the game.
        BorderPane gameBoarder = new BorderPane();
        gameBoarder.setStyle("-fx-background-color: #bbada0");

        GridPane board = new GridPane();
        board.setVgap(10);
        board.setHgap(10);
        board.setStyle("-fx-background-color: #cdc1b4");

                    //Done on 5/30/19:
        //instruction header text:
        Text insHead = new Text( "HOW TO PLAY: ");
        insHead.setFont(Font.font ("Calibri", FontWeight.BOLD, 20));
        insHead.setFill(Color.web("#776e65"));
        insHead.setStroke(Color.web("#776e65"));
        insHead.xProperty().bind(background.widthProperty().divide(2).subtract(200));
        insHead.yProperty().bind(background.heightProperty().subtract(75));
        background.getChildren().add(insHead);
        //instructions body text:
        Text inst1 = new Text("Use your arrow keys to move the tiles.");
        inst1.setFont(Font.font ("Calibri",15));
        inst1.setFill(Color.web("#776e65"));
        inst1.setStroke(Color.web("#776e65"));
        inst1.xProperty().bind(insHead.xProperty().add(130));
        inst1.yProperty().bind(background.heightProperty().subtract(75));
        background.getChildren().add(inst1);
        Text inst2 = new Text("When two tiles with the same " +
                "number touch, they merge into one!");
        inst2.setFont(Font.font ("Calibri",15));
        inst2.setFill(Color.web("#776e65"));
        inst2.setStroke(Color.web("#776e65"));
        inst2.xProperty().bind(background.widthProperty().divide(2).subtract(200));
        inst2.yProperty().bind(background.heightProperty().subtract(50));
        background.getChildren().add(inst2);

        gameBoarder.setCenter(board);

        background.getChildren().add(gameBoarder);

        Scene scene = new Scene(background, 500, 500);
        ps.setTitle("Emma 2048");
        ps.setScene(scene);
        ps.show();
    }
}

