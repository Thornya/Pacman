import Jeu.Jeu;

import java.util.*;

public class Console implements Observer {

    private Jeu jeu;

    public Console(Jeu jeu) {
        this.jeu = jeu;
        jeu.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        boolean[][] tab = jeu.getState();
        for(int i=0;i<tab.length;i++){
            boolean temp[] = tab[i];
            for(int j=0;j<temp.length;j++){
                System.out.println(i +" ---- " + j);
            }
        }
    }
}
