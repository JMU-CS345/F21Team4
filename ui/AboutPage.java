import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
  private JTextArea acknowledgementsTxtArea;
  private JTextArea sources;

  private String test;
  private String acknowledgements;
  private String appDescription;

  // Initializing JMU ImageIcon and JLabel
  private ImageIcon schoolImg;
  private JLabel schoolImgLabel;

  // Initializing profile name and image JLabels
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
  private JButton dogImageApi;
  private JButton randDogImgApi;
  private JButton dogFactApi;

  // Initializing SplitPanes and components
  private JSplitPane midSplitPane;
  private JSplitPane midSplitPaneSub;
  private JSplitPane topSplitPane;
  private JSplitPane bottomSplitPane;
  private JSplitPane splitPane;

  private JPanel topPanelImg;
  private JPanel topPanelName;

  private JPanel bottomSources;

  // Initializing AboutPage JPanel
  public JPanel aboutPage;

  public AboutPage() throws IOException
  {
    // Initializing all text areas and content
    appDescription = "App Description:\nOur dog app makes it easy to do various activities "
        + "involving dogs. One feature finds random images of dogs or an image of a specific "
        + "dog breed, another provides fleshed out image editing capability with import and "
        + "export features, another is a dog fact generator, and a list of dog games "
        + "for the user to open in their browser.";

    appDescriptTxtArea = new JTextArea(appDescription);
    appDescriptTxtArea.setLineWrap(true);
    appDescriptTxtArea.setWrapStyleWord(true);
    appDescriptTxtArea.setEditable(false);
    appDescriptTxtArea.setFont(new Font("Helvetica", Font.PLAIN, 20));

    acknowledgements = "Sources and Acknowledgements:\nOur \"Dog App\" application uses open "
        + "source API's to retrive images and facts.\nThe API's that we used can be found below "
        + "when you click the buttons.";

    acknowledgementsTxtArea = new JTextArea(acknowledgements);
    acknowledgementsTxtArea.setLineWrap(true);
    acknowledgementsTxtArea.setWrapStyleWord(true);
    acknowledgementsTxtArea.setEditable(false);
    acknowledgementsTxtArea.setFont(new Font("Helvetica", Font.PLAIN, 20));

    title = new JLabel("<html> <font color='green'>About Page</font></html>",
        SwingConstants.CENTER);
    title.setFont(new Font("Futura", Font.PLAIN, 20));

    name1 = new JLabel("Matthew Wong", SwingConstants.CENTER);
    name2 = new JLabel("Zachary Tucker", SwingConstants.CENTER);
    name3 = new JLabel("Jonathan Wist", SwingConstants.CENTER);
    name4 = new JLabel("Alex Polivka", SwingConstants.CENTER);
    name5 = new JLabel("Thomas Mandell", SwingConstants.CENTER);

    // Initializing and setting up "Back" button
    back = new JButton("Back");
    back.addActionListener((ActionListener) this);
    back.setPreferredSize(new Dimension(100, 100));
    back.setVisible(true);

    // Initializing and setting up "Dog Image API" button
    dogImageApi = new JButton("Dog Image API");
    dogImageApi.addActionListener((ActionListener) this);
    dogImageApi.setPreferredSize(new Dimension(100, 100));
    dogImageApi.setVisible(true);

    // Initializing and setting up "Random Dog Image API" button
    randDogImgApi = new JButton("Random Dog Image API");
    randDogImgApi.addActionListener((ActionListener) this);
    randDogImgApi.setPreferredSize(new Dimension(100, 100));
    randDogImgApi.setVisible(true);

    // Initializing and setting up "Dog Fact API" button
    dogFactApi = new JButton("Dog Fact API");
    dogFactApi.addActionListener((ActionListener) this);
    dogFactApi.setPreferredSize(new Dimension(100, 100));
    dogFactApi.setVisible(true);

    // Initializing all ImageIcons, JLabels, and JPanels
    schoolImg = new ImageIcon(MemeMaker.scaleImageIcon(
        new ImageIcon(readInImage("https://w3.cs.jmu.edu/cs101/isat-cs.jpg")), 600, 600));
    schoolImgLabel = new JLabel(schoolImg);
    schoolImgLabel.setPreferredSize(new Dimension(530, 300));

    topPanelImg = new JPanel(new GridLayout(1, 5));
    // Temproary
    for (int i = 0; i < 5; i++)
    {
      JLabel test = new JLabel(new ImageIcon(MemeMaker.scaleImageIcon(new ImageIcon(readInImage(
          "https://t4.ftcdn.net/jpg/02/15/84/43/360_F_215844325_ttX9YiIIyeaR7Ne6EaLLjMAmy4GvPC69.jpg")),
          100, 100)));
      test.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      topPanelImg.add(test);
    }
    // topPanelImg.add(pic1);
    // topPanelImg.add(pic2);
    // topPanelImg.add(pic3);
    // topPanelImg.add(pic4);
    // topPanelImg.add(pic5);

    topPanelName = new JPanel(new GridLayout(1, 5));
    topPanelName.setPreferredSize(new Dimension(50, 50));
    topPanelName.add(name1);
    topPanelName.add(name2);
    topPanelName.add(name3);
    topPanelName.add(name4);
    topPanelName.add(name5);

    // Initializing JButtons and bottom button panel
    bottomSources = new JPanel(new GridLayout(1, 4));

    bottomSources.add(back);
    bottomSources.add(dogImageApi);
    bottomSources.add(randDogImgApi);
    bottomSources.add(dogFactApi);

    // Initializing all SplitPanes
    topSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanelImg, topPanelName);
    topSplitPane.setDividerLocation(100);
    topSplitPane.setEnabled(false);

    midSplitPaneSub = new JSplitPane(JSplitPane.VERTICAL_SPLIT, appDescriptTxtArea,
        acknowledgementsTxtArea);
    midSplitPaneSub.setEnabled(false);

    midSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, midSplitPaneSub, schoolImgLabel);
    midSplitPane.setDividerLocation(530);
    midSplitPane.setEnabled(false);

    bottomSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, midSplitPane, bottomSources);
    bottomSplitPane.setDividerLocation(400);
    bottomSplitPane.setEnabled(false);

    topSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, title, topSplitPane);
    topSplitPane.setEnabled(false);

    splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplitPane, bottomSplitPane);
    splitPane.setEnabled(false);

    // Initializing JPanel that houses all other aboutPage components
    aboutPage = new JPanel(new GridLayout(1, 2));

    aboutPage.add(splitPane);
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
    else if (choice.equals("Dog Image API"))
    {
      int optionResult = JOptionPane.showConfirmDialog(Window.frame,
          "You will now be directed away from the Dog App to a webpage opened in your default browser.\nWould you still like to continue?",
          "Opening Page in Browser", JOptionPane.YES_NO_OPTION);
      if (optionResult == JOptionPane.YES_OPTION)
      {
        openWebpage("https://api.thedogapi.com/v1/breeds");
      }
    }
    else if (choice.equals("Random Dog Image API"))
    {
      int optionResult = JOptionPane.showConfirmDialog(Window.frame,
          "You will now be directed away from the Dog App to a webpage opened in your default browser.\nWould you still like to continue?",
          "Opening Page in Browser", JOptionPane.YES_NO_OPTION);
      if (optionResult == JOptionPane.YES_OPTION)
      {
        openWebpage("https://dog.ceo/api/breeds/image/random");
      }
    }
    else if (choice.equals("Dog Fact API"))
    {
      int optionResult = JOptionPane.showConfirmDialog(Window.frame,
          "You will now be directed away from the Dog App to a webpage opened in your default browser.\nWould you still like to continue?",
          "Opening Page in Browser", JOptionPane.YES_NO_OPTION);
      if (optionResult == JOptionPane.YES_OPTION)
      {
        openWebpage("https://raw.githubusercontent.com/DukeNgn/Dog-facts-API/master/data.json");
      }
    }
  }
}
