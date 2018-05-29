package Tanks313;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
* Conntects App class with keyboard
 */

public class KeyInput extends KeyAdapter {

    App game;
    public KeyInput(App game)
    {
        this.game=game;
    }

    public  void keyPressed(KeyEvent e)
    {
        game.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        game.keyReleased(e);
    }
}
