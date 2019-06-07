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

import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;
import static javafx.scene.input.KeyCode.DOWN;

public class Test extends Application {
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

        // Center
        // This is a color: 776e65


        board.setHgap(5);
        board.setVgap(5);
        board.setPadding(new Insets(5, 5, 5, 5));

        background.setCenter(board);

        for (int row=0; row<4; row++) {
            for (int column = 0; column < 4; column++) {
                board.add(makeRectangle(), row, column);
            }
        }

        for (int j = 0; j < 2; j++) {
            int row = (int)(Math.random()*4);
            int column = (int)(int)(Math.random()*4);
            int twoOrFour = (int)(Math.random()*10+1);
            while (a[row][column] != null) {
                column = (int)(Math.random()*4);
                row = (int)(Math.random()*4);
            }
            if (twoOrFour - 10 == 0) {
                StackPane s4 = makeS4();
                a[row][column] = s4;
            }
            else {
                StackPane s2 = makeS2();
                a[row][column] = s2;
            }
            board.add(a[row][column], column, row);
        }

        board.setOnKeyPressed(e -> {

            // What happens if they click the right arrow key
            if (e.getCode() == RIGHT) {
                for (int row = 0; row < 4; row++) {
                    for (int c = 3; c >= 0; c--) {
                        if (a[row][c] != null && c != 3) {

                            board.add(makeRectangle(), c, row);

                            for (int i = c + 1; i < 4; i++) {
                                if (a[row][i] != null) {
                                    if (a[row][c].getAccessibleText().equals(a[row][i].getAccessibleText())) {
                                        if (a[row][c].getAccessibleText().equals("2")) {
                                            board.add(makeRectangle(), i, row);
                                            board.add(makeS4(), i, row);
                                            a[row][i] = makeS4();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("4")) {
                                            board.add(makeRectangle(), i, row);
                                            board.add(makeS8(), i, row);
                                            a[row][i] = makeS8();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("8")) {
                                            board.add(makeRectangle(), i, row);
                                            board.add(makeS16(), i, row);
                                            a[row][i] = makeS16();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("16")) {
                                            board.add(makeRectangle(), i, row);
                                            board.add(makeS32(), i, row);
                                            a[row][i] = makeS32();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("32")) {
                                            board.add(makeRectangle(), i, row);
                                            board.add(makeS64(), i, row);
                                            a[row][i] = makeS64();
                                            break;
                                        }
                                        //do 4, 8, 16 etc.
                                    }
                                    else {
                                        if (a[row][c].getAccessibleText().equals("2")) {
                                            board.add(makeS2(), i - 1, row);
                                            a[row][i - 1] = makeS2();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("4")) {
                                            board.add(makeS4(), i - 1, row);
                                            a[row][i - 1] = makeS4();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("8")) {
                                            board.add(makeS8(), i - 1, row);
                                            a[row][i-1] = makeS8();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("16")) {
                                            board.add(makeS16(), i-1, row);
                                            a[row][i-1] = makeS16();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("32")) {
                                            board.add(makeS32(), i-1, row);
                                            a[row][i-1] = makeS32();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("64")) {
                                            board.add(makeS64(), i-1, row);
                                            a[row][i-1] = makeS64();
                                            break;
                                        }
                                    }
                                }
                                else if (i == 3 && a[row][i] == null) {
                                    if (a[row][c].getAccessibleText().equals("2")) {
                                        board.add(makeS2(), 3, row);
                                        a[row][i] = makeS2();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("4")) {
                                        board.add(makeS4(), 3, row);
                                        a[row][i] = makeS4();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("8")) {
                                        board.add(makeS8(), 3, row);
                                        a[row][i] = makeS8();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("16")) {
                                        board.add(makeS16(), 3, row);
                                        a[row][i] = makeS16();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("32")) {
                                        board.add(makeS32(), 3, row);
                                        a[row][i] = makeS32();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("64")) {
                                        board.add(makeS64(), 3, row);
                                        a[row][i] = makeS64();
                                        break;
                                    }

                                    //do 4, 8, 16 etc.
                                }
                            }

                            a[row][c] = null;

                        }
                    }

                }
            }

            // What happens if they click the left arrow key
            if (e.getCode() == LEFT) {
                for (int row = 0; row < 4; row++) {
                    for (int c = 0; c < 4; c++) {
                        if (a[row][c] != null && c != 0) {

                            board.add(makeRectangle(), c, row);


                            for (int i = c - 1; i >= 0; i--) {
                                if (a[row][i] != null) {
                                    if (a[row][c].getAccessibleText().equals(a[row][i].getAccessibleText())) {
                                        if (a[row][c].getAccessibleText().equals("2")) {
                                            board.add(makeRectangle(), i, row);
                                            board.add(makeS4(), i, row);
                                            a[row][i] = makeS4();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("4")) {
                                            board.add(makeRectangle(), i, row);
                                            board.add(makeS8(), i, row);
                                            a[row][i] = makeS8();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("8")) {
                                            board.add(makeRectangle(), i, row);
                                            board.add(makeS16(), i, row);
                                            a[row][i] = makeS16();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("16")) {
                                            board.add(makeRectangle(), i, row);
                                            board.add(makeS32(), i, row);
                                            a[row][i] = makeS32();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("32")) {
                                            board.add(makeRectangle(), i, row);
                                            board.add(makeS64(), i, row);
                                            a[row][i] = makeS64();
                                            break;
                                        }
                                        //do 4, 8, 16 etc.
                                    }
                                    else {
                                        if (a[row][c].getAccessibleText().equals("2")) {
                                            board.add(makeS2(), i+1, row);
                                            a[row][i+1] = makeS2();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("4")) {
                                            board.add(makeS4(), i+1, row);
                                            a[row][i+1] = makeS4();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("8")) {
                                            board.add(makeS8(), i+1, row);
                                            a[row][i+1] = makeS8();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("16")) {
                                            board.add(makeS16(), i+1, row);
                                            a[row][i+1] = makeS16();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("32")) {
                                            board.add(makeS32(), i+1, row);
                                            a[row][i+1] = makeS32();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("64")) {
                                            board.add(makeS64(), i+1, row);
                                            a[row][i+1] = makeS64();
                                            break;
                                        }

                                    }
                                }
                                else if (i == 0 && a[row][i] == null) {
                                    if (a[row][c].getAccessibleText().equals("2")) {
                                        board.add(makeS2(), 0, row);
                                        a[row][i] = makeS2();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("4")) {
                                        board.add(makeS4(), 0, row);
                                        a[row][i] = makeS4();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("8")) {
                                        board.add(makeS8(), 0, row);
                                        a[row][i] = makeS8();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("16")) {
                                        board.add(makeS16(), 0, row);
                                        a[row][i] = makeS16();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("32")) {
                                        board.add(makeS32(), 0, row);
                                        a[row][i] = makeS32();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("64")) {
                                        board.add(makeS64(), 0, row);
                                        a[row][i] = makeS64();
                                        break;
                                    }
                                }
                            }

                            a[row][c] = null;
                        }

                    }

                }
            }

            // What happens if they click the up arrow key
            if (e.getCode() == UP) {
                for (int c = 0; c < 4; c++) {
                    for (int row = 0; row < 4; row++) {
                        if (a[row][c] != null && row != 0) {

                            board.add(makeRectangle(), c, row);


                            for (int i = row - 1; i >= 0; i--) {
                                if (a[i][c] != null) {
                                    if (a[row][c].getAccessibleText().equals(a[i][c].getAccessibleText())) {
                                        if (a[row][c].getAccessibleText().equals("2")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS4(), c, i);
                                            a[i][c] = makeS4();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("4")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS8(), c, i);
                                            a[i][c] = makeS8();
                                            break;
                                        }
                                        //do 4, 8, 16 etc.
                                    }
                                    else {
                                        if (a[row][c].getAccessibleText().equals("2")) {
                                            board.add(makeS2(), c, i+1);
                                            a[i+1][c] = makeS2();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("4")) {
                                            board.add(makeS4(), c, i+1);
                                            a[i+1][c] = makeS4();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("8")) {
                                            board.add(makeS8(), c, i+1);
                                            a[i + 1][c] = makeS8();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("16")) {
                                            board.add(makeS16(), c, i+1);
                                            a[i + 1][c] = makeS16();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("32")) {
                                            board.add(makeS32(), c, i+1);
                                            a[i + 1][c] = makeS32();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("64")) {
                                            board.add(makeS64(), c, i+1);
                                            a[i + 1][c] = makeS64();
                                            break;
                                        }

                                    }
                                }
                                else if (i == 0 && a[i][c] == null) {
                                    if (a[row][c].getAccessibleText().equals("2")) {
                                        board.add(makeS2(), c, 0);
                                        a[i][c] = makeS2();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("4")) {
                                        board.add(makeS4(), c, 0);
                                        a[i][c] = makeS4();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("8")) {
                                        board.add(makeS8(), c, 0);
                                        a[i][c] = makeS8();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("16")) {
                                        board.add(makeS16(), c, 0);
                                        a[i][c] = makeS16();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("32")) {
                                        board.add(makeS32(), c, 0);
                                        a[i][c] = makeS32();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("64")) {
                                        board.add(makeS64(), c, 0);
                                        a[i][c] = makeS64();
                                        break;
                                    }
                                }
                            }

                            a[row][c] = null;
                        }

                    }
                }
            }

            // What happens if they click the up arrow key
            if (e.getCode() == DOWN) {
                for (int c = 0; c < 4; c++) {
                    for (int row = 3; row >= 0; row--) {
                        if (a[row][c] != null && row != 3) {

                            board.add(makeRectangle(), c, row);


                            for (int i = row + 1; i < 4; i++) {
                                if (a[i][c] != null) {
                                    if (a[row][c].getAccessibleText().equals(a[i][c].getAccessibleText())) {
                                        if (a[row][c].getAccessibleText().equals("2")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS4(), c, i);
                                            a[i][c] = makeS4();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("4")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS8(), c, i);
                                            a[i][c] = makeS8();
                                            break;
                                        }
                                        //do 4, 8, 16 etc.
                                    }
                                    else {
                                        if (a[row][c].getAccessibleText().equals("2")) {
                                            board.add(makeS2(), c, i-1);
                                            a[i-1][c] = makeS2();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("4")) {
                                            board.add(makeS4(), c, i-1);
                                            a[i-1][c] = makeS4();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("8")) {
                                            board.add(makeS8(), c, i-1);
                                            a[i - 1][c] = makeS8();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("16")) {
                                            board.add(makeS16(), c, i-1);
                                            a[i - 1][c] = makeS16();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("32")) {
                                            board.add(makeS32(), c, i-1);
                                            a[i - 1][c] = makeS32();
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("64")) {
                                            board.add(makeS64(), c, i-1);
                                            a[i - 1][c] = makeS64();
                                            break;
                                        }
                                    }
                                }
                                else if (i == 3 && a[i][c] == null) {
                                    if (a[row][c].getAccessibleText().equals("2")) {
                                        board.add(makeS2(), c, 3);
                                        a[i][c] = makeS2();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("4")) {
                                        board.add(makeS4(), c, 3);
                                        a[i][c] = makeS4();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("8")) {
                                        board.add(makeS8(), c, 3);
                                        a[i][c] = makeS8();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("16")) {
                                        board.add(makeS16(), c, 3);
                                        a[i][c] = makeS16();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("32")) {
                                        board.add(makeS32(), c, 3);
                                        a[i][c] = makeS32();
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("64")) {
                                        board.add(makeS64(), c, 3);
                                        a[i][c] = makeS64();
                                        break;
                                    }
                                }
                            }

                            a[row][c] = null;
                        }

                    }
                }
            }

            int twoOrFour = (int)(Math.random()*10 + 1);
            int r = (int)(Math.random()*4);
            int c = (int)(Math.random()*4);
            while (a[r][c] != null) {
                r = (int)(Math.random()*4);
                c = (int)(Math.random()*4);
            }
            if (twoOrFour - 10 == 0) {
                board.add(makeS4(), c, r);
                a[r][c] = makeS4();
            } else {
                board.add(makeS2(), c, r);
                a[r][c] = makeS2();
            }


        });

        background.setTop(topBar);
        background.setBottom(instructionPane);
        background.setLeft(side1);
        background.setRight(side2);

        background.setStyle("-fx-background-color: #F2E2D2");
        Scene scene = new Scene(background, 600, 500);
        ps.setTitle("Yale2048");
        ps.setScene(scene);
        ps.show();
        board.requestFocus();
    }

    public Rectangle makeRectangle() {
        Rectangle r = new Rectangle();
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setFill(Color.web("#cdc1b4"));
        r.setHeight(79);
        r.setWidth(79);
        return r;
    }

    public StackPane makeS2() {
        Rectangle r = new Rectangle();
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setFill(Color.web("#eee4da"));
        r.setHeight(79);
        r.setWidth(79);
        Text t = new Text("2");
        t.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t.setFill(Color.web("#776e65"));
        StackPane s = new StackPane();
        s.getChildren().addAll(r, t);
        s.setAccessibleText("2");
        return s;
    }

    public StackPane makeS4() {
        Rectangle r = new Rectangle();
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setFill(Color.web("#ede0c8"));
        r.setHeight(79);
        r.setWidth(79);
        Text t = new Text("4");
        t.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t.setFill(Color.web("#776e65"));
        StackPane s = new StackPane();
        s.getChildren().addAll(r, t);
        s.setAccessibleText("4");
        return s;
    }

    public StackPane makeS8() {
        Rectangle r = new Rectangle();
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setFill(Color.web("#f2b179"));
        r.setHeight(79);
        r.setWidth(79);
        Text t = new Text("8");
        t.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t.setFill(Color.web("#f9f6f2"));
        StackPane s = new StackPane();
        s.getChildren().addAll(r, t);
        s.setAccessibleText("8");
        return s;
    }

    public StackPane makeS16() {
        Rectangle r = new Rectangle();
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setFill(Color.web("#f59563"));
        r.setHeight(79);
        r.setWidth(79);
        Text t = new Text("16");
        t.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t.setFill(Color.web("#f9f6f2"));
        StackPane s = new StackPane();
        s.getChildren().addAll(r, t);
        s.setAccessibleText("16");
        return s;
    }

    public StackPane makeS32() {
        Rectangle r = new Rectangle();
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setFill(Color.web("#f67c5f"));
        r.setHeight(79);
        r.setWidth(79);
        Text t = new Text("32");
        t.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t.setFill(Color.web("#f9f6f2"));
        StackPane s = new StackPane();
        s.getChildren().addAll(r, t);
        s.setAccessibleText("32");
        return s;
    }

    public StackPane makeS64() {
        Rectangle r = new Rectangle();
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setFill(Color.web("#f65e3b"));
        r.setHeight(79);
        r.setWidth(79);
        Text t = new Text("64");
        t.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t.setFill(Color.web("#f9f6f2"));
        StackPane s = new StackPane();
        s.getChildren().addAll(r, t);
        s.setAccessibleText("64");
        return s;
    }

    public StackPane makeS128() {
        Rectangle r = new Rectangle();
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setFill(Color.web("#edcf72"));
        r.setHeight(79);
        r.setWidth(79);
        Text t = new Text("128");
        t.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t.setFill(Color.web("#f9f6f2"));
        StackPane s = new StackPane();
        s.getChildren().addAll(r, t);
        s.setAccessibleText("128");
        return s;
    }

    public StackPane makeS256() {
        Rectangle r = new Rectangle();
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setFill(Color.web("#edcc61"));
        r.setHeight(79);
        r.setWidth(79);
        Text t = new Text("256");
        t.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t.setFill(Color.web("#f9f6f2"));
        StackPane s = new StackPane();
        s.getChildren().addAll(r, t);
        s.setAccessibleText("256");
        return s;
    }
}
