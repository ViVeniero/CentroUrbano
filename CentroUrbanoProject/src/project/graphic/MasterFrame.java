package project.graphic;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import project.object.C_Urbano;
import project.object.Lotto;
import project.object.Settore;
import project.strutture.Ed_Privato;
import project.strutture.Ed_Pubblico;
import project.strutture.Strada;
import project.strutture.Edificio;

public class MasterFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JTextArea areaSettori;
	private JTextArea areaLotti;
	private JTextArea areaLottiLiberi;
	private JTextArea areaEdPubblici;
	private JTextArea areaEdPrivati;
	private JTextArea areaStrade;
	private C_Urbano centroUrbano;
	
	public MasterFrame(C_Urbano centro) {
		this.centroUrbano = centro;
		add(textAreaMasterFrame());
		add(buttonMasterFrame(), BorderLayout.SOUTH);
		aggiornaInformazioni();
	}
	
	private JPanel textAreaMasterFrame() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 0));
		JPanel panelSettori = new JPanel();
		JLabel labelSettori = new JLabel("n° Settori: ");
		areaSettori = new JTextArea(1, 5);
		areaSettori.setEditable(false);
		JPanel panelLotti = new JPanel();
		JLabel labelLotti = new JLabel("n° Lotti: ");
		areaLotti = new JTextArea(1, 5);
		areaLotti.setEditable(false);
		JPanel panelLottiLiberi = new JPanel();
		JLabel labelLottiLiberi = new JLabel("n° Lotti Liberi: ");
		areaLottiLiberi = new JTextArea(1, 5);
		areaLottiLiberi.setEditable(false);
		JPanel panelEdPubblici = new JPanel();
		JLabel labelEdPubblici = new JLabel("n° Edifici Pubblici: ");
		areaEdPubblici = new JTextArea(1, 5);
		areaEdPubblici.setEditable(false);
		JPanel panelEdPrivati = new JPanel();
		JLabel labelEdPrivati = new JLabel("n° Edifici Privati: ");
		areaEdPrivati = new JTextArea(1, 5);
		areaEdPrivati.setEditable(false);
		JPanel panelStrade = new JPanel();
		JLabel labelStrade = new JLabel("n° Strade: ");
		areaStrade = new JTextArea(1, 5);
		areaStrade.setEditable(false);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Informazioni Centro Urbano"));
		panelSettori.add(labelSettori);
		panelSettori.add(areaSettori);
		panelLotti.add(labelLotti);
		panelLotti.add(areaLotti);
		panelLottiLiberi.add(labelLottiLiberi);
		panelLottiLiberi.add(areaLottiLiberi);
		panelEdPrivati.add(labelEdPrivati);
		panelEdPrivati.add(areaEdPrivati);
		panelEdPubblici.add(labelEdPubblici);
		panelEdPubblici.add(areaEdPubblici);
		panelStrade.add(labelStrade);
		panelStrade.add(areaStrade);
		panel.add(panelSettori);
		panel.add(panelLotti);
		panel.add(panelLottiLiberi);
		panel.add(panelEdPrivati);
		panel.add(panelEdPubblici);
		panel.add(panelStrade);
		panel.add(aggiornaInfo());
		return panel;
	}
	
	private JButton aggiornaInfo() {
		JButton refresh = new JButton("Aggiorna Informazioni");
		refresh.addActionListener((y)->{
			aggiornaInformazioni();
		});
		return refresh;
	}
	
	
	private JPanel buttonMasterFrame() {
		JPanel panel = new JPanel();
		panel.add(gestion());
		panel.add(select());
		panel.add(visual());
		panel.add(save());
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Opzioni"));
		return panel;
	}
	
	private JButton gestion() {
		JButton gestion = new JButton("Gestione");
		gestion.addActionListener((y)->{
			JFrame gestionFrame = new GestionFrame(centroUrbano);
			gestionFrame.setVisible(true);
			gestionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			gestionFrame.setSize(600, 300);
		});
		return gestion;
	}
	
	private JButton select() {
		JButton select = new JButton("Selezione");
		select.addActionListener((y)->{
			ArrayList<Edificio> centro = new ArrayList<>();
			for(int i = 0; i < C_Urbano.ROWS; i++)
				for(int j = 0; j < C_Urbano.COLS; j++)
					for(int m = 0; m < Settore.ROWS; m++)
						for(int n = 0; n < Settore.COLS; n++)
							if(centroUrbano.getSettore(i, j).getLotto(m, n).getEdificio() != null)
								centro.add(centroUrbano.getSettore(i, j).getLotto(m, n).getEdificio().clone());
			JFrame gestionFrame = new SelectFrame(centro);
			gestionFrame.setVisible(true);
			gestionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			gestionFrame.setSize(450, 500);			
		});
		return select;
	}
	
	private JButton visual() {
		JButton visual = new JButton("Visualizzazione");
		visual.addActionListener((y)->{
			JFrame visualFrame = new VisualFrame(centroUrbano);
			visualFrame.setVisible(true);
			visualFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			visualFrame.setSize(1000, 600);
		});
		return visual;
	}
	
	private JButton save() {
		JButton save = new JButton("Salvataggio");
		save.addActionListener((y)->{
			try {
				File document = new File("CentroUrbano.dat");
				ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(document));
				outputStream.writeObject(centroUrbano);
				outputStream.close();
			}
			catch (IOException e) {
				System.out.println("Errore in Scrittura");
			}
		});
		return save;
	}
	
	private void aggiornaInformazioni() {
		int nSettori = 0, nLotti = 0, nLottiLiberi = 0, nEdPrivati = 0, nEdPubblici = 0, nStrade = 0;
		nSettori = this.centroUrbano.contaSettori();
		nLotti = this.centroUrbano.getSettore(0, 0).contaLotti() * nSettori;
		Settore sect = new Settore();
		Lotto lotto = new Lotto();
		Ed_Privato priv = new Ed_Privato();
		Ed_Pubblico pubb = new Ed_Pubblico();
		Strada street = new Strada();
		for(int i = 0; i < C_Urbano.ROWS; i++) 
			for(int j = 0; j < C_Urbano.COLS; j++) {
				sect = this.centroUrbano.getSettore(i, j);
				for(int a = 0; a < Settore.ROWS; a++)
					for(int b = 0; b < Settore.COLS; b++) {
						lotto = sect.getLotto(a, b);
						if(lotto.lottoPieno()) {
							if(lotto.getEdificio().getClass() == priv.getClass())
								nEdPrivati++;
							else if(lotto.getEdificio().getClass() == pubb.getClass())
								nEdPubblici++;
							else if(lotto.getEdificio().getClass() == street.getClass())
								nStrade++;
						}
					}
			}
		nLottiLiberi = nLotti - nEdPrivati - nEdPubblici - nStrade;
		areaSettori.setText(nSettori+"");
		areaLotti.setText(nLotti+"");
		areaLottiLiberi.setText(nLottiLiberi+"");
		areaEdPrivati.setText(nEdPrivati+"");
		areaEdPubblici.setText(nEdPubblici+"");
		areaStrade.setText(nStrade+"");
	}
}
