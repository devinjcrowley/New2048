import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Yale2048 extends Application {
    public void start (Stage ps) {
        HBox hb = new HBox();

        BorderPane background = new BorderPane();

        BorderPane gameBorder = new BorderPane();
        gameBorder.setStyle("-fx-background-color: #bbada0");

        GridPane board = new GridPane();
        board.setStyle("-fx-background-color: #cdc1b4");

        gameBorder.getChildren().add(board);
        gameBorder.setCenter(board);


        // Top
        GridPane scoreAndText = new GridPane();
        VBox scorePane = new VBox();
        VBox bestScorePane = new VBox();

        Text text = new Text("2048");
        text.setFont(Font.font("Calibri", 50));
        text.setFill(Color.web("#776e65"));

        Text score = new Text("Score");
        score.setFont(Font.font("Calibri", 30));
        score.setFill(Color.web("#eee4da"));

        Text scoreText = new Text("0");
        scoreText.setFont(Font.font("Calibri", 30));
        scoreText.setFill(Color.web("#ffffff"));

        scoreAndText.add(scorePane, 0, 0);
        scoreAndText.add(bestScorePane, 1, 0);
        scoreAndText.setHgap(20);

        scorePane.setPrefHeight(50);
        scorePane.setPrefWidth(75);
        scorePane.getChildren().add(score);
        scorePane.getChildren().add(scoreText);
        scorePane.setAlignment(Pos.CENTER);
        scorePane.setStyle("-fx-background-color: #bbada0");

        Text bestScore = new Text("Best");
        bestScore.setFont(Font.font("Calibri", 30));
        bestScore.setFill(Color.web("#eee4da"));

        Text bestScoreText = new Text("0");
        bestScoreText.setFont(Font.font("Calibri", 30));
        bestScoreText.setFill(Color.web("#ffffff"));

        bestScorePane.setPrefWidth(75);
        bestScorePane.setPrefHeight(50);
        bestScorePane.getChildren().add(bestScore);
        bestScorePane.getChildren().add(bestScoreText);
        bestScorePane.setAlignment(Pos.CENTER);
        bestScorePane.setStyle("-fx-background-color: #bbada0");

        hb.getChildren().addAll(text, scoreAndText);
        hb.setSpacing(300);
        background.setStyle("-fx-background-color: #F2E2D2");



        // Center

        board.setHgap(5);
        board.setVgap(5);

        background.setCenter(gameBorder);
        background.setTop(hb);

        Scene scene = new Scene(background, 600, 400);
        ps.setScene(scene);
        ps.show();
    }
}
