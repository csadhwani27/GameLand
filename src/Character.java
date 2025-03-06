import java.awt.*;

public class Character {
    //declare variables
    public int xpos;
    public int ypos;
    public int dx;//speed in x direction
    public int dy;//speed in y direction
    public int width;
    public int height;
    public boolean isAlive;
    public Image pic;
    public Rectangle rec;//hit box of character




    //constructor
public Character(int pXpos,int pYpos, int pDx, int pDy){


    xpos=pXpos;
    ypos=pYpos;
    dx=pDx;
    dy=pDy;
    width = 60;
    height=80;
    isAlive=true;
}

    //additional methods, including a printInfo()
public void bouncemove(){

    if(xpos>(1000-width)){
        dx=(-1)*dx;
    }
    if(xpos<0){
        dx=(-1)*dx;
    }
if (ypos>(700-height)){
    dy=(-1)*dy;
}
if (ypos<0){
    dy=(-1)*dy;
}


    xpos=xpos+dx; //updates the xpos and creates movement
    ypos=ypos+dy; //updates the ypos and creates movement
    rec=new Rectangle(xpos,ypos,width,height);





}

    public void wrapMove() {

        xpos=xpos+dx; //updates the xpos and creates movement
        ypos=ypos+dy; //updates the ypos and creates movement

        if(xpos > 1000){//right wall teleport
           xpos=0-width;
        }

        if (xpos<0){//right wall teleport
            xpos=0;
        }

        if (ypos>700){
            ypos=0-height;
        }
        if (ypos<0){
            ypos=700;
        }
        xpos=xpos+dx; //updates the xpos and creates movement
        ypos=ypos+dy; //updates the ypos and creates movement
        rec=new Rectangle(xpos,ypos,width,height);


    }


    public void printInfo(){
    System.out.println("X position"+xpos);
    System.out.println("Y position"+ypos);
    System.out.println("x speed"+dx);
    System.out.println("y speed"+dy);
    System.out.println("width"+width);
    System.out.println("height"+height);
    System.out.println("is alive"+isAlive);


    }

}
