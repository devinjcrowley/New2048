import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Test extends Application{
    public void start (Stage ps) {
        GridPane board = new GridPane();

        board.setVgap(5);
        board.setHgap(5);

        for (int row=0; row<4; row++) {
            for (int column = 0; column < 4; column++) {
                Rectangle r = new Rectangle();
                r.setFill(Color.web("#cdc1b4"));
                r.setHeight(50);
                r.setWidth(50);
                board.add(r, column, row);
            }
        }

        Scene scene = new Scene(board, 600, 500);
        ps.setTitle("Yale2048");
        ps.setScene(scene);
        ps.show();
    }
}
