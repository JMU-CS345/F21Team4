import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

/**
 * CS 159 Pixel Test.
 * @author Zack Tucker
 * @version 2-8-21
 */

class PixelTest {

    /**
     * Tests the pixel constructor and getter methods.
     */
    @Test
    public void pixelConstructor() {
        Pixel p = new Pixel(0, 0, 0);
        assertEquals(0, p.getRed());
        assertEquals(0, p.getGreen());
        assertEquals(0, p.getBlue());

        Pixel p1 = new Pixel(256, 256, 256);
        assertEquals(255, p1.getRed());
        assertEquals(255, p1.getGreen());
        assertEquals(255, p1.getBlue());

        Pixel p2 = new Pixel(-1, -1, -1);
        assertEquals(0, p2.getRed());
        assertEquals(0, p2.getGreen());
        assertEquals(0, p2.getBlue());

    }

    /**
     * Tests the channel method.
     */
    @Test
    public void getChannelTest() {
        Pixel p3 = new Pixel(1, 2, 3);
        assertEquals(1, p3.getChannel(0));
        assertEquals(2, p3.getChannel(1));
        assertEquals(3, p3.getChannel(2));
        
        assertThrows(IllegalArgumentException.class, () -> {
            p3.getChannel(5);
        });
            
            assertThrows(IllegalArgumentException.class, () -> {
                p3.getChannel(-1);
        });

    }
    
    /**
     * Tests the toString method.
     */
    @Test
    public void testToString() {
        Pixel p = new Pixel(0, 255, 50);
        assertEquals("(  0, 255,  50)", p.toString());


    }
    
    /**
     * Tests the equals method.
     */
    @Test
    public void testEquals() {
        Pixel p = new Pixel(0, 255, 50);
        Pixel p1 = new Pixel(0, 255, 50);
        assertEquals(true, p.equals(p1));
        
        Pixel p2 = new Pixel(1, 23, 20);
        Pixel p3 = new Pixel(0, 5, 50);
        assertEquals(false, p2.equals(p3));
        
        Pixel p4 = new Pixel(1, 2, 3);
        Pixel p5 = new Pixel(0, 2, 3);
        assertEquals(false, p4.equals(p5));
        
        Pixel p6 = new Pixel(1, 0, 3);
        assertEquals(false, p4.equals(p6));
        
        Pixel p7 = new Pixel(1, 2, 0);
        assertEquals(false, p4.equals(p7));
        
        Object other = new Object();
        assertEquals(false, p2.equals(other));
        
        
        
        


    }
    
    
    
    }

