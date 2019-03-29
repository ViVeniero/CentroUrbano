package project.graphic;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JFrame;

import project.object.C_Urbano;

public class Starter {

	public static void main(String[] args) {
		C_Urbano urban = new C_Urbano();
		try {
			File document = new File("CentroUrbano.dat");
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(document));
			System.out.println("File Esistente");
			System.out.println("Leggo File...");
			urban = (C_Urbano)inputStream.readObject();
			System.out.println("CentroUrbano Caricato!");
			inputStream.close();
		}
		catch (Exception e) {
			System.out.println("File Inesistente");
		}
		System.out.println("Avvio GUI");
		JFrame frame = new MasterFrame(urban);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
	}

}
