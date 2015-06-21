package Chess;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame 
{
	public MainFrame() 
	{
		init(); 
	}
	
	public final void init()
	{
		setTitle("My chess");
		setSize(640, 480); 
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel(new BorderLayout()); 
		
		// Left Panel
		JPanel leftPanel = new JPanel(); 
		
		JButton buttonSearch = new JButton("Szukaj"); 
		
		leftPanel.add(buttonSearch);
		
		mainPanel.add(leftPanel);
		
		// Right Panel 
		
		this.add(mainPanel); 		
	}
}
