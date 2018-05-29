package Tanks313;

import java.awt.image.BufferedImage;

/*
* This class is for setting textures for every object
* from .png file
* Every texture is rectangle 32x32 pixels
* in /res/SpriteSheet.png
* All movable classes gas got three textures for
* animation
 */

public class Textures {

    private SpriteSheet ss;

    public BufferedImage[] player= new BufferedImage[3];
    public BufferedImage[] missile= new BufferedImage[3];
    public BufferedImage[] enemy= new BufferedImage[3];

    public Textures(App game)
    {
      ss = new SpriteSheet(game.getSpriteSheet());
      getTextures();
    }

    /*
    * For every movable class it takes 3
    * textures from SpriteSheet.
    * Every class has own column.
     */
    private void getTextures()
    {
        player[0] = ss.grabImage(1,1,32,32);
        player[1] = ss.grabImage(1,2,32,32);
        player[2] = ss.grabImage(1,3,32,32);

        missile[0] = ss.grabImage(2,1,32,32);
        missile[1] = ss.grabImage(2,2,32,32);
        missile[2] = ss.grabImage(2,3,32,32);

        enemy[0] = ss.grabImage(3,1,32,32);
        enemy[1] = ss.grabImage(3,2,32,32);
        enemy[2] = ss.grabImage(3,3,32,32);
    }
}
