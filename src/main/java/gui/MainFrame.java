package gui;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private MainPanel mainPan;
	
	public MainFrame() {
		mainPan = new MainPanel();
		
		setSize(870,600);
		setResizable(false);
		setTitle("gestion des dossiers");
		getContentPane().add(mainPan);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
