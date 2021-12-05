import static org.junit.Assert.assertNull;
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
    testPixel = new Pixel(255, 255, 255);

  }

  @Test
  void testConstructor()
  {
    assertEquals(testPixel, image.getPixel(0, 0));
    assertEquals(testPixel, coloredImage.getPixel(0, 0));
    assertNull(coloredImage.getPixel(3000, 0));

    assertThrows(IllegalArgumentException.class, () -> {
      image = new CustomImage(-1, 100);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      image = new CustomImage(1, -100);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      image = new CustomImage(-1, 100, 255, 255, 255);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      image = new CustomImage(1, -100, 255, 255, 255);
    });
  }

  @Test
  void testGetPixel()
  {
    testPixel = new Pixel(255, 255, 255);
    assertEquals(testPixel, image.getPixel(0, 0));
    assertEquals(testPixel, coloredImage.getPixel(0, 0));
    assertNull(coloredImage.getPixel(3000, 0));

    assertNull(coloredImage.getPixel(0, 3000));
    assertNull(coloredImage.getPixel(-1, 0));
    assertNull(coloredImage.getPixel(0, -1));

  }

  @Test
  void testSetPixel()
  {
    testPixel = null;
    image.setPixel(0, 0, testPixel);
    image.setPixel(-1, 0, new Pixel(100, 100, 100));
    image.setPixel(0, -1, new Pixel(100, 100, 100));
    image.setPixel(0, 3000, new Pixel(100, 100, 100));
    image.setPixel(3000, 0, new Pixel(100, 100, 100));

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

    assertEquals(500, image.getHeight());
    assertEquals(500, coloredImage.getHeight());
  }

  @Test
  void testEquals()
  {
    int test = 10;
    assertTrue(image.equals(coloredImage));
    
    assertFalse(image.equals(test));

    image = new CustomImage(500, 100);
    assertFalse(image.equals(coloredImage));
    image = new CustomImage(100, 500);
    assertFalse(image.equals(coloredImage));

    coloredImage = new CustomImage(500, 500, 0, 0, 0);

    assertFalse(image.equals(coloredImage));
    assertFalse(coloredImage.equals(image));
  }

  @Test
  void testToString()
  {
    assertEquals("<Image width=500 height=500>", image.toString());
  }
  
}
