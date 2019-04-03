package modèle;

public abstract class Entity implements Affichable, Movable{

    private String imgPath;
    private int mapCode;
    private int xPos;
    private int yPos;
    private int xInitPos;
    private int yInitPos;

    State state = State.NORMAL;
    private boolean hasMoved = false;

    private String id;


    private Dir currDirection = null;
    private Dir nextDir = null;


    Entity(String imgPath, int xPos, int yPos) {
        this.imgPath = imgPath;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void move() {
        if (this.getNextDir() != null){
            //Permet de gérer la préselection des direction
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
            //changeDirection change hasMoved en true
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

    //Méthode permettant de changer la currentDirection
    //Utilisez lorsque la direction dans nextDir est disponible
    private void changeDirection(){
        setCurrDirection(this.getNextDir());
        setNextDir(null);
        hasMoved = true;
    }

    //Getters and Setters
    public String getImgPath() {
        return imgPath;
    }
    private int getxPos() {
        return xPos;
    }
    void setxPos(int xPos) {
        this.xPos = xPos;
    }
    private int getyPos() {
        return yPos;
    }
    void setyPos(int yPos) {
        this.yPos = yPos;
    }
    public int getMapCode(){
        return mapCode;
    }
    void setMapCode(int code){
        mapCode = code;
    }
    private Dir getNextDir() {
        return nextDir;
    }
    public void setNextDir(Dir nextDir) {
        this.nextDir = nextDir;
    }
    private Dir getCurrDirection() {
        return currDirection;
    }
    private void setCurrDirection(Dir currDirection) {
        this.currDirection = currDirection;
    }
    public String getId() {
        return id;
    }
    void setId(String id) {
        this.id = id;
    }
    State getState(){return state;}
    int getxInitPos() {return xInitPos; }
    void setxInitPos(int xInitPos) {this.xInitPos = xInitPos;}
    int getyInitPos() {return yInitPos;}
    void setyInitPos(int yInitPos) {this.yInitPos = yInitPos;    }
}
