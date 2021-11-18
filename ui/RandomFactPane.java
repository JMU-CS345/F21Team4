import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class RandomFactPane extends JPanel implements ListSelectionListener {

  String urlString;
  URL urlDogPic;
  JButton random = new JButton("Random Dog Picture");
  JPanel dogRandom = new JPanel();


  private final int windowWidth = 1024;
  private final int windowHeight = 768;

  public RandomFactPane() {

  }


  @Override
  public void valueChanged(ListSelectionEvent e) {
    // TODO Auto-generated method stub
    urlString = "https://dog.ceo/api/breeds/image/random";
    try {
      urlDogPic = new URL(urlString);
    } catch (MalformedURLException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

}
