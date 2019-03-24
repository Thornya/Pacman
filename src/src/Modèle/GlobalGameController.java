package Modèle;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import vue.grilleView;

import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GlobalGameController extends Observable implements Runnable {
    private static int score = 0;
    public static void gameOver() {
    }
    //jpense faut


    @Override
    public void run() {

        MapLoader.mapSetup();
/*
        String[] args = new String[2];
        grilleView gv = new grilleView();
        gv.main(args);
*/

        Runnable movement = () -> {
            PacMan.getInstance().move();


        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(movement, 0, 1000, TimeUnit.MILLISECONDS);
    }

    public static void addScore(int addedScore){
        score += addedScore;
    }
}
