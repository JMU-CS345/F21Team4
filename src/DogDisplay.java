import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
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

public class DogDisplay implements ListSelectionListener {

  private JFrame frame;
  private final int windowWidth = 1024;
  private final int windowHeight = 768;


  private List<String> dogBreeds;
  private List<URL> dogPictures;
  private List<Dog> dogList;
  private JList dogJList;
  private JScrollPane scrollPane;
  private JSplitPane splitPane;
  private JPanel pictureAndText;
  private JLabel dogBreedLabel = new JLabel();
  private JButton fullScreenButton;

  /**
   * Constructor for all the DogDisplay elements, including the frame, panes, and scrollable-list
   * along with the dog picture URLs and names.
   * 
   * @throws IOException If getting the names or photo URLs fail to pull from the API.
   */
  public DogDisplay() throws IOException {

    frame = new JFrame("Dog Display");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    scrollPane = new JScrollPane();
    // These two lists below need to be removed and there
    // functionality needs to be applied to dogList
    dogBreeds = new ArrayList<String>();
    getDogNames();
    dogPictures = new ArrayList<URL>();
    getDogPhotoURL();
    
    dogJList = new JList(dogBreeds.toArray());
    scrollPane.setViewportView(dogJList);

    dogJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    dogJList.addListSelectionListener(this);

    pictureAndText = new JPanel();
    pictureAndText.add(dogBreedLabel);
    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, pictureAndText);
    
    fullScreenButton = new JButton("FullScreen");
    
  }


  public void createAndShowGUI() {


    // Create and set up the window.
    frame = new JFrame("DogDisplay");
    frame.add(this.splitPane);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Display the window.
    frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
    frame.pack();
    frame.setVisible(true);
  }


  /**
   * Gets the names of the dogs from the API and stores them in an array.
   * 
   * @throws IOException If the URL to the API is invalid.
   */
  public void getDogNames() throws IOException {

    URL url = new URL("https://api.thedogapi.com/v1/breeds");

    ObjectMapper mapper = new ObjectMapper();
    JsonNode tree = mapper.readTree(url);

    for (int x = 0; x < tree.size(); x++) {
      JsonNode breedNode = tree.get(x);

      String dogBreed = breedNode.get("name").asText();
      this.dogBreeds.add(x, dogBreed);
    }

  }

  public void getDogPhotoURL() throws IOException {

    URL url = new URL("https://api.thedogapi.com/v1/breeds");

    ObjectMapper mapper = new ObjectMapper();
    JsonNode tree = mapper.readTree(url);



    for (int x = 0; x < tree.size(); x++) {
      JsonNode jsonNode = tree.get("photo_url");

      URL urlDogPics = null;
      String urlString = jsonNode.asText();
      urlDogPics = new URL(urlString);
      this.dogPictures.add(x, urlDogPics);
    }



  }
  
  public void getDogList() throws IOException {
    URL url = new URL("https://api.thedogapi.com/v1/breeds");

    ObjectMapper mapper = new ObjectMapper();
    JsonNode tree = mapper.readTree(url);
    
    for (int x = 0; x < tree.size(); x++)
    {
      JsonNode breedNode = tree.get(x);

      String dogBreed = breedNode.get("name").asText();
      String urlString = breedNode.get("image").get("url").asText();
      URL urlDogPics = new URL(urlString);
      String height = breedNode.get("height").asText();
      String weight = breedNode.get("weight").asText();
      String origin = breedNode.get("origin").asText();
      String lifespan = breedNode.get("lifespan").asText();
      String description = breedNode.get("description").asText();
      
      Dog dog = new Dog(dogBreed, urlDogPics, height, weight, origin, lifespan);
      this.dogList.add(dog);
    }
    
  }



  /**
   * Updates the state of the panel when the user clicks on an element of the scrollable-list.
   * 
   * @param e Scrollable-list selection.
   */
  public void valueChanged(ListSelectionEvent e) {
    if (!e.getValueIsAdjusting()) {
      Image currImg = null;
      try {
        int index = dogBreeds.indexOf(dogJList.getSelectedValue());
        URL picURL = dogPictures.get(index);
        currImg = ImageIO.read(picURL);
        currImg = currImg.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
      } catch (IOException exception) {
        exception.printStackTrace();
      }
      ImageIcon icon = new ImageIcon(currImg);
      dogBreedLabel.setIcon(icon);
      dogBreedLabel.setVerticalTextPosition(JLabel.BOTTOM);
      dogBreedLabel.setHorizontalTextPosition(JLabel.CENTER);

    }

  }
}
