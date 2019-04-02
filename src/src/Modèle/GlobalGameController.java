package Modèle;

import javafx.application.Platform;
import vue.grilleView;

import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GlobalGameController extends Observable implements Runnable {
    private static int score = 0;
    public static int superTime = -1;
    private static ScheduledExecutorService executor;
    public static void gameOver() {
        executor.shutdown();
        Platform.runLater(() -> grilleView.popupGameOver(score));

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
            Platform.runLater(()-> grilleView.setScore(score));
            switch(superTime){
                case -1: {
                    Platform.runLater(()->
                            grilleView.setTemps("Temps terminé ! Attention ! "));
                    break;
                }
                case 0: {
                    PacMan.getInstance().stopSGomme();
                    superTime = -1;
                    System.out.println("Temps terminé ! Attention ! ");
                    Platform.runLater(()->
                            grilleView.setTemps("Temps terminé ! Attention ! "));
                    break;
                }
                default:{
                    superTime--;
                    System.out.println("Temps restant : " + superTime);
                    Platform.runLater(()->
                            grilleView.setTemps(String.valueOf(superTime)));
                    break;
                }
            }

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
