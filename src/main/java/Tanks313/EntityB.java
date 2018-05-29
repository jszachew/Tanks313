package Tanks313;

import java.awt.*;

/*
 * Interfaces EntityA and EntityB looks simmilar.
 * Methods required to implement are the same, but
 * by them wy can distinguish group of object.
 * It is important for collision- this way
 * we can avoid data redundacy by having
 * all object just in two lists
 */

public interface EntityB {

    void tick();
    void render(Graphics g);
    Rectangle getBounds();

    double getX();
    double getY();
}
