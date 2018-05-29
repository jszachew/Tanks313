package Tanks313;

/*
* Main class implementing collisions.
* In every tick App checks if there is collision
* between objects from EntityA and EntityB
*
* If EntityB intesects in EntityA it means
* that Enemy is shooten
*
* If EntityA intesects in EntityB it means
* that Player crashes with Enemie
 */

public class Physics {

    static Bonus bonus = new Bonus();

    /*
    * We don't need to think who intersects on who
    * because methods Collision is reloaded, so it
    * adapts itself automatically- clever!
     */

    public static boolean Coliision(EntityA enta, EntityB entb)
    {

            if(enta.getBounds().intersects(entb.getBounds()))
            {
                if(App.getPOINTS() -5<0)
                {
                    App.setPOINTS(0);
                }
                else
                {
                    App.addPOINTS(-5);
                }

             return true;

            }

        return false;
    }

    public static boolean Coliision(EntityB entb, EntityA enta)
    {
            if(entb.getBounds().intersects(enta.getBounds()))
            {
                bonus.setBonus();
                return true;
            }


        return false;
    }


}
