package model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Dossier {
	private int numDossier;
	private String nomDossier ="";
	private String tf = "";
	
	private boolean isPreli = false;
	private boolean isAvantProjet= false;
	private boolean isExe = false;
	
	private int mntInit;
	private int recu;
	private int rest;
	
	
	
	private String id;
	
	//later on add path or something 
	
	
	/**
	 * constructor  
	 * @param num
	 * @param nom
	 * @param tf
	 */
	public Dossier(int num, String nom, String tf) {
		 id = IDGenerator.nextUUID();
		 this.numDossier = num;
		 this.nomDossier = nom;
		 this.tf = tf;
	}
	
	public Dossier (String id){
		this.id = id;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	public boolean isPreli() {
		return isPreli;
	}

	public void setPreli(boolean isPreli) {
		this.isPreli = isPreli;
	}

	public boolean isAvantProjet() {
		return isAvantProjet;
	}

	public void setAvantProjet(boolean isAvantProjet) {
		this.isAvantProjet = isAvantProjet;
	}

	public boolean isExe() {
		return isExe;
	}

	public void setExe(boolean isExe) {
		this.isExe = isExe;
	}

	public int getMntInit() {
		return mntInit;
	}

	public void setMntInit(int mntInit) {
		this.mntInit = mntInit;
	}

	public int getRecu() {
		return recu;
	}

	public void setRecu(int recu) {
		this.recu = recu;
	}

	public int getRest() {
		return rest;
	}

	public void setRest(int rest) {
		this.rest = rest;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		Dossier d= (Dossier) obj;
		return id.equals(d.id) ;
	}

	

}
