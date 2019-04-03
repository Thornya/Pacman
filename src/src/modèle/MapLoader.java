package modèle;

import javafx.application.Platform;
import vue.grilleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MapLoader{

    /*
    0 = vide
    1 = mur
    2 = petite gomme
    3 = maxi gomme
    4 = fantome rouge
    5 = fantome bleu
    6 = fantome violet
    7 = fantome orange
    8 = pacman
    9 = porte
     */

    //Compteur de Gomme restante
    private static int cptGomme=0;

    //Taille de la carte
    public static final int XSIZE = 28;
    public static final int YSIZE = 31;

    //Map utilisant les codes ci-dessus
    private static int[][] currentMap;

    //Map d'affichable (Entities + Items)
    private static ArrayList<Affichable>[][] affichablesMap;

    //31x28
    public static final int[][] BASEMAP = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
            {1, 3, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 3, 1},
            {1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
            {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1},
            {1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1},
            {1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1},
            {1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 2, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 2, 1, 1, 0, 1, 1, 1, 9, 9, 1, 1, 1, 0, 1, 1, 2, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 4, 5, 6, 7, 0, 1, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1},
            {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
            {1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
            {1, 3, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 8, 0, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 3, 1},
            {1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1},
            {1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1},
            {1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1},
            {1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1},
            {1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1},
            {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    //Recopie la BaseMap dans la map actuelle
    private static void copyMap(){
        for (int i = 0; i<YSIZE; i++)
            System.arraycopy(BASEMAP[i], 0, currentMap[i], 0, XSIZE);
    }


    static int getValueAt(int x, int y){
        if  (currentMap.length != BASEMAP.length)
            copyMap();
        return currentMap[y][x];
    }
    public static ArrayList<Affichable> getEntityOrItemAt(int x, int y){
        return affichablesMap[x][y];
    }

    //Permet de remplir la map d'affichable
    //Utilise la currentMap
    private static void affichablesMapFill(){
        affichablesMap = new ArrayList[YSIZE][XSIZE];
        for (int i = 0; i<YSIZE; i++)
            for (int j = 0; j<XSIZE; j++){
                affichablesMap[i][j] = new ArrayList<>();
                switch (currentMap[i][j]){
                    case 2:
                        affichablesMap[i][j].add(new Gomme());
                        cptGomme++;
                        break;
                    case 3:
                        affichablesMap[i][j].add(new SuperGomme());
                        cptGomme++;
                        break;
                    case 4:
                        affichablesMap[i][j].add(FantomeR.getInstance());
                        break;
                    case 5:
                        affichablesMap[i][j].add(new FantomeB(j, i));
                        break;
                    case 6:
                        affichablesMap[i][j].add(new FantomeV(j, i));
                        break;
                    case 7:
                        affichablesMap[i][j].add(new FantomeO(j, i));
                        break;
                    case 8:
                        affichablesMap[i][j].add(PacMan.getInstance());
                }
            }
    }

    //Prépare la map au lancement
    public static void mapSetup(){
        currentMap =  new int[YSIZE][XSIZE];
        copyMap();
        affichablesMapFill();
    }

    static void moveCase(int xStart, int yStart, int xEnd, int yEnd, Entity ent) {
        boolean hasBeenEaten = false;
        affichablesMap[yStart][xStart].remove(ent);
        if (affichablesMap[yStart][xStart].size() != 0)
            currentMap[yStart][xStart] = affichablesMap[yStart][xStart].get(0).getMapCode();
        List<Affichable> affichableTemp = new ArrayList<>();
        if (ent instanceof PacMan) {
            for (Affichable a : affichablesMap[yEnd][xEnd]) {
                //Pacman -> Fantome
                if (a instanceof Fantome) {
                    if(ent.getState()==State.NORMAL){
                        GlobalGameController.gameOver();
                        return;
                    }
                    else{
                        //Mange le Fantome
                        affichableTemp.add(a);
                    }
                }
                //Pacman -> Item
                else if (a instanceof Items) {
                    if (a instanceof SuperGomme) {
                        GlobalGameController.addScore(50);
                        ((PacMan) ent).mangerSGomme();
                    }
                    else
                        GlobalGameController.addScore(10);
                    cptGomme--;
                    affichableTemp.add(a);
                    Platform.runLater(()-> grilleView.miam(xEnd, yEnd));

                    //Lorsqu'il n'y a plus de gomme
                    if(cptGomme<=0)
                        GlobalGameController.gameOver();
                }
            }
        }
        else {
            for (Affichable a : affichablesMap[yEnd][xEnd])
                //Fantome -> Pacman
                if (a instanceof PacMan) {
                    if (ent.getState() == State.NORMAL) {
                        GlobalGameController.gameOver();
                        return;
                    } else {
                        //Mange le Fantome
                        affichableTemp.add(ent);
                        hasBeenEaten = true;

                    }
                }
        }
        //Parcours les affichable mangés et les retire
        //Dans le cas d'un Fantome mangé, le téléporte au spawn
        for(Affichable affDel : affichableTemp){
            if(affDel instanceof Fantome){
                if (hasBeenEaten)
                    moveCase(xStart,yStart,((Fantome) affDel).getxInitPos(),((Fantome) affDel).getyInitPos(),(Entity) affDel);
                else
                    moveCase(xEnd,yEnd,((Fantome) affDel).getxInitPos(),((Fantome) affDel).getyInitPos(),(Entity) affDel);
                ((Fantome) affDel).setxPos(((Fantome) affDel).getxInitPos());
                ((Fantome) affDel).setyPos(((Fantome) affDel).getyInitPos());
            }
            else
                affichablesMap[yEnd][xEnd].remove(affDel);
        }
        //Si l'entité n'a pas été mangé, la déplace sur la case
        if (!hasBeenEaten) {
            affichablesMap[yEnd][xEnd].add(ent);
            currentMap[yEnd][xEnd] = affichablesMap[yEnd][xEnd].get(affichablesMap[yEnd][xEnd].size() - 1).getMapCode();
            Platform.runLater(() -> GlobalGameController.setupGraphMove(xStart, yStart, xEnd, yEnd, ent));
        }
    }
}
