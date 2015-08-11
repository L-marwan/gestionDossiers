package start;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import model.Dossier;
import model.Dossiers;

public class Main {

	public static void main(String[] args) throws IOException, JAXBException {
		//new MainFrame();
		Dossiers d = Dossiers.getInstance("dossier.xml");
		
		Dossier dd = d.getDossiers().get(1);
		
		dd.setNomDossier("hhh");
		dd.setTf("111");
		
		d.modifier(dd);
		d.save("dossier.xml");
		
		
	}
}
