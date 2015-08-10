package model;

import java.util.ArrayList;

public class Dossier {
	private int numDossier;
	private String nomDossier;
	private String tf;
	
	//later on add path or something 
	
	
	/**
	 * constructor  
	 * @param num
	 * @param nom
	 * @param tf
	 */
	public Dossier(int num, String nom, String tf) {
		 this.numDossier = num;
		 this.nomDossier = nom;
		 this.tf = tf;
	}
	
	/**
	 *  default cunstructor 
	 */
	public Dossier (){
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	public int getNumDossier() {
		return numDossier;
	}
	public void setNumDossier(int numDossier) {
		this.numDossier = numDossier;
	}
	public String getNomDossier() {
		return nomDossier;
	}
	public void setNomDossier(String nomDossier) {
		this.nomDossier = nomDossier;
	}
	public String getTf() {
		return tf;
	}
	public void setTf(String tf) {
		this.tf = tf;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		Dossier d= (Dossier) obj;
		return numDossier==d.numDossier ;
	}
	
	/**
	 * charger la liste des dossiers 
	 * @return
	 */
	public static ArrayList<Dossier> charger(){
		
		ArrayList<Dossier> resultat = new ArrayList<Dossier>();
		
		return resultat;
	}
	

}
