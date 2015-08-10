package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class MainPanel  extends JPanel{
	
	private JTable listeDossiers;
	private JTextField chercherTF;
	private JLabel chercherLab;
	private JRadioButton numCB,nomCB,tfCB;
	private JPanel topPanel;
	private FormAjout ajout;
	private DefaultTableModel model;
	
	public MainPanel() {
		setLayout(new BorderLayout());

		topPanel = new  JPanel();
		chercherLab = new JLabel("chercher:");
		chercherTF = new JTextField(20);
		nomCB = new JRadioButton("nom");
		numCB = new JRadioButton("num");
		tfCB = new JRadioButton("TF");
		
		ButtonGroup btg = new ButtonGroup();
		btg.add(nomCB);
		btg.add(numCB);
		btg.add(tfCB);
		topPanel.setLayout(new FlowLayout());
		topPanel.add(chercherLab);
		topPanel.add(chercherTF);
		topPanel.add(nomCB);
		topPanel.add(numCB);
		topPanel.add(tfCB);
		
		String[] col = {"Num","Nom","TF",".."};
		String [][] data = new String[0][4];

		model = new DefaultTableModel(data,col){
			public boolean isCellEditable(int row, int column){  
		          return false;  
		      }
		};
		listeDossiers = new JTable(model);
		listeDossiers.setRowSelectionAllowed(true);
		JScrollPane scrollPane = new JScrollPane(listeDossiers);
		listeDossiers.setFillsViewportHeight(true); 
		listeDossiers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(scrollPane,BorderLayout.WEST);
		add(topPanel,BorderLayout.NORTH);
		
		ajout = new FormAjout();
		add(ajout);
	}

}
