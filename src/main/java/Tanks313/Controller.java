package Tanks313;

import java.awt.*;
  import java.util.LinkedList;
import java.util.Random;

/*
* Main class to manage all game objects
* All of them are in two linked list.
 */

public class Controller {

    /*
    * By using Entities A and B we need to have only
    * two lists of objects.
    * Adding new clases is easy- just implement
    * good interface.
    * EntityA crashes EntityB and so on
     */
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

    }

    /*
    * Every tick and render methodd call
    * methods of its classes, so more exciting
    * things are in objects classes
     */

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

    /*
    * addEntity makes connection between
    * App and object class
    * It is not directly removing and adding.
    * Everything goes through the controller.
    * (FASADA)
     */

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
