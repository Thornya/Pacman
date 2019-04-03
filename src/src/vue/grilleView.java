package vue;

import Modèle.Dir;
import Modèle.*;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class grilleView implements Observer {
    private static GridPane grid;
    private static AnchorPane mainPane;
    private static Label superTime, score;

    static void setupGrilleView(AnchorPane mainPanePara) {
        grilleView instance = new grilleView();
        grilleView.mainPane = mainPanePara;
        mainPane.getChildren().clear();
        Stage stage = (Stage) mainPane.getScene().getWindow();
        Group root = new Group();
        Scene scene = new Scene(root);

        mainPane = new AnchorPane();
        mainPane.setMinSize(580, 750);
        mainPane.setMaxSize(580, 750);
        mainPane.setPrefSize(580, 750);
        BorderPane border = new BorderPane();
        border.setMinSize(580, 750);
        border.setMaxSize(580, 750);
        border.setPrefSize(580, 750);

        score = new Label("");
        superTime = new Label("");
        mainPane.getChildren().add(border);
        grid = new GridPane();

        MapLoader.mapSetup();
        score.setStyle("-fx-background: white");
        superTime.setStyle("-fx-background: white");
        BorderPane.setAlignment(grid, Pos.CENTER);
        BorderPane.setAlignment(score, Pos.CENTER);
        BorderPane.setAlignment(superTime, Pos.CENTER);

        border.setCenter(grid);
        border.setTop(score);
        border.setBottom(superTime);

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
        double sizeCase = (12);
        ColumnConstraints columnConstraint = new ColumnConstraints();
        columnConstraint.setPercentWidth(sizeCase);
        columnConstraint.setMaxWidth(Double.MAX_VALUE);
        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setPercentHeight(sizeCase);
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
        ggc.addObserver(instance);
        new Thread(ggc).start();

        stage.setTitle("PacManChan");
        stage.setScene(scene);
        stage.sizeToScene();
    }

    private static void graphicMove(int xStart, int yStart, int xEnd, int yEnd, Entity ent){

        StackPane pane = (StackPane) grid.getChildren().get(yStart * MapLoader.XSIZE + xStart + 1);
        StackPane pane2 = (StackPane) grid.getChildren().get(yEnd * MapLoader.XSIZE + xEnd + 1);
        ImageView iv = null;
        for (Node node :  pane.getChildren()){
            iv = (ImageView) node;
            if (iv.getStyleClass().contains(ent.getId()))
                break;
        }

        if(iv!=null){
            pane.getChildren().remove(iv);
            pane2.getChildren().add(iv);
        }
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
                        setupImageView(coupPane, fantTemp.getImgPath(), i, j);
                        break;
                    }
                    case 5:{
                        FantomeB fantTemp = (FantomeB)MapLoader.getEntityOrItemAt(i,j).get(0);
                        setupImageView(coupPane, fantTemp.getImgPath(), i, j);
                        break;
                    }
                    case 6:{
                        FantomeV fantTemp = (FantomeV)MapLoader.getEntityOrItemAt(i,j).get(0);
                        setupImageView(coupPane, fantTemp.getImgPath(), i, j);
                        break;
                    }
                    case 7:{
                        FantomeO fantTemp = (FantomeO)MapLoader.getEntityOrItemAt(i,j).get(0);
                        setupImageView(coupPane, fantTemp.getImgPath(), i, j);
                        break;
                    }
                    case 8:{
                        PacMan pacManTemp = (PacMan)MapLoader.getEntityOrItemAt(i,j).get(0);
                        setupImageView(coupPane, pacManTemp.getImgPath(), i, j);
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
                coupPane.setOnMouseClicked(event -> System.out.println("i: "+finalI+" ; j: "+finalJ));
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


    public static void popupGameOver(int score) {

        Stage stageNewWindow = new Stage();
        Stage currentStage = (Stage) mainPane.getScene().getWindow();
        Group root = new Group();
        Scene scene = new Scene(root);

        AnchorPane mainPanePopUp = new AnchorPane();
        mainPanePopUp.setMinSize(200, 150);
        mainPanePopUp.setMaxSize(200, 150);
        mainPanePopUp.setPrefSize(200, 150);
        GridPane popUpGameOver = new GridPane();
        popUpGameOver.setMinSize(200, 150);
        popUpGameOver.setMaxSize(200, 150);
        popUpGameOver.setPrefSize(200, 150);

        ColumnConstraints columnConst = new ColumnConstraints();
        columnConst.setPercentWidth(10);
        columnConst.setMaxWidth(Double.MAX_VALUE);

        ColumnConstraints columnConst2 = new ColumnConstraints();
        columnConst2.setPercentWidth(80);
        columnConst2.setMaxWidth(Double.MAX_VALUE);

        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setPercentHeight(50);
        rowConstraint.setMaxHeight(Double.MAX_VALUE);

        startView.setupMenuColonnes(popUpGameOver, columnConst, columnConst2);

        for(int i = 0; i<2; i++){
            popUpGameOver.addRow(i);
            popUpGameOver.getRowConstraints().add(rowConstraint);
        }
        mainPanePopUp.getChildren().add(popUpGameOver);
        root.getChildren().add(mainPanePopUp);

        Button okButton = new Button("Quitter");
        Label labelScore = new Label("Score: " + score+"!");

        GridPane.setHalignment(labelScore, HPos.CENTER);
        GridPane.setHalignment(okButton, HPos.CENTER);
        popUpGameOver.add(labelScore, 1 ,0 );
        popUpGameOver.add(okButton, 1 ,1 );

        okButton.setOnAction(event -> stageNewWindow.close());
        stageNewWindow.setScene(scene);
        stageNewWindow.sizeToScene();
        stageNewWindow.setTitle("Partie terminée!");
        stageNewWindow.setResizable(false);
        stageNewWindow.initModality(Modality.APPLICATION_MODAL);
        stageNewWindow.showAndWait();
        currentStage.close();


    }

    public static void setScore(int scoreToSet){
        score.setText(String.valueOf(scoreToSet));
    }

    public static void setTemps(String timeToSet){
        if (!timeToSet.equals("-1"))
            superTime.setText(timeToSet);
        else
            superTime.setText(timeToSet);
    }


    @Override
    public void update(Observable o, Object arg) {
        graphicMove(GlobalGameController.getxStart(), GlobalGameController.getyStart(), GlobalGameController.getxEnd(), GlobalGameController.getyEnd(), GlobalGameController.getEntitytoMove());
    }
}