package gui;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private MainPanel mainPan;
	
	public MainFrame() {
		mainPan = new MainPanel();
		
		setSize(800,600);
		setTitle("gestion des dossiers");
		getContentPane().add(mainPan);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
