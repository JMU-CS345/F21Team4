import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MemeMaker extends JFrame implements ActionListener
{
  private JFrame memeMakeFrame;
  private JMenu menuFile;
  private JMenu menuEdit;
  private JMenuItem menuItem;
  private JMenuBar menuBar;

  String choice;

  public MemeMaker()
  {
    memeMakeFrame = new JFrame();
    memeMakeFrame.setSize(new Dimension(750, 750));

    // test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    memeMakeFrame.setVisible(true);
    memeMakeFrame.setJMenuBar(createMenuBar());

    memeMakeFrame.addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        DogDisplay.openAlready = false;
      }
    });
  }

  protected JMenuBar createMenuBar()
  {
    menuBar = new JMenuBar();

    setUpMenuFile();
    setUpMenuEdit();

    return menuBar;
  }

  private void setUpMenuEdit()
  {
    // Sets up the "Edit" menu and adds it to the menu bar
    menuEdit = new JMenu("Edit");
    menuEdit.setMnemonic(KeyEvent.VK_D);
    menuBar.add(menuEdit);

    // Sets up the "Quit" option in the "File" menu
    menuItem = new JMenuItem("Rotate");
    menuItem.setMnemonic(KeyEvent.VK_R);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
    menuItem.setActionCommand("rotate");
    menuItem.addActionListener(this);
    menuEdit.add(menuItem);
  }

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

  private void close()
  {
    DogDisplay.openAlready = false;
    memeMakeFrame.setVisible(false);
    memeMakeFrame.dispose();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // choice = e.getActionCommand();
    if ("save".equals(e.getActionCommand()))
    {
      System.out.println("Feature not available yet");
    }
    else if ("quit".equals(e.getActionCommand()))
    {
      close();
    }
    else if ("rotate".equals(e.getActionCommand()))
    {
      System.out.println("Feature not available yet");
    }
  }
}
