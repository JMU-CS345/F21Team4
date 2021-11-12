import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;

/**
 * Window class
 * 
 * @author Zack Tucker, Matt Wong, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 */

public class Window {
  JFrame frame = new JFrame("DogDisplay");
  private final int windowWidth = 1024;
  private final int windowHeight = 768;
  private DogDisplayAsAPane test = new DogDisplayAsAPane();

  public Window() throws IOException {

    test.setPreferredSize(new Dimension(windowWidth, windowHeight));

  }

  public void createAndShowGUI() {

    // Create and set up the window.
    frame.add(test.splitPane);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Display the window.
    frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
    frame.pack();
    frame.setVisible(true);
  }

}
