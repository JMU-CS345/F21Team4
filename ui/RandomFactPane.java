import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RandomFactPane extends JPanel implements ListSelectionListener {

  String urlString;
  URL urlDogPic;
  JButton random = new JButton("Random Dog Picture");
  JPanel factRandom = new JPanel();
  private ArrayList<String> factList;


  private final int windowWidth = 1024;
  private final int windowHeight = 768;

  public RandomFactPane() {
	  factList = getFacts();
	  
  }
  
  public ArrayList<String> getFacts() {
	    URL url = new URL("https://github.com/DukeNgn/Dog-facts-API");

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
	      this.factList.add(dog);
	    }
	  
  }


  @Override
  public void valueChanged(ListSelectionEvent e) {
    urlString = "https://dog.ceo/api/breeds/image/random";
    try {
      urlDogPic = new URL(urlString);
    } catch (MalformedURLException e1) {
      e1.printStackTrace();
    }
  }

}
