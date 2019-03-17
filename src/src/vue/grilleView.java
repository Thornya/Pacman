package vue;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;

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

        BorderPane.setAlignment(grid, Pos.CENTER);
        border.setCenter(grid);

        grid.setPrefSize(560, 620);
        grid.setMaxSize(560, 620);
        grid.setMinSize(560, 620);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        grid.setId("mainGrid");
        mainPane.setId("mainPane");

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
            grid.addRow(i);
            grid.getRowConstraints().add(rowConstraint);
        }

        for(int i = 0; i<28; i++){
            grid.addColumn(i);
            grid.getColumnConstraints().add(columnConstraint);
        }



        grid.setGridLinesVisible(true);
        System.out.println(new File(".").getAbsolutePath());
        File f = new File("src\\src\\ressources\\52631.png");
        System.out.println(f.exists());
        if(f.exists() && !f.isDirectory()) {
            // do something
        }
        this.addPane();
        stage.setTitle("PacManChan");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args){
        Application.launch(args);
    }

    private void addPane() {
        StackPane coupPane;

        for(int i = 0; i < MapLoader.XSIZE; i++){
            for(int j = 0; j < MapLoader.YSIZE; j++){
                coupPane = new StackPane();
                switch(MapLoader.BASEMAP[i][j]){
                    case 1:{

                        break;
                    }
                    case 2:{
                        ImageView test = new ImageView("file:src\\src\\ressources\\gomme.png");
                        test.setFitWidth(20);
                        test.setFitHeight(20);
                        coupPane.getChildren().add(test);
                        StackPane.setAlignment(coupPane.getChildren().get(0), Pos.CENTER);
                        grid.add(coupPane,j,i);
                        break;
                    }
                    case 8:{
                        File f = new File("src\\src\\ressources\\pacman.png");
                        System.out.println(f.exists());
                        ImageView test = new ImageView("file:src\\src\\ressources\\pacman.png");
                        test.setFitWidth(20);
                        test.setFitHeight(20);
                        coupPane.getChildren().add(test);
                        StackPane.setAlignment(coupPane.getChildren().get(0), Pos.CENTER);
                        grid.add(coupPane,j,i);
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }

        }
    }


}