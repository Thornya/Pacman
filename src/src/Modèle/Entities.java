package Modèle;
import Lib.Dir;

import java.util.ArrayList;

public abstract class Entities implements Affichable, Movable{

    private ArrayList<String> imgPaths = new ArrayList<String>();
    private int mapCode;
    private int xPos;
    private int yPos;
    private State state = State.NORMAL;


    private Dir currDirection = Dir.HAUT;

    public Entities(String imgPath, int xPos, int yPos) {
        this.imgPaths.add(imgPath);
        this.xPos = xPos;
        this.yPos = yPos;
    }


    @Override
    public abstract void afficher();

    @Override
    public abstract void move();

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
    public int getMapCode(){
        return mapCode;
    }
    public void setMapCode(int code){
        mapCode = code;
    }
    public void setCurrDirection(Dir currDirection) {
        this.currDirection = currDirection;
    }


}
