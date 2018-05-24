package Tanks313;

import java.util.LinkedList;

public class Physics {
    public static boolean Coliision(GameObject go, LinkedList<EntityA> enta)
    {
        for (int i=0; i <enta.size(); i++)
        {
            if(go.getBounds(32,32).intersects(enta.get(i).getBounds()));
        }


        return false;
    }

}
