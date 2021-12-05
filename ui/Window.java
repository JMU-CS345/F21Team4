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
  // Declaring the JFrame
  public static JFrame frame;

  // Declaring components foe the the panel layout
  public static CardLayout layout;
  public static JPanel layoutPane;

  // Declaring the different subsequent pages
  public static HomePage home;
  public static DogDisplay dogDisplay;
  public static RandomDogPane randDogPage;
  public static RandomFactPane randFactPage;
  public static GamePage gamePage;
  public static AboutPage aboutPage;

  // Declaring and initializing all default window dimensions
  private final int windowWidth = 1024;
  private final int windowHeight = 768;

  public Window() throws IOException
  {
    // Initializes the JFrame
    frame = new JFrame("Dog App");

    // Initializing and setting panel layout components
    layout = new CardLayout();
    layoutPane = new JPanel(layout);

    layoutPane.setPreferredSize(new Dimension(300, 310));
    layoutPane.setVisible(true);

    // Initializing subsequent pages
    home = new HomePage();
    dogDisplay = new DogDisplay();
    randDogPage = new RandomDogPane();
    randFactPage = new RandomFactPane();
    gamePage = new GamePage();
    // aboutPage = new AboutPage();

  }

  /**
   * Adds all of the panels to the layoutPane.
   */
  public void createAndShowGUI()
  {
    // Creates and set up the window.
    layoutPane.add(home.splitPane, "homescreen");
    layoutPane.add(dogDisplay.fullScreenImage, "fullscreen");
    layoutPane.add(dogDisplay.splitPane, "dogdisplay");
    layoutPane.add(randDogPage.randImgPane, "randImg");
    layoutPane.add(randFactPage.factRandom, "randFact");
    layoutPane.add(gamePage.gamePage, "gamePage");
    // layoutPane.add(aboutPage.aboutPage, "aboutPage");

    // Set up the HomePage
    layout.show(layoutPane, "homescreen");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(layoutPane);

    // Display the window.
    frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
    frame.pack();
    frame.setVisible(true);
  }
}
