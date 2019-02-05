import Jeu.Jeu;
import Lib.GrilleK;

public class main {

    public static void main(String args[]){
        Jeu jeu = new Jeu();
        Console c = new Console(jeu);
        GrilleK.setTab();

        new Thread(jeu).start();
    }
}
