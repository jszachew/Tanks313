package Tanks313;

import java.awt.*;

/*
*Enemies class is in EntityB, so can
* collide with Player class and Bullet class
 */

public class Enemy extends GameObject implements EntityB {

static public int speed=1;
private Textures tex;
private App game;
Animation anim;

/*
* Controller is important here-
* Is used for removing enemies from entity
 */
private Controller c;

public Enemy(double x, double y, Textures tex, Controller c, App game)
{
    super(x,y);
    this.tex = tex;

    this.c=c;
    this.game = game;

/*
* Try and catch is important here, because JUnit
* tests dont have to run animation. Just throws Exception
 */

    try
    {
        anim = new Animation(5,tex.enemy[0],tex.enemy[1],tex.enemy[2]);
    }
    catch (Exception e)
    {
       // e.printStackTrace();
    }

}

/*
* Every tick Enemies are going down for "speed"
* pixels.
* It also checks if there is collision with any
* object of EntityA (loop for)
* After collision object disapears.
 */

public void tick()
{
    y+=speed;

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

/*
* If Enemy is out of bounds player get - points
* and loses some stamina.
*
* Enemies are removed from list after disapears
 */

    if(y>(App.getHEIGHT()-20)*2)
    {
        App.addHealth(-10);
        App.addPOINTS(-3);
        c.removeEntity(this);
    }

    anim.runAnimation();

    /*
    * If App is not in game state it doesn't
    * need to have any enemies
     */

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

/*
* Rectangle getter is important for Collisions
 */

    public Rectangle getBounds()
    {
        return new Rectangle ((int)x,(int)y,32,32);
    }

}
