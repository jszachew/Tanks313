package Tanks313;

import java.awt.*;

/*
* This class is for showing the end of game.
* It only shows some buttons.
* At the end of game main App class sets
* points reached.
*
* It is much safer method, because player has
* no way to call static methods.
*
* It also shows reached points.
*
* Mouse control is defined in MouseInput class.
 */

public class TheEnd {

    public Rectangle playButton = new Rectangle(App.getWIDTH()+70,50,100,50);
    public Rectangle quitButon = new Rectangle(App.getWIDTH()+70,150,100,50);

    private int points=0;

    public void render(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        String m=String.valueOf(points);

        Font fnt1= new Font("arial",Font.BOLD,30);
        Font fnt3= new Font("arial",Font.BOLD,100);

        g.setFont(fnt1);
        g.drawString("MENU",playButton.x+5, playButton.y+35);
        g.drawString("EXIT",quitButon.x+20, quitButon.y+35);

        g.setColor(Color.RED);
        g.drawString(m,160,375);

        g.setColor(Color.WHITE);
        g2d.draw(playButton);
        g2d.draw(quitButon);
    }

    public void setPoints() {
        points=App.getPOINTS();
    }
}
