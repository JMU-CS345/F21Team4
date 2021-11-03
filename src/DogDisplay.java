import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

/**
 * A class.
 * 
 * @author Zack Tucker, Matt Wong, Thomas Mandell, Alex Polivka, Jonathan Wist
 * @version Nov 1, 2021
 */

public class DogDisplay {

	private JFrame frame;
	private final int windowWidth = 1024;
	private final int windowHeight = 768;
	private JScrollPane scrollPane;

	public DogDisplay() {
	}

	public void createAndShowGUI() {

		// Create and set up the window.
		frame = new JFrame("DogDisplay");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Display the window.
		frame.setMinimumSize(new Dimension(windowWidth, windowHeight));
		frame.pack();
		frame.setVisible(true);
	}

	public void getDogNames() {

	}
}
