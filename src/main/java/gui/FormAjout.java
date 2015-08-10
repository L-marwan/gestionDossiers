package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormAjout extends JPanel {
	private JLabel numLab, nomLab, tfLab;
	private JTextField numTF, nomTF , tfTF ;
	private JButton btnAjouter, btnSupprimer, btnModifier;
	
	
	/**
	 * 
	 */
	public FormAjout() {
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
			}
		});
		btnAjouter.setLocation(10, 113);
		btnAjouter.setSize(90, 23);
		btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModifier.setLocation(226, 113);
		btnModifier.setSize(95, 22);
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
}
