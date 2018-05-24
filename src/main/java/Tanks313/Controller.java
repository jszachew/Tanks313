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
    public Controller(Textures tex)
    {
        this.tex=tex;

        addEntity(new Enemy(r.nextInt(App.WIDTH*App.SCALE),0,tex));
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


    public int getEntitySize()
    {
        return ea.size();
    }
}
