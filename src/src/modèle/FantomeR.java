package modèle;


public class FantomeR extends Fantome {
    //Fantome rouge
    private static FantomeR theRFantome = new FantomeR(12,14);

    private FantomeR(int x, int y) {
        super("file:src\\src\\ressources\\fantomeRR.png", x, y);
        setMapCode(4);
        setId("R");
        setxInitPos(x);
        setyInitPos(y);
    }


    static FantomeR getInstance() {
        return theRFantome;
    }

}
