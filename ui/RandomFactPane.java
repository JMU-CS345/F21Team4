import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

public class RandomFactPane extends JPanel implements ActionListener
{
  // Declaring all button components and variables
  private String choice;
  private JPanel topButtonPad;
  private JPanel bottomButtonPad;

  private JButton random;
  private JButton back;
  private JButton addFact;

  private JButton save;

  // Declaring all variables for fact generation
  private ArrayList<String> factList;
  private List<String> userFactList;

  private String curFact;

  private JSplitPane topSplitPane;
  private JTextArea factTxtArea;

  private JSplitPane bottomSplitPane;
  private JTextArea listTxtArea;
  private JScrollPane scrollPane;

  private JSplitPane splitPane;

  // Declaring and initializing all default window dimensions
  private final int windowWidth = 1024;
  private final int windowHeight = 768;
  
  // Declaring all components and variables for Random Fact Display
  public JPanel factRandom;

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

    // Initializing "Save" button
    save = new JButton("Save List");
    save.addActionListener((ActionListener) this);
    save.setPreferredSize(new Dimension(100, 100));
    save.setVisible(true);

    // Initializing "AddFact" button
    addFact = new JButton("Add Fact");
    addFact.addActionListener((ActionListener) this);
    addFact.setPreferredSize(new Dimension(100, 100));
    addFact.setVisible(true);

    // Initializing and setting up buttonPads
    topButtonPad = new JPanel(new GridLayout(1, 2));
    topButtonPad.setPreferredSize(new Dimension(windowWidth, 100));
    topButtonPad.add(random);
    topButtonPad.add(back);

    bottomButtonPad = new JPanel(new GridLayout(2, 1));
    bottomButtonPad.setPreferredSize(new Dimension(windowWidth, 100));
    bottomButtonPad.add(addFact);
    bottomButtonPad.add(save);

    // Initializing variables and components for fact display and users fact list
    getFacts();

    curFact = factList.get((int) Math.floor(Math.random() * (100 - 0 + 1) + 0));

    factTxtArea = new JTextArea(curFact);
    factTxtArea.setVisible(true);
    factTxtArea.setLineWrap(true);
    factTxtArea.setWrapStyleWord(true);
    factTxtArea.setEditable(false);
    factTxtArea.setSize(windowWidth / 3, windowHeight - 200);

    listTxtArea = new JTextArea();
    listTxtArea.setVisible(true);
    listTxtArea.setLineWrap(true);
    listTxtArea.setWrapStyleWord(true);
    listTxtArea.setEditable(false);

    // Initializing top splitPane that houses the top components
    topSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topButtonPad, factTxtArea);

    // Initializing bottom splitPane that houses bottom components
    scrollPane = new JScrollPane();
    scrollPane.setViewportView(listTxtArea);
    userFactList = new ArrayList<String>();

    bottomSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, bottomButtonPad, scrollPane);

    // Initializing the SplitPane that holds both top and bottom page components
    splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topSplitPane, bottomSplitPane);
    splitPane.setPreferredSize(new Dimension(windowWidth, windowHeight));
    splitPane.setDividerLocation(200);
    splitPane.setEnabled(false);

    // Initializing and setting up Random Fact JPanel
    factRandom = new JPanel(new GridLayout(1, 1));
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
      String temp = factNode.get("fact").asText();
      factList.add(temp);
    }
  }

  /**
   * Clears the users fact list and resets the fact display.
   */
  public void clearList()
  {
    userFactList = new ArrayList<String>();

    listTxtArea = new JTextArea("");
    listTxtArea.setVisible(true);
    listTxtArea.setLineWrap(true);
    listTxtArea.setWrapStyleWord(true);
    listTxtArea.setEditable(false);

    scrollPane.setViewportView(listTxtArea);
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
      String temp = factList.get((int) Math.floor(Math.random() * (100 - 0 + 1) + 0));
      curFact = temp;

      factTxtArea = new JTextArea(temp);
      factTxtArea.setLineWrap(true);
      factTxtArea.setWrapStyleWord(true);
      factTxtArea.setEditable(false);
      topSplitPane.setBottomComponent(factTxtArea);
    }
    else if (choice.equals("Back"))
    {
      Window.layout.show(Window.layoutPane, "homescreen");
      Window.frame.setTitle("Dog App");
      clearList();
    }
    else if (choice.equals("Add Fact"))
    {
      if (curFact != null)
      {
        if (!userFactList.contains(curFact))
        {
          userFactList.add(curFact);
          String listAsText = "";
          StringBuilder listAsTextBuilder = new StringBuilder();

          for (int i = 0; i < userFactList.size(); i++)
          {
            listAsTextBuilder.append(userFactList.get(i) + "\n\n");
          }

          listAsText = listAsTextBuilder.toString();
          listTxtArea = new JTextArea(listAsText);
          listTxtArea.setVisible(true);
          listTxtArea.setLineWrap(true);
          listTxtArea.setWrapStyleWord(true);
          listTxtArea.setEditable(false);

          scrollPane.setViewportView(listTxtArea);
        }
      }
    }
    else if (choice.equals("Save List"))
    {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setDialogTitle("Specify a file to save");

      int userSelection = fileChooser.showSaveDialog(Window.frame);

      if (userSelection == JFileChooser.APPROVE_OPTION)
      {
        try
        {
          String pathName = fileChooser.getSelectedFile().toString();
          File file = new File(pathName);
          FileWriter fileWriter = new FileWriter(file.getPath() + ".txt");
          fileWriter.write("Thanks for using our Dog App!\nThe following are your dog facts:\n\n");

          for (int i = 0; i < userFactList.size(); i++)
          {
            fileWriter.write("Fact " + (i + 1) + ": " + userFactList.get(i) + "\n\n");
          }
          fileWriter.close();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
    else
    {
      System.out.println("Invalid Command");
    }
  }
}
