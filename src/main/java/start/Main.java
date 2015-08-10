package start;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import model.Dossier;
import model.Dossiers;
import gui.MainFrame;

public class Main {

	public static void main(String[] args) throws IOException, JAXBException {
		//new MainFrame();
		
		Dossiers ds = Dossiers.getInstance("dossiers.xml");
		
		for(Dossier d : ds.getDossiers()){
			System.out.println(d.getNumDossier());
		}
	}
}
