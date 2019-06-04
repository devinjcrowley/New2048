import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Yale2048 extends Application {
    public void start (Stage ps) {
        BorderPane background = new BorderPane();

        GridPane board = new GridPane();
        board.setStyle("-fx-background-color: #bbada0");

        // Top
        HBox topBar = new HBox();
        topBar.setPrefHeight(100);
        topBar.setSpacing(200);
        topBar.setAlignment(Pos.CENTER);
        GridPane scoreAndBest = new GridPane();
        VBox scorePane = new VBox();
        VBox bestScorePane = new VBox();

        Text text = new Text("2048");
        text.setFont(Font.font("Calibri", FontWeight.BOLD, 80));
        text.setFill(Color.web("#776e65"));

        Text score = new Text("Score");
        score.setFont(Font.font("Calibri", 30));
        score.setFill(Color.web("#eee4da"));

        Text scoreText = new Text("0");
        scoreText.setFont(Font.font("Calibri", 30));
        scoreText.setFill(Color.web("#ffffff"));

        scoreAndBest.add(scorePane, 0, 0);
        scoreAndBest.add(bestScorePane, 1, 0);
        scoreAndBest.setHgap(20);
        scoreAndBest.setVgap(50);

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

        topBar.getChildren().addAll(text, scoreAndBest);

        // Bottom
        Pane i = new Pane();
        i.prefHeightProperty().bind(board.heightProperty().add(60));
        i.prefWidthProperty().bind(background.widthProperty());
        background.setBottom(i);
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

        // Sides
        VBox side1 = new VBox();
        VBox side2 = new VBox();
        side1.setPrefWidth(131.5);
        side2.setPrefWidth(131.5);

        // Center
        // This is a color: 776e65


        board.setHgap(5);
        board.setVgap(5);
        board.setPadding(new Insets(5, 5, 5, 5));

        background.setCenter(board);

        for (int row=0; row<4; row++) {
            for (int column = 0; column < 4; column++) {
                Rectangle r = new Rectangle();
                r.setArcHeight(10);
                r.setArcWidth(10);
                r.setFill(Color.web("#cdc1b4"));
                r.setHeight(79);
                r.setWidth(79);
                board.add(r, row, column);
            }
        }

        for (int j = 0; j < 2; j++) {
            Rectangle r2 = new Rectangle();
            r2.setArcHeight(10);
            r2.setArcWidth(10);
            r2.setFill(Color.web("#eee4da"));
            r2.setHeight(79);
            r2.setWidth(79);
            Text t2 = new Text("2");
            t2.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
            t2.setFill(Color.web("#776e65"));
            int row = (int)(Math.random()*4);
            int column = (int)(int)(Math.random()*4);
            StackPane s2 = new StackPane();
            s2.getChildren().addAll(r2, t2);
            
            board.add(s2, row, column);
        }

//        while (board.getChildren().get(0) == null && board.getChildren().get(1) == null && board.getChildren().get(2) == null) {
//            board.setOnKeyPressed(e -> {
//                if (e.getCode() == KeyCode.RIGHT) {
//                    if (board.getChildren().get(1) == null)
//                }
//            });
//        }

        background.setTop(topBar);
        background.setBottom(i);
        background.setLeft(side1);
        background.setRight(side2);


        background.setStyle("-fx-background-color: #F2E2D2");
        Scene scene = new Scene(background, 600, 500);
        ps.setTitle("Yale2048");
        ps.setScene(scene);
        ps.show();
        board.requestFocus();
    }


}
