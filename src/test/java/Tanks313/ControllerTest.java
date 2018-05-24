package Tanks313;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;

public class ControllerTest extends TestCase {

    App game = new App();
    Textures tex;
    Controller c= new Controller(tex,game);
    Controller enemy = new Controller(tex,game);


    public void testControllerBullet() throws IOException {
        game.init();
        for (int i=0; i<10; i++)
        {
            c.addEntity(new Bullet(100,300, tex,game));
        }
        assertEquals(10,c.getEntitySize());
    }

    public void testRemovingBullet() throws InterruptedException {
        wait(100);
        assertEquals(0,c.getEntitySize());
    }

    public void testControllerEnemy() throws IOException {
        game.init();
        for (int i=0; i<10; i++)
        {
            enemy.addEntity(new Enemy(i,0, tex,c,game));
        }
        assertEquals(10,enemy.getEntitySize());
    }

    public void testRemovingEnemy() throws InterruptedException {

        assertEquals(0,enemy.getEntitySize());
    }


}