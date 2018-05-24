package Tanks313;



import java.util.LinkedList;

public class Physics {

    public static boolean Coliision(EntityA enta, LinkedList<EntityB> entb)
    {
        for (int i=0; i <entb.size(); i++)
        {
            if(enta.getBounds().intersects(entb.get(i).getBounds()))
            {
             return true;
            }
        }

        return false;
    }

    public static boolean Coliision(EntityB entb, LinkedList<EntityA> enta)
    {
        for (int i=0; i <enta.size(); i++)
        {
            if(entb.getBounds().intersects(enta.get(i).getBounds()))
            {
                return true;
            }
        }

        return false;
    }


}
