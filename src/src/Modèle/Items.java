package Modèle;

public class Items implements Affichable{
    protected String path;
    private int mapCode;
    public Items(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public void afficher() {

    }

    public int getMapCode(){
        return mapCode;
    }
    public void setMapCode(int code){
        mapCode = code;
    }
}
