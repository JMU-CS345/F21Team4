import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.CardLayout;

/**
 * Window class
 * 
 * @author Zack Tucker, Matt Wong, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 */

public class Window
{
  JFrame frame = new JFrame("DogDisplay");
  private final int windowWidth = 1024;
  private final int windowHeight = 768;
  public static DogDisplay test;
  private HomePage home = new HomePage();
  
  private JPanel layoutPane;

  public Window() throws IOException
  {
    layoutPane = new JPanel(new CardLayout());
    layoutPane.setPreferredSize(new Dimension(300, 310));
    layoutPane.setVisible(true);
    test = new DogDisplay();
    test.setPreferredSize(new Dimension(windowWidth, windowHeight));
    
  }

  public void createAndShowGUI()
  {
    // Create and set up the window.
    layoutPane.add(test.fullScreenImage);
    layoutPane.add(test.splitPane);
    layoutPane.add(home.buttonPad);
    test.fullScreenImage.setBounds(0, 0, windowWidth, windowHeight - 50);
    test.splitPane.setBounds(0, 0, windowWidth, windowHeight - 50);
    home.buttonPad.setBounds(0, 0, windowWidth, windowHeight - 50);
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(layoutPane);
    // Display the window.
    frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
    frame.pack();
    frame.setVisible(true);
  }

}
