package Modèle;

import javafx.stage.Stage;
import vue.grilleView;

import java.util.Observable;

public class GlobalGameController extends Observable implements Runnable {
    //jpense faut

    @Override
    public void run() {

        MapLoader.mapSetup();

        String[] args = new String[2];
        grilleView gv = new grilleView();
        gv.main(args);
    }
}
