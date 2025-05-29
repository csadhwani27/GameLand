//Basic Game Application
// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

//*******************************************************************************

public class GameLand implements Runnable, KeyListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too
public Character astro;
public Image astroPic;
public Character jayson;
public Image jaysonPic;
public Character jb;
public Image jbPic;
public Character shai;
public Image shaiPic;
public Character lebron;
public Image lebronPic;
public Character Luka;
public Image lukaPic;
public Character bball;
public Image ballPic;
public boolean jbIsIntersectingjayson=false;
public boolean shaiIsIntersectinglebron;
public boolean jaysonIsInteractingshai;
public boolean LebronIsIntersectingjayson;
public boolean jaysonIsIntersectingLebron;
public boolean jaysonIsIntersectingLuka;
public boolean jaysonIsIntersectingBball;
public Character[]jaysons;//step 1 declare
public int jaysonVelocity=5;
public int score;
public Image ScorePic;
public Character Score;
public boolean shaiIsIntersectingScore;
public boolean jbIsIntersectingScore;
public boolean lukaIsIntersectingScore;
public boolean lebrongIsIntersectingScore;
public boolean gameOver=false;
public boolean bballIsIntersenctingJayson;



public Image bballbackground;
public Image gameOverBackground;


//Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        GameLand ex = new GameLand();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public GameLand() { // BasicGameApp constructor

        setUpGraphics();


        //variable and objects
        //create (construct) the objects needed for the game
         astro = new Character(200,100,3,3, true);
         jayson=new Character(500,100,0,0,true);
         jb=new Character(100,100,4,4,true);
         shai=new Character(-100,-100,4,4,false);
         lebron=new Character(-100,-100,4,-4,false);
         Luka = new Character(-100,-100,4,4,false);
         bball=new Character(-100,-100,0,0, true);
         Score = new Character(745, 260,0,0,true);
         Score.height = 190;
        Score.width = 170;
        Score.rec.height = 190;
        Score.rec.width = 170;
        bball.rec.width=2;
        bball.rec.height=2;

         //construct and chose a size for your array
         jaysons = new Character[0];
         //step 3 use a for loop to fill your array
         //we need construct a character for each element in our array
        for(int x=0;x<jaysons.length;x=x+1){
        //we need to set x pos and y pos for our characters edit: try to think of a new way to get characters in new spots
            jaysons[x] = new Character(100,400,(int)(Math.random()*10),(int)(Math.random()*10),true);

        }


        //create/construct image objects below
        //astroPic = Toolkit.getDefaultToolkit().getImage("cartoon-astronaut-forCS.jpg");
        jaysonPic=Toolkit.getDefaultToolkit().getImage("JaysonTatum.png");
        jbPic=Toolkit.getDefaultToolkit().getImage("jb.png");
        shaiPic=Toolkit.getDefaultToolkit().getImage("shai.png");
        lebronPic=Toolkit.getDefaultToolkit().getImage("lebron.png");
        lukaPic=Toolkit.getDefaultToolkit().getImage("luka.png");
        ballPic=Toolkit.getDefaultToolkit().getImage("bball.png");
        ScorePic=Toolkit.getDefaultToolkit().getImage("Score.png");
        gameOverBackground=Toolkit.getDefaultToolkit().getImage("GAME OVER.png");



        bballbackground=Toolkit.getDefaultToolkit().getImage("bballbackground.png");

        astro.printInfo();
        jayson.printInfo();
        jb.printInfo();
        shai.printInfo();
        lebron.printInfo();
        Luka.printInfo();
        bball.printInfo();
        Score.printInfo();




    }
    // end BasicGameApp constructor



//*******************************************************************************
//User Method Section
//
// put your code to do things here.
//change
    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while(true) {
            moveThings();
            checkScore();
            checkIntersections();//move all the game objects

            render();  // paint the graphics
            pause(10); // sleep for 10 ms
        }
    }

    public void moveThings() {
        //call the move() code for each object
   jayson.bouncemove();
   //astro.bouncemove();
   jb.bouncemove();
        if(shai.isAlive==true){
            shai.bouncemove();
        }
   lebron.bouncemove();
   Luka.bouncemove();
   if(bball.isAlive==true) {
       bball.bouncemove();
   }
        for(int x=0; x<jaysons.length; x=x+1){
            jaysons[x].bouncemove();
        }





    }
    public void checkIntersections() {
        if (jb.rec.intersects(jayson.rec) && jbIsIntersectingjayson == false) {//turns our intersection into one intersection

            jbIsIntersectingjayson = true;
            jb.ypos = (int) (Math.random() * 700);
            jb.xpos = (int) (Math.random() * 1);
            score= score +50;
            //System.out.println(score);

        }

        if (jb.rec.intersects(jayson.rec) == false) {//should reset our rectangles as
            jbIsIntersectingjayson = false;
        }

        if (jayson.rec.intersects(lebron.rec)) {

            jaysonIsIntersectingLebron = true;

            lebron.ypos = (int) (Math.random() * 700);
            lebron.xpos = (int) (Math.random() * 1);
            score= score+50;

        }

        if (shai.isAlive && jayson.rec.intersects(shai.rec) ) {
            //shai.dy = (-1) * shai.dy;
            //shai.dx = (-1) * shai.dx;
            shai.ypos = (int) (Math.random() * 700);
            shai.xpos = (int) (Math.random() * 1);
            score=score +50;
        }
        if (jayson.rec.intersects(shai.rec) == false && shai.isAlive) {
            jaysonIsInteractingshai = false;
        }
//        if (lebron.rec.intersects(jayson.rec)) {
//            lebron.dy = 1 + lebron.dy;
//            lebron.dx = 1 + lebron.dx;
//
//        }

        if (lebron.rec.intersects(jayson.rec) == false) {
            LebronIsIntersectingjayson = false;
        }


        if (Luka.rec.intersects(jayson.rec) == false) {//should reset our rectangles as
          jaysonIsIntersectingLuka = false;
        }


        if (jayson.rec.intersects(Luka.rec)) {
            Luka.ypos = (int) (Math.random() * 700);
            Luka.xpos = (int) (Math.random() * 1);
        jaysonIsIntersectingLuka = true;
        score=score+50;
        }

        jaysonIsIntersectingBball=false;

        if (jayson.rec.intersects(bball.rec)){
            jaysonVelocity=jaysonVelocity+2;
            System.out.println("Interaction");


        }
        if (jayson.rec.intersects(bball.rec)){
            bball.ypos=30000000;
            bball.isAlive=true;


        }

        if (jb.rec.intersects(Score.rec)){
           jb.dy=(-1)*jb.dy;
            jb.dx=(-1)*jb.dx;

        }
        if (lebron.rec.intersects(Score.rec)){
            lebron.dy=(-1)*lebron.dy;
            lebron.dx=(-1)*lebron.dx;
        }
        if (shai.rec.intersects(Score.rec)){
            shai.dy=(-1)*shai.dy;
            shai.dx=(-1)*shai.dx;
        }
        if (Luka.rec.intersects(Score.rec)){
            Luka.dy=(-1)*Luka.dy;
            Luka.dx=(-1)*Luka.dx;
        }

        if (Luka.rec.intersects(Score.rec) == true){
            score=0;
            gameOver=true;
            System.out.println("score reset");
        }
        lukaIsIntersectingScore=false;

        if (shai.rec.intersects(Score.rec) == true){
            shaiIsIntersectingScore=true;
                    score=0;
                    gameOver=true;
            System.out.println("score reset");

        }
        shaiIsIntersectingScore=false;

        if (jb.rec.intersects(Score.rec) == true){
            jbIsIntersectingScore=true;
            score=0;
            gameOver=true;
            System.out.println("score reset");

        }
        jbIsIntersectingScore=false;

        if (lebron.rec.intersects(Score.rec)){
            lebrongIsIntersectingScore=true;
            score=0;
            gameOver=true;
            System.out.println("score reset");

        }
        lebrongIsIntersectingScore=false;






        for (int x = 0; x < jaysons.length; x = x + 1) {
            if (shai.rec.intersects(jaysons[x].rec) && shai.isAlive==true) {
                jaysons[x].xpos = 2000;
                jaysons[x].dx = 0;
                jaysons[x].dy = 0;



            }




        }
    }

    public void checkScore(){
        if (score>1000 && shai.xpos<0 || shai.xpos >1000 && shai.ypos<0 || shai.ypos>710){
            shai.isAlive=true;
            shai.xpos=50;
            shai.ypos=50;
        }

        if (score>500 && lebron.xpos<0 || lebron.xpos>1000 && lebron.ypos<0 || lebron.ypos>710){
            lebron.isAlive=true;
            lebron.xpos=50;
            lebron.ypos=50;
            lebron.dy=4;
            lebron.dx=4;
        }

        if (score>2000 && Luka.xpos<0 || Luka.xpos>1000 && Luka.ypos<0 || Luka.ypos>710){
            Luka.isAlive=true;
            Luka.xpos=50;
            Luka.ypos=50;
            Luka.dx=4;
            Luka.dy=4;
        }
        if (score>1000&& score<1200 && bball.isAlive==true &&  (bball.xpos<0 || bball.xpos>1000) && (bball.ypos<0 || bball.ypos>710)){
          bballIsIntersenctingJayson=true;
           // bball.isAlive=true;
            bball.xpos=(int) (Math.random() * 700);
            bball.ypos=(int) (Math.random() * 100);
            bball.dy=3;
            bball.dx=5;

        }
bballIsIntersenctingJayson=false;





    }


            //Paints things on the screen using bufferStrategy
            private void render () {
                Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
                g.clearRect(0, 0, WIDTH, HEIGHT);


                //draw the images(pic name x location, y location, width,height,null
                g.drawImage(bballbackground, 0, 0, WIDTH, HEIGHT, null);
                for (int x = 0; x < jaysons.length; x = x + 1) {
                    g.drawImage(jaysonPic, jaysons[x].xpos, jaysons[x].ypos, jaysons[x].width, jaysons[x].height, null);


                }


                //g.drawImage(astroPic,astro.xpos,astro.ypos,astro.width,astro.height,null);
                g.drawImage(jaysonPic, jayson.xpos, jayson.ypos, jayson.width, jayson.height, null);
                g.drawImage(jbPic, jb.xpos, jb.ypos, jb.width, jb.height, null);
                if(shai.isAlive==true) {
                    g.drawImage(shaiPic, shai.xpos, shai.ypos, shai.width, shai.height, null);
                }
                g.drawImage(lebronPic, lebron.xpos, lebron.ypos, lebron.width, lebron.height, null);
                g.drawImage(lukaPic,Luka.xpos,Luka.ypos,Luka.width,Luka.height,null);
                g.drawImage(ballPic,bball.xpos,bball.ypos,bball.width,bball.height,null);
                g.drawImage(ScorePic,Score.xpos, Score.ypos, Score.width,Score.height,null);
                g.drawRect(Score.rec.x, Score.rec.y, Score.rec.width,Score.rec.height);

                g.drawString("Score: " + score,800,100 );
                if (gameOver==true){
                g.drawImage(gameOverBackground,0,0,WIDTH,HEIGHT,null);
                }



                g.dispose();
                bufferStrategy.show();
            }

            //Pauses or sleeps the computer for the amount specified in milliseconds
            public void pause ( int time ){
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                }
            }

            //Graphics setup method
            private void setUpGraphics () {
                frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

                panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
                panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
                panel.setLayout(null);   //set the layout

                // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
                // and trap input events (Mouse and Keyboard events)
                canvas = new Canvas();
                canvas.setBounds(0, 0, WIDTH, HEIGHT);
                canvas.setIgnoreRepaint(true);

                panel.add(canvas);  // adds the canvas to the panel.

                // frame operations
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
                frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
                frame.setResizable(false);   //makes it so the frame cannot be resized
                frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
                canvas.addKeyListener(this);
                // sets up things so the screen displays images nicely.
                canvas.createBufferStrategy(2);
                bufferStrategy = canvas.getBufferStrategy();
                canvas.requestFocus();
                System.out.println("DONE graphic setup");
            }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();
        System.out.println("Key Pressed: "+key+" , Key Code: "+keyCode);

        if(keyCode == 65){
            jayson.dx=-jaysonVelocity;
        }
        if(keyCode == 83){
            jayson.dy=jaysonVelocity;
        }
        if (keyCode == 87){
            jayson.dy=-jaysonVelocity;
        }
        if (keyCode ==68){
            jayson.dx=jaysonVelocity;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        int keyCode = e.getKeyCode();
        System.out.println("Key Released: "+key+" , Key Code: "+keyCode);

        if(keyCode == 65){
            jayson.dx=0;
        }

        if(keyCode == 83){
            jayson.dy=0;
        }
        if (keyCode == 87) {
            jayson.dy = -0;
        }
        if (keyCode ==68){
            jayson.dx=0;
        }

    }
}

