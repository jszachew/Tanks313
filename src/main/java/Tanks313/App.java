package Tanks313;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
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
    public static String TITLE="INTROVERT.game";

    private boolean running=false;
    private Thread thread;

    private BufferedImage image =new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet=null;
    private BufferedImage background=null;
    private BufferedImage menuimg=null;
    private Menu menu;
    private boolean is_shooting=false;
    private int enemy_count=1;
    private int enemy_killed=0;

    public static int STAMINA=200;
    public static int SCORE=0;
    JFrame frame;

    @Override
    public void run() {

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
       Menu menu = new Menu();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setResizable(false);
       frame.setVisible(true);
       menu.render(frame);
   }

        public static void main( String[] args )
    {
       App game = new App();
    }

}
