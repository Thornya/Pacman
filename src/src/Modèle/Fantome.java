package Modèle;

import Lib.Dir;

public class Fantome extends Entities implements Affichable{

    private Dir nextDir;
    public Fantome(String imgPath, int xPos, int yPos) {
        super(imgPath, xPos, yPos);
    }
}
