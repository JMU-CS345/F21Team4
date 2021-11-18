import java.io.IOException;
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

  public RandomFactPane() throws IOException {
	  getFacts();
	  
  }
  
  public void getFacts() throws IOException {
	  factList = new ArrayList<String>();
	    URL url = new URL("https://github.com/DukeNgn/Dog-facts-API");

	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode tree = mapper.readTree(url);

	    for (int x = 0; x < tree.size(); x++)
	    {
	      JsonNode stringNode = tree.get(x);
	      String fact = stringNode.get("fact").asText();
	      this.factList.add(fact);
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
