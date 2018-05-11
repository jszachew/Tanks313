package Tanks313;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {

    private double x;
    private  double y;
    private int speed;

    BufferedImage image;

    public  Bullet(double x, double y, App game)
    {
        this.x=x;
        this.y=y;
        this.speed=10;

        SpriteSheet ss= new SpriteSheet( game.getSpriteSheet());

        image = ss.grabImage(2,1,32,32);
    }
    public void tick()
    {
        y-=speed;
    }

    public  void render(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, null );
    }
    public void setSpeed(int speed)
    {
        this.speed=speed;
    }
    public int getSpeed()
    {
        return speed;
    }
    public double getY()
    {
        return y;
    }


}
