package modèle;

public class FantomeO extends Fantome {
    private static FantomeO theOFantome = new FantomeO(15,14);

    FantomeO(int x, int y) {
        super("file:src\\src\\ressources\\fantomeOR.png", x, y);
        setMapCode(7);
        setId("O");
        setxInitPos(x);
        setyInitPos(y);
    }

    static FantomeO getInstance() {
        return theOFantome;
    }
}
