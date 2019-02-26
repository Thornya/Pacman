package Jeu;

import java.util.Observable;


public class Jeu extends Observable implements Runnable{


    public Jeu() {
    }


    @Override
    public void run(){
        while(true){
            setChanged();
            notifyObservers();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean[][] getState() {
        return new boolean[1][1];
    }
}
