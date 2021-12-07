import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * This class contains the contributers information and app description.
 * 
 * @author Matt Wong, Zach Tucker, Thomas Mandell, Alex Polivka, Jonathan Wist
 *
 */

public class AboutPage extends JPanel implements ActionListener
{
  // Declaring AppDescription Text Area and components
  private JTextArea appDescriptTxtArea;

  private JTextArea acknowledgements;

  private String appDescription;

  // Initializing profile and components
  private ImageIcon profile;
  private JLabel profileLabel;

  // Initializing profile names
  private JLabel name1;
  private JLabel name2;
  private JLabel name3;
  private JLabel name4;
  private JLabel name5;

  private JLabel pic1;
  private JLabel pic2;
  private JLabel pic3;
  private JLabel pic4;
  private JLabel pic5;

  private JLabel title;

  // Initializing JButtons and necessary variables
  private String choice;

  private JButton back;

  // Initializing Split Pane
  private JSplitPane splitPane;
  private JSplitPane bottomSplitPane;
  private JSplitPane topSplitPane;

  private JPanel topPanel;

  // Initializing AboutPage JPanel
  public JPanel aboutPage;

  public AboutPage() throws IOException
  {

    appDescription = new String(
        "App Description:\nOur dog app makes it easy to do various activities involving dogs. "
            + "One feature finds random images of dogs or an image of a specific dog breed, "
            + "another provides fleshed out image editing capability with import and "
            + "export features, another is a dog fact generator, and a list of dog games "
            + "for the user to open in their browser.");

    appDescriptTxtArea = new JTextArea(appDescription);
    appDescriptTxtArea.setLineWrap(true);
    appDescriptTxtArea.setWrapStyleWord(true);
    appDescriptTxtArea.setEditable(false);

    // Initializing and setting up "Back" button
    back = new JButton("Back");
    back.addActionListener((ActionListener) this);
    back.setPreferredSize(new Dimension(100, 100));
    back.setVisible(true);

    //
    bottomSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, appDescriptTxtArea, back);

    profileLabel = new JLabel();

    topPanel = new JPanel(new GridLayout(2, 5));

    for (int i = 0; i < 5; i++)
    {
      topPanel.add(new JLabel(new ImageIcon(MemeMaker.scaleImageIcon(new ImageIcon(readInImage(
          "https://t4.ftcdn.net/jpg/02/15/84/43/360_F_215844325_ttX9YiIIyeaR7Ne6EaLLjMAmy4GvPC69.jpg")),
          100, 100))));
    }
    for (int i = 0; i < 5; i++)
    {
      topPanel.add(new JLabel("first last", SwingConstants.CENTER));
    }
    title = new JLabel("<html> <font color='green'>About Page\nAuthors:</font></html>");
    topSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, title, topPanel);
    // Initializing aboutPage JPanel
    splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplitPane, bottomSplitPane);
    aboutPage = new JPanel(new GridLayout(1, 2));

    aboutPage.add(splitPane);

    // aboutPage.add(bottomSplitPane);

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

  @Override
  public void actionPerformed(ActionEvent e)
  {
    choice = e.getActionCommand();

    if (choice.equals("Back"))
    {
      Window.layout.show(Window.layoutPane, "homescreen");
      Window.frame.setTitle("Dog App");
    }
    else if (choice.equals(""))
    {

    }

  }
}
