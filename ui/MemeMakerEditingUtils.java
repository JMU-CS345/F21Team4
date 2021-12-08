import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * This class contains all of the editing functionality for the memeMaker.
 * 
 * @author Matt Wong, Zach Tucker, Thomas Mandell, Alex Polivka, Jonathan Wist
 *
 */

public class MemeMakerEditingUtils
{
  // Declaring the text graphics
  private static Graphics2D line1;
  private static Graphics2D line2;
  private static Graphics2D textGraphic;

  /**
   * Rotates the image right in the clockwise manner.
   * 
   * @param image
   *          The initial CustomImage object
   * @return The edited BufferdImage version of the initial image
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

    return customImgToBuffered(newImage);

  }

  /**
   * Rotates the image left in the counter clockwise manner.
   * 
   * @param image
   *          The initial CustomImage object
   * @return The edited BufferdImage version of the initial image
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
    return customImgToBuffered(newImage);

  }

  /**
   * Mirrors/Flips the image over the x axis
   * 
   * @param image
   *          The initial CustomImage object
   * @return The edited BufferdImage version of the initial image
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
    return customImgToBuffered(newImage);
  }

  /**
   * Mirrors/Flips the image over the y axis
   * 
   * @param image
   *          The initial CustomImage object
   * @return The edited BufferdImage version of the initial image
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
    return customImgToBuffered(newImage);
  }

  /**
   * Converts the image to greyscale.
   * 
   * @param image
   *          The initial CustomImage object
   * @return The edited BufferdImage version of the initial image
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

    return customImgToBuffered(grayscaled);

  }

  /**
   * Raises the brightness of the image by an RGB factor of 15.
   * 
   * @param image
   *          The initial CustomImage object
   * @return The edited BufferdImage version of the initial image
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

    return customImgToBuffered(adjustedBrightness);

  }

  /**
   * Lowers the brightness of the image by am RGB factor of 15.
   * 
   * @param image
   *          The initial CustomImage object
   * @return The edited BufferdImage version of the initial image
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

    return customImgToBuffered(adjustedBrightness);

  }

  /**
   * "Deep Fries"/distorts an image by tinting the image red and yellow, lowers the image quality,
   * and runs the image through a series of filters.
   * 
   * @param image
   *          The initial CustomImage object
   * @return The edited BufferdImage version of the initial image
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

    return customImgToBuffered(adjustedBrightness);
  }

  /**
   * Converts a BufferedImage into a CustomImage allowing us to edit the individual pixels
   * 
   * @param bufferedImage
   *          The initial image
   * @return The initial image as a CustomImage
   */
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
   * Takes a Buffered Image and adds text to the top of it.
   * 
   * @param image
   *          The initial image
   * @param topText
   *          The text you want to add
   * @return The initial image with the text
   */
  // fix this over lay not working
  public static BufferedImage setTopText(BufferedImage image, String topText, Color textColor,
      String fontType)
  {
    // Checks if the line is too long
    line1 = (Graphics2D) image.getGraphics();
    line1.setFont(new Font(fontType, Font.PLAIN, 30));

    double singleCharSize = (line1.getFontMetrics().getStringBounds("X", line1).getWidth());

    double maxNumCount = (image.getWidth() / singleCharSize) + 5;

    if (topText.length() > maxNumCount)
    {
      // Finds the point where you break the text in half
      int breakPoint = findBreak(topText, (int) maxNumCount);

      // Declares the line graphics
      line1 = (Graphics2D) image.getGraphics();
      line2 = (Graphics2D) image.getGraphics();

      // Sets the initial text colors
      line1.setColor(textColor);
      line2.setColor(textColor);

      // Sets the initial font
      line1.setFont(new Font(fontType, Font.PLAIN, 30));
      line2.setFont(new Font(fontType, Font.PLAIN, 30));

      // Finds the centers of the Strings themselves
      int line1TextCenter = (int) line1.getFontMetrics()
          .getStringBounds(topText.substring(0, breakPoint), line1).getWidth();

      int line2TextCenter = (int) line2.getFontMetrics()
          .getStringBounds(topText.substring(breakPoint), line2).getWidth();

      // Calculates the lines's X-values
      int line1Xval = (image.getWidth() / 2) - (line1TextCenter / 2);

      int line2Xval = (image.getWidth() / 2) - (line2TextCenter / 2);

      // Calculates the lines's Y-values
      double line1Yval = image.getHeight() * .1;

      double line2Yval = line1Yval + (int) line2.getFontMetrics()
          .getStringBounds(topText.substring(breakPoint), line2).getHeight();

      // Draws the graphics
      line1.drawString(topText.substring(0, breakPoint), line1Xval, (int) line1Yval);

      line2.drawString(topText.substring(breakPoint), line2Xval, (int) line2Yval);

      return image;
    }
    else
    {
      // Declares and initializes graphics
      textGraphic = (Graphics2D) image.getGraphics();

      // Sets the text to its default color and font
      textGraphic.setColor(textColor);
      textGraphic.setFont(new Font(fontType, Font.PLAIN, 30));

      // Finds the X-value of the text
      int TextCenter = (int) textGraphic.getFontMetrics().getStringBounds(topText, textGraphic)
          .getWidth();

      int textXval = (image.getWidth() / 2) - (TextCenter / 2);

      // Finds the Y-value of the text
      double textYval = (image.getHeight() * .05)
          + (int) textGraphic.getFontMetrics().getStringBounds(topText, textGraphic).getHeight();

      // Draws the graphic
      textGraphic.drawString(topText, textXval, (int) textYval);

      return image;
    }
  }

  /**
   * Takes a Buffered Image and adds text to the bottom of it.
   * 
   * @param image
   *          The initial image
   * @param bottomText
   *          The text you want to add
   * @return The initial image with the text
   */
  public static BufferedImage setBottomText(BufferedImage image, String bottomText, Color textColor,
      String fontType)
  {
    // Checks if the line is too long
    line1 = (Graphics2D) image.getGraphics();
    line1.setFont(new Font(fontType, Font.PLAIN, 30));

    double singleCharSize = (line1.getFontMetrics().getStringBounds("X", line1).getWidth());

    double maxNumCount = (image.getWidth() / singleCharSize) + 5;
    // This is a very long piece of text

    if (bottomText.length() > maxNumCount)
    {
      // Finds the point where you break the text in half
      int breakPoint = findBreak(bottomText, (int) maxNumCount);

      // Declares the line graphics
      line1 = (Graphics2D) image.getGraphics();
      line2 = (Graphics2D) image.getGraphics();

      // Sets the initial text colors
      line1.setColor(textColor);
      line2.setColor(textColor);

      // Sets the initial font
      line1.setFont(new Font(fontType, Font.PLAIN, 30));
      line2.setFont(new Font(fontType, Font.PLAIN, 30));

      // Finds the centers of the Strings themselves
      int line1TextCenter = (int) line1.getFontMetrics()
          .getStringBounds(bottomText.substring(0, breakPoint), line1).getWidth();

      int line2TextCenter = (int) line2.getFontMetrics()
          .getStringBounds(bottomText.substring(breakPoint), line2).getWidth();

      // Calculates the lines's X-values
      int line1Xval = (image.getWidth() / 2) - (line1TextCenter / 2);

      int line2Xval = (image.getWidth() / 2) - (line2TextCenter / 2);

      // Calculates the lines's Y-values
      double line1Yval = (image.getHeight() * .90) - (int) line2.getFontMetrics()
          .getStringBounds(bottomText.substring(breakPoint), line2).getHeight();

      double line2Yval = image.getHeight() * .90;

      // Draws the graphic
      line1.drawString(bottomText.substring(0, breakPoint), line1Xval, (int) line1Yval);

      line2.drawString(bottomText.substring(breakPoint), line2Xval, (int) line2Yval);

      return image;
    }
    else
    {
      // Declares and initializes graphics
      textGraphic = (Graphics2D) image.getGraphics();

      // Sets the text to its default color and font
      textGraphic.setColor(textColor);
      textGraphic.setFont(new Font(fontType, Font.PLAIN, 30));

      // Finds the X-value of the text
      int TextCenter = (int) textGraphic.getFontMetrics().getStringBounds(bottomText, textGraphic)
          .getWidth();

      int textXval = (image.getWidth() / 2) - (TextCenter / 2);

      // Finds the Y-value of the text
      double textYval = (image.getHeight() * .95)
          - (int) textGraphic.getFontMetrics().getStringBounds(bottomText, textGraphic).getHeight();

      // Draws the graphics
      textGraphic.drawString(bottomText, textXval, (int) textYval);
      textGraphic.getBackground();
      return image;
    }
  }

  /**
   * Takes a string of text and the maximum number of lines and breaks it at the first space it can
   * find from the maximum line.
   * 
   * @param text
   *          The text that you are breaking
   * @param maxCount
   *          The maximum number of characters per line
   * @return The index at which to break
   */
  public static int findBreak(String text, int maxCount)
  {
    String shortenedString = text;
    if (text.length() > maxCount + 3)
    {
      shortenedString = text.substring(0, maxCount + 3);
    }

    int index = shortenedString.length() - 1;
    while (index >= 0)
    {
      if (shortenedString.charAt(index) == ' ')
      {
        return index;
      }
      index--;
    }
    return text.length() / 2;
  }

  /**
   * Converts and CustomImage to a BufferedImage.
   * 
   * @param image
   *          The Image to convert.
   * @return Equivalent BufferedImage
   */
  public static BufferedImage customImgToBuffered(CustomImage image)
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

  /**
   * Converts an Image to a BuffredImage.
   * 
   * @param image
   *          The initial Image object
   * @return The BufferedImage version of the initial image
   */
  public static BufferedImage imageToBufferedImg(Image image)
  {
    BufferedImage initialImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
        BufferedImage.TYPE_INT_RGB);
    Graphics background = initialImage.getGraphics();
    background.drawImage(image, 0, 0, null);
    background.dispose();
    return initialImage;
  }
}
