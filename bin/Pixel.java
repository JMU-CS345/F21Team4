/**
 * \ Pixel class, which represents a single pixel made up of red, green, and
 * blue components.
 * 
 * @author Zack Tucker
 * @version 2-3-21
 */

public class Pixel {
    private final int red;
    private final int blue;
    private final int green;

    /**
     * Constructor for pixel.
     * 
     * @param red value for the constructor
     * @param green value for the constructor
     * @param blue value for the constructor
     */

    public Pixel(int red, int green, int blue) {

        if (red < 0) {

            this.red = 0;

        } else if (red > 255) {

            this.red = 255;

        } else {

            this.red = red;

        }

        if (green < 0) {

            this.green = 0;

        } else if (green > 255) {

            this.green = 255;

        } else {

            this.green = green;

        }

        if (blue < 0) {

            this.blue = 0;

        } else if (blue > 255) {

            this.blue = 255;

        } else {

            this.blue = blue;

        }

    }

    /**
     * Gets the red value of the pixel.
     * 
     * @return red value for the pixel
     */

    public int getRed() {

        return this.red;

    }

    /**
     * Gets the blue value of the pixel.
     * 
     * @return red value for the pixel
     */

    public int getBlue() {

        return this.blue;

    }

    /**
     * Gets the green value of the pixel.
     * 
     * @return red value for the pixel
     */

    public int getGreen() {

        return this.green;

    }

    /**
     * Gets the value for a specific channel.
     * 
     * @param channel to get the value
     * @return the value of the color.
     */

    public int getChannel(int channel) {
        int channelVal = -1;

        if (channel < 0 || channel > 2) {
            throw new IllegalArgumentException(
                    "Channel must be between" + "0 - 2");

        }

        if (channel == 0) {
            channelVal = this.red;
        }

        if (channel == 1) {
            channelVal = this.green;
        }

        if (channel == 2) {
            channelVal = this.blue;
        }

        return channelVal;
    }

    /**
     * Checks whether two pixel objects are equal.
     * 
     * @param other object to check if its equal to existing pixel
     * @return true if they are equal, false if not
     */

    public boolean equals(Object other) {
        if (!(other instanceof Pixel)) {
            return false;
        }

        Pixel otherPixel = (Pixel) other;

        return this.red == otherPixel.red && this.blue == otherPixel.blue
                && this.green == otherPixel.green;

    }

    /**
     * Displays a pixel in a readable string format.
     * 
     * @return the string that is the pixel.
     */

    public String toString() {
        return String.format("(%3d, %3d, %3d)", this.red, this.green,
                this.blue);

    }

}
