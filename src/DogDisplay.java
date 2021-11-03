import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * A class.
 * 
 * @author Zack Tucker, Matt Wong, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 */

public class DogDisplay {

  private JFrame frame;
  private final int windowWidth = 1024;
  private final int windowHeight = 768;
  List<String> dogBreeds = new ArrayList<String>();
  // private String[] dogNames;

  public DogDisplay() {

  }

  public void createAndShowGUI() {

    // Create and set up the window.
    frame = new JFrame("DogDisplay");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Display the window.
    frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
    frame.pack();
    frame.setVisible(true);
  }

  public void getDogNames() throws IOException {
    StringBuilder sb = new StringBuilder();
    String line;
    URL url = new URL("https://api.thedogapi.com/v1/breeds");



    ObjectMapper mapper = new ObjectMapper();
    JsonNode tree = mapper.readTree(url);
    List<String> dogBreeds = new ArrayList<String>();

    for (int x = 0; x < tree.size(); x++) {
      JsonNode breedNode = tree.get(x);

      String dogBreed = breedNode.get("name").asText();
      this.dogBreeds.add(x, dogBreed);
    }


  }
}
