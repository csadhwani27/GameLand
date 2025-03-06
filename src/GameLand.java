//Basic Game Application
// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

//*******************************************************************************

public class GameLand implements Runnable {

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
public boolean jbIsIntersectingjayson=false;
public boolean shaiIsIntersectinglebron;
public boolean jaysonIsInteractingshai;
public boolean LebronIsIntersectingjayson;


public Image bballbackground;


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
         astro = new Character(200,100,3,3);
         jayson=new Character(300,30,3,3);
         jb=new Character(200,500,3,3);
         shai=new Character(300,400,3,3);
         lebron=new Character(600,200,3,-3);


        astro.printInfo();
        jayson.printInfo();
        jb.printInfo();
        shai.printInfo();
        lebron.printInfo();

        //create/construct image objects below
        //astroPic = Toolkit.getDefaultToolkit().getImage("cartoon-astronaut-forCS.jpg");
        jaysonPic=Toolkit.getDefaultToolkit().getImage("JaysonTatum.png");
        jbPic=Toolkit.getDefaultToolkit().getImage("jb.png");
        shaiPic=Toolkit.getDefaultToolkit().getImage("shai.png");
        lebronPic=Toolkit.getDefaultToolkit().getImage("lebron.png");

        bballbackground=Toolkit.getDefaultToolkit().getImage("bballbackground.png");






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
   shai.bouncemove();
   lebron.bouncemove();



    }
    public void checkIntersections(){
        if (jb.rec.intersects(jayson.rec) && jbIsIntersectingjayson == false){//turns our intersection into one intersection

            jbIsIntersectingjayson=true;

            System.out.println("Broken Ankles!");
        }

    if (jb.rec.intersects(jayson.rec)== false){//should reset our rectangles as
        jbIsIntersectingjayson=false;
    }

    if (shai.rec.intersects(lebron.rec)) {

        shaiIsIntersectinglebron = true;

        lebron.ypos = (int) (Math.random() * 700);
        lebron.xpos = (int) (Math.random() * 1000);

    }

    if (jayson.rec.intersects(shai.rec)){
        shai.dy = (-1) * shai.dy;
        shai.dx = (-1) * shai.dx;
    }
        if (jayson.rec.intersects(shai.rec)== false){
            jaysonIsInteractingshai = false;
        }
    if (lebron.rec.intersects(jayson.rec)){
        lebron.dy = 1 + lebron.dy;
        lebron.dx = 1 + lebron.dx;
        
    }

    if (lebron.rec.intersects(jayson.rec)== false){
        LebronIsIntersectingjayson = false;
    }


    }



    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the images(pic name x location, y location, width,height,null
        g.drawImage(bballbackground,0,0,WIDTH,HEIGHT,null);
        //g.drawImage(astroPic,astro.xpos,astro.ypos,astro.width,astro.height,null);
        g.drawImage(jaysonPic,jayson.xpos,jayson.ypos,jayson.width,jayson.height,null);
        g.drawImage(jbPic,jb.xpos,jb.ypos,jb.width,jb.height,null);
        g.drawImage(shaiPic,shai.xpos,shai.ypos,shai.width,shai.height,null);
        g.drawImage(lebronPic,lebron.xpos,lebron.ypos,lebron.width,lebron.height,null);



        g.dispose();
        bufferStrategy.show();
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
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

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

}

