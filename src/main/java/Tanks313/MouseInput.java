package Tanks313;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Desktop;
import java.net.URI;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class MouseInput implements MouseListener{
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int mx=e.getX();
        int my=e.getY();

        //PLAY
        if(mx>=App.getWIDTH()+180 && mx<= App.getWIDTH()+280 && App.State== App.STATE.MENU)
        {
            if(my>=50 && my<=100)
            {
                App.State=App.STATE.GAME;
            }
        }




         if(mx>=App.getWIDTH()+180 && mx<= App.getWIDTH()+280 && App.State== App.STATE.MENU)
        {
            if(my>=150 && my<=200)
            {
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



        //PLAY
        if(mx>=App.getWIDTH()+180 && mx<= App.getWIDTH()+280 && App.State== App.STATE.MENU)
        {
            if(my>=250 && my<=300)
            {
                System.exit(1);
            }
        }

        if(mx>=App.getWIDTH()+70 && mx<= App.getWIDTH()+120 && App.State== App.STATE.END)
        {
            if(my>=50 && my<=100)
            {
                App.State=App.STATE.MENU;

            }

        }
        if(mx>=App.getWIDTH()+70 && mx<= App.getWIDTH()+120 && App.State== App.STATE.END)
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
