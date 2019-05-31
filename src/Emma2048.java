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

public class Emma2048 extends Application {

    @Override
    public void start(Stage ps) {
        BorderPane background = new BorderPane();

        BorderPane gameBorder = new BorderPane();
        gameBorder.setStyle("-fx-background-color: #bbada0");

        HBox hb = new HBox();
        GridPane board = new GridPane();
        gameBorder.getChildren().add(board);
        board.setStyle("-fx-background-color: #cdc1b4");

        GridPane scoreAndText = new GridPane();
        VBox scorePane = new VBox();
        VBox bestScorePane = new VBox();

        Text text = new Text("2048");
        text.setFont(Font.font("Calibri", FontWeight.BOLD, 50));
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

        Rectangle test = new Rectangle(30, 0);

        Pane i = new Pane();
        i.prefHeightProperty().bind(board.heightProperty().add(60));
        i.prefWidthProperty().bind(background.widthProperty());
        background.setBottom(i);
        //Done on 5/30/19:
//instruction header text:
        Text insHead = new Text( "HOW TO PLAY: ");
        insHead.setFont(Font.font ("Calibri", FontWeight.BOLD, 20));
        insHead.setFill(Color.web("#776e65"));
        insHead.setStroke(Color.web("#776e65"));
        insHead.xProperty().bind(i.widthProperty().divide(2).subtract(200));
        insHead.yProperty().bind(i.heightProperty().subtract(35));
//instructions body text:
        Text inst1 = new Text("Use your arrow keys to move the tiles.");
        inst1.setFont(Font.font ("Calibri",15));
        inst1.setFill(Color.web("#776e65"));
        inst1.setStroke(Color.web("#776e65"));
        inst1.xProperty().bind(insHead.xProperty().add(130));
        inst1.yProperty().bind(i.heightProperty().subtract(35));
        Text inst2 = new Text("When two tiles with the same " +
                "number touch, they merge into one!");
        inst2.setFont(Font.font ("Calibri",15));
        inst2.setFill(Color.web("#776e65"));
        inst2.setStroke(Color.web("#776e65"));
        inst2.xProperty().bind(i.widthProperty().divide(2).subtract(200));
        inst2.yProperty().bind(i.heightProperty().subtract(15));
        i.getChildren().addAll(insHead, inst1, inst2);


        board.setHgap(5);
        board.setVgap(5);

        background.setCenter(gameBorder);
        background.setTop(hb);

        Scene scene = new Scene(background, 570, 500);
        ps.setTitle("Emma 2048");
        ps.setScene(scene);
        ps.show();
    }
}
