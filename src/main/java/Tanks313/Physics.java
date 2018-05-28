package Tanks313;


public class Physics {

   // private static Random random;
   // private Random random = new Random();
    static Bonus bonus = new Bonus();


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
