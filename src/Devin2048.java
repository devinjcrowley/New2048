import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static javafx.scene.input.KeyCode.*;
import static javafx.scene.input.KeyEvent.KEY_PRESSED;


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
                blank.setAccessibleText("0");
                r.setHeight(79);
                r.setWidth(79);
                blank.getChildren().addAll(r);
                board.add(blank, row, column);
            }
        }

//        for (int j = 0; j < 2; j++) {
//            int row = (int)(Math.random()*4);
//            int column = (int)(int)(Math.random()*4);
//            int twoOrFour = (int)(Math.random()*10+1);
//            while (a[row][column] != null) {
//                column = (int)(Math.random()*4);
//                row = (int)(Math.random()*4);
//            }
//            if (twoOrFour - 10 == 0) {
//                StackPane s4 = makeS4();
//                a[row][column] = s4;
//            }
//            else {
//                StackPane s2 = makeS2();
//                a[row][column] = s2;
//            }
//            board.add(a[row][column], column, row);
//        }
        int r = 0;
        int c = 0;
        a[r][c] = makeS2();
        board.add(a[r][c], r, c);
        r = 1;
        c = 0;
        a[r][c] = makeS2();
        board.add(a[r][c], r, c);

        board.setOnKeyPressed(e -> {

            if (e.getCode() == RIGHT) {
                for (int column = 2; column >= 0; column--) {
                    for (int row = 0; row < 4; row++) {
                        if (a[row][column] != null && a[row][column + 1] == null) {
                            StackPane n = whatTheWhick(a, row, column);
                            board.add(n, column + 1, row);
                            board.getChildren().remove(a[row][column]);
                            a[row][column + 1] = n;
                            a[row][column] = null;
                        }
                        else if (a[row][column] != null && a[row][column + 1]!= null) {
                            if (a[row][column].getAccessibleText().equals(a[row][column + 1].getAccessibleText())) {
                                board.getChildren().remove(a[row][column]);
                                int sum = Integer.parseInt(a[row][column + 1].getAccessibleText()) +
                                        Integer.parseInt(a[row][column].getAccessibleText());
                                a[row][column + 1].setAccessibleText(Integer.toString(sum));
                                board.getChildren().remove(a[row][column + 1]);
                                StackPane n = whatTheWhick2(sum);
                                board.add(n, (column + 1), row);
                                a[row][column + 1] = n;
                                a[row][column] = null;
                            }
                        }
                    }
                }
                for (int column = 2; column >= 1; column--) {
                    for (int row = 0; row < 4; row++) {
                        if (a[row][column] != null && a[row][column + 1] == null) {
                            StackPane n = whatTheWhick(a, row, column);
                            board.add(n, column + 1, row);
                            board.getChildren().remove(a[row][column]);
                            a[row][column + 1] = n;
                            a[row][column] = null;
                        }
                        else if (a[row][column] != null && a[row][column + 1]!= null) {
                            if (a[row][column].getAccessibleText().equals(a[row][column + 1].getAccessibleText())) {
                                board.getChildren().remove(a[row][column]);
                                int sum = Integer.parseInt(a[row][column + 1].getAccessibleText()) +
                                        Integer.parseInt(a[row][column].getAccessibleText());
                                a[row][column + 1].setAccessibleText(Integer.toString(sum));
                                board.getChildren().remove(a[row][column + 1]);
                                StackPane n = whatTheWhick2(sum);
                                board.add(n, (column + 1), row);
                                a[row][column + 1] = n;
                                a[row][column] = null;
                            }
                        }
                    }
                }

                for (int row = 0; row < 4; row++) {
                    if (a[row][2] != null && a[row][2 + 1] == null) {
                        StackPane n = whatTheWhick(a, row, 2);
                        board.add(n, 2 + 1, row);
                        board.getChildren().remove(a[row][2]);
                        a[row][2 + 1] = n;
                        a[row][2] = null;
                    }
                    else if (a[row][2] != null && a[row][3]!= null) {
                        if (a[row][2].getAccessibleText().equals(a[row][3].getAccessibleText())) {
                            board.getChildren().remove(a[row][2]);
                            int sum = Integer.parseInt(a[row][3].getAccessibleText()) +
                                    Integer.parseInt(a[row][2].getAccessibleText());
                            a[row][3].setAccessibleText(Integer.toString(sum));
                            board.getChildren().remove(a[row][2 + 1]);
                            StackPane n = whatTheWhick2(sum);
                            board.add(n, (2 + 1), row);
                            a[row][2 + 1] = n;
                            a[row][2] = null;
                        }
                    }
                }

            }

            if (e.getCode() == LEFT) {
                for (int column = 1; column <= 3; column++) {
                    for (int row = 0; row < 4; row++) {
                        if  (a[row][column] != null && a[row][column-1] == null) {
                            StackPane n = whatTheWhick(a, row, column);
                            board.add(n, column - 1, row);
                            board.getChildren().remove(a[row][column]);
                            a[row][column - 1] = n;
                            a[row][column] = null;
                        } else if (a[row][column] != null && a[row][column - 1]!= null) {
                            if (a[row][column].getAccessibleText().equals(a[row][column - 1].getAccessibleText())) {
                                board.getChildren().remove(a[row][column]);
                                int sum = Integer.parseInt(a[row][column - 1].getAccessibleText()) +
                                        Integer.parseInt(a[row][column].getAccessibleText());
                                a[row][column - 1].setAccessibleText(Integer.toString(sum));
                                board.getChildren().remove(a[row][column - 1]);
                                StackPane n = whatTheWhick2(sum);
                                board.add(n, (column - 1), row);
                                a[row][column - 1] = n;
                                a[row][column] = null;
                            }
                        }
                    }
                }
                for (int column = 1; column <= 2; column++) {
                    for (int row = 0; row < 4; row++) {
                        if  (a[row][column] != null && a[row][column-1] == null) {
                            StackPane n = whatTheWhick(a, row, column);
                            board.add(n, column - 1, row);
                            board.getChildren().remove(a[row][column]);
                            a[row][column - 1] = n;
                            a[row][column] = null;
                        } else if (a[row][column] != null && a[row][column - 1]!= null) {
                            if (a[row][column].getAccessibleText().equals(a[row][column - 1].getAccessibleText())) {
                                board.getChildren().remove(a[row][column]);
                                int sum = Integer.parseInt(a[row][column - 1].getAccessibleText()) +
                                        Integer.parseInt(a[row][column].getAccessibleText());
                                a[row][column - 1].setAccessibleText(Integer.toString(sum));
                                board.getChildren().remove(a[row][column - 1]);
                                StackPane n = whatTheWhick2(sum);
                                board.add(n, (column - 1), row);
                                a[row][column - 1] = n;
                                a[row][column] = null;
                            }
                        }
                    }
                }

                for (int row = 0; row < 4; row++) {
                    if  (a[row][1] != null && a[row][0] == null) {
                        StackPane n = whatTheWhick(a, row, 1);
                        board.add(n, 0, row);
                        board.getChildren().remove(a[row][1]);
                        a[row][0] = n;
                        a[row][1] = null;
                    } else if (a[row][1] != null && a[row][1 - 1]!= null) {
                        if (a[row][1].getAccessibleText().equals(a[row][1 - 1].getAccessibleText())) {
                            board.getChildren().remove(a[row][1]);
                            int sum = Integer.parseInt(a[row][1 - 1].getAccessibleText()) +
                                    Integer.parseInt(a[row][1].getAccessibleText());
                            a[row][1 - 1].setAccessibleText(Integer.toString(sum));
                            board.getChildren().remove(a[row][1 - 1]);
                            StackPane n = whatTheWhick2(sum);
                            board.add(n, (1 - 1), row);
                            a[row][1 - 1] = n;
                            a[row][1] = null;
                        }
                    }
                }
            }
            if (e.getCode() == UP){
                for (int row = 1; row <= 3; row++) {
                    for (int column = 0; column < 4; column++) {
                        if  (a[row][column] != null && a[row - 1][column] == null) {
                            StackPane n = whatTheWhick(a, row, column);
                            board.add(n, column, row - 1);
                            board.getChildren().remove(a[row][column]);
                            a[row - 1][column] = n;
                            a[row][column] = null;
                        } else if (a[row][column] != null && a[row - 1][column]!= null) {
                            if (a[row][column].getAccessibleText().equals(a[row - 1][column].getAccessibleText())) {
                                board.getChildren().remove(a[row][column]);
                                int sum = Integer.parseInt(a[row - 1][column].getAccessibleText()) +
                                        Integer.parseInt(a[row][column].getAccessibleText());
                                a[row - 1][column].setAccessibleText(Integer.toString(sum));
                                board.getChildren().remove(a[row - 1][column]);
                                StackPane n = whatTheWhick2(sum);
                                board.add(n, (column), row - 1);
                                a[row - 1][column] = n;
                                a[row][column] = null;
                            }
                        }
                    }
                }
                for (int row = 1; row <= 2; row++) {
                    for (int column = 0; column < 4; column++) {
                        if  (a[row][column] != null && a[row - 1][column] == null) {
                            StackPane n = whatTheWhick(a, row, column);
                            board.add(n, column, row - 1);
                            board.getChildren().remove(a[row][column]);
                            a[row - 1][column] = n;
                            a[row][column] = null;
                        }  else if (a[row][column] != null && a[row - 1][column]!= null) {
                            if (a[row][column].getAccessibleText().equals(a[row - 1][column].getAccessibleText())) {
                                board.getChildren().remove(a[row][column]);
                                int sum = Integer.parseInt(a[row - 1][column].getAccessibleText()) +
                                        Integer.parseInt(a[row][column].getAccessibleText());
                                a[row - 1][column].setAccessibleText(Integer.toString(sum));
                                board.getChildren().remove(a[row - 1][column]);
                                StackPane n = whatTheWhick2(sum);
                                board.add(n, (column), row - 1);
                                a[row - 1][column] = n;
                                a[row][column] = null;
                            }
                        }
                    }
                }

                for (int column = 0; column < 4; column++) {
                    if  (a[1][column] != null && a[0][column] == null) {
                        StackPane n = whatTheWhick(a, 1, column);
                        board.add(n, column, 0);
                        board.getChildren().remove(a[1][column]);
                        a[0][column] = n;
                        a[1][column] = null;
                    }  else if (a[1][column] != null && a[1 - 1][column]!= null) {
                        if (a[1][column].getAccessibleText().equals(a[1 - 1][column].getAccessibleText())) {
                            board.getChildren().remove(a[1][column]);
                            int sum = Integer.parseInt(a[1 - 1][column].getAccessibleText()) +
                                    Integer.parseInt(a[1][column].getAccessibleText());
                            a[1 - 1][column].setAccessibleText(Integer.toString(sum));
                            board.getChildren().remove(a[1 - 1][column]);
                            StackPane n = whatTheWhick2(sum);
                            board.add(n, (column), 1 - 1);
                            a[1 - 1][column] = n;
                            a[1][column] = null;
                        }
                    }
                }
            }

            if (e.getCode() == DOWN){
                for (int row = 2; row >= 0; row--) {
                    for (int column = 0; column < 4; column++) {
                        if  (a[row][column] != null && a[row + 1][column] == null) {
                            StackPane n = whatTheWhick(a, row, column);
                            board.add(n, column, row + 1);
                            board.getChildren().remove(a[row][column]);
                            a[row + 1][column] = n;
                            a[row][column] = null;
                        } else if (a[row][column] != null && a[row + 1][column]!= null) {
                            if (a[row][column].getAccessibleText().equals(a[row + 1][column].getAccessibleText())) {
                                board.getChildren().remove(a[row][column]);
                                int sum = Integer.parseInt(a[row + 1][column].getAccessibleText()) +
                                        Integer.parseInt(a[row][column].getAccessibleText());
                                a[row + 1][column].setAccessibleText(Integer.toString(sum));
                                board.getChildren().remove(a[row + 1][column]);
                                StackPane n = whatTheWhick2(sum);
                                board.add(n, (column), row + 1);
                                a[row + 1][column] = n;
                                a[row][column] = null;
                            }
                        }
                    }
                }
                for (int row = 2; row >= 1; row--) {
                    for (int column = 0; column < 4; column++) {
                        if  (a[row][column] != null && a[row + 1][column] == null) {
                            StackPane n = whatTheWhick(a, row, column);
                            board.add(n, column, row + 1);
                            board.getChildren().remove(a[row][column]);
                            a[row + 1][column] = n;
                            a[row][column] = null;
                        }
                        else if (a[row][column] != null && a[row + 1][column]!= null) {
                            if (a[row][column].getAccessibleText().equals(a[row + 1][column].getAccessibleText())) {
                                board.getChildren().remove(a[row][column]);
                                int sum = Integer.parseInt(a[row + 1][column].getAccessibleText()) +
                                        Integer.parseInt(a[row][column].getAccessibleText());
                                a[row + 1][column].setAccessibleText(Integer.toString(sum));
                                board.getChildren().remove(a[row + 1][column]);
                                StackPane n = whatTheWhick2(sum);
                                board.add(n, (column), row + 1);
                                a[row + 1][column] = n;
                                a[row][column] = null;
                            }
                        }
                    }
                }

                for (int column = 3; column >= 0; column--) {
                    if  (a[2][column] != null && a[3][column] == null) {
                        StackPane n = whatTheWhick(a, 2, column);
                        board.add(n, column, 3);
                        board.getChildren().remove(a[2][column]);
                        a[3][column] = n;
                        a[2][column] = null;
                    } else if (a[2][column] != null && a[2 + 1][column]!= null) {
                        if (a[2][column].getAccessibleText().equals(a[2 + 1][column].getAccessibleText())) {
                            board.getChildren().remove(a[2][column]);
                            int sum = Integer.parseInt(a[2 + 1][column].getAccessibleText()) +
                                    Integer.parseInt(a[2][column].getAccessibleText());
                            a[2 + 1][column].setAccessibleText(Integer.toString(sum));
                            board.getChildren().remove(a[2 + 1][column]);
                            StackPane n = whatTheWhick2(sum);
                            board.add(n, (column), 2 + 1);
                            a[2 + 1][column] = n;
                            a[2][column] = null;
                        }
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
    //i made a bunch of new functions to make the different squares with the correct text n accessible text n colors
    public StackPane makeS2() {
        Rectangle r2 = new Rectangle();
        r2.setArcHeight(10);
        r2.setArcWidth(10);
        r2.setFill(Color.web("#d6d4d3"));
        r2.setHeight(79);
        r2.setWidth(79);
        Text t2 = new Text("2");
        t2.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t2.setFill(Color.web("#776e65"));
        StackPane s2 = new StackPane();
        s2.setAccessibleText("2");
        s2.getChildren().addAll(r2, t2);
        return s2;
    }

    public StackPane makeS4() {
        Rectangle r4 = new Rectangle();
        r4.setArcHeight(10);
        r4.setArcWidth(10);
        r4.setFill(Color.web("#e6ceb3"));
        r4.setHeight(79);
        r4.setWidth(79);
        Text t4 = new Text("4");
        t4.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t4.setFill(Color.web("#776e65"));
        StackPane s4 = new StackPane();
        s4.getChildren().addAll(r4, t4);
        s4.setAccessibleText("4");
        return s4;
    }

    public StackPane makeS8() {
        Rectangle r8 = new Rectangle();
        r8.setArcHeight(10);
        r8.setArcWidth(10);
        r8.setAccessibleText("8");
        r8.setFill(Color.web("#e8a67e"));
        r8.setHeight(79);
        r8.setWidth(79);
        Text t8 = new Text("8");
        t8.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t8.setFill(Color.web("#e6ceb3"));
        StackPane s8 = new StackPane();
        s8.getChildren().addAll(r8, t8);
        s8.setAccessibleText("8");
        return s8;
    }

    public StackPane makeS16() {
        Rectangle r16 = new Rectangle();
        r16.setArcHeight(10);
        r16.setArcWidth(10);
        r16.setAccessibleText("16");
        r16.setFill(Color.web("e88a4b"));
        r16.setHeight(79);
        r16.setWidth(79);
        Text t16 = new Text("16");
        t16.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t16.setFill(Color.web("#e6ceb3"));
        StackPane s16 = new StackPane();
        s16.getChildren().addAll(r16, t16);
        s16.setAccessibleText("16");
        return s16;
    }

    public StackPane makeS32() {
        Rectangle r32 = new Rectangle();
        r32.setArcHeight(10);
        r32.setArcWidth(10);
        r32.setAccessibleText("32");
        r32.setFill(Color.web("#ff7a65"));
        r32.setHeight(79);
        r32.setWidth(79);
        Text t32 = new Text("32");
        t32.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t32.setFill(Color.web("#e6ceb3"));
        StackPane s4 = new StackPane();
        s4.getChildren().addAll(r32, t32);
        s4.setAccessibleText("32");
        return s4;
    }

    public StackPane makeS64() {
        Rectangle r64 = new Rectangle();
        r64.setArcHeight(10);
        r64.setArcWidth(10);
        r64.setAccessibleText("64");
        r64.setFill(Color.web("#eb4e2f"));
        r64.setHeight(79);
        r64.setWidth(79);
        Text t64 = new Text("64");
        t64.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t64.setFill(Color.web("#e6ceb3"));
        StackPane s64 = new StackPane();
        s64.getChildren().addAll(r64, t64);
        s64.setAccessibleText("64");
        return s64;
    }

    public StackPane makeS128() {
        Rectangle r128 = new Rectangle();
        r128.setArcHeight(10);
        r128.setArcWidth(10);
        r128.setAccessibleText("128");
        r128.setFill(Color.web("#f0df73"));
        r128.setHeight(79);
        r128.setWidth(79);
        Text t128 = new Text("128");
        t128.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t128.setFill(Color.web("#e6ceb3"));
        StackPane s128 = new StackPane();
        s128.getChildren().addAll(r128, t128);
        s128.setAccessibleText("128");
        return s128;
    }

    public StackPane makeS256() {
        Rectangle r256 = new Rectangle();
        r256.setArcHeight(10);
        r256.setArcWidth(10);
        r256.setAccessibleText("256");
        r256.setFill(Color.web("#f0df48"));
        r256.setHeight(79);
        r256.setWidth(79);
        Text t256 = new Text("256");
        t256.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t256.setFill(Color.web("#e6ceb3"));
        StackPane s256 = new StackPane();
        s256.getChildren().addAll(r256, t256);
        s256.setAccessibleText("4");
        return s256;
    }

    public StackPane makeS512() {
        Rectangle r512 = new Rectangle();
        r512.setArcHeight(10);
        r512.setArcWidth(10);
        r512.setAccessibleText("512");
        r512.setFill(Color.web("#f2cf09"));
        r512.setHeight(79);
        r512.setWidth(79);
        Text t512 = new Text("512");
        t512.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t512.setFill(Color.web("#e6ceb3"));
        StackPane s512 = new StackPane();
        s512.getChildren().addAll(r512, t512);
        s512.setAccessibleText("512");
        return s512;
    }

    public StackPane makeS1024() {
        Rectangle r1024 = new Rectangle();
        r1024.setArcHeight(10);
        r1024.setArcWidth(10);
        r1024.setAccessibleText("1024");
        r1024.setFill(Color.web("#ffee6b"));
        r1024.setHeight(79);
        r1024.setWidth(79);
        Text t1024 = new Text("1024");
        t1024.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t1024.setFill(Color.web("#e6ceb3"));
        StackPane s1024 = new StackPane();
        s1024.getChildren().addAll(r1024, t1024);
        s1024.setAccessibleText("1024");
        return s1024;
    }

    public StackPane makeS2048() {
        Rectangle r2048 = new Rectangle();
        r2048.setArcHeight(10);
        r2048.setArcWidth(10);
        r2048.setAccessibleText("2048");
        r2048.setFill(Color.web("#fff587"));
        r2048.setHeight(79);
        r2048.setWidth(79);
        Text t2048 = new Text("2048");
        t2048.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t2048.setFill(Color.web("#e6ceb3"));
        StackPane s2048 = new StackPane();
        s2048.getChildren().addAll(r2048, t2048);
        s2048.setAccessibleText("2048");
        return s2048;
    }

    public StackPane whatTheWhick(StackPane[][] a, int row, int column) {
        if (a[row][column].getAccessibleText().equals("2")) {
            return makeS2();
        } else if (a[row][column].getAccessibleText().equals("4")) {
            return makeS4();
        } else if (a[row][column].getAccessibleText().equals("8")) {
            return makeS8();
        } else if (a[row][column].getAccessibleText().equals("16")) {
            return makeS16();
        } else if (a[row][column].getAccessibleText().equals("32")) {
            return makeS32();
        } else if (a[row][column].getAccessibleText().equals("64")) {
            return makeS64();
        } else if (a[row][column].getAccessibleText().equals("128")) {
            return makeS128();
        } else if (a[row][column].getAccessibleText().equals("256")) {
            return makeS256();
        } else if (a[row][column].getAccessibleText().equals("512")) {
            return makeS512();
        } else if (a[row][column].getAccessibleText().equals("1024")) {
            return makeS1024();
        } else if (a[row][column].getAccessibleText().equals("2048")) {
            return makeS2048();
        }
        return makeBlank();
    }

     public StackPane whatTheWhick2(int sum) {
        if (sum == 4) {
            return makeS4();
        } else if (sum == 8) {
            return makeS8();
        } else if (sum == 16) {
            return makeS16();
        } else if (sum == 32) {
            return makeS32();
        } else if (sum == 64) {
            return makeS64();
        } else if (sum == 128) {
            return makeS128();
        } else if (sum == 256) {
            return makeS256();
        } else if (sum == 512) {
            return makeS512();
        } else if (sum == 1024) {
            return makeS1024();
        } else if (sum == 2048) {
            return makeS2048();
        }
        return makeBlank();
    }

    public StackPane makeBlank() {
        StackPane blank = new StackPane();
        Rectangle r = new Rectangle();
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setFill(Color.web("#cdc1b4"));
        r.setHeight(79);
        r.setWidth(79);
        blank.setAccessibleText("0");
        blank.getChildren().add(r);
        return blank;
    }
}


