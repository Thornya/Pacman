package Modèle;

public class FantomeV extends Fantome {
    private static FantomeV theVFantome = new FantomeV(14,14);

    public FantomeV(int x, int y) {
        super("file:src\\src\\ressources\\fantomeVR.png", x, y);
        setMapCode(6);
        setId("V");
        setxInitPos(x);
        setyInitPos(y);
    }

    public static FantomeV getInstance() {
        return theVFantome;
    }

}
