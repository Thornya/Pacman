package Modèle;

import Lib.Dir;

public class PacMan extends Entities{
    private static PacMan thePacMan = new PacMan(13,23);

    private static Dir nextPacDir = Dir.GAUCHE;
    private PacMan(int x, int y) {
        super("file:src\\src\\ressources\\pacman.png", x, y);
        setMapCode(8);
    }

    public static PacMan getInstance() {
        return thePacMan;
    }

    public static Dir getNextPacDir() {
        return nextPacDir;
    }

    public static void setNextPacDir(Dir nextPacDir) {
        PacMan.nextPacDir = nextPacDir;
    }

    @Override
    public void afficher() {

    }

    @Override
    public void move() {
        if (nextPacDir != null){
            System.out.println(nextPacDir);
            switch (nextPacDir){
                case BAS:{
                    if(MapLoader.getValueAt(getxPos(), getyPos()+1) != 1){
                        MapLoader.moveCase(getxPos(), getyPos(), getxPos(), getyPos()+1, this);
                    }
                    System.out.println("1");
                }
                case HAUT:{
                    if(MapLoader.getValueAt(getxPos(), getyPos()-1) != 1){
                        MapLoader.moveCase(getxPos(), getyPos(), getxPos(), getyPos()-1, this);
                    }
                    System.out.println("2");
                    break;
                }
                case GAUCHE:{
                    if(MapLoader.getValueAt(getxPos()-1, getyPos()) != 1){
                        MapLoader.moveCase(getxPos(), getyPos(), getxPos()-1, getyPos(), this);
                    }
                    System.out.println("3");
                    break;
                }
                case DROITE:{
                    if(MapLoader.getValueAt(getxPos()+1, getyPos()) != 1){
                        MapLoader.moveCase(getxPos(), getyPos(), getxPos()+1, getyPos(), this);
                    }
                    System.out.println("4");
                    break;
                }
            }
        }
    }
}
