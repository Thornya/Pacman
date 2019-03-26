package Modèle;

import Lib.Dir;

public class PacMan extends Entities{
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

    @Override
    public void afficher() {

    }

    public void mangerSGomme(){
<<<<<<< HEAD
        this.state = State.SUPERPACMAN;
=======
        state = State.SUPERPACMAN;
>>>>>>> master
        FantomeR.getInstance().state = State.SCARED;
        FantomeB.getInstance().state = State.SCARED;
        FantomeV.getInstance().state = State.SCARED;
        FantomeO.getInstance().state = State.SCARED;
<<<<<<< HEAD
    }
=======

    }

>>>>>>> master

}
