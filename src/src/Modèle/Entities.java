package Modèle;
import Lib.Dir;

public abstract class Entities implements Affichable, Movable{

    private String imgPath;
    private int mapCode;
    private int xPos;
    private int yPos;
    private int xInitPos;
    private int yInitPos;

    protected State state = State.NORMAL;
    private boolean hasMoved = false;

    private String id;


    private Dir currDirection = null;
    private Dir nextDir = null;


    public Entities(String imgPath, int xPos, int yPos) {
        this.imgPath = imgPath;
        this.xPos = xPos;
        this.yPos = yPos;
    }


    @Override
    public abstract void afficher();

    @Override
    public void move() {
        if (this.getNextDir() != null){
            switch (this.getNextDir()){
                case BAS:{
                    if(MapLoader.getValueAt(getxPos(), getyPos()+1) != 1){
                        MapLoader.moveCase(getxPos(), getyPos(), getxPos(), getyPos()+1, this);
                        setyPos(getyPos()+1);
                        changeDirection();
                    }
                    break;
                }
                case HAUT:{
                    if(MapLoader.getValueAt(getxPos(), getyPos()-1) != 1){
                        MapLoader.moveCase(getxPos(), getyPos(), getxPos(), getyPos()-1, this);
                        setyPos(getyPos()-1);
                        changeDirection();
                    }
                    break;
                }
                case GAUCHE:{

                    if(getxPos() == 0 && getyPos() == 14) {
                        MapLoader.moveCase(getxPos(), getyPos(), 27, getyPos(), this);
                        setxPos(27);
                        changeDirection();
                    }else if((MapLoader.getValueAt(getxPos()-1, getyPos()) != 1 )){
                        MapLoader.moveCase(getxPos(), getyPos(), getxPos()-1, getyPos(), this);
                        setxPos(getxPos()-1);
                        changeDirection();
                    }
                    break;
                }
                case DROITE:{
                    if(getxPos() == 27 && getyPos() == 14) {
                        MapLoader.moveCase(getxPos(), getyPos(), 0, getyPos(), this);
                        setxPos(0);
                        changeDirection();
                    }else if ((MapLoader.getValueAt(getxPos() + 1, getyPos()) != 1 ) ) {
                            MapLoader.moveCase(getxPos(), getyPos(), getxPos()+1, getyPos(), this);
                            setxPos(getxPos()+1);
                            changeDirection();
                        }
                    break;
                }
            }
        }if (!hasMoved) {
            switch (this.getCurrDirection()) {
                case BAS: {
                    if (MapLoader.getValueAt(getxPos(), getyPos() + 1) != 1) {
                        MapLoader.moveCase(getxPos(), getyPos(), getxPos(), getyPos() + 1, this);
                        setyPos(getyPos() + 1);
                    }
                    break;
                }
                case HAUT: {
                    if (MapLoader.getValueAt(getxPos(), getyPos() - 1) != 1) {
                        MapLoader.moveCase(getxPos(), getyPos(), getxPos(), getyPos() - 1, this);
                        setyPos(getyPos() - 1);
                    }
                    break;
                }
                case GAUCHE: {

                    if(getxPos() == 0 && getyPos() == 14) {
                        MapLoader.moveCase(getxPos(), getyPos(), 27, getyPos(), this);
                        setxPos(27);
                    }
                    else if((MapLoader.getValueAt(getxPos()-1, getyPos()) != 1 )){
                        MapLoader.moveCase(getxPos(), getyPos(), getxPos()-1, getyPos(), this);
                        setxPos(getxPos()-1);
                    }
                    break;
                }
                case DROITE: {
                    if(getxPos() == 27 && getyPos() == 14) {
                        MapLoader.moveCase(getxPos(), getyPos(), 0, getyPos(), this);
                        setxPos(0);
                    }else if ((MapLoader.getValueAt(getxPos() + 1, getyPos()) != 1 ) ) {
                        MapLoader.moveCase(getxPos(), getyPos(), getxPos()+1, getyPos(), this);
                        setxPos(getxPos()+1);
                    }
                    break;
                }
            }
        }
        hasMoved = false;
    }

    private void changeDirection(){
        setCurrDirection(this.getNextDir());
        setNextDir(null);
        hasMoved = true;
    }

    public String getImgPath() {
        return imgPath;
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
    public Dir getNextDir() {
        return nextDir;
    }
    public void setNextDir(Dir nextDir) {
        this.nextDir = nextDir;
    }
    public Dir getCurrDirection() {
        return currDirection;
    }
    public void setCurrDirection(Dir currDirection) {
        this.currDirection = currDirection;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public State getState(){return state;}
    public int getxInitPos() {return xInitPos; }
    public void setxInitPos(int xInitPos) {this.xInitPos = xInitPos;}
    public int getyInitPos() {return yInitPos;}
    public void setyInitPos(int yInitPos) {this.yInitPos = yInitPos;    }
}
