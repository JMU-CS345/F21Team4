import java.awt.Dimension;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

>>>>>>> 763bc4e0220597d59ac9d5cc5985ee8f378447cb

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

/**
 * A class.
 * 
 * @author Zack Tucker, Matt Wong, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 */

public class DogDisplay {

<<<<<<< HEAD
	private JFrame frame;
	private final int windowWidth = 1024;
	private final int windowHeight = 768;
	private JScrollPane scrollPane;

	public DogDisplay() {
	}
=======
  private JFrame frame;
  private final int windowWidth = 1024;
  private final int windowHeight = 768;
  List<String> dogBreeds = new ArrayList<String>();
  // private String[] dogNames;

  public DogDisplay() {

  }
>>>>>>> 763bc4e0220597d59ac9d5cc5985ee8f378447cb

	public void createAndShowGUI() {

		// Create and set up the window.
		frame = new JFrame("DogDisplay");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Display the window.
		frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
		frame.pack();
		frame.setVisible(true);
	}

<<<<<<< HEAD
	public void getDogNames() {
=======
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

>>>>>>> 763bc4e0220597d59ac9d5cc5985ee8f378447cb

	}
}
