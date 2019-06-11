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



public class Yale2048 extends Application {
    private StackPane[][] a = {
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
    };
    private GridPane board = new GridPane();
    public void start (Stage ps) {

        BorderPane background = new BorderPane();

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
            board.add(makeS2(), 0, 3);
            board.add(makeS2(), 0, 2);
            board.add(makeS2(), 0, 1);
            board.add(makeS2(), 0, 0);
            a[0][0] = makeS2();
            a[1][0] = makeS2();
            a[2][0] = makeS2();
            a[3][0] = makeS2();


        }

        board.setOnKeyPressed(e -> {

            // What happens if they click the down arrow key
            if (e.getCode() == DOWN) {
                int ifMoved = 0;
                boolean ifMerged = false;
                boolean fourInARow = false;
                for (int c = 0; c < 4; c++) {
                    if (a[3][c] != null && a[2][c] != null && a[1][c] != null && a[0][c] != null && a[3][c].getAccessibleText().equals(a[2][c].getAccessibleText()) && a[2][c].getAccessibleText().equals(a[1][c].getAccessibleText()) && a[1][c].getAccessibleText().equals(a[0][c].getAccessibleText())) {
                        fourInARow = true;
                    }
                    for (int row = 3; row >= 0; row--) {
                        if (a[row][c] != null && row != 3) {


                            for (int i = row + 1; i < 4; i++) {
                                if (a[i][c] != null) {
                                    if (fourInARow) {
                                        board.add(makeRectangle(), c, row);
                                        if (a[row][c].getAccessibleText().equals("2")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS4(), c, i);
                                            a[i][c] = makeS4();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("4")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS8(), c, i);
                                            a[i][c] = makeS8();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("8")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS16(), c, i);
                                            a[i][c] = makeS16();
                                            a[row][c] = null;
                                            break;
                                        }

                                        //do 4, 8, 16 etc.

                                    }
                                    else if ((a[row][c].getAccessibleText().equals(a[i][c].getAccessibleText()) && !ifMerged)) {
                                        ifMoved++;
                                        ifMerged = true;
                                        board.add(makeRectangle(), c, row);
                                        if (a[row][c].getAccessibleText().equals("2")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS4(), c, i);
                                            a[i][c] = makeS4();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("4")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS8(), c, i);
                                            a[i][c] = makeS8();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("8")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS16(), c, i);
                                            a[i][c] = makeS16();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("16")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS32(), c, i);
                                            a[i][c] = makeS32();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("32")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS64(), c, i);
                                            a[i][c] = makeS64();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("64")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS128(), c, i);
                                            a[i][c] = makeS128();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("128")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS256(), c, i);
                                            a[i][c] = makeS256();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("256")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS512(), c, i);
                                            a[i][c] = makeS512();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("512")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS1024(), c, i);
                                            a[i][c] = makeS1024();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("1024")) {
                                            board.add(makeRectangle(), c, i);
                                            board.add(makeS2048(), c, i);
                                            a[i][c] = makeS2048();
                                            a[row][c] = null;
                                            break;
                                        }

                                        //do 4, 8, 16 etc.
                                    }

                                    else if ((row != i - 1 && !a[row][c].getAccessibleText().equals(a[i][c].getAccessibleText())) || (ifMerged && row != i - 1)) {
                                        ifMoved++;
                                        board.add(makeRectangle(), c, row);
                                        if (a[row][c].getAccessibleText().equals("2")) {
                                            board.add(makeS2(), c, i-1);
                                            a[i-1][c] = makeS2();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("4")) {
                                            board.add(makeS4(), c, i-1);
                                            a[i-1][c] = makeS4();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("8")) {
                                            board.add(makeS8(), c, i-1);
                                            a[i-1][c] = makeS8();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("16")) {
                                            board.add(makeS16(), c, i-1);
                                            a[i-1][c] = makeS16();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("32")) {
                                            board.add(makeS32(), c, i-1);
                                            a[i-1][c] = makeS32();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("64")) {
                                            board.add(makeS64(), c, i-1);
                                            a[i-1][c] = makeS64();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("128")) {
                                            board.add(makeS128(), c, i-1);
                                            a[i-1][c] = makeS128();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("256")) {
                                            board.add(makeS256(), c, i-1);
                                            a[i-1][c] = makeS256();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("512")) {
                                            board.add(makeS512(), c, i-1);
                                            a[i-1][c] = makeS512();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("1024")) {
                                            board.add(makeS1024(), c, i-1);
                                            a[i-1][c] = makeS1024();
                                            a[row][c] = null;
                                            break;
                                        }
                                        else if (a[row][c].getAccessibleText().equals("2048")) {
                                            board.add(makeS2048(), c, i-1);
                                            a[i-1][c] = makeS2048();
                                            a[row][c] = null;
                                            break;
                                        }

                                    }
                                    else if (row == i - 1 && !a[row][c].getAccessibleText().equals(a[i][c].getAccessibleText())) {
                                        break;
                                    }
                                }
                                else if (i == 3 && a[i][c] == null) {
                                    ifMoved++;
                                    board.add(makeRectangle(), c, row);
                                    if (a[row][c].getAccessibleText().equals("2")) {
                                        board.add(makeS2(), c, 3);
                                        a[i][c] = makeS2();
                                        a[row][c] = null;
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("4")) {
                                        board.add(makeS4(), c, 3);
                                        a[i][c] = makeS4();
                                        a[row][c] = null;
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("8")) {
                                        board.add(makeS8(), c, 3);
                                        a[i][c] = makeS8();
                                        a[row][c] = null;
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("16")) {
                                        board.add(makeS16(), c, 3);
                                        a[i][c] = makeS16();
                                        a[row][c] = null;
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("32")) {
                                        board.add(makeS32(), c, 3);
                                        a[i][c] = makeS32();
                                        a[row][c] = null;
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("64")) {
                                        board.add(makeS64(), c, 3);
                                        a[i][c] = makeS64();
                                        a[row][c] = null;
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("128")) {
                                        board.add(makeS128(), c, 3);
                                        a[i][c] = makeS128();
                                        a[row][c] = null;
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("256")) {
                                        board.add(makeS256(), c, 3);
                                        a[i][c] = makeS256();
                                        a[row][c] = null;
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("512")) {
                                        board.add(makeS512(), c, 3);
                                        a[i][c] = makeS512();
                                        a[row][c] = null;
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("1024")) {
                                        board.add(makeS1024(), c, 3);
                                        a[i][c] = makeS1024();
                                        a[row][c] = null;
                                        break;
                                    }
                                    else if (a[row][c].getAccessibleText().equals("2048")) {
                                        board.add(makeS2048(), c, 3);
                                        a[i][c] = makeS2048();
                                        a[row][c] = null;
                                        break;
                                    }

                                }
                            }
                            //a[row][c] = null;

                        }

                    }
                    ifMerged = false;
                }
                /*if (ifMoved > 0) {
                    makeNew();
                }
                */
            }

            for (int row = 0; row < 4; row++) {
                for (int column = 0; column < 4; column++) {
                    if (a[row][column] == null) {
                        board.add(makeRectangle(), column, row);
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

    public StackPane makeS512() {
        Rectangle r = new Rectangle();
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setFill(Color.web("#edc850"));//Change the color to the actual color ya know?
        r.setHeight(79);
        r.setWidth(79);
        Text t = new Text("512");
        t.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t.setFill(Color.web("#f9f6f2"));
        StackPane s = new StackPane();
        s.getChildren().addAll(r, t);
        s.setAccessibleText("512");
        return s;
    }

    public StackPane makeS1024() {
        Rectangle r = new Rectangle();
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setFill(Color.web("#edc53f"));//Change the color to the actual color ya know?
        r.setHeight(79);
        r.setWidth(79);
        Text t = new Text("1024");
        t.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t.setFill(Color.web("#f9f6f2"));
        StackPane s = new StackPane();
        s.getChildren().addAll(r, t);
        s.setAccessibleText("1024");
        return s;
    }

    public StackPane makeS2048() {
        Rectangle r = new Rectangle();
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setFill(Color.web("#f65e3b"));//Change the color to the actual color ya know?
        r.setHeight(79);
        r.setWidth(79);
        Text t = new Text("2048");
        t.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t.setFill(Color.web("#f9f6f2"));
        StackPane s = new StackPane();
        s.getChildren().addAll(r, t);
        s.setAccessibleText("2048");
        return s;
    }

    public StackPane makeS4096() {
        Rectangle r = new Rectangle();
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setFill(Color.web("#f65e3b"));//Change the color to the actual color ya know?
        r.setHeight(79);
        r.setWidth(79);
        Text t = new Text("4096");
        t.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t.setFill(Color.web("#f9f6f2"));
        StackPane s = new StackPane();
        s.getChildren().addAll(r, t);
        s.setAccessibleText("4096");
        return s;
    }

    public StackPane makeS8192() {
        Rectangle r = new Rectangle();
        r.setArcHeight(10);
        r.setArcWidth(10);
        r.setFill(Color.web("#f65e3b"));//Change the color to the actual color ya know?
        r.setHeight(79);
        r.setWidth(79);
        Text t = new Text("8192");
        t.setFont(Font.font ("Calibri", FontWeight.BOLD, 40));
        t.setFill(Color.web("#f9f6f2"));
        StackPane s = new StackPane();
        s.getChildren().addAll(r, t);
        s.setAccessibleText("8192");
        return s;
    }

    public void makeNew() {
        int twoOrFour = (int)(Math.random()*10 + 1);
        int r1 = (int)(Math.random()*4);
        int c1 = (int)(Math.random()*4);
        while (a[r1][c1] != null) {
            r1 = (int)(Math.random()*4);
            c1 = (int)(Math.random()*4);
        }
        if (twoOrFour - 10 == 0) {
            board.add(makeS4(), c1, r1);
            a[r1][c1] = makeS4();
        }
        else {
            board.add(makeS2(), c1, r1);
            a[r1][c1] = makeS2();
        }
    }
}
