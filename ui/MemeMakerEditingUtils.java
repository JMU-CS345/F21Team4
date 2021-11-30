import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * This class contains all of the editing functionality for the memeMaker.
 * 
 * @author Matt Wong, Zach Tucker, Thomas Mandell, Alex Polivka, Jonathan Wist
 *
 */

public class MemeMakerEditingUtils
{
  /**
   * Rotates the image right in the clockwise manner.
   */
  public static BufferedImage rightRotate(Image image)
  {
	  
      Image newImage = new Image(image.getHeight(), image.getWidth());
      for (int y = 0; y < image.getHeight(); y++) {
          for (int x = 0; x < image.getWidth(); x++) {
              Pixel pixel = image.getPixel(x, y);
              newImage.setPixel(image.getHeight() - y - 1, x, pixel);
          }
      }
      return imageToBufferedImage(newImage);

  }

  /**
   * Rotates the image left in the counter clockwise manner.
   */
  public static BufferedImage leftRotate(Image image)
  {
      Image newImage = new Image(image.getHeight(), image.getWidth());
      for (int y = 0; y < image.getHeight(); y++) {
          for (int x = 0; x < image.getWidth(); x++) {
              Pixel pixel = image.getPixel(x, y);
              newImage.setPixel(y, image.getWidth() - x - 1, pixel);
          }
      }
      return imageToBufferedImage(newImage);

  }

  /**
   * Mirrors/Flips the image over the horizontal axis
   */
  public static BufferedImage horizontalFlip(Image image)
  {
	  return null;
  }

  /**
   * Mirrors/Flips the image over the vertical axis
   */
  public static BufferedImage verticalFlip(Image image)
  {
	  return null;
  }

  /**
   * Converts the image to greyscale.
   */
  public static BufferedImage greyScale(Image image)
  {
	  
      Image grayscaled = new Image(image.getWidth(), image.getHeight());

      for (int i = 0; i < image.getWidth(); i++) {
          for (int x = 0; x < image.getHeight(); x++) {
              Pixel p = image.getPixel(i, x);
              int red = p.getRed();
              int green = p.getGreen();
              int blue = p.getBlue();
              int luminosity = (int) ((0.299 * red) + (0.587 * green)
                      + (0.114 * blue));
              Pixel grayP = new Pixel(luminosity, luminosity, luminosity);
              grayscaled.setPixel(i, x, grayP);
          }
      }

      return imageToBufferedImage(grayscaled);

  }

  /**
   * Raises the brightness of the image by an RGB factor of 15.
   */
  public static BufferedImage brighter(Image image)
  {
	  int amount = 15;
      Image adjustedBrightness = new Image(image.getWidth(),
              image.getHeight());

      for (int i = 0; i < image.getWidth(); i++) {
          for (int x = 0; x < image.getHeight(); x++) {
              Pixel p = image.getPixel(i, x);
              int red = p.getRed() + amount;
              int green = p.getGreen() + amount;
              int blue = p.getBlue() + amount;

              Pixel adjustedP = new Pixel(red, green, blue);
              adjustedBrightness.setPixel(i, x, adjustedP);
          }
      }

      return imageToBufferedImage(adjustedBrightness);

  }

  /**
   * Lowers the brightness of the image by am RGB factor of 15.
   */
  public static BufferedImage darken(Image image)
  {
	  
	  int amount = 15;
      Image adjustedBrightness = new Image(image.getWidth(),
              image.getHeight());

      for (int i = 0; i < image.getWidth(); i++) {
          for (int x = 0; x < image.getHeight(); x++) {
              Pixel p = image.getPixel(i, x);
              int red = p.getRed() - amount;
              int green = p.getGreen() - amount;
              int blue = p.getBlue() - amount;

              Pixel adjustedP = new Pixel(red, green, blue);
              adjustedBrightness.setPixel(i, x, adjustedP);
          }
      }

      return imageToBufferedImage(adjustedBrightness);

  }

  /**
   * "Deep Fries"/distorts an image by tinting the image red and yellow, lowers the image quality,
   * and runs the image through a series of filters.
   */
  public static BufferedImage dEePfRy(Image image)
  {
	  return null;
  }
  
  
  /**
   * Converts a bufferedImage to an image
   * @param bufferedImage
   * @return
   */
  public static Image bufferedImageToImage(BufferedImage bufferedImage) {
      Image newImage = new Image(bufferedImage.getWidth(),
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
   * 
   * @param image
   * @return
   */
  public static BufferedImage imageToBufferedImage(Image image) {
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
