package Modèle;

public class Items implements Affichable{
    protected String path;

    public Items(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public void afficher() {

    }
}
