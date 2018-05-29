package Tanks313;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;

/*
* Main application class
* All mechanics and connections are used there.
 */

public class App extends Canvas implements Runnable
{
    /*
    * Sets default sizes of game frame
     */
    private static final long serialVersionUID=1L;
    private static final int WIDTH=320;
    private static final int HEIGHT=WIDTH/12*9;
    private static final int SCALE=2;
    private static String TITLE="TANKS.game";


    private boolean running=false;
    private Thread thread;

    /*
    * isShooting added to avoid fire like in
    * machine gun.
    * Every shoot is new spacebar click
     */
    private boolean isShooting = false;

    private Player p;
    private  Controller c;
    private  Textures tex;

    /*
    * Making connection with our database
     */
    private DBConnect database = new DBConnect();

    private Random r = new Random();

    private BufferedImage image =new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private BufferedImage background=null;
    private BufferedImage menuSheet=null;
    private BufferedImage spriteSheet=null;
    private BufferedImage theEndSheet=null;

    /*
    * Variables of menu and end of the game state
     */
    private Menu menu;
    private TheEnd theEnd;

    /*
    * static variables for the stamina
     */
    private static int HEALTH=200;
    private static int MAX_HEALTH=200;

    /*
    * In which state the App is
     */
    public enum STATE
    {
        MENU,
        GAME,
        END
    };

    /*
    * Game starts in menu state
     */
    public static STATE State = STATE.MENU;

    /*
    * Every level up increses number of Enemies
     */
    private int enemy_killed=0;
    static private int LEVEL =1; //how much enemies create
    private int howMuchCreate=2550;
    private static int POINTS =0;

    public LinkedList<EntityA> ea;
    LinkedList<EntityB> eb;


    static JFrame frame;

    private BufferedImage player;

    public void init() throws IOException {

        /*
        * Dont need to click on the window
         */
        requestFocus();


        /*
        * try to load backgrounds and textures
         */
        BufferedImageLoader loader = new BufferedImageLoader();
        try
        {
            spriteSheet = loader.loadImage("res/SpriteSheet.png");
            background =loader.loadImage("res/background.png");
            menuSheet = loader.loadImage("res/menuSheet.png");
            theEndSheet = loader.loadImage("res/theEndSheet.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        /*
        * Add mouse input
         */
        addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput());

        /*
        * Making textures and new game controller
         */
        tex = new Textures(this);
        c = new Controller(tex,this);

        /*
        * Setting player in 200, 200 using controller c
         */
        p = new Player(200,200,tex,this,c);

        menu=new Menu();
        theEnd=new TheEnd();

        /*
        * Setting ea and eb from game controller
         */
        ea=c.getEntityA();
        eb=c.getEntityB();
    }


    private  synchronized void  start()
    {

        if (running)
            return;

        running = true;

        thread = new Thread(this);
        thread.start();
    }

    private  synchronized  void  stop() throws InterruptedException {
        if (!running)
            return;
        running=false;
        try {
            thread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    @Override
    public void run() {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long lastTime = System.nanoTime();
        final double maxFPS = 60.0;
        double ns = 1000000000 / maxFPS;
        double delta =0;
        int updates=0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running)
        {
            long now = System.nanoTime();
            delta += ( now - lastTime ) / ns;
            lastTime = now;
            if(delta >1 )
            {
                try {
                    tick();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                updates++;
                delta--;
            }

            /*
            * Creating enemies
             */
            if(frames%howMuchCreate==0)
            {
                c.addEntity(new Enemy(r.nextInt(App.WIDTH*App.SCALE),-150,tex,c,this));
                EnemyJAXB enm= new EnemyJAXB();
               // enm.marshall();
            }

            render();
            frames++;
            if(System.currentTimeMillis()-timer > 10)
            {
                timer+=1000;
                System.out.println("fps: " + frames);
                updates=0;
                frames=0;
            }


        }

        try {
            stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private void tick() throws SQLException {

        if (State == STATE.GAME)
        {
            p.tick();
            c.tick();

            /*
            * if player has no stamina game state is END
            * and static variables are in their statring possition
             */
            if(HEALTH <= 0)
            {
                database.addResult(POINTS, LEVEL);
                HEALTH = 200;
                MAX_HEALTH = 200;
                theEnd.setPoints();
                POINTS =0;
                enemy_killed=0;
                LEVEL =1; //how much enemies create
                howMuchCreate=2550;
                State =STATE.END;
            }
        }

        /*
        * Making difficult of game after leveling up
         */
    if(enemy_killed > 10)
    {
        LEVEL++;

        if(LEVEL <=5)
        {
            Enemy.setSpeed(LEVEL);
        }
        else
            Enemy.setSpeed(5);


        if(howMuchCreate>=510)
        {
            howMuchCreate-=200;
        }
        else howMuchCreate=200;

        System.out.println("Level: "+ LEVEL);
        enemy_killed=0;

    }
    }

    /*
    * Rendering Frame in every case (Start, Game, End)
     */
    private void  render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            createBufferStrategy(3);
            return;
        }


        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0x0, 0x0, getWidth(), getHeight(), this);
        if(State == STATE.MENU)
        {
            g.drawImage(menuSheet,0,0,null);
            menu.render(g);
            c.clearEnemies();
        }

        if(State == STATE.END)
        {
            g.drawImage(theEndSheet,0,0,null);
            theEnd.render(g);
            c.clearEnemies();
        }

        if(State == STATE.GAME)
        {
            g.drawImage(background,0,0,null);
            p.render(g);
            c.render(g);

            g.setColor(Color.GRAY);
            g.fillRect(5,5 ,MAX_HEALTH,20);

            g.setColor(Color.orange);
            g.fillRect(5,5 ,HEALTH,20);

            g.setColor(Color.WHITE);
            g.drawRect(5,5 ,MAX_HEALTH,20);


            if(HEALTH >= 30)
            {
                Font fnt0 = new Font("arial",Font.PLAIN ,15);
                g.setFont(fnt0);
                g.setColor(Color.black);
                g.drawString("STAMINA",20,20);

            }
            else
            {
                Font fnt0 = new Font("arial",Font.BOLD ,15);
                g.setFont(fnt0);
                g.setColor(Color.RED);

                g.drawString("STAMINA",20,20);
            }

            Font fnt0 = new Font("arial",Font.BOLD ,25);
            g.setFont(fnt0);
            g.setColor(Color.DARK_GRAY);
            g.drawString("POINTS: "+ String.valueOf(POINTS) + " LEVEL: " + String.valueOf(LEVEL),5,(HEIGHT-20)*2);

        }

        g.dispose();
        bs.show();

    }

    public void setEnemy_killed(int enemy_killed) {
        this.enemy_killed = enemy_killed;
        POINTS +=enemy_killed;
    }

    public int getEnemy_killed() {
        return enemy_killed;
    }



   public App()
   {
       setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT * SCALE) );
       setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT * SCALE) );
       setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT * SCALE) );
       frame = new JFrame (TITLE);
       frame.setSize(new Dimension(WIDTH*SCALE, HEIGHT * SCALE));

       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setResizable(false);
       frame.setVisible(true);
   }

   public static void main( String[] args ) throws InterruptedException
   {
            App game = new App();
            final Component add = frame.add(game);
            game.start();
    }

    public  BufferedImage getSpriteSheet()
    {
        return spriteSheet;
    }

    /*
    * Keyboard input using
    * More in KeyInput class
     */
    public  void keyPressed(KeyEvent e)
    {
        int key=e.getKeyCode();


        if(State == STATE.GAME)
        {
            if(key==KeyEvent.VK_RIGHT)
            {
                p.setVelX(8);
            }
            else if (key==KeyEvent.VK_LEFT)
            {
                p.setVelX(-8);
            }
            else if(key==KeyEvent.VK_DOWN)
            {
                p.setVelY(8);        }
            else if(key==KeyEvent.VK_UP)
            {
                p.setVelY(-8);
            }
            else if(key==KeyEvent.VK_SPACE && !isShooting)
            {
                isShooting = true;
                c.addEntity(new Bullet(p.getX(),p.getY(),tex,this));
            }
        }

    }

    public void keyReleased(KeyEvent e)
    {
        int key=e.getKeyCode();

        if(key==KeyEvent.VK_RIGHT)
        {
            p.setVelX(0);
        }
        else if (key==KeyEvent.VK_LEFT)
        {
            p.setVelX(0);
        }
        else if(key==KeyEvent.VK_DOWN)
        {
            p.setVelY(0);        }
        else if(key==KeyEvent.VK_UP)
        {
            p.setVelY(0);
        }
        else if(key==KeyEvent.VK_SPACE)
        {
            isShooting=false;
        }

    }

    public static void setPOINTS(int toAdd)
    {
        POINTS = toAdd;
    }

    public static int getHEALTH()
    {
        return HEALTH;
    }

    public static int getMAX_HEALTH()
    {
        return MAX_HEALTH;
    }

    public static void addHealth(int toAdd)
    {
        if(HEALTH+toAdd>0)
        {
            HEALTH+=toAdd;
        }
        else
        {
            HEALTH=0;
        }

    }

    public static void setMaxHealth(int toAdd)
    {
        App.MAX_HEALTH+=toAdd;
    }

    public static void setHEALTH(int health)
    {
        HEALTH = health;
    }

    public static int getHEIGHT()
    {
        return HEIGHT;
    }

    public static int getWIDTH()
    {
        return WIDTH;
    }

    public static int getPOINTS()
    {
        return POINTS;
    }

    public static void addPOINTS(int toAdd)
    {
        if(POINTS +toAdd>0)
        {
            POINTS +=toAdd;
        }
        else
        {
            POINTS =0;
        }
    }

    public static int getSCALE()
    {
        return SCALE;
    }

}
