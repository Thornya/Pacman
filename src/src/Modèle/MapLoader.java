package Modèle;

import vue.grilleView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MapLoader implements Observer {

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

    public static final int XSIZE = 28;
    public static final int YSIZE = 31;
    private static int[][] currentMap;
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
            {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private static void copyMap(){
        for (int i = 0; i<YSIZE; i++)
            for (int j = 0; j<XSIZE; j++) {
                currentMap[i][j] = BASEMAP[i][j];
            }
    }

    public static int getValueAt(int x, int y){
        if  (currentMap.length != BASEMAP.length)
            copyMap();
        return currentMap[x][y];
    }

    public static ArrayList<Affichable> getEntityOrItemAt(int x, int y){
        return affichablesMap[x][y];
    }

    public boolean isEntityOrItemAt(int x, int y){
        return (currentMap[x][y] != 0 && currentMap[x][y] != 1 && currentMap[x][y] != 9);
    }

    private static void affichablesMapFill(){
        affichablesMap = new ArrayList[YSIZE][XSIZE];
        for (int i = 0; i<YSIZE; i++)
            for (int j = 0; j<XSIZE; j++){
                affichablesMap[i][j] = new ArrayList<Affichable>();
                switch (currentMap[i][j]){
                    case 2:
                        affichablesMap[i][j].add(new Gomme());
                        break;
                    case 3:
                        affichablesMap[i][j].add(new SuperGomme());
                        break;
                    case 4:
                        affichablesMap[i][j].add(new FantomeR(j, i));
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
                        System.out.println(i+ " " + j);
                        affichablesMap[i][j].add(PacMan.getInstance());
                }

            }
    }

    public static void mapSetup(){
        currentMap =  new int[YSIZE][XSIZE];
        copyMap();
        affichablesMapFill();
    }


    @Override
    public void update(Observable o, Object arg) {

    }

    public static void moveCase(int xStart, int yStart, int xEnd, int yEnd, Entities ent){
        affichablesMap[xStart][yStart].remove(ent);
        if (affichablesMap[xStart][yStart].size() != 0)
            currentMap[xStart][yStart] = affichablesMap[xStart][xEnd].get(0).getMapCode();

        if (ent instanceof PacMan) {
            for (Affichable a : affichablesMap[xEnd][yEnd]) {
                if (a instanceof Fantome) {
                    GlobalGameController.gameOver();
                    return;
                } else if (a instanceof Items) {
                    if (a instanceof SuperGomme)
                        GlobalGameController.addScore(50);
                    else
                        GlobalGameController.addScore(10);
                    affichablesMap[xEnd][yEnd].remove(a);
                }
            }
        }

        affichablesMap[xEnd][yEnd].add(ent);
        currentMap[xEnd][yEnd] = affichablesMap[xEnd][yEnd].get(affichablesMap[xEnd][yEnd].size()-1).getMapCode();
        System.out.println("t1");
        grilleView.graphicMove(xStart, yStart, xEnd, yEnd);
        System.out.println("terminé");
    }
}
