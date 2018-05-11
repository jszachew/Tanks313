package Tanks313;

import java.awt.image.BufferedImage;

public class Textures {
    private SpriteSheet ss;
    public BufferedImage player, missile, enemy;

    public Textures(App game)
    {
      ss = new SpriteSheet(game.getSpriteSheet());
      getTextures();
    }

    private void getTextures()
    {
        player = ss.grabImage(1,1,32,32);
        missile = ss.grabImage(2,1,32,32);
        enemy = ss.grabImage(3,1,32,32);
    }
}
