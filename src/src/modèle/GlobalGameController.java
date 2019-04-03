package modèle;

import javafx.application.Platform;
import vue.grilleView;

import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GlobalGameController extends Observable implements Runnable {
    private static GlobalGameController instance;
    private static int score = 0;

    //Temps restant de la SuperPacGomme
    static int superTime = -1;

    private static ScheduledExecutorService executor;

    //Attributs utilisés par la classe graphique
    private static int xStart, xEnd, yStart, yEnd;
    private static Entity entitytoMove;

    //Méthode pour fin de partie
    //Fonctionne pour victoire et défaite
    static void gameOver() {
        executor.shutdown();
        Platform.runLater(() -> grilleView.popupGameOver(score));
    }


    @Override
    public void run() {
        instance = this;
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
                    Platform.runLater(()->
                            grilleView.setTemps("Temps terminé ! Attention ! "));
                    break;
                }
                default:{
                    superTime--;
                    Platform.runLater(()->
                            grilleView.setTemps(String.valueOf(superTime)));
                    break;
                }
            }
        };
        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(movement, 1000, 100, TimeUnit.MILLISECONDS);
    }

    //Cette méthode met à jour les attributs puis notify l'observer
    static void setupGraphMove(int xStartToMove, int yStartToMove, int xEndToMove, int yEndToMove, Entity ent){
        xStart = xStartToMove;
        yStart = yStartToMove;
        xEnd = xEndToMove;
        yEnd = yEndToMove;
        entitytoMove = ent;
        getInstance().setChanged();
        getInstance().notifyObservers();
    }

    //Getters and Setters
    static void addScore(int addedScore){
        score += addedScore;
    }
    public static int getxStart() {
        return xStart;
    }
    public static int getxEnd() {
        return xEnd;
    }
    public static int getyStart() {
        return yStart;
    }
    public static int getyEnd() {
        return yEnd;
    }
    public static Entity getEntitytoMove() {
        return entitytoMove;
    }
    private static GlobalGameController getInstance(){
        return instance;
    }
}
