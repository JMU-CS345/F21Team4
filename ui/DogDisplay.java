import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A class.
 * 
 * @author Zack Tucker, Matt Wong, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 */

public class DogDisplay extends JPanel implements ListSelectionListener
{

  private String choice;
  
  private final int windowWidth = 1024;
  private final int windowHeight = 768;

  private List<String> dogBreeds;
  private List<Dog> dogList;
  private JList dogJList;
  private JScrollPane scrollPane;
  public JSplitPane splitPane;
  private JPanel pictureAndText;
  public JPanel fullPane;
  private JLabel dogPictureLabel = new JLabel(" ", JLabel.CENTER);
  private JLabel dogInformationLabel = new JLabel(" ", JLabel.CENTER);
  private JButton fullScreenButton;
  private Image currImg = null;
  public JPanel fullScreenImage;
  private JLabel fullScreenLabel;

  /**
   * Constructor for all the DogDisplay elements, including the frame, panes, and scrollable-list
   * along with the dog picture URLs and names.
   * 
   * @throws IOException
   *           If getting the names or photo URLs fail to pull from the API.
   */
  public DogDisplay() throws IOException
  {

    scrollPane = new JScrollPane();

    dogList = new ArrayList<Dog>();
    getDogList();

    dogBreeds = new ArrayList<String>();
    getDogNames();

    dogJList = new JList(dogBreeds.toArray());
    scrollPane.setViewportView(dogJList);

    dogJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    dogJList.addListSelectionListener(this);

    fullScreenButton = new JButton("FullScreen");
    fullScreenButton.setPreferredSize(new Dimension(100, 100));

    pictureAndText = new JPanel();
    pictureAndText.setLayout(new GridLayout(3, 1));
    pictureAndText.add(dogPictureLabel);
    pictureAndText.add(dogInformationLabel);
    pictureAndText.add(fullScreenButton);
    
    fullScreenImage = new JPanel();
    fullScreenLabel = new JLabel();

    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, pictureAndText);
    splitPane.setVisible(false);

  }

  /**
   * Gets the names of the dogs from the API and stores them in an array.
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
   *          Scrollable-list selection.
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
            + dogList.get(index).getTemperament() + "<html/>");
      }
      catch (IOException exception)
      {
        exception.printStackTrace();
      }
      ImageIcon icon = new ImageIcon(currImg);
      dogPictureLabel.setIcon(icon);
      dogPictureLabel.setVerticalTextPosition(JLabel.BOTTOM);
      dogPictureLabel.setHorizontalTextPosition(JLabel.CENTER);

    }

  }

  private class ButtonPress implements ActionListener
  {

    @Override
    public void actionPerformed(ActionEvent e)
    {
      choice = e.getActionCommand();
      if (currImg != null) {
        fullScreenLabel.setIcon(new ImageIcon(currImg));
        fullScreenImage.add(fullScreenLabel);
        fullScreenImage.setVisible(true);
      }
    }
  }

  public void show()
  {
    this.splitPane.setVisible(true);
  }
}
