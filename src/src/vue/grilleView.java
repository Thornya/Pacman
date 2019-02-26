package vue;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class grilleView extends Application {
    private static AnchorPane mainPane;
    private static BorderPane border;
    private static GridPane grid;

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);

        mainPane = new AnchorPane();
        mainPane.setMinSize(750, 750);
        mainPane.setMaxSize(750, 750);
        mainPane.setPrefSize(750, 750);
        border = new BorderPane();
        border.setMinSize(750, 750);
        border.setMaxSize(750, 750);
        border.setPrefSize(750, 750);

        mainPane.getChildren().add(border);
        grid = new GridPane();

        //BorderPane.setAlignment(grid, Pos.CENTER);
        //Button bite = new Button("bite");
        //BorderPane.setAlignment(bite, Pos.CENTER);
        //Button bite2 = new Button("bite2");
        //BorderPane.setAlignment(bite2, Pos.CENTER);

        BorderPane.setAlignment(grid, Pos.CENTER);

       // border.setLeft(bite);
        //border.setTop(bite2);
        border.setCenter(grid);
        grid.setStyle("-fx-background-color: blue");

        grid.setPrefSize(600, 600);
        grid.setMaxSize(600, 600);
        grid.setMinSize(600, 600);


        root.getChildren().add(mainPane);
        double size = ((100/5) - (40/5));
        //c buggé fo dépasé
        System.out.println(size);
        ColumnConstraints columnConstraint = new ColumnConstraints();
        columnConstraint.setPercentWidth(size);
        columnConstraint.setMaxWidth(Double.MAX_VALUE);
        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setPercentHeight(size);
        rowConstraint.setMaxHeight(Double.MAX_VALUE);

        for(int i = 0; i<31; i++){
            grid.addColumn(i);
            grid.getRowConstraints().add(rowConstraint);
            grid.addRow(i);
            grid.getColumnConstraints().add(columnConstraint);
        }
        grid.setGridLinesVisible(true);
        grid.setStyle(" -fx-background-image:url('../ressources/52631.png')");
        grid.setStyle("-fx-background-color: blue");

        stage.setTitle("PacManChan");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args){

        Application.launch(args);

    }
}
