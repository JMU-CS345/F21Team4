import java.awt.Dimension;
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

public class RandomDogPane extends JPanel implements ActionListener {

  public JPanel dogRandom = new JPanel();
  private JLabel dogPic = new JLabel(" ", JLabel.CENTER);
  private JButton random = new JButton("New Dog Picture");
  private JSplitPane sp;
  URL dogImage;

  private final int windowWidth = 1024;
  private final int windowHeight = 768;

  public RandomDogPane() {
    random.setVisible(true);
    random.addActionListener((ActionListener) this);
    dogPic.setVisible(true);
    dogPic.setPreferredSize(new Dimension(windowWidth, windowHeight));
    sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, random, dogPic);
    dogRandom.add(sp);

  }

  public void getDogImage() throws IOException {
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



  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

    try {
      this.getDogImage();
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    // j = new JTextArea(factList.get((int) Math.floor(Math.random() * (100 - 0 + 1) + 0)));
    // sp.setBottomComponent(j);


  }



}
