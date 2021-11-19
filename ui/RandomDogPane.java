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
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RandomDogPane extends JPanel implements ActionListener
{
  // Declaring all button components and variables
  private String choice;

  private JPanel buttonPad;

  private JButton back;
  private JButton random;
  private JButton makeMeme;

  // Declaring all things for image displaying
  URL dogImage;

  public JPanel randImgPane;
  private JLabel randDogIcon;

  private JSplitPane sp;

  // Declaring and initializing all default dimensions
  private final int windowWidth = 1024;
  private final int windowHeight = 768;

  public RandomDogPane()
  {
    // Initializing and setting up "New Dog Picture" button
    random = new JButton("New Dog Picture");
    random.setVisible(true);
    random.setPreferredSize(new Dimension(100, 100));
    random.addActionListener((ActionListener) this);

    // Initializing and setting up "Back" button
    back = new JButton("Back");
    back.addActionListener((ActionListener) this);
    back.setPreferredSize(new Dimension(100, 100));
    back.setVisible(true);

    // Initializing and setting up "Make a meme!" button
    makeMeme = new JButton("Make a meme!");
    makeMeme.addActionListener((ActionListener) this);
    makeMeme.setPreferredSize(new Dimension(100, 100));
    makeMeme.setVisible(false);

    // Initializing and setting up
    randDogIcon = new JLabel(" ", JLabel.CENTER);
    randDogIcon.setVisible(true);
    randDogIcon.setPreferredSize(new Dimension(windowWidth, windowHeight));

    // Initializing and setting up buttonPad
    buttonPad = new JPanel();
    buttonPad.setPreferredSize(new Dimension(windowWidth, 100));
    buttonPad = new JPanel(new GridLayout(1, 2));
    buttonPad.add(random);
    buttonPad.add(back);
    // buttonPad.add(makeMeme);

    // Initializing SplitPane that houses the image buttons and images
    sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonPad, randDogIcon);

    // Initializing JPanel that hold everything else
    randImgPane = new JPanel();
    randImgPane.add(sp);

  }

  public void getDogImage() throws IOException
  {
    URL url = new URL("https://dog.ceo/api/breeds/image/random");

    ObjectMapper mapper = new ObjectMapper();
    JsonNode tree = mapper.readTree(url);

    String urlString = tree.get("message").asText();
    URL urlDogPics = new URL(urlString);

    Image currImg;
    currImg = ImageIO.read(urlDogPics);
    ImageIcon icon = new ImageIcon(currImg);
    randDogIcon.setIcon(icon);
  }

  /*
   * Holds the functionality for all buttons in the Random Image Pane.
   */
  public void actionPerformed(ActionEvent e)
  {
    choice = e.getActionCommand();

    if (choice.equals("New Dog Picture"))
    {
      try
      {
        this.getDogImage();
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
    }
    else if (choice.equals("Back"))
    {
      Window.layout.show(Window.layoutPane, "homescreen");
    }
    else if (choice.equals("Make a meme!"))
    {
      System.out.println("Feature not available yet");
    }
    else
    {
      System.out.println("Invalid Command");
    }
  }
  // j = new JTextArea(factList.get((int) Math.floor(Math.random() * (100 - 0 + 1) + 0)));
  // sp.setBottomComponent(j);

}
