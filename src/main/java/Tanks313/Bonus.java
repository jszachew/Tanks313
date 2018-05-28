package Tanks313;

import java.util.Random;

public class Bonus {

    public Bonus(){}
    long now;
    boolean inProgress=false;

    public void setBonus()
    {
        int setBonus= random();
        if(setBonus>=0 && setBonus <7)
        {
            if(App.HEALTH+50<=App.MAX_HEALTH)
            {
                App.HEALTH+=50;
                inProgress=true;
            }
            else
            {
                App.HEALTH=App.MAX_HEALTH;
                inProgress=true;
            }
        }

        if(setBonus>=7 && setBonus<=9)
        {
            if(App.MAX_HEALTH<=500)
            {
                App.MAX_HEALTH+=50;
                inProgress=true;
            }
        }
        if(setBonus>9 && setBonus<=11)
        {
            if(App.HEALTH<=App.MAX_HEALTH)
            {
                App.HEALTH=App.MAX_HEALTH;
                inProgress=true;
            }
        }


    }

    private int random()
    {
        Random random = new Random();
        return random.nextInt(100);
    }

}
