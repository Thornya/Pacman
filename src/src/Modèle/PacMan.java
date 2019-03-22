package Modèle;

import Lib.Dir;

public class PacMan extends Entities{

    private static Dir nextPacDir;
    public PacMan(int x, int y) {
        super("file:src\\src\\ressources\\pacman.png", x, y);
    }


    public static Dir getNextPacDir() {
        return nextPacDir;
    }

    public static void setNextPacDir(Dir nextPacDir) {
        PacMan.nextPacDir = nextPacDir;
    }
}
