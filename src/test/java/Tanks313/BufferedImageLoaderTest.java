package Tanks313;

import junit.framework.TestCase;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BufferedImageLoaderTest extends TestCase {

    private BufferedImage image;
    String testImagePath = "res/SpriteSheet.png";
    BufferedImageLoader l = new BufferedImageLoader();

    @Override
    protected void setUp() throws Exception {
        image=l.loadImage(testImagePath);
    }

    public void testBufferedImageLoaderWidth() throws IOException
    {

        int width = image.getTileWidth();

        assertEquals(256,width);
    }

    public void testBufferedImageLoaderHeight() throws IOException
    {
        int height = image.getTileHeight();

        assertEquals(256,height);
    }

}