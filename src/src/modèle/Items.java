package modèle;

public class Items implements Affichable{
    String path;
    private int mapCode;

    Items(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    public int getMapCode(){
        return mapCode;
    }
    void setMapCode(int code){
        mapCode = code;
    }
}
