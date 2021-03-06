package Tanks313;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
* Main class implementing player movement and its phisics
* It also check if player is not going out of bounds
* or crashes with Ememies
 */

public class Player extends GameObject implements EntityA{

    /*
    * By using velX and velY movement is a lot smoother
    * than with only x and y. There is no 1 second delay
    * after pressing key
     */
        private  double velX = 0; //velX and vel Y are for smoother movement
        private  double velY = 0;

        Controller controller;
        App game;

        Animation anim;
        private BufferedImage player;

        private Textures tex;

        public Player(double x, double y,Textures tex, App game, Controller controller)
        {
            super(x,y);
            this.tex=tex;
            this.controller = controller;
            this.game = game;

            anim = new Animation(5, tex.player[0],tex.player[1],tex.player[2]);

        }

        public void tick()
        {
            x+=velX; //reduces the 1 sec. of waiting after holding the key
            y+=velY;

            if(x<=0)
            {
                x=0;
            }
            if(x>=(App.getWIDTH()*App.getSCALE())-32)
            {
                x=(App.getWIDTH()*App.getSCALE())-32;
            }
            if(y<=0)
            {
                y=0;
            }
            if(y>=(App.getHEIGHT()*App.getSCALE())-80)
            {
                y=(App.getHEIGHT()*App.getSCALE())-80;
            }

            for (int i=0 ; i<game.eb.size(); i++)
            {
                EntityB tempEnt = game.eb.get(i);
                if (Physics.Coliision(this,tempEnt))
                {
                   controller.removeEntity(tempEnt);
                   if(App.getHEALTH()-10<0)
                   {
                       App.setHEALTH(0);
                   }
                   else
                   {
                       App.addHealth(-10);
                   }

                }
            }

            anim.runAnimation();
        }

        public  void render(Graphics g)
        {
            anim.drawAnimation(g,x,y,0);

        }
    public Rectangle getBounds()
    {
        return new Rectangle ((int)x,(int)y,32,32);
    }

        public double getX()
        {
            return x;
        }

        public double getY()
        {
            return y;
        }
        public  void  setX(double x)
        {
            this.x=x;
        }
        public  void  setY(double y)
        {
            this.y=y;
        }

        public void  setVelX(double velX)
        {
        this.velX=velX;
        }
        public void setVelY (double velY)
        {
            this.velY=velY;
        }
}
