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

import javax.swing.UIManager;

import java.awt.Color;

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
		numLab.setBounds(40, 20, 80, 14);
		nomLab = new JLabel("Projet :");
		nomLab.setBounds(40, 50, 80, 14);
		tfLab = new JLabel ("TF :");
		tfLab.setBounds(40, 80, 80, 14);
		
		numTF = new JTextField();
		numTF.setBounds(130, 20, 99, 20);
		nomTF = new JTextField();
		nomTF.setBounds(130, 50, 99, 20);
		tfTF = new JTextField();
		tfTF.setBounds(130, 80, 99, 20);
		
		btnAjouter = new JButton("Ajouter");
		
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouter();
			}
		});
		
		btnAjouter.setLocation(15, 460);
		btnAjouter.setSize(100, 25);
		
		btnModifier = new JButton("Modifier");
		
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifier();
			}
		});
		
		btnModifier.setLocation(150, 460);
		btnModifier.setSize(100, 25);
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
		btnSupprimer.setLocation(285, 460);
		btnSupprimer.setSize(100, 25);
		
		
		setLayout(null);
		JPanel  dossier= new JPanel();
		dossier.setSize(380, 127);
		dossier.setLocation(10, 10);
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
		PhaseEtude.setLocation(10, 160);
		PhaseEtude.setSize(380, 120);

		TitledBorder tb1 = BorderFactory.createTitledBorder("Phase étude : ");
		PhaseEtude.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Phase \u00E9tude : ", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		finance.setLocation(10, 300);
		finance.setSize(380, 127);
		TitledBorder tb2 = BorderFactory.createTitledBorder("Dossier financier");
		finance.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dossier financier : ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		mntInitLab = new JLabel("Mantant initiale:");
		mntInitLab.setBounds(40, 20, 100, 14);
		recuLab = new JLabel("Mantant reçu :");
		recuLab.setBounds(40, 50, 100, 14);
		restLab = new JLabel("Reste :");
		restLab.setLocation(40, 80);
		restLab.setSize(100, 14);
		mntInitTF = new JTextField();
		mntInitTF.setBounds(130, 20, 100, 20);
		recuTF = new JTextField();
		recuTF.setBounds(130, 50, 100, 20);
		restTF = new JTextField();
		restTF.setLocation(130, 80);
		restTF.setSize(100, 20);
		finance.setLayout(null);
		
		finance.add(mntInitLab);
		finance.add(mntInitTF);
		finance.add(recuLab);
		finance.add(recuTF);
		finance.add(restLab);
		finance.add(restTF);
		
		add(finance);
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
			columnNames.add("bool");
			// Data of the table
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			for(Dossier d :rs) {
				Vector<Object> vector = new Vector<Object>();
				vector.add(d.getNumDossier());
				vector.add(d.getNomDossier());
				vector.add(d.getTf());
				vector.add(new Boolean(true));
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
	
	public boolean isSet(JTextField tf){
		return !tf.getText().equals("");
	}
	/**
	 * 
	 */
	private void ajouter(){
		try {
			Dossiers d = Dossiers.getInstance(FILE_PATH);
			
			if(validEntries()){
				Dossier aAjouter= new Dossier(Integer.parseInt(numTF.getText()),nomTF.getText(),tfTF.getText());
				if(isSet(mntInitTF)){
					aAjouter.setMntInit(Integer.parseInt(mntInitTF.getText()));
				}
				if(isSet(recuTF)){
					aAjouter.setRecu(Integer.parseInt(recuTF.getText()));
				}
				if(prelim.isSelected()){
					aAjouter.setPreli(true);
				}
				if(avantpro.isSelected()){
					aAjouter.setAvantProjet(true);
				}
				if(exe.isSelected()){
					aAjouter.setExe(true);
				}
				
				d.ajouter(aAjouter);
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
	
	private void modifier(){
		try {
			Dossiers d = Dossiers.getInstance(FILE_PATH);
			int index = table.getSelectedRow();
			if(index>=0){
				if(validEntries()){
					Dossier aAjouter= new Dossier(Integer.parseInt(numTF.getText()),nomTF.getText(),tfTF.getText());
					if(isSet(mntInitTF)){
						aAjouter.setMntInit(Integer.parseInt(mntInitTF.getText()));
					}
					if(isSet(recuTF)){
						aAjouter.setRecu(Integer.parseInt(recuTF.getText()));
					}
					if(prelim.isSelected()){
						aAjouter.setPreli(true);
					}
					if(avantpro.isSelected()){
						aAjouter.setAvantProjet(true);
					}
					if(exe.isSelected()){
						aAjouter.setExe(true);
					}
				aAjouter.setId(d.getDossiers().get(index).getId());
				d.modifier(aAjouter);
				d.save(FILE_PATH);
				loadData();
			}
			}	
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
}
