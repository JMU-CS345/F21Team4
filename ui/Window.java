import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class creates a Jframe that houses the rest of our items and sets initial parameters for
 * frames and such.
 * 
 * @author Zack Tucker, Matt Wong, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 */

public class Window
{
  public static JFrame frame = new JFrame("DogDisplay");
  private final int windowWidth = 1024;
  private final int windowHeight = 768;
  public static DogDisplay test;
  public static RandomDogPane randDogPage;
  public static RandomFactPane randFactPage;
  public static HomePage home;
  public static CardLayout layout;

  public static JPanel layoutPane;

  public Window() throws IOException
  {
    layout = new CardLayout();
    layoutPane = new JPanel(layout);
    layoutPane.setPreferredSize(new Dimension(300, 310));
    layoutPane.setVisible(true);
    test = new DogDisplay();
    test.setPreferredSize(new Dimension(windowWidth, windowHeight));
    randDogPage = new RandomDogPane();

    home = new HomePage();
    
    randDogPage = new RandomDogPane();
    randFactPage = new RandomFactPane();
  }

  public void createAndShowGUI()
  {
    // Create and set up the window.
    layoutPane.add(test.fullScreenImage, "fullscreen");
    layoutPane.add(test.splitPane, "dogdisplay");
    layoutPane.add(home.splitPane, "homescreen");
    layoutPane.add(randDogPage.randImgPane, "randImg");
    layoutPane.add(randFactPage.factRandom, "randFact");

    layout.show(layoutPane, "homescreen");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(layoutPane);
    // Display the window.
    frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
    frame.pack();
    frame.setVisible(true);
  }

}
