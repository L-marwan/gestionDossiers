package start;

import gui.MainFrame;

import java.awt.EventQueue;
import java.io.IOException;

import javax.xml.bind.JAXBException;

public class Main {

	public static void main(String[] args) throws IOException, JAXBException {
		//new MainFrame();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});


	}
}

