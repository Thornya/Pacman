package Modèle;

import javafx.application.Platform;
import vue.grilleView;

import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GlobalGameController extends Observable implements Runnable {
    private static int score = 0;
    private static ScheduledExecutorService executor;
    public static void gameOver() {
        executor.shutdown();
        Platform.runLater(() -> grilleView.popupGameOver(score));
        System.out.println("t0");


    }


    @Override
    public void run() {

        Runnable movement = () -> {
            PacMan.getInstance().move();
            FantomeR.getInstance().setRandomDir();
            FantomeR.getInstance().move();
            FantomeB.getInstance().setRandomDir();
            FantomeB.getInstance().move();
            FantomeV.getInstance().setRandomDir();
            FantomeV.getInstance().move();
            FantomeO.getInstance().setRandomDir();
            FantomeO.getInstance().move();
            System.out.println("Score: " + getScore());
        };
        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(movement, 1000, 500, TimeUnit.MILLISECONDS);
    }

    public static void addScore(int addedScore){
        score += addedScore;
    }
    public static int getScore(){
        return score;
    }
}
