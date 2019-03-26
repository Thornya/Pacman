package Modèle;

import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GlobalGameController extends Observable implements Runnable {
    private static int score = 0;
    public static void gameOver() {
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
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(movement, 1000, 100, TimeUnit.MILLISECONDS);
    }

    public static void addScore(int addedScore){
        score += addedScore;
    }
    public static int getScore(){
        return score;
    }
}
