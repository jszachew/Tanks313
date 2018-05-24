package Tanks313;

import java.awt.*;
import java.security.PublicKey;

public class Enemy extends GameObject implements EntityB {


private int speed;
private Textures tex;
private App game;
Animation anim;
private Controller c;  //we have to remove enemies from entity

public Enemy(double x, double y, Textures tex, Controller c, App game)
{
    super(x,y);
    this.tex = tex;
    speed =1;
    this.c=c;
    this.game = game;

    anim = new Animation(5,tex.enemy[0],tex.enemy[1],tex.enemy[2]);

}

public void tick()
{
    y+=speed;

    if(Physics.Coliision(this,game.ea))
    {
        c.removeEntity(this);
        game.setEnemy_killed(game.getEnemy_killed()+1);

    }
    anim.runAnimation();
}

public double getX()
{
    return x;
}

public double getY()
{
    return y;
}

public int getSpeed()
{
    return  speed;
}

public void setSpeed(int s)
{
    this.speed=s;
}

public void render(Graphics g)
{
anim.drawAnimation(g,x,y,0);
}

    public Rectangle getBounds()
    {
        return new Rectangle ((int)x,(int)y,32,32);
    }

}
