package Lib;

public class Grille {
    private static Grille ourInstance = new Grille();

    public static Grille getInstance() {
        return ourInstance;
    }

    private Grille() {
    }
}
