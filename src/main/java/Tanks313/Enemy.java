package Tanks313;

import java.awt.*;
import java.security.PublicKey;

public class Enemy extends GameObject implements EntityB {


private int speed;
private Textures tex;

public Enemy(double x, double y, Textures tex)
{
    super(x,y);
    this.tex = tex;
    speed =1;

}

public void tick()
{
    y+=speed;
}

public double getX()
{
    return x;
}

public double getY()
{
    return y;
}

public int getSpeed()
{
    return  speed;
}

public void setSpeed(int s)
{
    this.speed=s;
}

public void render(Graphics g)
{
g.drawImage(tex.enemy,(int)x,(int)y, null);
}

    public Rectangle getBounds()
    {
        return new Rectangle ((int)x,(int)y,32,32);
    }

}
