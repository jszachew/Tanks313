package Tanks313;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject implements EntityA {


    private int speed;

    private Textures tex;
    private App game;



    public  Bullet(double x, double y, Textures tex, App game)
    {
        super(x,y);
        this.speed=10;
        this.tex = tex;
        this.game = game;

    }
    public void tick()
    {
        y-=speed;

        if(Physics.Coliision(this,game.eb))
        {
            System.out.println("Collision");
        }

    }

    public  void render(Graphics g)
    {
        g.drawImage(tex.missile, (int)x, (int)y, null );
    }

    @Override
    public double getX() {
        return x;
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
    public Rectangle getBounds()
    {
        return new Rectangle ((int)x,(int)y,32,32);
    }


}
