package Tanks313;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

        private  double x;
        private  double y;

        private  double velX = 0; //velX and vel Y are for smoother movement
        private  double velY = 0;

        private BufferedImage player;

        private Textures tex;

        public Player(double x, double y,Textures tex)
        {
            this.x=x;
            this.y=y;
            this.tex=tex;

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
        }

        public  void render(Graphics g)
        {
            g.drawImage(tex.player,(int)x,(int)y,null);

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
