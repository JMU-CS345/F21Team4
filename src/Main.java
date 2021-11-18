import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

/**
 * Driver class for Dog Display.
 * 
 * @author Zack Tucker, Matt Wong, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 */
public class Main {
  
  public static void main(String[] args) throws IOException {
    // DogDisplay dd = new DogDisplay();
    // dd.createAndShowGUI();

    Window mainWindow = new Window();
    mainWindow.createAndShowGUI();
  }
  private JFrame frame;
//  /**
//   * Launch the application.
//   */
//  public static void main(String[] args)
//  {
//    EventQueue.invokeLater(new Runnable()
//    {
//      public void run()
//      {
//        try
//        {
//          DogDisplay window = new DogDisplay();
//          window.frame.setVisible(true);
//        }
//        catch (Exception e)
//        {
//          e.printStackTrace();
//        }
//      }
//    });
//  }
}


