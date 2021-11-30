/*
 * This work complies with the JMU Honor Code.
 * 
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 */

/**
 * A class that represents an image object made up of pixel objects.
 * 
 * @author Zack Tucker
 * @version 2-3-21
 */

public class CustomImage {
    private Pixel[][] pixels;

    /**
     * Default constructor for image, sets all RGB to 255.
     * 
     * @param width width of the image
     * @param height height of the image
     */

    public CustomImage(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                    "width & height must be " + "greater than 0");
        }

        this.pixels = new Pixel[width][height];

        for (int i = 0; i < this.getWidth(); i++) {
            for (int x = 0; x < this.getHeight(); x++) {
                this.pixels[i][x] = new Pixel(255, 255, 255);
            }
        }

    }

    /**
     * Explicit constructor for Image.
     * 
     * @param width the width of the image
     * @param height the height of the image
     * @param red the red value of the image
     * @param green the green value of the image
     * @param blue the blue value of the image
     */

    public CustomImage(int width, int height, int red, int green, int blue) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                    "width & height must be " + "greater than 0");
        }

        this.pixels = new Pixel[width][height];

        for (int i = 0; i < this.getWidth(); i++) {
            for (int x = 0; x < this.getHeight(); x++) {
                this.pixels[i][x] = new Pixel(red, green, blue);
            }
        }

    }

    /**
     * Gets the pixel at the specified x and y coordinates.
     * 
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @return the pixel at the specified x and y coordinates.
     */

    public Pixel getPixel(int x, int y) {
        if (x < this.getWidth() && x > -1 && y < this.getHeight() && y > -1) {
            return this.pixels[x][y];
        } else {
            return null;
        }

    }

    /**
     * Sets the pixel at the specified x and y coordinates.
     * 
     * @param x the x coordinate of the pixel
     * @param y the y coordinate of the pixel
     * @param pixel the pixel to insert at the specified coordinates.
     */

    public void setPixel(int x, int y, Pixel pixel) {
        if (pixel != null && x < this.getWidth() && x > -1
                && y < this.getHeight() && y > -1) {
            this.pixels[x][y] = pixel;
        }
    }

    /**
     * Getter for height of image.
     * 
     * @return height of image
     */

    public int getHeight() {
        return this.pixels[0].length;
    }

    /**
     * Getter for width of image.
     * 
     * @return width of image.
     */

    public int getWidth() {
        return this.pixels.length;
    }

    /**
     * equals method to test if two images are object.
     * 
     * @param other as the image to check against
     * @return true if the images are the same, false if not.
     */

    public boolean equals(Object other) {
        boolean token = false;
        if (!(other instanceof CustomImage)) {
            return false;
        }

        CustomImage otherImage = (CustomImage) other;
        int count = 0;
        if (otherImage.getHeight() == this.getHeight()
                && otherImage.getWidth() == this.getWidth()) {

            for (int i = 0; i < this.getWidth(); i++) {
                for (int x = 0; x < this.getHeight(); x++) {
                    Pixel otherP = otherImage.getPixel(i, x);

                    if (otherP.equals(this.getPixel(i, x))) {
                        count++;
                    }

                }

            }

        }

        if (this.getWidth() * this.getHeight() == count) {
            token = true;
        } else {
            token = false;
        }

        return token;

    }

    /**
     * Returns a toString representation of the image.
     * 
     * @return a string representing the image
     */

    public String toString() {
        return "<Image width=" + this.getWidth() + " height=" + this.getHeight()
                + ">";
    }

    /**
     * Returns a toString of the image as a 2d.
     * 
     * @return the image as a 2d array in string form.
     */

    public String toStringDebug() {
        StringBuilder str = new StringBuilder();
        str.append("<Image width=" + this.getWidth() + " height="
                + this.getHeight() + ">\n");

        for (int i = 0; i < this.getWidth(); i++) {
            for (int x = 0; x < this.getHeight(); x++) {
                str.append(" ");
                str.append(this.pixels[i][x].toString());
                if (x + 1 == this.getHeight()) {
                    str.append("\n");

                }
            }

        }

        String r = str.toString();
        return r;

    }

}
