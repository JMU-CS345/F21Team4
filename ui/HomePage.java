import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

/**
 * This class holds the components and actions that make up the HomePage of our dog application.
 * 
 * @author Zack Tucker, Matt Wong, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 */

public class HomePage extends JPanel
{
  // Declaring all button components and variables
  private String choice;

  private JPanel buttonPad;
  private JPanel titleDisplay;

  private JButton dogDisplay;
  private JButton randomFact;
  private JButton randomPic;
  private JButton gamesPage;
  private JButton aboutPage;

  // Declaring HomePage panel and variables
  public JSplitPane splitPane;
  private JLabel welcomeText;
  private JLabel dogImageLabel;

  private ImageIcon dogImage;
  // Declaring and initializing all default window dimensions
  private final int windowWidth = 1024;
  private final int windowHeight = 768;

  public HomePage() throws IOException
  {
    // Initializing "Dog Display" button
    dogDisplay = new JButton("Dog Display");
    dogDisplay.addActionListener(new ButtonPress());

    // Initializing "Random Dog Picture" button
    randomPic = new JButton("Random Dog Picture");
    randomPic.addActionListener(new ButtonPress());

    // Initializing "Random Facts" button
    randomFact = new JButton("Random Facts");
    randomFact.addActionListener(new ButtonPress());

    // Initializing "Dog Games" button
    gamesPage = new JButton("Dog Games");
    gamesPage.addActionListener(new ButtonPress());

    // Initializing "About Page" button
    aboutPage = new JButton("About Page");
    aboutPage.addActionListener(new ButtonPress());

    // Initializing buttonPad
    buttonPad = new JPanel(new GridLayout(1, 5));
    buttonPad.add(dogDisplay);
    buttonPad.add(randomPic);
    buttonPad.add(randomFact);
    buttonPad.add(gamesPage);
    buttonPad.add(aboutPage);

    buttonPad.setPreferredSize(new Dimension(windowWidth, (windowHeight / 3)));
    buttonPad.setVisible(true);

    // Initializing welcomeText JLabel
    welcomeText = new JLabel();

    // Setting font color, text content, alignment, font type, and size
    welcomeText.setText("<html> <font color='green'>Welcome to our Dog App!</font></html>");
    welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
    welcomeText.setFont(new Font("Futura", Font.PLAIN, 50));

    welcomeText.setVisible(true);

    // Initializing DogImage and DogImageLabel
    dogImage = new ImageIcon(readInImage(
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=1.00xw:0.669xh;0,0.190xh&resize=1200:*"));
    dogImage = new ImageIcon(MemeMaker.scaleImageIcon(dogImage, windowWidth, 500));

    dogImageLabel = new JLabel(dogImage);

    // Initializing TitleDisplay
    titleDisplay = new JPanel(new FlowLayout());
    titleDisplay.add(welcomeText);
    titleDisplay.add(dogImageLabel);

    titleDisplay.setPreferredSize(new Dimension(windowWidth, 300));

    // Initializes split pane that houses all other HomePage components
    splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titleDisplay, buttonPad);
    splitPane.setEnabled(false);
  }

  /**
   * This class holds all of the functionality for the different buttons on the HomePage.
   * 
   * @author Matt Wong, Zach Tucker, Thomas Mandell, Alex Polivka, Jonathan Wist
   * @version Nov 1, 2021
   */
  private class ButtonPress implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      choice = e.getActionCommand();

      if (choice.equals("Dog Display"))
      {
        Window.layout.show(Window.layoutPane, "dogdisplay");
        Window.frame.setTitle("Dog Display");
      }
      else if (choice.equals("Random Dog Picture"))
      {
        Window.layout.show(Window.layoutPane, "randImg");
        Window.frame.setTitle("Random Dog Picture");
      }
      else if (choice.equals("Random Facts"))
      {
        Window.layout.show(Window.layoutPane, "randFact");
        Window.frame.setTitle("Random Dog Fact");
      }

      else if (choice.equals("Dog Games"))
      {
        Window.layout.show(Window.layoutPane, "gamePage");
        Window.frame.setTitle("Dog Games");
      }
      else if (choice.equals("About Page"))
      {
        // Window.layout.show(Window.layoutPane, "aboutPage");
        // Window.frame.setTitle("AboutPage");
      }
    }
  }

  /**
   * Reads in an image from the Internet.
   * 
   * @param URL
   *          The url to the image
   * @return The image that was read in
   * @throws IOException
   */
  public Image readInImage(String Url) throws IOException
  {
    URL url = new URL(Url);

    Image image = ImageIO.read(url);
    return image;
  }
}
