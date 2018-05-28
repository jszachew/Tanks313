package Tanks313;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MouseInput implements MouseListener{
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int mx=e.getX();
        int my=e.getY();
        /*
    public Rectangle playButton = new Rectangle(App.WIDTH+180,50,100,50);
    public Rectangle databaseButton = new Rectangle(App.WIDTH+180,150,100,50);
    public Rectangle quitButon = new Rectangle(App.WIDTH+180,250,100,50);
         */

        //PLAY
        if(mx>=App.WIDTH+180 && mx<= App.WIDTH+280 && App.State== App.STATE.MENU)
        {
            if(my>=50 && my<=100)
            {
                App.State=App.STATE.GAME;
            }
        }




         if(mx>=App.WIDTH+180 && mx<= App.WIDTH+280 && App.State== App.STATE.MENU)
        {
            if(my>=150 && my<=200)
            {
                App.State=App.STATE.GAME;

            }

        }





        //PLAY
        if(mx>=App.WIDTH+180 && mx<= App.WIDTH+280 && App.State== App.STATE.MENU)
        {
            if(my>=250 && my<=300)
            {
                System.exit(1);
            }
        }

        if(mx>=App.WIDTH+70 && mx<= App.WIDTH+120 && App.State== App.STATE.END)
        {
            if(my>=50 && my<=100)
            {
                App.State=App.STATE.MENU;

            }

        }
        if(mx>=App.WIDTH+70 && mx<= App.WIDTH+120 && App.State== App.STATE.END)
        {
            if(my>=150 && my<=200)
            {
                System.exit(1);
            }

        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
