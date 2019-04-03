package modèle;

public class FantomeV extends Fantome {
    //Fantome Vert
    private static FantomeV theVFantome = new FantomeV(14,14);

    FantomeV(int x, int y) {
        super("file:src\\src\\ressources\\fantomeVR.png", x, y);
        setMapCode(6);
        setId("V");
        setxInitPos(x);
        setyInitPos(y);
    }

    static FantomeV getInstance() {
        return theVFantome;
    }

}
