import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

/**
 * This class holds the components and actions that make up the HomePage of our dog application.
 * 
 * @author Zack Tucker, Matt Wong, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 */

public class HomePage extends JPanel
{
  // Declaring all button components and variables
  private String choice;

  public JPanel buttonPad;

  private JButton dogDisplay;
  private JButton randomFact;
  private JButton randomPic;
  private JButton memeCreator;

  // Declaring HomePage panel and variables
  public JSplitPane splitPane;
  private JLabel welcomeText;

  // Declaring and initializing all default dimensions
  private final int windowWidth = 1024;
  private final int windowHeight = 768;

  public HomePage()
  {
    // Initializing "Dog Display" button
    dogDisplay = new JButton("Dog Display");
    dogDisplay.addActionListener(new ButtonPress());

    // Initializing "Random Dog Picture" button
    randomPic = new JButton("Random Dog Picture");
    randomPic.addActionListener(new ButtonPress());

    // Initializing "Random Facts" button
    randomFact = new JButton("Random Facts");
    randomFact.addActionListener(new ButtonPress());

    // Initializing "Meme Creator" button
    // memeCreator = new JButton("Meme Creator");
    // memeCreator.addActionListener(new ButtonPress());

    // Initializing buttonPad
    buttonPad = new JPanel(new GridLayout(1, 3));
    buttonPad.add(dogDisplay);
    buttonPad.add(randomPic);
    buttonPad.add(randomFact);
    // buttonPad.add(memeCreator);

    buttonPad.setPreferredSize(new Dimension(windowWidth, 334));
    buttonPad.setVisible(true);

    // Initializing welcomeText JLabel
    welcomeText = new JLabel();

    // Setting font color, text content, alignment, font type, and size
    welcomeText.setText("<html> <font color='green'>Welcome to our Dog App!</font></html>");
    welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
    welcomeText.setFont(new Font("Futura", Font.PLAIN, 50));

    welcomeText.setVisible(true);

    // Initializes split pane that houses all other HomePage components
    splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, welcomeText, buttonPad);
    splitPane.setEnabled(false);
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
        Window.frame.setTitle("Dog Display");
      }
      else if (choice.equals("Random Dog Picture"))
      {
        Window.layout.show(Window.layoutPane, "randImg");
        Window.frame.setTitle("Random Dog Picture");
      }
      else if (choice.equals("Random Facts"))
      {
        Window.layout.show(Window.layoutPane, "randFact");
        Window.frame.setTitle("Random Dog Fact");
      }
    }
  }
}
