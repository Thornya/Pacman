package Modèle;

import Lib.Dir;

public class PacMan extends Entities{
    private static PacMan thePacMan = new PacMan(13,23);

    private PacMan(int x, int y) {
        super("file:src\\src\\ressources\\pacman.png", x, y);
        setMapCode(8);
        setId("P");
        this.setNextDir(Dir.GAUCHE);
    }

    public static PacMan getInstance() {
        return thePacMan;
    }

    @Override
    public void afficher() {

    }


}
