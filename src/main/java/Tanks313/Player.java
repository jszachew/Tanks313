package Tanks313;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject implements EntityA{



        private  double velX = 0; //velX and vel Y are for smoother movement
        private  double velY = 0;

        Animation anim;
        private BufferedImage player;

        private Textures tex;

        public Player(double x, double y,Textures tex)
        {
            super(x,y);
            this.tex=tex;
            //                   v-speed of anim
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
            if(x>=(App.WIDTH*App.SCALE)-32)
            {
                x=(App.WIDTH*App.SCALE)-32;
            }
            if(y<=0)
            {
                y=0;
            }
            if(y>=(App.HEIGHT*App.SCALE)-80)
            {
                y=(App.HEIGHT*App.SCALE)-80;
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
