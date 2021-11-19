import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RandomDogPane extends JPanel implements ActionListener
{

  private String choice;
  
  private JButton back = new JButton("Back");
  private JPanel buttonPad = new JPanel();

  public JPanel dogRandom = new JPanel();
  private JLabel dogPic = new JLabel(" ", JLabel.CENTER);
  private JButton random = new JButton("New Dog Picture");
  private JSplitPane sp;
  URL dogImage;

  private final int windowWidth = 1024;
  private final int windowHeight = 768;

  public RandomDogPane()
  {
    buttonPad = new JPanel(new GridLayout(1, 2));
    
    random.setVisible(true);
    random.addActionListener((ActionListener) this);
    
    back.addActionListener((ActionListener) this);
    back.setPreferredSize(new Dimension(100, 100));
    back.setVisible(true);
    
    dogPic.setVisible(true);
    dogPic.setPreferredSize(new Dimension(windowWidth, windowHeight));
    
    buttonPad.add(random);
    buttonPad.add(back);
    sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonPad, dogPic);

    dogRandom.add(sp);

  }

  public void getDogImage() throws IOException
  {
    URL url = new URL("https://dog.ceo/api/breeds/image/random");

    ObjectMapper mapper = new ObjectMapper();
    JsonNode tree = mapper.readTree(url);

    String urlString = tree.get("message").asText();
    URL urlDogPics = new URL(urlString);

    Image currImg;
    currImg = ImageIO.read(urlDogPics);
    ImageIcon icon = new ImageIcon(currImg);
    dogPic.setIcon(icon);

  }

  public void actionPerformed(ActionEvent e)
  {

    choice = e.getActionCommand();
    if (choice.equals("New Dog Picture"))
    {
      try
      {
        this.getDogImage();
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
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
  // j = new JTextArea(factList.get((int) Math.floor(Math.random() * (100 - 0 + 1) + 0)));
  // sp.setBottomComponent(j);

}
