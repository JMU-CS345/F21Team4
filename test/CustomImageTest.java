import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomImageTest
{
  CustomImage image;
  CustomImage coloredImage;

  Pixel testPixel;

  @BeforeEach
  void beforeEach() throws IOException
  {
    image = new CustomImage(500, 500);

    coloredImage = new CustomImage(500, 500, 255, 255, 255);

  }

  @Test
  void testGetPixel()
  {
    testPixel = new Pixel(255, 255, 255);
    assertEquals(testPixel, image.getPixel(0, 0));
    assertEquals(testPixel, coloredImage.getPixel(0, 0));
  }

  @Test
  void testSetPixel()
  {
    testPixel = new Pixel(100, 100, 100);
    image.setPixel(0, 0, new Pixel(100, 100, 100));

    assertEquals(testPixel, image.getPixel(0, 0));

  }

  @Test
  void testGetWidth()
  {
    
    assertEquals(500, image.getWidth());
    assertEquals(500, coloredImage.getWidth());
  }

  @Test
  void testGetHeight()
  {
    testPixel = new Pixel(255, 255, 255);
    assertEquals(testPixel, image.getPixel(0, 0));
    assertEquals(testPixel, coloredImage.getPixel(0, 0));
  }

  @Test
  void testEquals()
  {
    testPixel = new Pixel(255, 255, 255);
    assertEquals(testPixel, image.getPixel(0, 0));
    assertEquals(testPixel, coloredImage.getPixel(0, 0));
  }

  @Test
  void testToString()
  {
    testPixel = new Pixel(255, 255, 255);
    assertEquals(testPixel, image.getPixel(0, 0));
    assertEquals(testPixel, coloredImage.getPixel(0, 0));
  }
}
