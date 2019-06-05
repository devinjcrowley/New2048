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

import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;

public class Devin2048 extends Application {
    public void start (Stage ps) {
        StackPane[][] a = {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
        };
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

        // Sides
        VBox side1 = new VBox();
        VBox side2 = new VBox();
        side1.setPrefWidth(131.5);
        side2.setPrefWidth(131.5);

        //------------------------------------------------------------------------------------

        board.setHgap(5);
        board.setVgap(5);
        board.setPadding(new Insets(5, 5, 5, 5));

        background.setCenter(board);

        for (int row=0; row<4; row++) {
            for (int column = 0; column < 4; column++) {
                StackPane blank = new StackPane();
                Rectangle r = new Rectangle();
                r.setArcHeight(10);
                r.setArcWidth(10);
                r.setFill(Color.web("#cdc1b4"));
                r.setHeight(79);
                r.setWidth(79);
                blank.getChildren().addAll(r);
                board.add(blank, row, column);
            }
        }

        for (int j = 0; j < 2; j++) {
            StackPane s2 = makeS2();
            int row = (int)(Math.random()*4);
            int column = (int)(int)(Math.random()*4);
            a[row][column] = s2;
            board.add(s2, column, row);
        }

        board.setOnKeyPressed(e -> {

            if (e.getCode() == RIGHT) {
                for (int column = 2; column >= 0; column--) {
                    for (int row = 0; row < 4; row++) {
                        if  (a[row][column] != null && a[row][column+1] == null) {
                            StackPane n = makeS2();
                            board.add(n, column + 1, row);
                            board.getChildren().remove(a[row][column]);
                            a[row][column + 1] = n;
                            a[row][column] = null;
                        }
                    }
                }
                for (int column = 2; column >= 1; column--) {
                    for (int row = 0; row < 4; row++) {
                        if  (a[row][column] != null && a[row][column+1] == null) {
                            StackPane n = makeS2();
                            board.add(n, column + 1, row);
                            board.getChildren().remove(a[row][column]);
                            a[row][column + 1] = n;
                            a[row][column] = null;
                        }
                    }
                }

                for (int row = 0; row < 4; row++) {
                    if  (a[row][2] != null && a[row][2+1] == null) {
                        StackPane n = makeS2();
                        board.add(n, 2 + 1, row);
                        board.getChildren().remove(a[row][2]);
                        a[row][2 + 1] = n;
                        a[row][2] = null;
                    }
                }
            }
            if (e.getCode() == LEFT) {
                for (int column = 1; column <= 3; column++) {
                    for (int row = 0; row < 4; row++) {
                        if  (a[row][column] != null && a[row][column-1] == null) {
                            StackPane n = makeS2();
                            board.add(n, column - 1, row);
                            board.getChildren().remove(a[row][column]);
                            a[row][column - 1] = n;
                            a[row][column] = makeBlank();
                        }
                    }
                }
                for (int column = 1; column <= 2; column++) {
                    for (int row = 0; row < 4; row++) {
                        if  (a[row][column] != null && a[row][column+1] == null) {
                            StackPane n = makeS2();
                            board.add(n, column + 1, row);
                            board.getChildren().remove(a[row][column]);
                            a[row][column - 1] = n;
                            a[row][column] = makeBlank();
                        }
                    }
                }

                for (int row = 0; row < 4; row++) {
                    if  (a[row][1] != null && a[row][0] == null) {
                        StackPane n = makeS2();
                        board.add(n, 0, row);
                        board.getChildren().remove(a[row][1]);
                        a[row][0] = n;
                        a[row][1] = makeBlank();
                    }
                }
            }

        });

        background.setTop(topBar);
        background.setBottom(instructionPane);
        background.setLeft(side1);
        background.setRight(side2);


        background.setStyle("-fx-background-color: #F2E2D2");
        Scene scene = new Scene(background, 600, 500);
        ps.setTitle("Devin2048");
        ps.setScene(scene);
        ps.show();
        board.requestFocus();
    }

    public StackPane makeS2() {
        Rectangle r2 = new Rectangle();
        r2.setArcHeight(10);
        r2.setArcWidth(10);
        r2.setFill(Color.web("#eee4da"));
        r2.setHeight(79);
        r2.setWidth(79);
        Text t2 = new Text("2");
        t2.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t2.setFill(Color.web("#776e65"));
        StackPane s2 = new StackPane();
        s2.getChildren().addAll(r2, t2);
        return s2;
    }

    public StackPane makeS4() {
        Rectangle r4 = new Rectangle();
        r4.setArcHeight(10);
        r4.setArcWidth(10);
        r4.setFill(Color.web("#eee4da"));
        r4.setHeight(79);
        r4.setWidth(79);
        Text t4 = new Text("2");
        t4.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t4.setFill(Color.web("#776e65"));
        StackPane s4 = new StackPane();
        s4.getChildren().addAll(r4, t4);
        return s4;
    }

    public StackPane makeBlank() {
        StackPane blank = new StackPane();
        Rectangle r = new Rectangle();
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setFill(Color.web("#cdc1b4"));
        r.setHeight(79);
        r.setWidth(79);
        blank.getChildren().add(r);
        return blank;
    }
}
