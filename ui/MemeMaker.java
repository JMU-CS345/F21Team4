import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * This class sets up the Meme Makers UI.
 * 
 * @author Matt Wong, Zach Tucker, Thomas Mandell, Alex Polivka, Jonathan Wist
 *
 */
public class MemeMaker extends JFrame implements ActionListener
{
  private JFrame memeMakeFrame;
  private JMenu menuFile;
  private JMenu menuEdit;
  private JMenuItem menuItem;
  private JMenuBar menuBar;

  public Stack changeHistory;

  public MemeMaker()
  {
    memeMakeFrame = new JFrame();
    memeMakeFrame.setSize(new Dimension(750, 750));

    memeMakeFrame.setVisible(true);
    memeMakeFrame.setJMenuBar(createMenuBar());

    // Initializes the change history for each instance of the MemeEditor Effective reseting it upon
    // each open.
    changeHistory = new Stack();

    memeMakeFrame.addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        DogDisplay.openAlready = false;
      }
    });
  }

  /*
   * This creates the menu bar at the top of the meme editor.
   */
  protected JMenuBar createMenuBar()
  {
    menuBar = new JMenuBar();

    setUpMenuFile();
    setUpMenuEdit();

    return menuBar;
  }

  /*
   * This method sets up the "Edit" menu and its items for the menu bar in the meme editor.
   */
  private void setUpMenuEdit()
  {
    // Sets up the "Edit" menu and adds it to the menu bar
    menuEdit = new JMenu("Edit");
    menuEdit.setMnemonic(KeyEvent.VK_D);
    menuBar.add(menuEdit);

    // Sets up the "Undo" option in the "File" menu
    menuItem = new JMenuItem("Undo");
    menuItem.setMnemonic(KeyEvent.VK_Z);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.ALT_MASK));
    menuItem.setActionCommand("undo");
    menuItem.addActionListener(this);
    menuEdit.add(menuItem);

    // Sets up the "Redo" option in the "File" menu
    menuItem = new JMenuItem("Redo");
    menuItem.setMnemonic(KeyEvent.VK_R);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
    menuItem.setActionCommand("redo");
    menuItem.addActionListener(this);
    menuEdit.add(menuItem);
  }

  /*
   * This method sets up the "File" menu and its items for the menu bar in the meme editor.
   */
  private void setUpMenuFile()
  {
    // Sets up the "File" menu and adds it to the menu bar
    menuFile = new JMenu("File");
    menuFile.setMnemonic(KeyEvent.VK_D);
    menuBar.add(menuFile);

    // Sets up the "Save" option in the "File" menu
    menuItem = new JMenuItem("Save");
    menuItem.setMnemonic(KeyEvent.VK_N);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
    menuItem.setActionCommand("save");
    menuItem.addActionListener(this);
    menuFile.add(menuItem);

    // Sets up the "Quit" option in the "File" menu
    menuItem = new JMenuItem("Quit");
    menuItem.setMnemonic(KeyEvent.VK_Q);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
    menuItem.setActionCommand("quit");
    menuItem.addActionListener(this);
    menuFile.add(menuItem);
  }

  /*
   * Closes the Meme Makers frame and sets the openAlready boolean to false.
   */
  private void close()
  {
    DogDisplay.openAlready = false;
    memeMakeFrame.setVisible(false);
    memeMakeFrame.dispose();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if ("save".equals(e.getActionCommand()))
    {
      System.out.println("Feature not available yet");
    }
    else if ("quit".equals(e.getActionCommand()))
    {

      int optionResult = JOptionPane.showConfirmDialog(memeMakeFrame,
          "If you exit this page without saving, you will lose all progress.\nWould you still like to continue?",
          "Do not exit yet", JOptionPane.YES_NO_OPTION);
      if (optionResult == JOptionPane.YES_OPTION)
      {
        close();
      }
    }
    else if ("undo".equals(e.getActionCommand()))
    {
      System.out.println("Feature not available yet");
    }
    else if ("redo".equals(e.getActionCommand()))
    {
      System.out.println("Feature not available yet");
    }
  }
}
