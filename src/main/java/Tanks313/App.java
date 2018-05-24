package Tanks313;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;

public class App extends Canvas implements Runnable
{
    private static final long serialVersionUID=1L;
    public static final int WIDTH=320;
    public static final int HEIGHT=WIDTH/12*9;
    public static final int SCALE=2;
    public static String TITLE="TANKS.game";

    private boolean running=false;
    private Thread thread;
    private  boolean gameState=false;
    private  boolean menuState=true;

    private boolean isShooting = false;

    private Player p;
    private  Controller c;
    private  Textures tex;

    Random r = new Random();
    private BufferedImage image =new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private BufferedImage background=null;
    private BufferedImage menuSheet=null;
    private BufferedImage spriteSheet=null;
    private BufferedImage menuimg=null;
    private Menu menu;

    private enum STATE
    {
        MENU,
        GAME
    };

    private STATE State = STATE.MENU;


    private int enemy_count=1;
    private int enemy_killed=0;
    static private int level=1; //how much enemies create
    static private int howMuchCreate=2550;
    static private int points=0;

    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;


    public static int STAMINA=200;
    public static int SCORE=0;
    static JFrame frame;

    ////
    private BufferedImage player;

    public void init() throws IOException {
        requestFocus(); //don t need to click on the window
        BufferedImageLoader loader = new BufferedImageLoader();
        try
        {
            spriteSheet = loader.loadImage("res/SpriteSheet.png");
            background =loader.loadImage("res/background.png");
            menuSheet = loader.loadImage("res/menuSheet.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        addKeyListener(new KeyInput(this));

        tex = new Textures(this);

        p = new Player(200,200,tex);
        c = new Controller(tex,this);

        ea=c.getEntityA();
        eb=c.getEntityB();
    }

    private void showMenu()
    {
        running=false;
        gameState=false;
        menuState=true;
        Menu menu = new Menu();
        menu.render(frame);
        return;
    }



    private  synchronized void  start()
    {
        menuState=false;
        gameState=true;
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
            gameState=false;
            menuState=true;
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
                tick();
                updates++;
                delta--;
            }
            if(frames%howMuchCreate==0)
                c.addEntity(new Enemy(r.nextInt(App.WIDTH*App.SCALE),0,tex,c,this));
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

    private void tick() {

        if (State == STATE.GAME)
        {
            p.tick();
            c.tick();

        }


    if(enemy_killed > 10)
    {
        level++;
        if(howMuchCreate>=510)
        {
            howMuchCreate-=200;
        }
        else howMuchCreate=200;
        points+=enemy_killed;
        System.out.println("Level: "+level);
        enemy_killed=0;
    }

    }

    private void  render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        ///////////////

        g.drawImage(image, 0x0, 0x0, getWidth(), getHeight(), this);
        if(State == STATE.MENU)
        {
            g.drawImage(menuSheet,0,0,null);
        }

        if(State == STATE.GAME)
        {
            g.drawImage(background,0,0,null);
            p.render(g);
            c.render(g);
        }





        //////////////
        g.dispose();
        bs.show();

    }

    public void setEnemy_killed(int enemy_killed) {
        this.enemy_killed = enemy_killed;
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
            /* game.show1Menu(); */
            game.start();
    }

    public  BufferedImage getSpriteSheet()
    {
        return spriteSheet;
    }

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

}
