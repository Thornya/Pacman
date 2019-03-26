package vue;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class startView extends Application {
    private static AnchorPane mainPane;

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);

        mainPane = new AnchorPane();
        mainPane.setMinSize(300, 400);
        mainPane.setMaxSize(300, 400);
        mainPane.setPrefSize(300, 400);
        GridPane menu = new GridPane();
        menu.setMinSize(300, 400);
        menu.setMaxSize(300, 400);
        menu.setPrefSize(300, 400);

        ColumnConstraints columnConstraint = new ColumnConstraints();
        columnConstraint.setPercentWidth(20);
        columnConstraint.setMaxWidth(Double.MAX_VALUE);

        ColumnConstraints columnConstraint2 = new ColumnConstraints();
        columnConstraint2.setPercentWidth(60);
        columnConstraint2.setMaxWidth(Double.MAX_VALUE);

        RowConstraints rowConstraint = new RowConstraints();
        rowConstraint.setPercentHeight(20);
        rowConstraint.setMaxHeight(Double.MAX_VALUE);

        menu.addColumn(0);
        menu.getColumnConstraints().add(columnConstraint);
        menu.addColumn(1);
        menu.getColumnConstraints().add(columnConstraint2);
        menu.addColumn(2);
        menu.getColumnConstraints().add(columnConstraint);

        for(int i = 0; i<6; i++){
            menu.addRow(i);
            menu.getRowConstraints().add(rowConstraint);
        }

        System.out.println(menu);

       // menu.setGridLinesVisible(true);
        mainPane.getChildren().add(menu);

        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        root.getChildren().add(mainPane);

        Button playButton = new Button("Jouer");
        Button quitButton = new Button("Quitter");
        Label labelTitle = new Label("PacManChan");

        labelTitle.setId("labelTitle");
        playButton.setId("buttonPlay");
        quitButton.setId("buttonQuit");
        GridPane.setHalignment(playButton, HPos.CENTER);
        GridPane.setHalignment(quitButton, HPos.CENTER);
        menu.add(labelTitle, 1 ,1 );
        menu.add(playButton, 1 ,3 );
        menu.add(quitButton, 1 ,4 );

        playButton.setOnAction(event -> {
            grilleView.setupGrilleView(mainPane);
        });
        quitButton.setOnAction(event -> {
            stage.close();
        });
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
