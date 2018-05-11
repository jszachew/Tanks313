package Tanks313;

import java.awt.*;
import java.util.LinkedList;

public class Controller {

    private LinkedList<Bullet> b= new LinkedList<Bullet>();
    private LinkedList<Enemy> e = new LinkedList<Enemy>();
    Bullet TempBullet;
    Enemy TempEnemy;
    App game;
    Textures tex;
    public Controller(App game,Textures tex)
    {
        this.game=game;
        this.tex=tex;
        for (int x=0; x<(App.WIDTH * App.SCALE ); x+=64)
        {
            addEnemy((new Enemy(x,0,tex)));
        }
    }

    public  void tick()
    {
         for (int i=0; i< b.size(); i++)
        {
            TempBullet=b.get(i);
            if(TempBullet.getY() < 0)
            {
                removeBullet(TempBullet);
            }
            TempBullet.tick();
        }
        for (int i=0; i< e.size(); i++)
        {
            TempEnemy=e.get(i);
            if(TempEnemy.getY() > 480)
            {
                removeEnemy(TempEnemy);
            }
            TempEnemy.tick();
        }

    }

    public void  render(Graphics g)
    {
        for (int i=0; i< b.size(); i++)
        {
            TempBullet=b.get(i);
            TempBullet.render(g);
        }
        for (int i=0; i< e.size(); i++)
        {
            TempEnemy=e.get(i);
            TempEnemy.render(g);
        }
    }

    public void addBullet(Bullet toAdd)
    {
        b.add(toAdd);
    }
    public void removeBullet(Bullet toRemove)
    {
        b.remove(toRemove);
    }
    public int getListSize()
    {
        return b.size();
    }
    public void addEnemy(Enemy toAdd)
    {
        e.add(toAdd);
    }

    public void setEnemySpeed(int s)
    {
        for(int i=0; i<e.size(); i++)
        {
            e.get(i).setSpeed(s);
        }
    }
    public void removeEnemy(Enemy toRemove)
    {
        e.remove(toRemove);
    }
    public int getEnemyListSize()
    {
        return e.size();
    }
}
