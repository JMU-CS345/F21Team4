import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;
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

  private final int windowWidth = 800;
  private final int windowHeight = 750;

  // Declaring image editing components
  private JLabel picLabel;
  private ImageIcon imageIcon;
  private BufferedImage initImg;
  private BufferedImage changesNoText;

  private JPanel buttonPad;

  private JButton leftRotate;
  private JButton rightRotate;
  private JButton horizontalFlip;
  private JButton verticalFlip;
  private JButton greyScale;
  private JButton brighten;
  private JButton darken;
  private JButton deepFry;
  private JButton topText;
  private JButton bottomText;
  private JButton reset;

  private String choice;

  private String currentTop;
  private String currentBottom;

  // Declaring the Menu's and Menu Item
  private JMenuBar menuBar;

  private JMenu menuFile;
  private JMenu menuEdit;

  private JMenuItem menuItem;

  // Declaring the changeHistory variables
  private Stack<ImageIcon> changeHistory;
  private Stack<ImageIcon> redoHistory;

  public MemeMaker(ImageIcon icon)
  {
    // Initializes all the frame components
    memeMakeFrame = new JFrame();
    memeMakeFrame.setSize(new Dimension(windowWidth, windowHeight));

    memeMakeFrame.setVisible(true);
    memeMakeFrame.setJMenuBar(createMenuBar());

    // Initializing JButtons
    leftRotate = new JButton("Rotate Left");
    leftRotate.addActionListener(new ButtonPress());

    rightRotate = new JButton("Rotate Right");
    rightRotate.addActionListener(new ButtonPress());

    horizontalFlip = new JButton("Horizontal Flip");
    horizontalFlip.addActionListener(new ButtonPress());

    verticalFlip = new JButton("Vertical Flip");
    verticalFlip.addActionListener(new ButtonPress());

    brighten = new JButton("Brighten");
    brighten.addActionListener(new ButtonPress());

    darken = new JButton("Darken");
    darken.addActionListener(new ButtonPress());

    greyScale = new JButton("Grey Scale");
    greyScale.addActionListener(new ButtonPress());

    deepFry = new JButton("Deep Fry");
    deepFry.addActionListener(new ButtonPress());

    topText = new JButton("Set Top Text");
    topText.addActionListener(new ButtonPress());

    bottomText = new JButton("Set Bottom Text");
    bottomText.addActionListener(new ButtonPress());

    reset = new JButton("Reset Image");
    reset.addActionListener(new ButtonPress());

    // Initializes buttonPad and adds its JButtons
    buttonPad = new JPanel(new GridLayout(6, 2));

    buttonPad.add(leftRotate);
    buttonPad.add(rightRotate);
    buttonPad.add(horizontalFlip);
    buttonPad.add(verticalFlip);
    buttonPad.add(brighten);
    buttonPad.add(darken);
    buttonPad.add(greyScale);
    buttonPad.add(deepFry);
    buttonPad.add(topText);
    buttonPad.add(bottomText);
    buttonPad.add(reset);

    // Initializing scaled Image Icon
    Image scaledImage = scaleImageIcon(icon);

    // Initializing image text modifying variables
    initImg = MemeMakerEditingUtils.imageToBufferedImg(scaledImage);
    changesNoText = initImg;

    currentTop = " ";
    currentBottom = " ";

    // Initializes the SplitPane and its components
    imageIcon = new ImageIcon(scaledImage);
    picLabel = new JLabel(imageIcon);
    picLabel.setPreferredSize(new Dimension(600, 600));

    imagePanel = new JPanel();
    imagePanel.add(picLabel);

    toolPanel = new JPanel();
    toolPanel.add(buttonPad);

    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, imagePanel, toolPanel);

    memeMakeFrame.add(splitPane);

    // Initializes the change history for each instance of the MemeEditor effectively reseting it
    // upon each open.
    changeHistory = new Stack<ImageIcon>();

    changeHistory.push(new ImageIcon(scaledImage));

    redoHistory = new Stack<ImageIcon>();

    // Listens for to the window to check if it's ever closed.
    memeMakeFrame.addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        DogDisplay.openAlready = false;
        RandomDogPane.openAlready = false;

        imageIcon.setImage(scaledImage);
        picLabel.setIcon(imageIcon);
      }
    });
  }

  /**
   * Converts the ImageIcon into a buffered image, scales it down then converts it to an image and
   * returns it.
   * 
   * @param icon
   *          The initial image icon
   * @return The scaled image
   */
  public Image scaleImageIcon(ImageIcon icon)
  {
    initImg = MemeMakerEditingUtils.imageToBufferedImg(icon.getImage());
    double iconHeight = icon.getIconHeight();
    double iconWidth = icon.getIconWidth();
    while (iconHeight > 600 || iconWidth > 450)
    {
      iconHeight *= .85;
      iconWidth *= .85;
    }
    Image scaledImage = initImg.getScaledInstance((int) iconWidth, (int) iconHeight,
        Image.SCALE_AREA_AVERAGING);
    return scaledImage;
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
    RandomDogPane.openAlready = false;
    memeMakeFrame.setVisible(false);
    memeMakeFrame.dispose();

    imageIcon.setImage(initImg);
    picLabel.setIcon(imageIcon);
  }

  /**
   * Clones a buffered Image so that they do not share the same reference
   * 
   * @param intlBufferImg
   * @return
   */
  public static BufferedImage cloneBuffer(BufferedImage intlBufferImg)
  {
    BufferedImage bufferCopy = new BufferedImage(intlBufferImg.getWidth(),
        intlBufferImg.getHeight(), intlBufferImg.getType());
    Graphics bufferedImageGraphics = bufferCopy.getGraphics();

    bufferedImageGraphics.drawImage(intlBufferImg, 0, 0, null);
    bufferedImageGraphics.dispose();

    return bufferCopy;
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
        BufferedImage iconAsBuffer = MemeMakerEditingUtils.imageToBufferedImg(imageIcon.getImage());
        String name = fileChooser.getSelectedFile().toString();
        // String name = fileChooser.getSelectedFile().toString() +"png";
        File file = new File(name);
        try
        {
          ImageIO.write(iconAsBuffer, "png", file);
        }
        catch (IOException e1)
        {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
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
      if (changeHistory.size() > 1)
      {
        redoHistory.push(changeHistory.pop());

        imageIcon.setImage(changeHistory.peek().getImage());
        picLabel.setIcon(changeHistory.peek());
        
        changesNoText = MemeMakerEditingUtils.imageToBufferedImg(changeHistory.peek().getImage());
      }
    }
    else if ("redo".equals(e.getActionCommand()))
    {
      if (redoHistory.size() > 0)
      {
        changeHistory.push(redoHistory.pop());

        imageIcon.setImage(changeHistory.peek().getImage());
        picLabel.setIcon(changeHistory.peek());
        
        changesNoText = MemeMakerEditingUtils.imageToBufferedImg(changeHistory.peek().getImage());
      }
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

          Image scaledImage = scaleImageIcon(
              new ImageIcon(selectedFile.getAbsolutePath().toString()));
          ImageIcon scaledIcon = new ImageIcon(scaledImage);

          changeHistory.push(scaledIcon);
          imageIcon.setImage(changeHistory.peek().getImage());
          picLabel.setIcon(changeHistory.peek());

          initImg = MemeMakerEditingUtils.imageToBufferedImg(changeHistory.peek().getImage());

          changesNoText = MemeMakerEditingUtils.imageToBufferedImg(changeHistory.peek().getImage());

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
        BufferedImage iconAsBuffer = MemeMakerEditingUtils.imageToBufferedImg(imageIcon.getImage());
        CustomImage iconAsCustom = MemeMakerEditingUtils.bufferedImageToImage(iconAsBuffer);
        iconAsBuffer = MemeMakerEditingUtils.leftRotate(iconAsCustom);

        CustomImage temp = MemeMakerEditingUtils.bufferedImageToImage(changesNoText);
        changesNoText = MemeMakerEditingUtils.leftRotate(temp);

        imageIcon.setImage(iconAsBuffer);
        picLabel.setIcon(new ImageIcon(iconAsBuffer));
        changeHistory.push(new ImageIcon(iconAsBuffer));
      }
      else if (choice.equals("Rotate Right"))
      {
        BufferedImage iconAsBuffer = MemeMakerEditingUtils.imageToBufferedImg(imageIcon.getImage());
        CustomImage iconAsCustom = MemeMakerEditingUtils.bufferedImageToImage(iconAsBuffer);
        iconAsBuffer = MemeMakerEditingUtils.rightRotate(iconAsCustom);

        CustomImage temp = MemeMakerEditingUtils.bufferedImageToImage(changesNoText);
        changesNoText = MemeMakerEditingUtils.rightRotate(temp);

        imageIcon.setImage(iconAsBuffer);
        picLabel.setIcon(new ImageIcon(iconAsBuffer));
        changeHistory.push(new ImageIcon(iconAsBuffer));
      }
      else if (choice.equals("Horizontal Flip"))
      {
        BufferedImage iconAsBuffer = MemeMakerEditingUtils.imageToBufferedImg(imageIcon.getImage());
        CustomImage iconAsCustom = MemeMakerEditingUtils.bufferedImageToImage(iconAsBuffer);
        iconAsBuffer = MemeMakerEditingUtils.horizontalFlip(iconAsCustom);

        CustomImage temp = MemeMakerEditingUtils.bufferedImageToImage(changesNoText);
        changesNoText = MemeMakerEditingUtils.horizontalFlip(temp);

        imageIcon.setImage(iconAsBuffer);
        picLabel.setIcon(new ImageIcon(iconAsBuffer));
        changeHistory.push(new ImageIcon(iconAsBuffer));
      }
      else if (choice.equals("Vertical Flip"))
      {
        BufferedImage iconAsBuffer = MemeMakerEditingUtils.imageToBufferedImg(imageIcon.getImage());
        CustomImage iconAsCustom = MemeMakerEditingUtils.bufferedImageToImage(iconAsBuffer);
        iconAsBuffer = MemeMakerEditingUtils.verticalFlip(iconAsCustom);

        CustomImage temp = MemeMakerEditingUtils.bufferedImageToImage(changesNoText);
        changesNoText = MemeMakerEditingUtils.verticalFlip(temp);

        imageIcon.setImage(iconAsBuffer);
        picLabel.setIcon(new ImageIcon(iconAsBuffer));
        changeHistory.push(new ImageIcon(iconAsBuffer));
      }
      else if (choice.equals("Brighten"))
      {
        BufferedImage iconAsBuffer = MemeMakerEditingUtils.imageToBufferedImg(imageIcon.getImage());
        CustomImage iconAsCustom = MemeMakerEditingUtils.bufferedImageToImage(iconAsBuffer);
        iconAsBuffer = MemeMakerEditingUtils.brighten(iconAsCustom);

        CustomImage temp = MemeMakerEditingUtils.bufferedImageToImage(changesNoText);
        changesNoText = MemeMakerEditingUtils.brighten(temp);

        imageIcon.setImage(iconAsBuffer);
        picLabel.setIcon(new ImageIcon(iconAsBuffer));
        changeHistory.push(new ImageIcon(iconAsBuffer));
      }
      else if (choice.equals("Darken"))
      {
        BufferedImage iconAsBuffer = MemeMakerEditingUtils.imageToBufferedImg(imageIcon.getImage());
        CustomImage iconAsCustom = MemeMakerEditingUtils.bufferedImageToImage(iconAsBuffer);
        iconAsBuffer = MemeMakerEditingUtils.darken(iconAsCustom);

        CustomImage temp = MemeMakerEditingUtils.bufferedImageToImage(changesNoText);
        changesNoText = MemeMakerEditingUtils.darken(temp);

        imageIcon.setImage(iconAsBuffer);
        picLabel.setIcon(new ImageIcon(iconAsBuffer));
        changeHistory.push(new ImageIcon(iconAsBuffer));
      }
      else if (choice.equals("Grey Scale"))
      {
        BufferedImage iconAsBuffer = MemeMakerEditingUtils.imageToBufferedImg(imageIcon.getImage());
        CustomImage iconAsCustom = MemeMakerEditingUtils.bufferedImageToImage(iconAsBuffer);
        iconAsBuffer = MemeMakerEditingUtils.greyScale(iconAsCustom);

        CustomImage temp = MemeMakerEditingUtils.bufferedImageToImage(changesNoText);
        changesNoText = MemeMakerEditingUtils.greyScale(temp);

        imageIcon.setImage(iconAsBuffer);
        picLabel.setIcon(new ImageIcon(iconAsBuffer));
        changeHistory.push(new ImageIcon(iconAsBuffer));
      }
      else if (choice.equals("Deep Fry"))
      {
        BufferedImage iconAsBuffer = MemeMakerEditingUtils.imageToBufferedImg(imageIcon.getImage());
        CustomImage iconAsCustom = MemeMakerEditingUtils.bufferedImageToImage(iconAsBuffer);
        iconAsBuffer = MemeMakerEditingUtils.dEePfRy(iconAsCustom);

        CustomImage temp = MemeMakerEditingUtils.bufferedImageToImage(changesNoText);
        changesNoText = MemeMakerEditingUtils.dEePfRy(temp);

        imageIcon.setImage(iconAsBuffer);

        picLabel.setIcon(new ImageIcon(iconAsBuffer));
        changeHistory.push(new ImageIcon(iconAsBuffer));
      }
      else if (choice.equals("Set Top Text"))
      {
        currentTop = (String) JOptionPane.showInputDialog(memeMakeFrame,
            "What would you like the top text to be?");

        // If a string was returned, say so.
        if ((currentTop != null) && (currentTop.length() > 0))
        {
          if (currentTop.length() > 40)
          {
            JOptionPane.showMessageDialog(memeMakeFrame,
                "Your top text is too long, please try again", "Too Many Characters",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          {
            BufferedImage iconAsBuffer = cloneBuffer(changesNoText);

            iconAsBuffer = MemeMakerEditingUtils.setTopText(iconAsBuffer, currentTop);
            iconAsBuffer = MemeMakerEditingUtils.setBottomText(iconAsBuffer, currentBottom);

            imageIcon.setImage(iconAsBuffer);
            picLabel.setIcon(new ImageIcon(iconAsBuffer));

            changeHistory.push(new ImageIcon(iconAsBuffer));
          }
        }
        else
        {
          JOptionPane.showMessageDialog(memeMakeFrame, "You need to enter some text",
              "No Text Was Entered", JOptionPane.WARNING_MESSAGE);
        }
      }
      else if (choice.equals("Set Bottom Text"))
      {

        currentBottom = (String) JOptionPane.showInputDialog(memeMakeFrame,
            "What would you like the bottom text to be?");

        // If a string was returned, say so.
        if ((currentBottom != null) && (currentBottom.length() > 0))
        {
          if (currentBottom.length() > 40)
          {
            JOptionPane.showMessageDialog(memeMakeFrame,
                "Your top text is too long, please try again", "Too Many Characters",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          {
            BufferedImage iconAsBuffer = cloneBuffer(changesNoText);

            iconAsBuffer = MemeMakerEditingUtils.setTopText(iconAsBuffer, currentTop);
            iconAsBuffer = MemeMakerEditingUtils.setBottomText(iconAsBuffer, currentBottom);

            imageIcon.setImage(iconAsBuffer);
            picLabel.setIcon(new ImageIcon(iconAsBuffer));

            changeHistory.push(new ImageIcon(iconAsBuffer));
          }
        }
        else
        {
          JOptionPane.showMessageDialog(memeMakeFrame, "You need to enter some text",
              "No Text Was Entered", JOptionPane.WARNING_MESSAGE);
        }
      }
      else if (choice.equals("Reset Image"))
      {
        currentTop = " ";
        currentBottom = " ";
        imageIcon.setImage(initImg);

        // imageIcon.setImage(scaleImageIcon(imageIcon));

        picLabel.setIcon(new ImageIcon(initImg));
        changeHistory.push(new ImageIcon(initImg));
      }
      else
      {
        JOptionPane.showMessageDialog(memeMakeFrame, "You need to enter some text",
            "No Text Was Entered", JOptionPane.WARNING_MESSAGE);

      }
    }
  }
}
