package Modèle;

public class PacMan extends Entity {
    private static PacMan thePacMan = new PacMan(13,23);

    private PacMan(int x, int y) {
        super("file:src\\src\\ressources\\pacman.png", x, y);
        setMapCode(8);
        setId("P");
        this.setNextDir(Dir.GAUCHE);
        setxInitPos(x);
        setyInitPos(y);
    }

    public static PacMan getInstance() {
        return thePacMan;
    }

    void mangerSGomme() {
        state = State.SUPERPACMAN;
        FantomeR.getInstance().state = State.SCARED;
        FantomeB.getInstance().state = State.SCARED;
        FantomeV.getInstance().state = State.SCARED;
        FantomeO.getInstance().state = State.SCARED;
        GlobalGameController.superTime = 50;
    }

    void stopSGomme(){
        state = State.NORMAL;
        FantomeR.getInstance().state = State.NORMAL;
        FantomeB.getInstance().state = State.NORMAL;
        FantomeV.getInstance().state = State.NORMAL;
        FantomeO.getInstance().state = State.NORMAL;
    }

}
