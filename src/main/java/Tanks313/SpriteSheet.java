package Tanks313;

import java.awt.image.BufferedImage;

/*
* Class is used to safe getting textures
* from SpriteSheet.
* Every column has got 32 pixels
* Every row has got 32 pixels
 */

public class SpriteSheet {

    private BufferedImage image;
    public  SpriteSheet (BufferedImage image)
    {
        this.image = image;
    }

    public BufferedImage grabImage (int col, int row, int width, int height)
    {
        BufferedImage img = image.getSubimage((col*32)-32, (row*32)-32, width, height);
        return  img;
    }


}
