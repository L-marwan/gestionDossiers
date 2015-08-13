package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBException;

import model.Dossier;
import model.Dossiers;

public class FormAjout extends JPanel {
	private JLabel numLab, nomLab, tfLab, mntInitLab,recuLab,restLab;
	private JTextField numTF, nomTF , tfTF,mntInitTF,recuTF,restTF;
	private JButton btnAjouter, btnSupprimer, btnModifier;
	
	private JCheckBox prelim,avantpro,exe;
	
	private DefaultTableModel model;
	private JTable table;
	

	
	private static final String FILE_PATH = "dossiers.xml";
	
	
	/**
	 * 
	 */
	public FormAjout(final JTable table ) {
		this.table = table;
		this.model = (DefaultTableModel)table.getModel();
		
		numLab = new JLabel("Num Dossier :");
		numLab.setBounds(40, 27, 80, 14);
		nomLab = new JLabel("Projet :");
		nomLab.setBounds(40, 52, 80, 14);
		tfLab = new JLabel ("TF :");
		tfLab.setBounds(40, 72, 80, 14);
		
		numTF = new JTextField();
		numTF.setBounds(130, 24, 99, 20);
		nomTF = new JTextField();
		nomTF.setBounds(130, 47, 99, 20);
		tfTF = new JTextField();
		tfTF.setBounds(130, 69, 99, 20);
		
		

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
		btnAjouter.setLocation(0, 253);
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
		btnModifier.setLocation(205, 253);
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
		btnSupprimer.setLocation(100, 253);
		btnSupprimer.setSize(95, 23);
		
		
		setLayout(null);
		JPanel  dossier= new JPanel();
		dossier.setSize(400, 100);
		dossier.setLocation(10, 11);
		TitledBorder tb = BorderFactory.createTitledBorder("Dossier Technique : ");
		dossier.setBorder(tb);
		dossier.setLayout(null);
		dossier.add(numLab);
		dossier.add(numTF);
		
		dossier.add(nomLab);
		dossier.add(nomTF);
		
		dossier.add(tfLab);
		dossier.add(tfTF);
		
		JPanel  PhaseEtude= new JPanel();
		PhaseEtude.setLocation(10, 122);
		PhaseEtude.setSize(400, 120);

		TitledBorder tb1 = BorderFactory.createTitledBorder("Phase étude : ");
		PhaseEtude.setBorder(tb1);
		prelim= new JCheckBox("Etude préliminaire");
		prelim.setBounds(40, 22, 165, 23);
		avantpro = new JCheckBox("Etude avant projet");
		avantpro.setBounds(40, 50, 165, 23);
		exe = new JCheckBox("Etude projet d'execution");
		exe.setBounds(40, 76, 165, 23);
		PhaseEtude.setLayout(null);
		PhaseEtude.add(prelim);
		PhaseEtude.add(avantpro);
		PhaseEtude.add(exe);
		
		JPanel finance = new JPanel();
		TitledBorder tb2 = BorderFactory.createTitledBorder("Dossier finanananacier");
		
		
		add(PhaseEtude);
		add(dossier);
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
