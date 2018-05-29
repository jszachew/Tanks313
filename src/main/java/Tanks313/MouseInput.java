package Tanks313;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Desktop;
import java.net.URI;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

/*
* Mouse controller encapsulated to new class.
* Checks State of App and adapts mouse controller to
* case.
* Uses awt MouseListener
 */

public class MouseInput implements MouseListener{
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int mx=e.getX();
        int my=e.getY();

        /*
        * MENU STATE
         */
        if(mx>=App.getWIDTH()+180 && mx<= App.getWIDTH()+280 && App.State== App.STATE.MENU)
        {
            if(my>=50 && my<=100)
            {
                /*
                * START THE GAME BUTTON
                 */
                App.State=App.STATE.GAME;
            }
        }


         if(mx>=App.getWIDTH()+180 && mx<= App.getWIDTH()+280 && App.State== App.STATE.MENU)
        {
            if(my>=150 && my<=200)
            {
                /*
                * SCORE BUTTON
                * Launch php script getting and showing data from database.
                 */
                Desktop d= Desktop.getDesktop();
                try {
                    d.browse(new URI("http://localhost/show.php"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }

            }

        }

        if(mx>=App.getWIDTH()+180 && mx<= App.getWIDTH()+280 && App.State== App.STATE.MENU)
        {
            if(my>=250 && my<=300)
            {
                /*
                * Exit button
                 */
                System.exit(1);
            }
        }

        /*
        * END OD MENU STATE
         */

        /*
        * END STATE
         */

        if(mx>=App.getWIDTH()+70 && mx<= App.getWIDTH()+120 && App.State== App.STATE.END)
        {
            if(my>=50 && my<=100)
            {
                /*
                * Menu button
                 */
                App.State=App.STATE.MENU;

            }

        }
        if(mx>=App.getWIDTH()+70 && mx<= App.getWIDTH()+120 && App.State== App.STATE.END)
        {
            if(my>=150 && my<=200)
            {
                /*
                * Exit button
                 */
                System.exit(1);
            }

        }

        /*
        * END OF END STATE
         */


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
