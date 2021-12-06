import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class hold the components that make up the dog display, including the dog Image displayer,
 * Dog fact Display, scroll pane and back button.
 * 
 * @author Zack Tucker, Matt Wong, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 */

public class DogDisplay extends JPanel implements ListSelectionListener
{
  // Declaring all button components and variables
  private String choice;

  private JButton fullScreenButton;
  private JButton back;
  private JButton makeMeme;

  // Declaring all variables and components for the scroll pane
  private JScrollPane scrollPane;
  public JSplitPane splitPane;
  private JPanel pictureAndText;

  private List<String> dogBreeds;
  private List<Dog> dogList;
  private JList dogJList;

  // Declaring and and initializing variables for displaying
  private JLabel dogPictureLabel = new JLabel(" ", JLabel.CENTER);
  private JLabel dogInformationLabel = new JLabel(" ", JLabel.CENTER);

  // Declaring all variable and components for full screen mode
  public Image currImg;
  public ImageIcon icon;
  public JPanel fullScreenImage;
  private JLabel fullScreenLabel;
  private JLabel fullscreenInfo = new JLabel("Hit esc to exit fullscreen and return to the information page.");

  // Declaring and initializing MemeEditor property variable
  public static boolean openAlready = false;

  // Declaring and initializing all default window dimensions
  private final int windowWidth = 1024;
  private final int windowHeight = 768;

  /**
   * Constructor for all the DogDisplay elements, including the frame, panes, and scrollable-list
   * along with the dog picture URLs and names.
   * 
   * @throws IOException
   *           If getting the names or photo URLs fail to pull from the API.
   */
  public DogDisplay() throws IOException
  {
    // Initializing scroll pane and all necessary information variables
    scrollPane = new JScrollPane();

    dogList = new ArrayList<Dog>();
    getDogList();

    dogBreeds = new ArrayList<String>();
    getDogNames();

    dogJList = new JList(dogBreeds.toArray());
    scrollPane.setViewportView(dogJList);

    dogJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    dogJList.addListSelectionListener(this);

    // Initializing "Back" button
    back = new JButton("Back");
    back.addActionListener(new ButtonPress());
    back.setPreferredSize(new Dimension(100, 100));
    back.setVisible(true);

    // Initializing "Full Screen" button
    fullScreenButton = new JButton("Full Screen");
    fullScreenButton.addActionListener(new ButtonPress());
    fullScreenButton.setPreferredSize(new Dimension(100, 100));
    fullScreenButton.setVisible(false);

    // Initializing and setting up "Make a meme!" button
    makeMeme = new JButton("Make a meme!");
    makeMeme.addActionListener(new ButtonPress());
    makeMeme.setPreferredSize(new Dimension(100, 100));
    makeMeme.setVisible(false);

    // Initializing components and variables necessary for full screen mode
    fullScreenImage = new JPanel();
    fullScreenLabel = new JLabel();

    fullScreenImage.setPreferredSize(new Dimension(windowWidth, windowHeight));
    fullScreenLabel.setPreferredSize(new Dimension(windowWidth, windowHeight));
    fullscreenInfo.setSize(windowWidth, 50);
    fullscreenInfo.setFont(new Font("Serif", Font.PLAIN, 24));
    fullscreenInfo.setVerticalAlignment(JLabel.TOP);
    fullscreenInfo.setHorizontalAlignment(JLabel.CENTER);
    fullscreenInfo.setForeground(Color.WHITE);
    fullscreenInfo.setBackground(Color.BLACK);
    fullscreenInfo.setOpaque(true);

    fullScreenImage.add(fullScreenLabel);
    fullScreenLabel.add(fullscreenInfo);

    fullScreenImage.addKeyListener(new ButtonPress());

    // Initializing picture and text pane
    pictureAndText = new JPanel();
    pictureAndText.setLayout(new GridLayout(3, 3));
    pictureAndText.add(dogPictureLabel);
    pictureAndText.add(dogInformationLabel);
    pictureAndText.add(fullScreenButton);
    pictureAndText.add(back);
    pictureAndText.add(makeMeme);

    // Initializing split pane that houses all other DigDisplay components
    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, pictureAndText);
    splitPane.setVisible(false);

  }

  /**
   * Gets the names of the dogs from the API and stores them in a list.
   * 
   * @throws IOException
   *           If the URL to the API is invalid.
   */
  public void getDogNames() throws IOException
  {
    for (Dog dog : dogList)
    {
      this.dogBreeds.add(dog.getName());
    }
  }

  /**
   * Creates an object mapper to find all the names, image urls, height, weight, origin, life span,
   * and temperament in the API and stores them in an ArrayList.
   * 
   * @throws IOException
   */
  public void getDogList() throws IOException
  {
    URL url = new URL("https://api.thedogapi.com/v1/breeds");

    ObjectMapper mapper = new ObjectMapper();
    JsonNode tree = mapper.readTree(url);

    for (int x = 0; x < tree.size(); x++)
    {
      JsonNode breedNode = tree.get(x);

      String dogBreed = breedNode.get("name").asText();
      String urlString = breedNode.get("image").get("url").asText();
      URL urlDogPics = new URL(urlString);
      String height = breedNode.get("height").get("imperial").asText();
      String weight = breedNode.get("weight").get("imperial").asText();
      String origin = null;
      if (breedNode.get("origin") != null)
        origin = breedNode.get("origin").asText();
      String lifespan = breedNode.get("life_span").asText();
      String temperament = null;
      if (breedNode.get("temperament") != null)
        temperament = breedNode.get("temperament").asText();

      Dog dog = new Dog(dogBreed, urlDogPics, height, weight, origin, lifespan, temperament);
      this.dogList.add(dog);
    }

  }

  /**
   * Updates the state of the panel when the user clicks on an element of the scrollable-list.
   * 
   * @param e
   *          scrollable list selection.
   */
  public void valueChanged(ListSelectionEvent e)
  {
    if (!e.getValueIsAdjusting())
    {
      currImg = null;
      try
      {
        int index = -1;
        for (Dog dog : dogList)
        {
          if (dog.getName().equals(dogJList.getSelectedValue()))
          {
            index = dogList.indexOf(dog);
            break;
          }
        }
        URL picURL = dogList.get(index).getURL();
        currImg = ImageIO.read(picURL);
        currImg = currImg.getScaledInstance(windowWidth / 3, windowHeight / 3, Image.SCALE_SMOOTH);
        dogInformationLabel.setText("<html>" + "Dog Breed: " + dogList.get(index).getName()
            + "<br/>" + " Dog Height: " + dogList.get(index).getHeight() + " inches<br/>"
            + " Dog Weight: " + dogList.get(index).getWeight() + "lbs<br/>" + " Dog Lifespan: "
            + dogList.get(index).getLifespan() + "<br/>" + " Dog Tempermant: "
            + dogList.get(index).getTemperament() + "<br/>" + "Press ESC to exit fullscreen"
            + "<html/>");
      }
      catch (IOException exception)
      {
        exception.printStackTrace();
      }
      icon = new ImageIcon(currImg);
      dogPictureLabel.setIcon(icon);

      fullScreenButton.setVisible(true);
      makeMeme.setVisible(true);

      dogPictureLabel.setVerticalTextPosition(JLabel.BOTTOM);
      dogPictureLabel.setHorizontalTextPosition(JLabel.CENTER);
    }
  }

  /*
   * Holds the functionality for all buttons in the DogDisplay Pane.
   */
  private class ButtonPress implements ActionListener, KeyListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      choice = e.getActionCommand();

      if (choice.equals("Full Screen"))
      {
        if (currImg != null)
        {
          fullScreenLabel.setIcon(new ImageIcon(
              currImg.getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH)));
          Window.layout.show(Window.layoutPane, "fullscreen");
          fullScreenImage.requestFocus();
        }
      }
      else if (choice.equals("Back"))
      {
        if (!openAlready)
        {
          Window.layout.show(Window.layoutPane, "homescreen");
          Window.frame.setTitle("Dog App");
        }
        else
        {
          JOptionPane.showMessageDialog(Window.frame,
              "You have a meme editor open. \nPlease close your Meme editor window to go back, thank you.\n",
              "Open Meme Editor", JOptionPane.WARNING_MESSAGE);
        }
      }
      else if (choice.equals("Make a meme!"))
      {
        if (!openAlready)
        {
          new MemeMaker(icon);
          openAlready = true;
        }
        else
        {
          JOptionPane.showMessageDialog(Window.frame,
              "You already have a meme editor open. \nPlease close your current Meme editor window to open a new one, thank you.\n",
              "Open Meme Editor", JOptionPane.WARNING_MESSAGE);
        }
      }
      else
      {
        System.out.println("Invalid Command");
      }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
      // Does nothing, only for implementation.
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
      // Does nothing, only for implementation.
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
      if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
      {
        Window.layout.show(Window.layoutPane, "dogdisplay");
      }
    }
  }
}
