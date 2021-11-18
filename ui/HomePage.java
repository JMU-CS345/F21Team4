import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HomePage extends JPanel
{
  public JPanel buttonPad;
  private String choice;

  public HomePage()
  {
    buttonPad = new JPanel(new GridLayout(1, 1));
    JButton dogDisplay = new JButton("Dog Display");

    dogDisplay.addActionListener(new ButtonPress());

    buttonPad.add(dogDisplay);

    buttonPad.setVisible(true);

  }

  private class ButtonPress implements ActionListener
  {

    @Override
    public void actionPerformed(ActionEvent e)
    {
      choice = e.getActionCommand();
      buttonPad.setVisible(false);
      Window.layout.show(Window.layoutPane, "dogdisplay");
    }

  }

}
