package Tanks313;

import java.awt.*;
import java.security.PublicKey;

public class Enemy extends GameObject implements EntityB {


static public int speed=1;
private Textures tex;
private App game;
Animation anim;
private Controller c;  //we have to remove enemies from entity

public Enemy(double x, double y, Textures tex, Controller c, App game)
{
    super(x,y);
    this.tex = tex;

    this.c=c;
    this.game = game;

    try
    {
        anim = new Animation(5,tex.enemy[0],tex.enemy[1],tex.enemy[2]);
    }
    catch (Exception e)
    {
       // e.printStackTrace();
    }

}

public void tick()
{
    y+=speed;
//tempEnt- bullet
    for(int i=0; i<game.ea.size(); i++)
    {
        EntityA tempEnt= game.ea.get(i);
        if(Physics.Coliision(this,tempEnt))
        {
            c.removeEntity(tempEnt); //zeby po trafieniu znikal pocisk
            c.removeEntity(this);
            game.setEnemy_killed(game.getEnemy_killed()+1);

        }
    }
    anim.runAnimation();

    if(App.State != App.STATE.GAME)
    {
        delEnemies();

    }
}

    private void delEnemies()
    {
        for(int i=0; i<game.eb.size(); i++)
        {
            c.removeEntity(game.eb.get(i));
        }
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

public static void setSpeed(int s)
{
    speed=s;
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
