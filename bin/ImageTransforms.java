/*
 * This work complies with the JMU Honor Code.
 * 
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 */

/**
 * A class that can perform adjustments on an image.
 * 
 * @author Zack Tucker
 * @version 2-10-21
 */
public class ImageTransforms {

    /**
     * Static method to adjust the brightness of a image.
     * 
     * @param image the image to adjust the brightness on.
     * @param amount the amount the image's brightness should be adjusted.
     * @return a new image with adjusted brightness.
     */

    public static Image adjustBrightness(Image image, int amount) {
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

        return adjustedBrightness;

    }

    /**
     * Convert an image to grayscale.
     * 
     * @param image the image to convertToGrayscale
     * @return the imaged after it has been grayscaled
     */

    public static Image convertToGrayscale(Image image) {

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

        return grayscaled;

    }

    /**
     * Convert an image to black and white.
     * 
     * @param image to convert to b&w
     * @param threshold to use to set for b&w
     * @return the image after it has been converted to b&w
     */

    public static Image threshold(Image image, int threshold) {
        Image baw = convertToGrayscale(image);
        for (int i = 0; i < baw.getWidth(); i++) {
            for (int x = 0; x < baw.getHeight(); x++) {
                Pixel p = baw.getPixel(i, x);

                int red = p.getRed();
                if (red >= threshold) {
                    red = 255;
                } else {
                    red = 0;
                }

                int green = p.getGreen();
                if (green >= threshold) {
                    green = 255;
                } else {
                    green = 0;
                }

                int blue = p.getBlue();
                if (blue >= threshold) {
                    blue = 255;
                } else {
                    blue = 0;
                }

                Pixel blackorwhiteP = new Pixel(red, blue, green);
                baw.setPixel(i, x, blackorwhiteP);

            }

        }

        return baw;
    }
    
    //**********************************************************
    
    /*
     * THE NEXT FOUR METHODS ARE NOT MINE. THEY WERE INCLUDED IN THE PA2 MIMP
     * SOLTUION AND THEY WERE COPIED INTO THIS FILE TO ENSURE THAT PA4 MIMPIO
     * WOULD WORK CORRECTLY. I TAKE NO CREDIT FOR THIS CODE AND IT WAS ONLY
     * INCLUDED TO ENSURE FUNCTIONALITY WITHIN THE MIMPIO.
     * THIS WORK COMPLIES WITH THE JMU HONOR CODE.
     */

    /**
     * Rotate the image to the right.
     * 
     * @param image the image to rotateRight
     * @return the image after it is rotatedRight
     */

    public static Image rotateRight(Image image) {

        Image newImage = new Image(image.getHeight(), image.getWidth());
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = image.getPixel(x, y);
                newImage.setPixel(image.getHeight() - y - 1, x, pixel);
            }
        }
        return newImage;

    }

    /**
     * Rotate the image to the left.
     * 
     * @param image to rotateLeft
     * @return the image that has been rotatedLeft
     */

    public static Image rotateLeft(Image image) {
        Image newImage = new Image(image.getHeight(), image.getWidth());
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = image.getPixel(x, y);
                newImage.setPixel(y, image.getWidth() - x - 1, pixel);
            }
        }
        return newImage;

    }

    /**
     * Mirror an existing image.
     * 
     * @param image to mirror
     * @return the mirrored image
     */

    public static Image mirror(Image image) {
        Image newImage = new Image(image.getWidth(), image.getHeight());
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = image.getPixel(x, y);
                newImage.setPixel(image.getWidth() - x - 1, y, pixel);
            }
        }
        return newImage;
    }

    /**
     * Flips the given image.
     * 
     * @param image to flip
     * @return the image after it is flip
     */

    public static Image flip(Image image) {
        Image newImage = new Image(image.getWidth(), image.getHeight());
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Pixel pixel = image.getPixel(x, y);
                newImage.setPixel(x, image.getHeight() - y - 1, pixel);
            }
        }
        return newImage;

    }
}
