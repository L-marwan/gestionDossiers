package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Dossiers {
	
	private ArrayList<Dossier> dossiers;
	
	
	
	public Dossiers() {
		dossiers = new ArrayList<Dossier>();
	}
	
	public Dossiers (String path) throws IOException, JAXBException{
		File f = new File (path);
		if(!f.exists()){
			f.createNewFile();	
		}
		
		JAXBContext context = JAXBContext.newInstance(Dossiers.class);
		try {
			context = JAXBContext.newInstance(Dossiers.class);
			Unmarshaller um = context.createUnmarshaller();

			// Reading XML from the file and unmarshalling.
			Dossiers p = (Dossiers) um.unmarshal(f);
			dossiers = p.getDossiers();
		} catch (JAXBException e) {
			dossiers = new ArrayList<Dossier>();
		}
	}
	
	public void ajouter (Dossier d){
		dossiers.add(d);
	}
	
	public void supprimer (int numDossier ){
		dossiers.remove(new Dossier(numDossier));
	}
	
	public void save (String path) throws IOException, JAXBException{
		File f = new File (path);
		if(!f.exists()){
			f.createNewFile();	
		}
		JAXBContext context;
		context = JAXBContext.newInstance(Dossiers.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(this, f);
	}
	
	public boolean numDossierExiste(int numDossier){
		for (Dossier d : dossiers){
			if (d.getNumDossier()==numDossier){
				return true;
			}
		}
		return false;
	}
	
	
	public ArrayList<Dossier> getDossiers() {
		return dossiers;
	}
	
	public void setDossiers(ArrayList<Dossier> dossiers) {
		this.dossiers = dossiers;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}