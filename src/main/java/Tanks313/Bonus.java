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
            if(App.getHEALTH()+50<=App.getMAX_HEALTH())
            {
                App.addHealth(50);
                inProgress=true;
            }

        }

        if(setBonus>=7 && setBonus<=9)
        {
            if(App.getMAX_HEALTH()<=500)
            {
                App.setMaxHealth(50);
                inProgress=true;
            }
        }
        if(setBonus>9 && setBonus<=11)
        {
            if(App.getHEALTH()<=App.getMAX_HEALTH())
            {
                App.setHEALTH(App.getMAX_HEALTH());
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
