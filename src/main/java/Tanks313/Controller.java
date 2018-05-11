package Tanks313;

import java.awt.*;
import java.util.LinkedList;

public class Controller {

    private LinkedList<Bullet> b= new LinkedList<Bullet>();
    Bullet TempBullet;

    App game;

    public Controller(App game)
    {
        this.game=game;
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
    }

    public void  render(Graphics g)
    {
        for (int i=0; i< b.size(); i++)
        {
            TempBullet=b.get(i);
            TempBullet.render(g);
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
}
