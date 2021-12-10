import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * This class hold the functionality and components that make up the Game Page of the DogApp.
 * 
 * @author Matt Wong, Zach Tucker, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 */
public class GamePage extends JPanel implements ActionListener
{
  // Declaring the buttons of the GameDisplay page
  private JButton gameOne;
  private JButton gameTwo;
  private JButton gameThree;
  private JButton back;

  // Declaring the Image Icons for the game posters
  private ImageIcon gameOneIcon;
  private ImageIcon gameTwoIcon;
  private ImageIcon gameThreeIcon;

  // Declaring the labels for the image icons
  private JLabel gameOneLabel;
  private JLabel gameTwoLabel;
  private JLabel gameThreeLabel;

  // Declaring all components for the GameDisplay
  private JPanel buttonPad;
  private JSplitPane gameSplitPane;
  public JPanel gamePage;

  private String choice;

  public GamePage() throws IOException
  {
    // Initializing the panels
    gamePage = new JPanel(new GridLayout(1, 1));
    buttonPad = new JPanel(new GridLayout(3, 2));

    // Initializing "Dog Simulator" button
    gameOne = new JButton("Dog Simulator");
    gameOne.addActionListener(this);

    // Initializing "Doge Miner" button
    gameTwo = new JButton("Doge Miner");
    gameTwo.addActionListener(this);

    // Initializing "Doge 2048" button
    gameThree = new JButton("Doge 2048");
    gameThree.addActionListener(this);

    // Initializing "Back" button
    back = new JButton("Back");
    back.addActionListener(this);
    back.setPreferredSize(new Dimension(768, 100));

    // Initializing the ImageIcons with their respective game posters
    gameOneIcon = new ImageIcon(readInImage(
        "https://images.crazygames.com/dog-simulator-3d/20210210175744/dog-simulator-3d-cover?auto=format,compress&q=75&cs=strip"));
    gameTwoIcon = new ImageIcon(readInImage(
        "https://images.crazygames.com/games/doge-miner/cover-1593443166599.png?auto=format,compress&q=75&cs=strip&ch=DPR&w=1200&h=630&fit=crop"));
    gameThreeIcon = new ImageIcon(
        readInImage("https://i.ytimg.com/vi/wTqTaAGmLY4/maxresdefault.jpg"));

    // Setting the ImageIcons their scaled versions
    gameOneIcon = new ImageIcon(MemeMaker.scaleImageIcon(gameOneIcon, 220, 512));
    gameTwoIcon = new ImageIcon(MemeMaker.scaleImageIcon(gameTwoIcon, 220, 512));
    gameThreeIcon = new ImageIcon(MemeMaker.scaleImageIcon(gameThreeIcon, 220, 512));

    // Initializing the labels with their respective ImageIcons
    gameOneLabel = new JLabel(gameOneIcon);
    gameTwoLabel = new JLabel(gameTwoIcon);
    gameThreeLabel = new JLabel(gameThreeIcon);

    // Adding the buttons to the buttons and labels to the button pad
    buttonPad.add(gameOne);
    buttonPad.add(gameOneLabel);

    buttonPad.add(gameTwo);
    buttonPad.add(gameTwoLabel);

    buttonPad.add(gameThree);
    buttonPad.add(gameThreeLabel);

    // Adding components to the overall GamePage panel
    gameSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, back, buttonPad);
    gameSplitPane.setDividerLocation(100);
    gameSplitPane.setEnabled(false);

    gamePage.add(gameSplitPane);

  }

  /**
   * Reads in an image from the Internet.
   * 
   * @param Url The url to the image
   * @return The image that was read in
   * @throws IOException
   */
  public Image readInImage(String Url) throws IOException
  {
    URL url = new URL(Url);

    Image image = ImageIO.read(url);
    return image;
  }

  /**
   * Takes a URL and opens it in a new tabe of the users default browser.
   * 
   * @param urlString
   *          The URL to the game
   */
  public static void openWebpage(String urlString)
  {
    try
    {
      Desktop.getDesktop().browse(new URL(urlString).toURI());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    choice = e.getActionCommand();

    if (choice.equals("Dog Simulator"))
    {
      int optionResult = JOptionPane.showConfirmDialog(Window.frame,
          "You will now be directed away from the Dog App to a webpage opened in your default browser.\nWould you still like to continue?",
          "Opening Page in Browser", JOptionPane.YES_NO_OPTION);
      if (optionResult == JOptionPane.YES_OPTION)
      {
        openWebpage("https://www.crazygames.com/game/dog-simulator-3d");
      }
    }
    else if (choice.equals("Doge Miner"))
    {
      int optionResult = JOptionPane.showConfirmDialog(Window.frame,
          "You will now be directed away from the Dog App to a webpage opened in your default browser.\nWould you still like to continue?",
          "Opening Page in Browser", JOptionPane.YES_NO_OPTION);
      if (optionResult == JOptionPane.YES_OPTION)
      {
        openWebpage("https://www.crazygames.com/game/doge-miner");
      }
    }
    else if (choice.equals("Doge 2048"))
    {
      int optionResult = JOptionPane.showConfirmDialog(Window.frame,
          "You will now be directed away from the Dog App to a webpage opened in your default browser.\nWould you still like to continue?",
          "Opening Page in Browser", JOptionPane.YES_NO_OPTION);
      if (optionResult == JOptionPane.YES_OPTION)
      {
        openWebpage("http://www.crazygames.com/game/doge-2048");
      }
    }
    else if (choice.equals("Back"))
    {
      Window.layout.show(Window.layoutPane, "homescreen");
      Window.frame.setTitle("Dog App");
    }
  }
}
