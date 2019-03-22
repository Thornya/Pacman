import Jeu.Jeu;
import Modèle.GlobalGameController;

public class main {

    public static void main(String args[]){
        GlobalGameController ggc = new GlobalGameController();


        new Thread(ggc).start();
    }
}
