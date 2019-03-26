package vue;

import Lib.Dir;
import Modèle.*;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class grilleView {
    private static AnchorPane mainPane;
    private static BorderPane border;
    public static GridPane grid;


    public static void setupGrilleView(AnchorPane mainPane) {
        mainPane.getChildren().clear();
        Stage stage = (Stage) mainPane.getScene().getWindow();
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
        grid.setId("mainGrid");
        mainPane.setId("mainPane");
        scene.getStylesheets().add("vue/style.css");


        //Recentrer la fenêtre lors du changement de taille
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2.5);
        stage.setY(((screenBounds.getHeight() - stage.getHeight()) / 4));


        root.getChildren().add(mainPane);
        double size = ((100/5) - (40/5));
        //c buggé fo dépasé
        ColumnConstraints columnConstraint = new ColumnConstraints();
        columnConstraint.setPercentWidth(size);
        columnConstraint.setMaxWidth(Double.MAX_VALUE);
        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setPercentHeight(size);
        rowConstraint.setMaxHeight(Double.MAX_VALUE);

        //Génération de la grille 31x28
        for(int i = 0; i< MapLoader.YSIZE; i++){
            grid.addRow(i);
            grid.getRowConstraints().add(rowConstraint);
        }
        for(int i = 0; i<MapLoader.XSIZE; i++){
            grid.addColumn(i);
            grid.getColumnConstraints().add(columnConstraint);
        }



        grid.setGridLinesVisible(true);


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

        setupGrilleGraphique();

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

    private static void setupGrilleGraphique() {
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
                        setupImageView(coupPane, gommeTemp.getPath(), i, j);
                        break;
                    }
                    case 3:{
                        SuperGomme sGommeTemp = (SuperGomme)MapLoader.getEntityOrItemAt(i,j).get(0);
                        setupImageView(coupPane, sGommeTemp.getPath(), i, j);
                        break;
                    }
                    case 4:{
                        FantomeR fantTemp = (FantomeR)MapLoader.getEntityOrItemAt(i,j).get(0);
                       // setupImageView(coupPane, fantTemp.getPath(), i, j);
                        break;
                    }
                    case 5:{
                        FantomeB fantTemp = (FantomeB)MapLoader.getEntityOrItemAt(i,j).get(0);
                        //setupImageView(coupPane, fantTemp.getPath(), i, j);
                        break;
                    }
                    case 6:{
                        FantomeV fantTemp = (FantomeV)MapLoader.getEntityOrItemAt(i,j).get(0);
                        //setupImageView(coupPane, fantTemp.getPath(), i, j);
                        break;
                    }
                    case 7:{
                        FantomeO fantTemp = (FantomeO)MapLoader.getEntityOrItemAt(i,j).get(0);
                        //setupImageView(coupPane, fantTemp.getPath(), i, j);
                        break;
                    }
                    case 8:{
                        PacMan pacManTemp = (PacMan)MapLoader.getEntityOrItemAt(i,j).get(0);
                        //setupImageView(coupPane, pacManTemp.getPath(), i, j);
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

    private static void setupImageView(StackPane sp, String path, int i, int j){
        ImageView test = new ImageView(path);
        test.setFitWidth(20);
        test.setFitHeight(20);
        sp.getChildren().add(test);
        StackPane.setAlignment(sp.getChildren().get(0), Pos.CENTER);
        grid.add(sp,j,i);
    }

}