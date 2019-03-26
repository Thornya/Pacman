package vue;

import Lib.Dir;
import Modèle.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;

public class grilleView extends Application {
    private static AnchorPane mainPane;
    private static BorderPane border;
    public static GridPane grid;

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

        MapLoader.mapSetup();

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
                    PacMan.getInstance().setNextDir(Dir.DROITE);
                    break;
                case LEFT:
                    PacMan.getInstance().setNextDir(Dir.GAUCHE);
                    break;
                case UP:
                    PacMan.getInstance().setNextDir(Dir.HAUT);
                    break;
                case DOWN:
                    PacMan.getInstance().setNextDir(Dir.BAS);
                    break;
            }
        });

        this.addPane();

        GlobalGameController ggc = new GlobalGameController();
        new Thread(ggc).start();
        stage.setTitle("PacManChan");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);


        stage.show();
    }

    public static void graphicMove(int xStart, int yStart, int xEnd, int yEnd, Entities ent){

        StackPane pane = (StackPane) grid.getChildren().get(yStart * MapLoader.XSIZE + xStart + 1);
        StackPane pane2 = (StackPane) grid.getChildren().get(yEnd * MapLoader.XSIZE + xEnd + 1);
        ImageView iv = null;
        for (Node node :  pane.getChildren()){
            iv = (ImageView) node;
            if (iv.getStyleClass().contains(ent.getId()))
                break;
        }

        pane.getChildren().remove(iv);
        pane2.getChildren().add(iv);
    }

    public static void miam(int x, int y){
        StackPane pane = (StackPane) grid.getChildren().get(y * MapLoader.XSIZE + x + 1);
        pane.getChildren().remove(0);

    }

    private void addPane() {
        StackPane coupPane;

        for(int i = 0; i < MapLoader.YSIZE; i++){
            for(int j = 0; j < MapLoader.XSIZE; j++){
                coupPane = new StackPane();
                switch(MapLoader.BASEMAP[i][j]){
                    case 0:{
                        grid.add(coupPane,j,i);
                        break;
                    }
                    case 1:{
                        grid.add(coupPane,j,i);
                        break;
                    }
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
                        ImageView test = new ImageView(fantTemp.getImgPaths());
                        test.getStyleClass().add(fantTemp.getId());
                        test.setId(fantTemp.getId());
                        test.setFitWidth(20);
                        test.setFitHeight(20);
                        coupPane.getChildren().add(test);
                        StackPane.setAlignment(coupPane.getChildren().get(0), Pos.CENTER);
                        grid.add(coupPane,j,i);
                        break;
                    }
                    case 5:{
                        FantomeB fantTemp = (FantomeB)MapLoader.getEntityOrItemAt(i,j).get(0);
                        ImageView test = new ImageView(fantTemp.getImgPaths());
                        test.getStyleClass().add(fantTemp.getId());

                        test.setId(fantTemp.getId());
                        test.setFitWidth(20);
                        test.setFitHeight(20);
                        coupPane.getChildren().add(test);
                        StackPane.setAlignment(coupPane.getChildren().get(0), Pos.CENTER);
                        grid.add(coupPane,j,i);
                        break;
                    }
                    case 6:{
                        FantomeV fantTemp = (FantomeV)MapLoader.getEntityOrItemAt(i,j).get(0);
                        ImageView test = new ImageView(fantTemp.getImgPaths());
                        test.getStyleClass().add(fantTemp.getId());

                        test.setId(fantTemp.getId());
                        test.setFitWidth(20);
                        test.setFitHeight(20);
                        coupPane.getChildren().add(test);
                        StackPane.setAlignment(coupPane.getChildren().get(0), Pos.CENTER);
                        grid.add(coupPane,j,i);
                        break;
                    }
                    case 7:{
                        FantomeO fantTemp = (FantomeO)MapLoader.getEntityOrItemAt(i,j).get(0);
                        ImageView test = new ImageView(fantTemp.getImgPaths());
                        test.getStyleClass().add(fantTemp.getId());

                        test.setId(fantTemp.getId());
                        test.setFitWidth(20);
                        test.setFitHeight(20);
                        coupPane.getChildren().add(test);
                        StackPane.setAlignment(coupPane.getChildren().get(0), Pos.CENTER);
                        grid.add(coupPane,j,i);
                        break;
                    }
                    case 8:{
                        PacMan pacManTemp = (PacMan)MapLoader.getEntityOrItemAt(i,j).get(0);
                        ImageView test = new ImageView(pacManTemp.getImgPaths());
                        test.getStyleClass().add(pacManTemp.getId());
                        test.setId(pacManTemp.getId());
                        test.setFitWidth(20);
                        test.setFitHeight(20);
                        coupPane.getChildren().add(test);
                        StackPane.setAlignment(coupPane.getChildren().get(0), Pos.CENTER);
                        grid.add(coupPane,j,i);
                        break;
                    }
                    case 9:{
                        grid.add(coupPane,j,i);
                        break;
                    }
                    default:{
                        break;
                    }
                }
                int finalI = i;
                int finalJ = j;
                coupPane.setOnMouseClicked(event -> {
                    System.out.println("i: "+finalI+" ; j: "+finalJ);

                });
            }
        }
    }

    public static void main(String[] args){
        Application.launch(args);
    }

}