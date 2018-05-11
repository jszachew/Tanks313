package Tanks313;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

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

    private Player p;


    private BufferedImage image =new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet=null;
    private BufferedImage background=null;
    private BufferedImage menuimg=null;
    private Menu menu;
    private int enemy_count=1;
    private int enemy_killed=0;

    public static int STAMINA=200;
    public static int SCORE=0;
    static JFrame frame;

    ////
    private BufferedImage player;

    public void init() throws IOException {
        requestFocus(); //dont need to click on the window
        BufferedImageLoader loader = new BufferedImageLoader();
        try
        {
            spriteSheet = loader.loadImage("res/SpriteSheet.png");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        addKeyListener(new KeyInput(this));

        p = new Player(200,202,this);
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
    p.tick();
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
        p.render(g);

        //////////////
        g.dispose();
        bs.show();

    }

    public  enum STATE{MENU,GAME,END,WIN};

    public static STATE state=STATE.MENU;

   /* private Player p;
    private Controller c;
    private Textures t;
    public LinkedList<EntityA> ea;
    public LinkedList<EntityB> eb;
  */

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
    }

}
