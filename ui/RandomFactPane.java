import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RandomFactPane extends JPanel implements ListSelectionListener, ActionListener
{

  private String choice;
  private JPanel buttonPad = new JPanel();

  String urlString;
  URL urlDogPic;
  JButton back = new JButton("Back");
  JPanel factRandom = new JPanel();
  private ArrayList<String> factList;
  private JLabel textLabel;
  private JTextArea j;
  private JSplitPane sp;
  private Button b;

  private final int windowWidth = 1024;
  private final int windowHeight = 768;

  public RandomFactPane() throws IOException
  {
    buttonPad = new JPanel(new GridLayout(1, 2));
    buttonPad.setPreferredSize(new Dimension(windowWidth, 100));
    b = new Button("New Fact!");
    b.setVisible(true);
    b.addActionListener(this);

    back.addActionListener((ActionListener) this);
    back.setPreferredSize(new Dimension(100, 100));
    back.setVisible(true);

    buttonPad.add(b);
    buttonPad.add(back);

    getFacts();
    textLabel = new JLabel();
    j = new JTextArea(factList.get(0));
    j.setVisible(true);
    j.setLineWrap(true);
    j.setWrapStyleWord(true);
    j.setSize(windowWidth, windowHeight);
    sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonPad, j);
    sp.setPreferredSize(new Dimension(windowWidth, windowHeight));
    factRandom.add(sp);

  }

  public void getFacts() throws IOException
  {
    factList = new ArrayList<String>();
    URL url = new URL("https://dog-facts-api.herokuapp.com/api/v1/resources/dogs/all");

    ObjectMapper mapper = new ObjectMapper();
    JsonNode tree = mapper.readTree(url);
    // tree.asText();
    for (int x = 0; x < tree.size(); x++)
    {
      JsonNode factNode = tree.get(x);
      String fact = factNode.get("fact").asText();
      this.factList.add(fact);
    }

  }

  @Override
  public void valueChanged(ListSelectionEvent e)
  {
    urlString = "https://dog.ceo/api/breeds/image/random";
    try
    {
      urlDogPic = new URL(urlString);
    }
    catch (MalformedURLException e1)
    {
      e1.printStackTrace();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    choice = e.getActionCommand();
    if (choice.equals("New Fact!"))
    {
      j = new JTextArea(factList.get((int) Math.floor(Math.random() * (100 - 0 + 1) + 0)));
      sp.setBottomComponent(j);
    }
    else if (choice.equals("Back"))
    {
      Window.layout.show(Window.layoutPane, "homescreen");
    }
    else
    {
      System.out.println("Invalid Command");
    }

  }

}
