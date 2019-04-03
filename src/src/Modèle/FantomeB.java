package Modèle;

public class FantomeB extends Fantome{
    private static FantomeB theBFantome = new FantomeB(13,14);

    FantomeB(int x, int y) {
        super("file:src\\src\\ressources\\fantomeBR.png", x, y);
        setMapCode(5);
        setId("B");
        setxInitPos(x);
        setyInitPos(y);
    }

    static FantomeB getInstance() {
        return theBFantome;
    }


}
