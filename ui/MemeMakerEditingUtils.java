import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Graphics;

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
  public static BufferedImage rightRotate(CustomImage image)
  {

    CustomImage newImage = new CustomImage(image.getHeight(), image.getWidth());
    for (int y = 0; y < image.getHeight(); y++)
    {
      for (int x = 0; x < image.getWidth(); x++)
      {
        Pixel pixel = image.getPixel(x, y);
        newImage.setPixel(image.getHeight() - y - 1, x, pixel);
      }
    }

    return imageToBufferedImage(newImage);

  }

  /**
   * Rotates the image left in the counter clockwise manner.
   */
  public static BufferedImage leftRotate(CustomImage image)
  {
    CustomImage newImage = new CustomImage(image.getHeight(), image.getWidth());
    for (int y = 0; y < image.getHeight(); y++)
    {
      for (int x = 0; x < image.getWidth(); x++)
      {
        Pixel pixel = image.getPixel(x, y);
        newImage.setPixel(y, image.getWidth() - x - 1, pixel);
      }
    }
    return imageToBufferedImage(newImage);

  }

  /**
   * Mirrors/Flips the image over the x axis
   */
  public static BufferedImage horizontalFlip(CustomImage image)
  {
    CustomImage newImage = new CustomImage(image.getWidth(), image.getHeight());
    for (int y = 0; y < image.getHeight(); y++)
    {
      for (int x = 0; x < image.getWidth(); x++)
      {
        Pixel pixel = image.getPixel(x, y);
        newImage.setPixel(x, image.getHeight() - y - 1, pixel);
      }
    }
    return imageToBufferedImage(newImage);
  }

  /**
   * Mirrors/Flips the image over the y axis
   */
  public static BufferedImage verticalFlip(CustomImage image)
  {
    CustomImage newImage = new CustomImage(image.getWidth(), image.getHeight());
    for (int y = 0; y < image.getHeight(); y++)
    {
      for (int x = 0; x < image.getWidth(); x++)
      {
        Pixel pixel = image.getPixel(x, y);
        newImage.setPixel(image.getWidth() - x - 1, y, pixel);
      }
    }
    return imageToBufferedImage(newImage);
  }

  /**
   * Converts the image to greyscale.
   */
  public static BufferedImage greyScale(CustomImage image)
  {

    CustomImage grayscaled = new CustomImage(image.getWidth(), image.getHeight());

    for (int i = 0; i < image.getWidth(); i++)
    {
      for (int x = 0; x < image.getHeight(); x++)
      {
        Pixel p = image.getPixel(i, x);
        int red = p.getRed();
        int green = p.getGreen();
        int blue = p.getBlue();
        int luminosity = (int) ((0.299 * red) + (0.587 * green) + (0.114 * blue));
        Pixel grayP = new Pixel(luminosity, luminosity, luminosity);
        grayscaled.setPixel(i, x, grayP);
      }
    }

    return imageToBufferedImage(grayscaled);

  }

  /**
   * Raises the brightness of the image by an RGB factor of 15.
   */
  public static BufferedImage brighten(CustomImage image)
  {
    int amount = 15;
    CustomImage adjustedBrightness = new CustomImage(image.getWidth(), image.getHeight());

    for (int i = 0; i < image.getWidth(); i++)
    {
      for (int x = 0; x < image.getHeight(); x++)
      {
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
  public static BufferedImage darken(CustomImage image)
  {

    int amount = 15;
    CustomImage adjustedBrightness = new CustomImage(image.getWidth(), image.getHeight());

    for (int i = 0; i < image.getWidth(); i++)
    {
      for (int x = 0; x < image.getHeight(); x++)
      {
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
  public static BufferedImage dEePfRy(CustomImage image)
  {
    int amount = 32;
    CustomImage adjustedBrightness = new CustomImage(image.getWidth(), image.getHeight());
    int range = (3 - 0) + 0;
    for (int i = 0; i < image.getWidth(); i++)
    {
      for (int x = 0; x < image.getHeight(); x++)
      {
        if (Math.random() < .05)
        {
          Pixel p = image.getPixel(i, x);
          int red = p.getRed() - amount;
          int green = p.getGreen() - amount;
          int blue = p.getBlue() - amount;

          Pixel adjustedP = new Pixel(red, green, blue);
          adjustedBrightness.setPixel(i, x, adjustedP);
        }
        else if (Math.random() > .5)
        {
          Pixel p = image.getPixel(i, x);
          int red = p.getRed() + p.getRed() / 5;
          int green = p.getGreen() + p.getGreen() / 5;
          int blue = p.getBlue() + p.getBlue() / 5;
          Pixel adjustedP = new Pixel(red, green, blue);
          adjustedBrightness.setPixel(i, x, adjustedP);
        }
        else
        {
          Pixel p = image.getPixel(x, x);
          adjustedBrightness.setPixel(i, x, p);
        }
      }
    }

    return imageToBufferedImage(adjustedBrightness);
  }

  public static CustomImage bufferedImageToImage(BufferedImage bufferedImage)
  {
    CustomImage newImage = new CustomImage(bufferedImage.getWidth(), bufferedImage.getHeight());
    Color color;

    for (int y = 0; y < bufferedImage.getHeight(); y++)
    {
      for (int x = 0; x < bufferedImage.getWidth(); x++)
      {
        color = new Color(bufferedImage.getRGB(x, y));
        newImage.setPixel(x, y, new Pixel(color.getRed(), color.getGreen(), color.getBlue()));
      }
    }
    return newImage;

  }

  /**
   * Convert from Image to java.awt.image.BufferedImage.
   * 
   * @param image
   *          The Image to convert.
   * @return Equivalent BufferedImage
   */
  public static BufferedImage imageToBufferedImage(CustomImage image)
  {
    BufferedImage bufferedImage = new BufferedImage(image.getWidth(), image.getHeight(),
        BufferedImage.TYPE_INT_RGB);
    Pixel pixel;
    Color color;

    for (int y = 0; y < bufferedImage.getHeight(); y++)
    {
      for (int x = 0; x < bufferedImage.getWidth(); x++)
      {
        pixel = image.getPixel(x, y);
        color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
        bufferedImage.setRGB(x, y, color.getRGB());
      }
    }
    return bufferedImage;

  }

  public static BufferedImage iconToBufferedImage(Image im)
  {
    BufferedImage bi = new BufferedImage(im.getWidth(null), im.getHeight(null),
        BufferedImage.TYPE_INT_RGB);
    Graphics bg = bi.getGraphics();
    bg.drawImage(im, 0, 0, null);
    bg.dispose();
    return bi;
  }
}
