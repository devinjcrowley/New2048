import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Devin2048 extends Application {

    @Override
    public void start(Stage ps) {
        //Instantiating Background
        BorderPane background = new BorderPane();
        background.setStyle("-fx-background-color: #F2E2D2");

        //Instantiating the top bar of the screen.
        HBox topBar = new HBox();

        //Instantiating Scores
        GridPane scoreAndText = new GridPane();
        VBox scorePane = new VBox();
        VBox bestScorePane = new VBox();

        //Instantiating 2048 text
        Text name = new Text("2048");
        name.setFont(Font.font("Calibri", FontWeight.BOLD, 50));
        name.setFill(Color.web("#776e65"));

        //Instantiating Score title
        Text score = new Text("Score");
        score.setFont(Font.font("Calibri", 30));
        score.setFill(Color.web("#eee4da"));

        //Instantiating Score text
        Text scoreText = new Text("0");
        scoreText.setFont(Font.font("Calibri", 30));
        scoreText.setFill(Color.web("#ffffff"));

        //Instantiating Score group
        scoreAndText.add(scorePane, 0, 0);
        scoreAndText.add(bestScorePane, 1, 0);
        scoreAndText.setHgap(20);

        //Setting up scorePane and adding its children
        scorePane.setPrefHeight(50);
        scorePane.setPrefWidth(75);
        scorePane.getChildren().add(score);
        scorePane.getChildren().add(scoreText);
        scorePane.setAlignment(Pos.CENTER);
        scorePane.setStyle("-fx-background-color: #bbada0");

        //Instantiating bestScore
        Text bestScore = new Text("Best");
        bestScore.setFont(Font.font("Calibri", 30));
        bestScore.setFill(Color.web("#eee4da"));

        //Instantiating text for bestScore
        Text bestScoreText = new Text("0");
        bestScoreText.setFont(Font.font("Calibri", 30));
        bestScoreText.setFill(Color.web("#ffffff"));

        //Setting up bestScorePane and its children
        bestScorePane.setPrefWidth(75);
        bestScorePane.setPrefHeight(50);
        bestScorePane.getChildren().add(bestScore);
        bestScorePane.getChildren().add(bestScoreText);
        bestScorePane.setAlignment(Pos.CENTER);
        bestScorePane.setStyle("-fx-background-color: #bbada0");

        //Adding "2048" and the scores to the top bar
        topBar.getChildren().addAll(name, scoreAndText);
        topBar.setSpacing(300);

        //Creating the border for the game
        BorderPane gameBorder = new BorderPane();
        gameBorder.setStyle("-fx-background-color: #bbada0");

        //Creating the game board
        GridPane board = new GridPane();
        gameBorder.getChildren().add(board);
        board.setStyle("-fx-background-color: #cdc1b4");

        //Starting the instructions
        Pane instructionPane = new Pane();
        instructionPane.prefHeightProperty().bind(board.heightProperty().add(60));
        instructionPane.prefWidthProperty().bind(background.widthProperty());
        background.setBottom(instructionPane);

        //instruction header text:
        Text instructionsHead = new Text( "HOW TO PLAY: ");
        instructionsHead.setFont(Font.font ("Calibri", FontWeight.BOLD, 20));
        instructionsHead.setFill(Color.web("#776e65"));
        instructionsHead.setStroke(Color.web("#776e65"));
        instructionsHead.xProperty().bind(instructionPane.widthProperty().divide(2).subtract(200));
        instructionsHead.yProperty().bind(instructionPane.heightProperty().subtract(35));

        //instructions body text:
        Text instructionsOne = new Text("Use your arrow keys to move the tiles.");
        instructionsOne.setFont(Font.font ("Calibri",15));
        instructionsOne.setFill(Color.web("#776e65"));
        instructionsOne.setStroke(Color.web("#776e65"));
        instructionsOne.xProperty().bind(instructionsHead.xProperty().add(130));
        instructionsOne.yProperty().bind(instructionPane.heightProperty().subtract(35));

        Text instructionsTwo = new Text("When two tiles with the same " +
                "number touch, they merge into one!");
        instructionsTwo.setFont(Font.font ("Calibri",15));
        instructionsTwo.setFill(Color.web("#776e65"));
        instructionsTwo.setStroke(Color.web("#776e65"));
        instructionsTwo.xProperty().bind(instructionPane.widthProperty().divide(2).subtract(200));
        instructionsTwo.yProperty().bind(instructionPane.heightProperty().subtract(15));
        instructionPane.getChildren().addAll(instructionsHead, instructionsOne, instructionsTwo);

        board.setHgap(5);
        board.setVgap(5);

        background.setCenter(gameBorder);
        background.setTop(topBar);



        Scene scene = new Scene(background, 570, 500);
        ps.setTitle("Devin 2048");
        ps.setScene(scene);
        ps.show();
    }
}
