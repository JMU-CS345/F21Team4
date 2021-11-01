import java.awt.Dimension;
import javax.swing.JFrame;



/**
 * A class.
 * 
 * @author Zack Tucker, Matt Wong, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 */

public class DogDisplay {

  private JFrame frame;
  private final int windowWidth = 1024;
  private final int windowHeight = 768;
  private String dogNames[];

  public DogDisplay() {

  }

  public void createAndShowGUI() {

    // Create and set up the window.
    frame = new JFrame("DogDisplay");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Display the window.
    frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
    frame.pack();
    frame.setVisible(true);
  }

  public void getDogNames() {

  }
}
