package Tanks313;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {

    private double x;
    private  double y;
    private int speed;

    private Textures tex;



    public  Bullet(double x, double y, Textures tex)
    {
        this.x=x;
        this.y=y;
        this.speed=10;
        this.tex = tex;

    }
    public void tick()
    {
        y-=speed;
    }

    public  void render(Graphics g)
    {
        g.drawImage(tex.missile, (int)x, (int)y, null );
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
