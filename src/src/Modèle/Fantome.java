package Modèle;

import Lib.Dir;

import java.util.Random;

public class Fantome extends Entities implements Affichable{

    private int lastDir = -2;
    public Fantome(String imgPath, int xPos, int yPos) {
        super(imgPath, xPos, yPos);
    }

    public void setRandomDir(){
        Random rand = new Random();
        int nombreAleatoire;
        //genère un random jusqu'à changer de direction
        nombreAleatoire = rand.nextInt(5)-2;
        while((nombreAleatoire == 0 || nombreAleatoire == (lastDir*-1)))
            nombreAleatoire = rand.nextInt(5)-2;
        switch (nombreAleatoire){
            case 0: break;
            case 2:{
                setNextDir(Dir.BAS);
                lastDir = 2;
                break;
            }
            case -2:{
                setNextDir(Dir.HAUT);
                lastDir = -2;
                break;
            }
            case -1:{
                setNextDir(Dir.GAUCHE);
                lastDir = -1;
                break;
            }
            case 1:{
                setNextDir(Dir.DROITE);
                lastDir = 1;
                break;
            }
        }
    }

    @Override
    public void afficher() {

    }


}
