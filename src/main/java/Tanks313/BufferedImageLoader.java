package Tanks313;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BufferedImageLoader {
    private BufferedImage image;

/*
* This class is for loading Sheets from resources during init of the game
 */

    public BufferedImage loadImage (String path) throws IOException {
        image = ImageIO.read(new File(path));
        return image;

    }
}
