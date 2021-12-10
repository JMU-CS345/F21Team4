import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PixelTest
{
  
  private Pixel red;
  private Pixel green;
  private Pixel blue;
  private Pixel white;
  private Pixel black;
  private Pixel someColor;
  
  @Test
  void pixelTest()
  {
    red = new Pixel(300, 0, -25);
    assertEquals(255, red.getRed());
    assertEquals(0, red.getBlue());
    
    green = new Pixel(-785, 765, 0);
    assertEquals(255, green.getGreen());
    assertEquals(0, green.getRed());
    
    blue = new Pixel(0, -456, 7859);
    assertEquals(255, blue.getBlue());
    assertEquals(0, blue.getGreen());
    
    white = new Pixel(255, 255, 255);
    black = new Pixel(0, 0, 0);
    someColor = new Pixel(234, 103, 182);
    
    assertEquals("(234, 103, 182)", someColor.toString());
    assertEquals("(  0,   0,   0)", black.toString());
    assertEquals("(255, 255, 255)", white.toString());
  }
  
  @Test
  void channelTest() {
    black = new Pixel(0, 0, 0);
    white = new Pixel(255, 255, 255);
    someColor = new Pixel(234, 103, 182);
    assertEquals(0, black.getChannel(1));
    assertEquals(255, white.getChannel(0));
    
    assertEquals(182, someColor.getChannel(2));
    assertEquals(103, someColor.getChannel(1));
    assertEquals(234, someColor.getChannel(0));
  }
  
  @Test
  void illegalChannelTest() throws IllegalArgumentException{
    someColor = new Pixel(234, 103, 182);
    assertThrows(IllegalArgumentException.class, () -> someColor.getChannel(6));
    assertThrows(IllegalArgumentException.class, ()-> someColor.getChannel(-1));
  }
  
  @Test
  void equalsTest() {
    white = new Pixel(255, 255, 255);
    black = new Pixel(0, 0, 0);
    someColor = new Pixel(234, 103, 182);
    Pixel otherColor = new Pixel(234, 103, 182);
    Pixel notWhite = new Pixel(255, 255, 243);
    Pixel notBlack = new Pixel(0, 65, 0);
    Pixel wrongColor = new Pixel(34, 103, 182);
    
    assertTrue(white.equals(white));
    assertTrue(black.equals(black));
    assertTrue(someColor.equals(someColor));
    assertTrue(someColor.equals(otherColor));
    assertTrue(otherColor.equals(someColor));
    
    assertFalse(someColor.equals(white));
    assertFalse(someColor.equals(black));
    assertFalse(white.equals(notWhite));
    assertFalse(black.equals(notBlack));
    assertFalse(someColor.equals(wrongColor));
    assertFalse(otherColor.equals(wrongColor));
    assertFalse(someColor.equals(new String()));
    
  }

}
