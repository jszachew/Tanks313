package Tanks313;

import java.awt.*;
import java.security.PublicKey;

public class Enemy {

private  double x,y;
private int speed;
private Textures tex;

public Enemy(double x, double y, Textures tex)
{
    this.x = x;
    this.y =y;
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

}
