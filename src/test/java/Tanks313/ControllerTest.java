package Tanks313;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;

public class ControllerTest extends TestCase {

    App game = new App();
    Textures tex;
    Controller c= new Controller(game,tex);


    public void testControllerBullet() throws IOException {
        game.init();
        for (int i=0; i<10; i++)
        {
            c.addBullet(new Bullet(100,300, tex));
        }
        assertEquals(10,c.getListSize());
    }

    public void testRemovingBullet() throws InterruptedException {

        assertEquals(0,c.getListSize());
    }

    public void testControllerEnemy() throws IOException {
        game.init();
        for (int i=0; i<10; i++)
        {
            c.addEnemy(new Enemy(i,0, tex));
        }
        assertEquals(10,c.getEnemyListSize());
    }

    public void testRemovingEnemy() throws InterruptedException {

        assertEquals(0,c.getEnemyListSize());
    }


}