package Modèle;


public class FantomeR extends Fantome {
    private static FantomeR theRFantome = new FantomeR(12,14);

    private FantomeR(int x, int y) {
        super("file:src\\src\\ressources\\fantomeRR.png", x, y);
        setMapCode(4);
        setId("R");
    }


    public static FantomeR getInstance() {
        return theRFantome;
    }

}
