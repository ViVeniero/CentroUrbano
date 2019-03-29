package project.graphic;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import project.exception.LottoNonDisponibileException;
import project.exception.ZonaNonDemolibileException;
import project.object.Lotto;
import project.object.Settore;
import project.strutture.Ed_Privato;
import project.strutture.Ed_Pubblico;
import project.strutture.Strada;

public class ModificaLottoFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JRadioButton edPubblico;
	private JRadioButton edPrivato;
	private JRadioButton strada;
	private JTextArea text;
	private JButton buttDemolizione;
	private JTextField fieldNome;
	private JTextField fieldValore;
	private JTextArea textInfo;
	private Settore sett;
	private ModificaFrame modificaFrame;
	private int flag = -1;
	public ModificaLottoFrame(Settore sett, Lotto lot) {
		this.sett = sett;
		add(infoArea(lot), BorderLayout.NORTH);
		add(comandPanel(lot));
		add(textAzione(), BorderLayout.SOUTH);
		modFrame();
	}
	
	private JPanel infoArea(Lotto lot) {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Informazioni Lotto"));
		textInfo = new JTextArea(1, 40);
		textInfo.setEditable(false);
		textInfo.setText(lot.toString());
		panel.add(textInfo);
		return panel;
	}
	
	private JPanel comandPanel(Lotto lot) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,1));
		panel.add(comboPanel());
		panel.add(setAll());
		panel.add(buttonPanel(lot));
		return panel;
	}
	
	private JPanel setAll() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Info Dell'Edificio"));
		JLabel nome = new JLabel("Nome:");
		fieldNome = new JTextField(10);
		fieldNome.setEditable(false);
		JLabel valore = new JLabel("Valore:");
		fieldValore = new JTextField(10);
		fieldValore.setEditable(false);
		panel.add(nome);
		panel.add(fieldNome);
		panel.add(valore);
		panel.add(fieldValore);
		return panel;
	}
	
	private JPanel buttonPanel(Lotto lot) {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Azione"));
		panel.add(demolizione(lot));
		panel.add(costruzioneEdificio(lot));
		return panel;
	}
	
	private JButton demolizione(Lotto lot) {
		buttDemolizione = new JButton("Demolizione");
		buttDemolizione.addActionListener((y)->{
			try {
				lot.demolizioneEdificio();
				text.setText("Demolizione Avvenuta con Successo(Attendere Invecchiamento Ed Pubblico)");
			} catch (ZonaNonDemolibileException e) {
				text.setText("Demolizione non Effettuabile Lotto Vuoto");
			}
			this.modificaFrame.rework();
			});
		return buttDemolizione;
	}
	
	private JButton costruzioneEdificio(Lotto lot) {
		JButton buttCostruzione = new JButton("Costruisci");
		buttCostruzione.addActionListener((y)->{
			if(edPrivato.isSelected()) {
				this.flag = 0;
			}
			else if(edPubblico.isSelected()) {
				this.flag = 1;
			}
			else if(strada.isSelected()) {
				this.flag = 2;
			}
			try {
				costruzione(lot);
				text.setText("Costruzione Effettuata");
				textInfo.setText(lot.toString());
			}
			catch (LottoNonDisponibileException e) {
				text.setText("Lotto Occupato");
			}
			this.modificaFrame.rework();
		});
		
		return buttCostruzione;
	}
	
	private JPanel comboPanel() {
		JPanel panel = new JPanel();		
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Costuisci"));
		edPubblico = new JRadioButton("Ed Pubblico");
		edPrivato = new JRadioButton("Ed Privato");
		strada = new JRadioButton("Strada");
		ButtonGroup group = new ButtonGroup();
		group.add(edPubblico);
		group.add(edPrivato);
		group.add(strada);
		panel.add(edPubblico);
		panel.add(edPrivato);
		panel.add(strada);
		edPubblico.addActionListener((y)->{
			fieldNome.setEditable(true);
			fieldValore.setEditable(true);
		});
		edPrivato.addActionListener((y)->{
			fieldNome.setEditable(true);
			fieldValore.setEditable(true);
		});
		strada.addActionListener((y)->{
			fieldNome.setEditable(false);
			fieldNome.setText("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
			fieldValore.setEditable(true);
		});
		return panel;
	}
	
	private JPanel textAzione() {
		JPanel panel = new JPanel();
		text = new JTextArea(1, 40);
		JScrollPane scroll = new JScrollPane(text);
		JLabel label = new JLabel("Azione Eseguita: ");
		panel.add(label);
		panel.add(scroll);
		return panel;
	}
	
	private void modFrame() {
		this.modificaFrame = new ModificaFrame(sett);
		this.modificaFrame.setVisible(true);
		this.modificaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.modificaFrame.setSize(500, 500);
	}
	
	private void costruzione(Lotto lot) throws LottoNonDisponibileException{
		if(this.flag == 0) {
			sett.incrementaValoreEdificiPrivati(Double.parseDouble(fieldValore.getText())*0.10);
			lot.costruzioneEdificio(new Ed_Privato(fieldNome.getText(), 100, 10, Double.parseDouble(fieldValore.getText())));
		}
		if(this.flag == 1) {
			lot.costruzioneEdificio(new Ed_Pubblico(fieldNome.getText(), 100, 10, Double.parseDouble(fieldValore.getText())));
		}
		if(this.flag == 2) {
			lot.costruzioneEdificio(new Strada( 100, 10, Double.parseDouble(fieldValore.getText())));
		}
	}
}
