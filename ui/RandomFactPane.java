import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class hold the components that make up the Random Fact Generator Page of the DogApp.
 * 
 * @author Matt Wong, Zach Tucker, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 */
// export list maybe
public class RandomFactPane extends JPanel implements ActionListener
{
  // Declaring all button components and variables
  private String choice;
  private JPanel buttonPad;

  private JButton random;
  private JButton back;

  // Declaring all variables for fact generation
  String urlString;
  URL urlDogPic;

  // Declaring all components and variables for Random Fact Display
  JPanel factRandom;
  private ArrayList<String> factList;

  private JTextArea factTxtArea;
  private JSplitPane splitPane;

  // Declaring and initializing all default window dimensions
  private final int windowWidth = 1024;
  private final int windowHeight = 768;

  public RandomFactPane() throws IOException
  {
    // Initializing "New Fact!" (random fact generation) button
    random = new JButton("New Fact!");
    random.setVisible(true);
    random.setPreferredSize(new Dimension(100, 100));
    random.addActionListener(this);

    // Initializing "Back" button
    back = new JButton("Back");
    back.addActionListener((ActionListener) this);
    back.setPreferredSize(new Dimension(100, 100));
    back.setVisible(true);

    // Initializing and setting up button Pad
    buttonPad = new JPanel(new GridLayout(1, 2));
    buttonPad.setPreferredSize(new Dimension(windowWidth, 100));
    buttonPad.add(random);
    buttonPad.add(back);

    // Initializing variables and components for fact display
    getFacts();

    factTxtArea = new JTextArea(factList.get(0));
    factTxtArea.setVisible(true);
    factTxtArea.setLineWrap(true);
    factTxtArea.setWrapStyleWord(true);
    factTxtArea.setSize(windowWidth, windowHeight);

    // Initializing SplitPane that houses the buttonPad and fact panel
    splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonPad, factTxtArea);
    splitPane.setPreferredSize(new Dimension(windowWidth, windowHeight));

    // Initializing and setting up Random Fact JPanel
    factRandom = new JPanel();
    factRandom.add(splitPane);

  }

  /**
   * Creates an object mapper to find all the "facts" in the API and stores them in an ArrayList.
   * 
   * @throws IOException
   */
  public void getFacts() throws IOException
  {
    factList = new ArrayList<String>();
    URL url = new URL("https://raw.githubusercontent.com/DukeNgn/Dog-facts-API/master/data.json");

    ObjectMapper mapper = new ObjectMapper();
    JsonNode tree = mapper.readTree(url);

    for (int x = 0; x < tree.size(); x++)
    {
      JsonNode factNode = tree.get(x);
      String fact = factNode.get("fact").asText();
      this.factList.add(fact);
    }

  }

  /*
   * Holds the functionality for all buttons in the Random Fact Pane.
   */
  @Override
  public void actionPerformed(ActionEvent buttonEvent)
  {
    choice = buttonEvent.getActionCommand();
    if (choice.equals("New Fact!"))
    {
      factTxtArea = new JTextArea(
          factList.get((int) Math.floor(Math.random() * (100 - 0 + 1) + 0)));
      splitPane.setBottomComponent(factTxtArea);
    }
    else if (choice.equals("Back"))
    {
      Window.layout.show(Window.layoutPane, "homescreen");
      Window.frame.setTitle("Dog App");
    }
    else
    {
      System.out.println("Invalid Command");
    }

  }

}
