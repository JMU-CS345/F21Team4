import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * A class.
 * 
 * @author Zack Tucker, Matt Wong, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 */

public class DogDisplay implements ListSelectionListener
{

  private JFrame frame;
  private final int windowWidth = 1024;
  private final int windowHeight = 768;
  List<String> dogBreeds;
  List<URL> dogPictures;
  JList dogJList;
  JScrollPane scrollPane;
  JSplitPane splitPane;
  JPanel pictureAndText;
  static JLabel dogBreedLabel = new JLabel();

  public DogDisplay() throws IOException
  {
    
    frame = new JFrame("Dog Display");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    scrollPane = new JScrollPane();
    dogBreeds = new ArrayList<String>();
    getDogNames();
    dogJList = new JList(dogBreeds.toArray());
    scrollPane.setViewportView(dogJList);
    
    dogJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    dogJList.addListSelectionListener(this);

    pictureAndText = new JPanel();
    pictureAndText.add(dogBreedLabel);
    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollPane,pictureAndText);
    
  }

  public void createAndShowGUI()
  {

    // Create and set up the window.
    frame = new JFrame("DogDisplay");
    frame.add(this.splitPane);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Display the window.
    frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
    frame.pack();
    frame.setVisible(true);
  }

  public void getDogNames() throws IOException
  {
    StringBuilder sb = new StringBuilder();
    String line;
    URL url = new URL("https://api.thedogapi.com/v1/breeds");

    ObjectMapper mapper = new ObjectMapper();
    JsonNode tree = mapper.readTree(url);

    for (int x = 0; x < tree.size(); x++)
    {
      JsonNode breedNode = tree.get(x);

      String dogBreed = breedNode.get("name").asText();
      this.dogBreeds.add(x, dogBreed);
    }

  }
  
  public void getDogPhotoURL() throws IOException {
    StringBuilder sb = new StringBuilder();
    String line;
    URL url = new URL("https://api.thedogapi.com/v1/breeds%22");

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

  @Override
  public void valueChanged(ListSelectionEvent e)
  {
    if (!e.getValueIsAdjusting())
    {
      String textDisp = "No info yet";
      dogBreedLabel.setText(textDisp);
      dogBreedLabel.setVerticalTextPosition(JLabel.BOTTOM);
      dogBreedLabel.setHorizontalTextPosition(JLabel.CENTER);
    }

  }
}
