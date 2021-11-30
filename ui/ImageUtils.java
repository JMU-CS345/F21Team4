import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Utility class for converting back and forth between Image objects and
 * java.awt.image.BufferedImage objects.
 * 
 * @author CS 159 Instructors
 * @version V1.0, 3/7/2021
 * 
 */
public class ImageUtils {

    /**
     * Convert from java.awt.image.BufferedImage to Image.
     * 
     * @param bufferedImage The BufferedImage to convert.
     * @return Equivalent Image.
     */
    public static CustomImage bufferedImageToImage(BufferedImage bufferedImage) {
        CustomImage newImage = new CustomImage(bufferedImage.getWidth(),
                bufferedImage.getHeight());
        Color color;

        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                color = new Color(bufferedImage.getRGB(x, y));
                newImage.setPixel(x, y, new Pixel(color.getRed(),
                        color.getGreen(), color.getBlue()));
            }
        }
        return newImage;

    }

    /**
     * Convert from Image to java.awt.image.BufferedImage.
     * 
     * @param image The Image to convert.
     * @return Equivalent BufferedImage
     */
    public static BufferedImage imageToBufferedImage(CustomImage image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(),
                image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Pixel pixel;
        Color color;

        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                pixel = image.getPixel(x, y);
                color = new Color(pixel.getRed(), pixel.getGreen(),
                        pixel.getBlue());
                bufferedImage.setRGB(x, y, color.getRGB());
            }
        }
        return bufferedImage;

    }
}
