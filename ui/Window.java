import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * Window class
 * 
 * @author Zack Tucker, Matt Wong, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 */

public class Window
{
  private JFrame frame;
  private final int windowWidth = 1024;
  private final int windowHeight = 768;
  private DogDisplayAsAPane test;

  public Window() throws IOException
  {
    test = new DogDisplayAsAPane();
    // Create and set up the window.
    frame = new JFrame("DogDisplay");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Display the window.
    frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
    frame.pack();
    frame.setVisible(true);

    frame.setLayout(new BorderLayout());

    frame.getContentPane().add(test);
  }

}
