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

public class Menu implements ActionListener {

    JButton bExit = bExit = new JButton("NO CODE!", new ImageIcon("C:/Users/kubas/tanks2/res/exitButton.png"));
    JButton bStart =  bStart = new JButton("NO CODE!", new ImageIcon("C:/Users/kubas/tanks2/res/startButton.png"));
    public static final int WIDTH=320;
    public static final int HEIGHT=WIDTH/12*9;
    public static final int SCALE=2;
    JFrame m = new JFrame();

    public  void render(JFrame g)
     {
         m=g;
         try
         {
             g.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:/Users/kubas/tanks2/res/tank.png")))));
         } catch (IOException e)
         {
             System.out.println("Image not found");
         }

         bStart.setBounds(10, 10, 190, 80);
         bExit.setBounds(10, 10 + 90, 190, 80);
         bStart.setContentAreaFilled(false);
         bExit.setContentAreaFilled(false);
         g.add(bStart);
         g.setResizable(false);
         g.add(bExit);
         bStart.addActionListener( this);
         bExit.addActionListener( this);

         g.pack();

     }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if  (source==bStart)
        {
            System.out.println(new Date()); /* STATE =2, running =1, run()*/

            try
            {
                m.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:/Users/kubas/tanks2/res/noCode.png")))));
            } catch (IOException z)
            {
                System.out.println("Image not found");
            }

            bExit = new JButton("NO CODE!", new ImageIcon("C:/Users/kubas/tanks2/res/abortButton.png"));
            bExit.setBounds((WIDTH*SCALE)/2-100, (HEIGHT * SCALE)/2 + 20, 190, 80);
            m.add(bExit);
            bExit.addActionListener(this);
            m.pack();
        }
        if (source == bExit)
        {
            m.dispose();
        }
    }
}
