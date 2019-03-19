package Modèle;
import java.util.ArrayList;

public class Entities implements Affichable{

    private ArrayList<String> imgPaths = new ArrayList<String>();
    private int xPos;
    private int yPos;
    private State state = State.NORMAL;

    public Entities(String imgPath, int xPos, int yPos) {
        this.imgPaths.add(imgPath);
        this.xPos = xPos;
        this.yPos = yPos;
    }


    @Override
    public void afficher() {

    }


    public ArrayList<String> getImgPaths() {
        return imgPaths;
    }
    public void addImgPaths(String imgPath) {
        this.imgPaths.add(imgPath);
    }
    public int getxPos() {
        return xPos;
    }
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }
    public int getyPos() {
        return yPos;
    }
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
