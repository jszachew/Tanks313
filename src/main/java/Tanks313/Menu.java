package Tanks313;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Menu  {

    public Rectangle playButton = new Rectangle(App.WIDTH+180,50,100,50);
    public Rectangle databaseButton = new Rectangle(App.WIDTH+180,150,100,50);
    public Rectangle quitButon = new Rectangle(App.WIDTH+180,250,100,50);

    public void render(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("arial",Font.BOLD,50);
        g.setFont(fnt0);
        g.setColor(Color.WHITE);
        g.drawString("TANKS313", App.WIDTH+50, App.HEIGHT+170);

        Font fnt1= new Font("arial",Font.BOLD,30);
        Font fnt2= new Font("arial",Font.BOLD,25);
        g.setFont(fnt1);
        g.drawString("Play",playButton.x+20, playButton.y+35);
        g.setFont(fnt2);
        g.drawString("Scores",databaseButton.x+10, databaseButton.y+35);
        g.setFont(fnt1);
        g.drawString("Exit",quitButon.x+20, quitButon.y+35);
        g2d.draw(playButton);
        g2d.draw(databaseButton);
        g2d.draw(quitButon);
    }


}
