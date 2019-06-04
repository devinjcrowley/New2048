import javafx.application.Application;
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

import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyEvent.KEY_PRESSED;


public class Emma2048 extends Application {
    public void start (Stage ps) {
        StackPane[][] a = {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
        };

        BorderPane background = new BorderPane();

        BorderPane gameBorder = new BorderPane();
        gameBorder.setStyle("-fx-background-color: #bbada0");

        GridPane board = new GridPane();
        board.setStyle("-fx-background-color: #bbada0");

        gameBorder.setCenter(board);


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
        side1.setPrefWidth(134);
        side2.setPrefWidth(134);

        // Center

        board.setHgap(5);
        board.setVgap(5);



        background.setCenter(board);

        for (int row=0; row<4; row++) {
            for (int column = 0; column < 4; column++) {
                Pane r = new Pane();
                r.setStyle(("-fx-background-color:#cdc1b4"));
                r.setPrefHeight(79);
                r.setPrefWidth(79);
                //stuff to change color based on the number hahahahhhahahhhahha
                r.setAccessibleText("");

                board.add(r, row, column);
            }
        }

//        board.setOnKeyPressed(e->{
//            if(e.getCode() == KeyCode.KP_LEFT) {
//
//            }
//            if(e.getCode() == KeyCode.KP_RIGHT){
//
//            }
//            if(e.getCode() == KeyCode.KP_UP){
//
//            }
//            if(e.getCode() == KeyCode.KP_DOWN){
//
//            }
//                });

        //this is a variable to check whether or not the board is full, and below is a VERY long if loop for it
        int full = 0;

        if(a[0][0]!=null && a[0][1]!=null && a[0][2]!=null && a[0][3]!=null && a[0][4]!=null &&
                a[1][0]!=null && a[1][1]!=null && a[1][2]!=null && a[1][3]!=null && a[1][4]!=null
                && a[2][0]!=null && a[2][1]!=null && a[2][2]!=null && a[2][3]!=null
                && a[2][4]!=null && a[3][0]!=null && a[3][1]!=null && a[3][2]!=null && a[3][3]!=null
                && a[3][4]!=null){
            full = 1;
        }
         for (int j = 0; j < 2; j++) {
              Rectangle r2 = new Rectangle();
              r2.setArcHeight(10);
              r2.setArcWidth(10);
              r2.setFill(Color.web("#eee4da"));
              r2.setHeight(79);
              r2.setWidth(79);
              int num = (int)(Math.random()*2);
              StackPane s2 = new StackPane();
              if(num>=1){
                  Text t4 = new Text("4");
                  t4.setFont(Font.font("Calibri", FontWeight.BOLD, 40));
                  r2.setAccessibleText("4");
                  s2.getChildren().addAll(r2, t4);
              }
              else if(num>=0){
                  Text t2 = new Text("2");
                  t2.setFont(Font.font("Calibri", FontWeight.BOLD, 40));
                  r2.setAccessibleText("2");
                  s2.getChildren().addAll(r2, t2);
              }
             if (r2.getAccessibleText().equals("")) {
                 r2.setFill(Color.web("#eee4da"));
             } else if (r2.getAccessibleText().equals("2")) {
                 r2.setFill(Color.web("#d6d4d3"));
             } else if (r2.getAccessibleText().equals("4")) {
                 r2.setFill(Color.web("#e6ceb3"));
             } else if (r2.getAccessibleText().equals("8")) {
                 r2.setFill(Color.web("#e8a67e"));
             } else if (r2.getAccessibleText().equals("16")) {
                 r2.setFill(Color.web("#e88a4b"));
             } else if (r2.getAccessibleText().equals("32")) {
                 r2.setFill(Color.web("#ff7a65"));
             } else if (r2.getAccessibleText().equals("64")) {
                 r2.setFill(Color.web("#eb4e2f"));
             } else if (r2.getAccessibleText().equals("128")) {
                 r2.setFill(Color.web("#f0df73"));
             } else if (r2.getAccessibleText().equals("256")) {
                 r2.setFill(Color.web("#f0df48"));
             } else if (r2.getAccessibleText().equals("512")) {
                 r2.setFill(Color.web("#f2cf09"));
             } else if (r2.getAccessibleText().equals("1024")) {
                 r2.setFill(Color.web("#ffee6b"));
             } else if (r2.getAccessibleText().equals("2048")) {
                 r2.setFill(Color.web("#fff587"));
             }

             int row = (int) (Math.random() * 4);
              int column = (int) (int) (Math.random() * 4);
              board.add(s2, row, column);
         }

        board.setOnKeyPressed(e -> {

            if (e.getCode() == RIGHT) {
                for (int row = 0; row < 4; row++) {
                    for (int c = 0; c < 4; c++) {
                        if (a[row][c] != null) {
                            board.getChildren().remove(a[row][c]);

                            if (a[row][3] != null) {
                                if (a[row][3] == a[row][c] && a[row][3] == makeS2()) {
                                    StackPane s4 = makeS4();
                                    board.add(s4, 3, row);
                                }
                            }
                            else {
                                board.add(a[row][c], 3, row);
                                a[row][3] = a[row][c];
                            }
                        }
                    }

                }
            }
            if (e.getCode() == LEFT) {
                for (int row = 0; row < 4; row++) {
                    for (int c = 0; c < 4; c++) {
                        if (a[row][c] != null) {
                            board.getChildren().remove(a[row][c]);
                            board.add(a[row][c], 0, row);
                        }
                    }

                }
            }

        });

        background.setTop(topBar);
        background.setBottom(i);
        background.setLeft(side1);
        background.setRight(side2);


        background.setStyle("-fx-background-color: #F2E2D2");
        Scene scene = new Scene(background, 600, 500);
        ps.setTitle("Emma2048");
        ps.setScene(scene);
        ps.show();
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
}
