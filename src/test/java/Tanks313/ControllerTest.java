package Tanks313;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;

public class ControllerTest extends TestCase {

    App game = new App();
    Controller c= new Controller(game);

    public void testController() throws IOException {
        game.init();
        for (int i=0; i<10; i++)
        {
            c.addBullet(new Bullet(100,300, game));
        }
        assertEquals(10,c.getListSize());
    }

    public void testRemoving() throws InterruptedException {

        assertEquals(0,c.getListSize());
    }

}