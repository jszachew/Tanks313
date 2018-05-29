package Tanks313;

import java.util.Random;

/*
* This class if for generating random bonuses for player
* 7%- extra 50 points of stamina
* 3%- extra 50 to max stamina
* 1%- regenetion all stamina
 */

public class Bonus {

    public Bonus(){}
    long now;
    boolean inProgress=false;

    public void setBonus()
    {
/*
* Generate random number
 */
        int setBonus= random();
/*
* extra 50 stamina
 */
        if(setBonus>=0 && setBonus <7)
        {
            if(App.getHEALTH()+50<=App.getMAX_HEALTH())
            {
                App.addHealth(50);
                inProgress=true;
            }

        }
/*
* bigger max stamina
 */
        if(setBonus>=7 && setBonus<=9)
        {
            if(App.getMAX_HEALTH()<=500)
            {
                App.setMaxHealth(50);
                inProgress=true;
            }
        }

/*
* 100% regaratarion
 */
        if(setBonus==9)
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
