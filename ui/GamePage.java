import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

/**
 * This class sets up the Game page.
 * 
 * @author Matt Wong, Zach Tucker, Thomas Mandell, Alex Polivka, Jonathan Wist
 *
 */
public class GamePage extends JPanel implements ActionListener
{
  public JPanel buttonPad;
  public JPanel gamePage;
  // private JPanel buttonPad;

  private JButton gameOne;
  private JButton gameTwo;
  private JButton gameThree;
  private JButton back;

  private JLabel gameOneLabel;
  private JLabel gameTwoLabel;
  private JLabel gameThreeLabel;

  private ImageIcon gameOneIcon;
  private ImageIcon gameTwoIcon;
  private ImageIcon gameThreeIcon;

  private String choice;

  public GamePage() throws IOException
  {
    gamePage = new JPanel();
    buttonPad = new JPanel(new GridLayout(3, 2));

    gameOne = new JButton("Dog Simulator");
    gameOne.addActionListener(this);

    gameTwo = new JButton("Doge Miner");
    gameTwo.addActionListener(this);

    gameThree = new JButton("Doge 2048");
    gameThree.addActionListener(this);

    back = new JButton("Back");
    back.addActionListener(this);
    back.setPreferredSize(new Dimension(768, 100));

    gameOneIcon = new ImageIcon(readInImage(
        "https://images.crazygames.com/dog-simulator-3d/20210210175744/dog-simulator-3d-cover?auto=format,compress&q=75&cs=strip"));
    gameTwoIcon = new ImageIcon(readInImage(
        "https://images.crazygames.com/games/doge-miner/cover-1593443166599.png?auto=format,compress&q=75&cs=strip&ch=DPR&w=1200&h=630&fit=crop"));
    gameThreeIcon = new ImageIcon(
        readInImage("https://i.ytimg.com/vi/wTqTaAGmLY4/maxresdefault.jpg"));

    gameOneIcon = new ImageIcon(MemeMaker.scaleImageIcon(gameOneIcon, 220, 512));
    gameTwoIcon = new ImageIcon(MemeMaker.scaleImageIcon(gameTwoIcon, 220, 512));
    gameThreeIcon = new ImageIcon(MemeMaker.scaleImageIcon(gameThreeIcon, 220, 512));

    gameOneLabel = new JLabel(gameOneIcon);
    gameTwoLabel = new JLabel(gameTwoIcon);
    gameThreeLabel = new JLabel(gameThreeIcon);

    buttonPad.add(gameOne);
    buttonPad.add(gameOneLabel);

    buttonPad.add(gameTwo);
    buttonPad.add(gameTwoLabel);

    buttonPad.add(gameThree);
    buttonPad.add(gameThreeLabel);

    gamePage.add(back);
    gamePage.add(buttonPad);

  }

  /**
   * Reads in an image from the internet
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

  /**
   * Takes a URL and opens the a game in the users default browser.
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
