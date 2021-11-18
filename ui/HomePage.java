import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HomePage extends JPanel
{
  public static JPanel buttonPad;
  private String choice;

  public HomePage()
  {
    buttonPad = new JPanel(new GridLayout(1, 3));
    JButton dogDisplay = new JButton("Dog Display");
    JButton randomPic = new JButton("Random Dog Picture");
    JButton randomFact = new JButton("Random Facts");

    dogDisplay.addActionListener(new ButtonPress());
    randomPic.addActionListener(new ButtonPress());
    randomFact.addActionListener(new ButtonPress());

    buttonPad.add(dogDisplay);
    buttonPad.add(randomPic);
    buttonPad.add(randomFact);

    buttonPad.setVisible(true);

  }

  private class ButtonPress implements ActionListener
  {

    @Override
    public void actionPerformed(ActionEvent e)
    {
      choice = e.getActionCommand();
      if (choice.equals("Dog Display"))
      {

        Window.layout.show(Window.layoutPane, "dogdisplay");
      }
      else if (choice.equals("Random Dog Picture"))
      {

        System.out.print("worked");
      }
      else
      {
        System.out.print("worked1");
      }
      buttonPad.setVisible(false);

      
    }


    }
  }


