package Lib;

public class GrilleK {
    public static final int YMAX = 5;
    public static final int XMAX = 10;
    private static boolean tab[][];

    public static void setTab(){
        tab = new boolean[XMAX][YMAX];
    }

    public static boolean[][] getTab() {
        return tab;
    }
}
