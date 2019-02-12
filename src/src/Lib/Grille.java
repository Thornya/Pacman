package Lib;

public class Grille {

    public static final int YMAX = 5;
    public static final int XMAX = 10;
    private static boolean tab[][];

    private static Grille ourInstance = new Grille();

    public static Grille getInstance() {

        return ourInstance;
    }

    private Grille() {
        tab = new boolean[XMAX][YMAX];
    }
}
