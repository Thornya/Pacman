package vue;

import Lib.Dir;
import Modèle.*;
import com.sun.jndi.toolkit.ctx.PartialCompositeContext;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.util.Map;

public class grilleView extends Application {
    private static AnchorPane mainPane;
    private static BorderPane border;
    private static GridPane grid;

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);

        mainPane = new AnchorPane();
        mainPane.setMinSize(580, 750);
        mainPane.setMaxSize(580, 750);
        mainPane.setPrefSize(580, 750);
        border = new BorderPane();
        border.setMinSize(580, 750);
        border.setMaxSize(580, 750);
        border.setPrefSize(580, 750);

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
        ColumnConstraints columnConstraint = new ColumnConstraints();
        columnConstraint.setPercentWidth(size);
        columnConstraint.setMaxWidth(Double.MAX_VALUE);
        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setPercentHeight(size);
        rowConstraint.setMaxHeight(Double.MAX_VALUE);

        for(int i = 0; i< MapLoader.YSIZE; i++){
            grid.addRow(i);
            grid.getRowConstraints().add(rowConstraint);
        }

        for(int i = 0; i<MapLoader.XSIZE; i++){
            grid.addColumn(i);
            grid.getColumnConstraints().add(columnConstraint);
        }



        grid.setGridLinesVisible(true);
        File f = new File("src\\src\\ressources\\52631.png");
        if(f.exists() && !f.isDirectory()) {
            // do something
        }

        scene.setOnKeyPressed(e -> {
            switch(e.getCode()){
                case RIGHT:
                    PacMan.setNextPacDir(Dir.DROITE);
                    break;
                case LEFT:
                    PacMan.setNextPacDir(Dir.GAUCHE);
                    break;
                case UP:
                    PacMan.setNextPacDir(Dir.HAUT);
                    break;
                case DOWN:
                    PacMan.setNextPacDir(Dir.BAS);
                    break;
            }
        });

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

        for(int i = 0; i < MapLoader.YSIZE; i++){
            for(int j = 0; j < MapLoader.XSIZE; j++){
                coupPane = new StackPane();
                switch(MapLoader.BASEMAP[i][j]){
                    case 2:{
                        Gomme gommeTemp = (Gomme)MapLoader.getEntityOrItemAt(i,j).get(0);
                        ImageView test = new ImageView(gommeTemp.getPath());
                        test.setFitWidth(20);
                        test.setFitHeight(20);
                        coupPane.getChildren().add(test);
                        StackPane.setAlignment(coupPane.getChildren().get(0), Pos.CENTER);
                        grid.add(coupPane,j,i);
                        break;
                    }
                    case 3:{
                        SuperGomme sGommeTemp = (SuperGomme)MapLoader.getEntityOrItemAt(i,j).get(0);
                        ImageView test = new ImageView(sGommeTemp.getPath());
                        test.setFitWidth(20);
                        test.setFitHeight(20);
                        coupPane.getChildren().add(test);
                        StackPane.setAlignment(coupPane.getChildren().get(0), Pos.CENTER);
                        grid.add(coupPane,j,i);
                        break;
                    }
                    case 4:{
                        FantomeR fantTemp = (FantomeR)MapLoader.getEntityOrItemAt(i,j).get(0);
                        ImageView test = new ImageView(fantTemp.getImgPaths().get(0));
                        test.setFitWidth(20);
                        test.setFitHeight(20);
                        coupPane.getChildren().add(test);
                        StackPane.setAlignment(coupPane.getChildren().get(0), Pos.CENTER);
                        grid.add(coupPane,j,i);
                        break;
                    }
                    case 5:{
                        FantomeB fantTemp = (FantomeB)MapLoader.getEntityOrItemAt(i,j).get(0);
                        ImageView test = new ImageView(fantTemp.getImgPaths().get(0));
                        test.setFitWidth(20);
                        test.setFitHeight(20);
                        coupPane.getChildren().add(test);
                        StackPane.setAlignment(coupPane.getChildren().get(0), Pos.CENTER);
                        grid.add(coupPane,j,i);
                        break;
                    }
                    case 6:{
                        FantomeV fantTemp = (FantomeV)MapLoader.getEntityOrItemAt(i,j).get(0);
                        ImageView test = new ImageView(fantTemp.getImgPaths().get(0));
                        test.setFitWidth(20);
                        test.setFitHeight(20);
                        coupPane.getChildren().add(test);
                        StackPane.setAlignment(coupPane.getChildren().get(0), Pos.CENTER);
                        grid.add(coupPane,j,i);
                        break;
                    }
                    case 7:{
                        FantomeO fantTemp = (FantomeO)MapLoader.getEntityOrItemAt(i,j).get(0);
                        ImageView test = new ImageView(fantTemp.getImgPaths().get(0));
                        test.setFitWidth(20);
                        test.setFitHeight(20);
                        coupPane.getChildren().add(test);
                        StackPane.setAlignment(coupPane.getChildren().get(0), Pos.CENTER);
                        grid.add(coupPane,j,i);
                        break;
                    }
                    case 8:{
                        PacMan pacManTemp = (PacMan)MapLoader.getEntityOrItemAt(i,j).get(0);
                        ImageView test = new ImageView(pacManTemp.getImgPaths().get(0));
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