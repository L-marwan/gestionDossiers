package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

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
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBException;

import model.Dossier;
import model.Dossiers;

public class MainPanel  extends JPanel{

	private JTable listeDossiers;
	private JTextField chercherTF;
	private JLabel chercherLab;
	private JRadioButton numCB,nomCB,tfCB;
	private JPanel topPanel;
	private FormAjout ajout;
	private DefaultTableModel model;
	
	private static final String FILE_PATH = "dossiers.xml";

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


		model = new DefaultTableModel(){
			public boolean isCellEditable(int row, int column){  
				return false;  
			}
		};
		listeDossiers = new JTable(model);
		listeDossiers.setRowSelectionAllowed(true);
		JScrollPane scrollPane = new JScrollPane(listeDossiers);
		listeDossiers.setFillsViewportHeight(true); 
		listeDossiers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//populate the jtable
		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				loadData();
				return null;
			}
		}.execute();
		
		add(scrollPane,BorderLayout.WEST);
		add(topPanel,BorderLayout.NORTH);

		ajout = new FormAjout(listeDossiers);
		add(ajout);
	}



	private void loadData() {
		System.out.println("START loadData method");
		
		ArrayList<Dossier> rs;

		try {
			rs = Dossiers.getInstance(FILE_PATH).getDossiers();
			
			// Names of columns
			Vector<String> columnNames = new Vector<String>();
			columnNames.add("Num Dossier");
			columnNames.add("Nom Projet");
			columnNames.add("T.F");
			// Data of the table
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			
			for(Dossier d :rs) {
				Vector<Object> vector = new Vector<Object>();
				vector.add(d.getNumDossier());
				vector.add(d.getNomDossier());
				vector.add(d.getTf());
				data.add(vector);
			}

			model.setDataVector(data, columnNames);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("END loadData method");
	}
}
