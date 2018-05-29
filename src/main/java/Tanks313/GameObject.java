package Tanks313;

import java.awt.*;

/*
* This class has got only information about possition of objects
* It also returns rectangle of our object-
* important for collioson and Physics class
 */

public class GameObject {

    public  double x,y;

    public GameObject(double x,double y)
    {
        this.x = x;
        this.y = y;
    }

    public Rectangle getBounds(int width, int height)
    {
        return new Rectangle ((int)x,(int)y,width,height);
    }
}
