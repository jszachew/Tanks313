package Tanks313;

import java.awt.*;
  import java.util.LinkedList;
import java.util.Random;

public class Controller {

    private LinkedList<EntityA> ea= new LinkedList<EntityA>();
    private LinkedList<EntityB> eb= new LinkedList<EntityB>();
    EntityA enta;
    EntityB entb;
    Textures tex;
    Random r = new Random();
    private App game;
    public Controller(Textures tex, App game)
    {
        this.tex=tex;
        this.game = game;

       // addEntity(new Enemy(r.nextInt(App.WIDTH*App.SCALE),0,tex,this,game));
    }

    public  void tick()
    {
        for(int i=0; i<ea.size();i++)
        {
            enta = ea.get(i);
            enta.tick();
        }

        for(int i=0; i<eb.size();i++)
        {
            entb = eb.get(i);
            entb.tick();

        }

    }

    public void  render(Graphics g)
    {
        for(int i=0; i<ea.size();i++)
        {
            enta = ea.get(i);
            enta.render(g);
        }

        for(int i=0; i<eb.size();i++)
        {
            entb = eb.get(i);
            entb.render(g);
        }
    }

    public void addEntity(EntityA toAdd)
    {
        ea.add(toAdd);
    }

   public void clearEnemies()
   {
       for(int i=0; i<eb.size();i++)
       {
           eb.remove(eb.get(i));
       }
   }

    public void removeEntity(EntityA toRemove)
    {
        ea.remove(toRemove);
    }

    public void addEntity(EntityB toAdd)
    {
        eb.add(toAdd);
    }

    public void removeEntity(EntityB toRemove)
    {
        eb.remove(toRemove);
    }


    public int getEntityBSize()
    {
        return eb.size();
    }

    public int getEntityASize()
    {
        return ea.size();
    }

    public LinkedList<EntityA> getEntityA() {
        return ea;
    }

    public LinkedList<EntityB> getEntityB() {
        return eb;
    }
}
