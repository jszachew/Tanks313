package Tanks313;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
* this class show how bullet works in game
* They are in EntityA, so can destory objects in EntityB (Enemies)
 */

public class Bullet extends GameObject implements EntityA {


    private int speed;

    private Textures tex;
    private App game;

    Animation anim;



    public  Bullet(double x, double y, Textures tex, App game)
    {
        super(x,y);
        this.speed=10;
        this.tex = tex;
        this.game = game;

        try
        {
            anim = new Animation(5,tex.missile[0],tex.missile[1],tex.missile[2]);
        }
       catch (Exception e)
       {
           e.printStackTrace();
       }

    }

    /*
    * Every bullet in every tick goes upwards by speed pixels.
    *
    * Collision is not implemened there because it is implementad in
    * Enemy class. If bullet hits Enemy it means the same as
    * Enemy hits the bullet
     */
    public void tick()
    {
        y-=speed;
        anim.runAnimation();

    }

    public  void render(Graphics g)
    {
        anim.drawAnimation(g,x,y,0);
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
