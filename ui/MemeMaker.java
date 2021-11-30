import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Stack;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

import java.awt.image.BufferedImage;

/**
 * This class sets up the Meme Makers UI.
 * 
 * @author Matt Wong, Zach Tucker, Thomas Mandell, Alex Polivka, Jonathan Wist
 *
 */
public class MemeMaker extends JFrame implements ActionListener
{

  // Declaring frames and panels for the meme Maker
  private JFrame memeMakeFrame;

  private JSplitPane splitPane;

  private JPanel imagePanel;
  private JPanel toolPanel;

  // Declaring image editing components
  private JLabel picLabel;
  private ImageIcon imageIcon;

  private JPanel buttonPad;

  private JButton leftRotate;
  private JButton rightRotate;
  private JButton horizontalFlip;
  private JButton verticalFlip;
  private JButton greyScale;
  private JButton brighten;
  private JButton darken;
  private JButton deepFry;

  private String choice;

  // Declaring the Menu's and Menu Item
  private JMenuBar menuBar;

  private JMenu menuFile;
  private JMenu menuEdit;

  private JMenuItem menuItem;

  // Declaring the changeHistory variables
  private Stack changeHistory;
  private Stack redoHistory;

  public MemeMaker(ImageIcon icon)
  {
    // Initializes all the frame components
    memeMakeFrame = new JFrame();
    memeMakeFrame.setSize(new Dimension(750, 750));

    memeMakeFrame.setVisible(true);
    memeMakeFrame.setJMenuBar(createMenuBar());

    // Initializing JButtons
    leftRotate = new JButton("Rotate Left");
    leftRotate.addActionListener(new ButtonPress());

    rightRotate = new JButton("Rotate Right");
    rightRotate.addActionListener(new ButtonPress());

    horizontalFlip = new JButton("Horizontal Flip");
    horizontalFlip.addActionListener(this);

    verticalFlip = new JButton("Vertical Flip");
    verticalFlip.addActionListener(this);

    brighten = new JButton("Brighten");
    brighten.addActionListener(this);

    darken = new JButton("Darken");
    darken.addActionListener(this);

    greyScale = new JButton("Grey Scale");
    greyScale.addActionListener(this);

    deepFry = new JButton("Deep Fry");
    deepFry.addActionListener(this);

    // Initializes buttonPad and adds its JButtons
    buttonPad = new JPanel(new GridLayout(4, 2));

    buttonPad.add(leftRotate);
    buttonPad.add(rightRotate);
    buttonPad.add(horizontalFlip);
    buttonPad.add(verticalFlip);
    buttonPad.add(brighten);
    buttonPad.add(darken);
    buttonPad.add(greyScale);
    buttonPad.add(deepFry);

    // Initializes the SplitPane and its components
    imageIcon = icon;
    picLabel = new JLabel(imageIcon);
    imagePanel = new JPanel();
    imagePanel.add(picLabel);

    toolPanel = new JPanel();
    toolPanel.add(buttonPad);

    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imagePanel, toolPanel);

    // splitPane.setDividerLocation(600);

    memeMakeFrame.add(splitPane);

    // Initializes the change history for each instance of the MemeEditor effectively reseting it
    // upon each open.
    changeHistory = new Stack<ImageIcon>();
    redoHistory = new Stack<ImageIcon>();

    // Listens for to the window to check if it's ever closed.
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
    menuEdit.setMnemonic(KeyEvent.VK_E);
    menuBar.add(menuEdit);

    // Sets up the "Undo" option in the "File" menu
    menuItem = new JMenuItem("Undo");
    menuItem.setMnemonic(KeyEvent.VK_Z);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
    menuItem.setActionCommand("undo");
    menuItem.addActionListener(this);
    menuEdit.add(menuItem);

    // Sets up the "Redo" option in the "File" menu
    menuItem = new JMenuItem("Redo");
    menuItem.setMnemonic(KeyEvent.VK_R);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
    menuItem.setActionCommand("redo");
    menuItem.addActionListener(this);
    menuEdit.add(menuItem);
  }

  /*
   * This method sets up the "File" menu and its items for the menu bar in the MemeMaker frame.
   */
  private void setUpMenuFile()
  {
    // Sets up the "File" menu and adds it to the menu bar
    menuFile = new JMenu("File");
    menuFile.setMnemonic(KeyEvent.VK_D);
    menuBar.add(menuFile);

    // Sets up the "Save" option in the "File" menu
    menuItem = new JMenuItem("Save");
    menuItem.setMnemonic(KeyEvent.VK_S);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    menuItem.setActionCommand("save");
    menuItem.addActionListener(this);
    menuFile.add(menuItem);

    // Sets up the "Quit" option in the "File" menu
    menuItem = new JMenuItem("Quit");
    menuItem.setMnemonic(KeyEvent.VK_Q);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
    menuItem.setActionCommand("quit");
    menuItem.addActionListener(this);
    menuFile.add(menuItem);

    //// Sets up the "Upload" option in the "File" menu
    menuItem = new JMenuItem("Upload");
    menuItem.setMnemonic(KeyEvent.VK_U);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
    menuItem.setActionCommand("upload");
    menuItem.addActionListener(this);
    menuFile.add(menuItem);
  }

  /*
   * Closes the MemeMaker frame and sets the openAlready boolean to false.
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
      // System.out.println("Save feature not available yet");
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setDialogTitle("Specify a file to save");

      int userSelection = fileChooser.showSaveDialog(memeMakeFrame);

      if (userSelection == JFileChooser.APPROVE_OPTION)
      {
        File fileToSave = fileChooser.getSelectedFile();

        System.out.println("Save as file: " + fileToSave.getAbsolutePath());
      }
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
      redoHistory.push(changeHistory.pop());
      picLabel.setIcon((Icon) changeHistory.peek());
    }
    else if ("redo".equals(e.getActionCommand()))
    {
      changeHistory.push(redoHistory.pop());
      picLabel.setIcon((Icon) changeHistory.peek());
    }
    else if ("upload".equals(e.getActionCommand()))
    {
      int optionResult = JOptionPane.showConfirmDialog(memeMakeFrame,
          "If you upload a new image this page without saving, you will lose all progress.\nWould you still like to continue?",
          "Do not exit yet", JOptionPane.YES_NO_OPTION);
      if (optionResult == JOptionPane.YES_OPTION)
      {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION)
        {
          File selectedFile = fileChooser.getSelectedFile();
          System.out.println("Selected file: " + selectedFile.getAbsolutePath());

          picLabel.setIcon(new ImageIcon(selectedFile.getAbsolutePath().toString()));
        }
      }
    }
  }

  private class ButtonPress implements ActionListener
  {

    @Override
    public void actionPerformed(ActionEvent e)
    {
      choice = e.getActionCommand();

      if (choice.equals("Rotate Left"))
      {
        // BufferedImage b = (BufferedImage) imageIcon.getImage();
        // CustomImage ci = MemeMakerEditingUtils.bufferedImageToImage(b);
        // b = MemeMakerEditingUtils.leftRotate(ci);
        // picLabel.setIcon(new ImageIcon(b));
      }
      else if (choice.equals("Rotate Right"))
      {
        BufferedImage b = MemeMakerEditingUtils.iconToBufferedImage(imageIcon.getImage());
        CustomImage ci = MemeMakerEditingUtils.bufferedImageToImage(b);
        b = MemeMakerEditingUtils.rightRotate(ci);
        picLabel.setIcon(new ImageIcon(b));
        System.out.println("worked");
        // BufferedImage test = MemeMakerEditingUtils.rightRotate(i);
      }
      else if (choice.equals("Horizontal Flip"))
      {

      }
      else if (choice.equals("Vertical Flip"))
      {

      }
      else if (choice.equals("Brighten"))
      {

      }
      else if (choice.equals("Darken"))
      {

      }
      else if (choice.equals("Grey Scale"))
      {

      }
      else if (choice.equals("Deep Fry"))
      {

      }
    }
  }

}
