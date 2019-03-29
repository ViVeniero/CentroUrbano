package project.graphic;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import project.interfaces.Selectable;
import project.object.Ordinatore;
import project.object.Selezionatore;
import project.strutture.Edificio;

public class SelectFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Edificio> collezioneLotti = new ArrayList<>();
	private ArrayList<Edificio> risultato = new ArrayList<>();
	private JTextField text;
	private JRadioButton sel1;
	private JRadioButton sel2;
	private JRadioButton sel3;
	private JRadioButton sort1;
	private JRadioButton sort2;
	private JRadioButton sort3;
	private JTextArea areaText;
	private ArrayList<Selectable<Edificio>> mM;
	private ArrayList<Comparator<Edificio>> oO;
	
	public SelectFrame(ArrayList<Edificio> cUrbano) {
		JPanel panelGeneral = new JPanel();
		panelGeneral.setLayout(new GridLayout(3, 1));
		panelGeneral.add(selectorPanel());
		panelGeneral.add(sorterPanel());
		panelGeneral.add(areaResult());
		add(panelGeneral);
		add(stampaRicerca(), BorderLayout.SOUTH);
		collezioneLotti = cUrbano;
		creaArrayComparator();
		creaArraySelectable();
	}

	public JPanel selectorPanel() {
		JPanel selectPanel = new JPanel();
		text = new JTextField(10);
		selectPanel.setLayout(new GridLayout(5, 1));
		selectPanel.setBorder(new TitledBorder(new EtchedBorder(), "Metodo di Selezione"));
		sel1 = new JRadioButton("Valore Superiore a");
		sel2 = new JRadioButton("Efficienza Superiore a");
		sel3 = new JRadioButton("Danneggiamento Ricevuto Superiore a");
		sel1.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		group.add(sel1);
		group.add(sel2);
		group.add(sel3);
		selectPanel.add(sel1);
		selectPanel.add(sel2);
		selectPanel.add(sel3);
		selectPanel.add(text);
		selectPanel.add(selezionaButton());
		return selectPanel;
	}
	
	public JPanel sorterPanel() {
		JPanel sortPanel = new JPanel();
		sortPanel.setBorder(new TitledBorder(new EtchedBorder(), "Metodo di Ordinamento"));
		sortPanel.setLayout(new GridLayout(4, 1));
		sort1 = new JRadioButton("Coeff Efficienza");
		sort2 = new JRadioButton("Coeff Invecchiamento");
		sort3 = new JRadioButton("Valore");
		ButtonGroup group = new ButtonGroup();
		group.add(sort1);
		group.add(sort2);
		group.add(sort3);
		sortPanel.add(sort1);
		sortPanel.add(sort2);
		sortPanel.add(sort3);
		sort1.setSelected(true);
		sortPanel.add(sortButton());
		return sortPanel;
	}
	
	public JPanel areaResult() {
		JPanel textPanel = new JPanel();
		textPanel.setBorder(new TitledBorder(new EtchedBorder(), "Risultato Ricerca"));
		areaText = new JTextArea(6, 35);
		JScrollPane scroll = new JScrollPane(areaText);
		textPanel.add(scroll);
		return textPanel;
	}
	
	public JButton stampaRicerca() {
		JButton stampaButton = new JButton("Stampa Ricerca");
		stampaButton.addActionListener((y)->{
			areaText.setText("");
			for(int i = 0; i < risultato.size(); i++) {
				areaText.append(risultato.get(i).toString() + "\n");
			}
		});
		return stampaButton;
	}
	
	private JButton selezionaButton() {
		JButton sel = new JButton("Seleziona");
		sel.addActionListener((z)->{
			risultato.clear();
			int intro = 0;
			if(sel1.isSelected()) {
				intro = 0;
			}
			if(sel2.isSelected()) {
				intro = 1;
			}
			if(sel3.isSelected()) {
				intro = 2;
			}
			for(int i = 0; i < collezioneLotti.size(); i++) {
				if(mM.get(intro).seleziona(collezioneLotti.get(i), Integer.parseInt(text.getText())))
					risultato.add(collezioneLotti.get(i));
			}
		});
		return sel;
	}
	
	private JButton sortButton() {
		JButton sort = new JButton("Ordina");
		sort.addActionListener((z)->{
			int index = 0; 
			if(sort1.isSelected()) {
				index = 0;
			}	
			if(sort2.isSelected()) {
				index = 1;
			}
			if(sort3.isSelected()) {
				index = 2;
			}
			Collections.sort(risultato, oO.get(index));;
		});
		return sort;
	}
	
	public void creaArrayComparator() {
		oO = new ArrayList<>();
		oO.add(new Ordinatore.OrdinatoreCoeffEfficienza());
		oO.add(new Ordinatore.OrdinatoreCoeffInvecchiamento());
		oO.add(new Ordinatore.OrdinatoreValore());
	}
	
	public void creaArraySelectable() {
		mM = new ArrayList<>();
		mM.add(new Selezionatore.SelezionatoreValore());
		mM.add(new Selezionatore.SelezionatoreEfficienza());
		mM.add(new Selezionatore.SelezionatoreDanneggiamento());
	}
}