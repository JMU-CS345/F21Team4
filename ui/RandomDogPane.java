import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RandomDogPane extends JPanel {
  URL urlDogPic;

  JButton random = new JButton("Random Dog Picture");
  public static JPanel dogRandom = new JPanel();
  JLabel image = new JLabel(" ", JLabel.CENTER);

  private Image currImg = null;

  private final int windowWidth = 1024;
  private final int windowHeight = 768;

  public RandomDogPane() {
    dogRandom.setPreferredSize(new Dimension(1024, 760));

    random.addActionListener(new ButtonPress());
    random.setPreferredSize(new Dimension(100, 100));
    random.setVisible(true);

    image.setVisible(true);
    dogRandom.add(image);
    dogRandom.add(random);

    dogRandom.setVisible(true);

  }

  public void getDogImage() throws IOException {

    URL url = new URL("https://dog.ceo/api/breeds/image/random");


    ObjectMapper mapper = new ObjectMapper();
    JsonNode tree = mapper.readTree(url);

    JsonNode breedNode = tree.get(0);

    String urlString = breedNode.get("message").asText();

    urlDogPic = new URL(urlString);

  }

  private class ButtonPress implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub

      ImageIcon icon = new ImageIcon(urlDogPic);
      image.setIcon(icon);
      // back.setVisible(true);

    }
  }


}
