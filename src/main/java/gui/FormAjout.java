package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBException;

import model.Dossier;
import model.Dossiers;

public class FormAjout extends JPanel {
	private JLabel numLab, nomLab, tfLab;
	private JTextField numTF, nomTF , tfTF ;
	private JButton btnAjouter, btnSupprimer, btnModifier;
	
	private DefaultTableModel model;
	private JTable table;
	

	
	private static final String FILE_PATH = "dossiers.xml";
	
	
	/**
	 * 
	 */
	public FormAjout(final JTable table ) {
		this.table = table;
		this.model = (DefaultTableModel)table.getModel();
		
		numLab = new JLabel("Num :");
		numLab.setBounds(33, 8, 43, 14);
		nomLab = new JLabel("Nom :");
		nomLab.setBounds(33, 39, 43, 14);
		tfLab = new JLabel ("TF :");
		tfLab.setBounds(33, 72, 43, 14);
		
		numTF = new JTextField();
		numTF.setBounds(96, 5, 90, 20);
		nomTF = new JTextField();
		nomTF.setBounds(96, 36, 90, 20);
		tfTF = new JTextField();
		tfTF.setBounds(96, 69, 90, 20);

		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Dossiers d = Dossiers.getInstance(FILE_PATH);
					
					if(validEntries()){
						d.ajouter(new Dossier(Integer.parseInt(numTF.getText()),nomTF.getText(),tfTF.getText()));
						d.save(FILE_PATH);
						
						new SwingWorker<Void, Void>() {
							@Override
							protected Void doInBackground() throws Exception {
								loadData();
								return null;
							}
						}.execute();
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		btnAjouter.setLocation(10, 113);
		btnAjouter.setSize(90, 23);
		btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Dossiers d = Dossiers.getInstance(FILE_PATH);
					int index = table.getSelectedRow();
					if(index>=0){
						System.out.println(d.getDossiers().get(index).getNumDossier());
						Dossier doss = new Dossier (Integer.parseInt(numTF.getText()),nomTF.getText(),tfTF.getText());
						doss.setId(d.getDossiers().get(index).getId());
						d.modifier(doss);
						d.save(FILE_PATH);
						loadData();
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		btnModifier.setLocation(226, 113);
		btnModifier.setSize(95, 22);
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Dossiers d = Dossiers.getInstance(FILE_PATH);
					int index = table.getSelectedRow();
					if(index>=0){
						d.supprimer(d.getDossiers().get(index).getId());
						d.save(FILE_PATH);
						loadData();
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSupprimer.setLocation(117, 113);
		btnSupprimer.setSize(95, 23);
		
		
		setLayout(null);
		
		add(numLab);
		add(numTF);
		
		add(nomLab);
		add(nomTF);
		
		add(tfLab);
		add(tfTF);	
		
		add(btnAjouter);
		add(btnSupprimer);
		add(btnModifier);
	}
	
	private boolean validEntries(){
		return !numTF.getText().equals("")&& !nomTF.getText().equals("") && !tfTF.getText().equals("") ;
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
