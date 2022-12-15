package model;

public class Position {
    private int posX;
    private int posY;

    public Position(int x, int y) {
        posX = x;
        posY = y;
    }
    public Position(){

    }

    public String affiche() {
        String ch = "(" + posX + "," + posY + ")";
        return ch;
    }

    public void modifierPosition(int x, int y) {
        posX =  x;
        posY =  y;
    }
    public void copy(Position p){
        posX=p.posX;
        posY=p.posY;
    }
    public static void copy(Position pd, Position ps ){
        pd.posX=ps.posX;
        pd.posY=ps.posY;
    }
    public int getPosX(){
        return posX;
    }
    public void setPosX(int x){
        posX=x;
    }
    public int getPosY(){
        return posY;
    }
    public void setPosY(int y){
        posY=y;
    }
    @Override
    public String toString() {
        return "Position ("+posX + ", " + posY + ")";
    }

}