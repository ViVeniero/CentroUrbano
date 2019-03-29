package project.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import project.exception.CostruzionePercorsoStradaleException;
import project.object.Lotto;
import project.object.Settore;
import project.strutture.Ed_Privato;
import project.strutture.Ed_Pubblico;
import project.strutture.Strada;

public class ModificaFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private JRadioButton modStrada;
	private JRadioButton modLotto;
	private boolean flag = true; 
	private Settore settore;
	private JTextArea text;
	private JPanel disegno;
	
	public ModificaFrame(Settore sect) {
		this.settore = sect;
		add(radioSelection(), BorderLayout.NORTH);
		disegno = visualSectorSelectioned(sect);
		add(disegno);
		add(infoText(), BorderLayout.SOUTH);
	}
	
	private JPanel radioSelection() {
		JPanel panelRadio = new JPanel();
		panelRadio.setBorder(new EtchedBorder());
		panelRadio.add(radioButton());
		return panelRadio;
	}
	
	private JPanel radioButton(){
		JPanel panel = new JPanel();
		modLotto = new JRadioButton("Modifica Lotto");
		modStrada = new JRadioButton("Costruisci Strada");
		modStrada.addActionListener((q)->{
			text.setText("Seleziona il Primo Lotto");
		});
		ButtonGroup group = new ButtonGroup();
		group.add(modLotto);
		group.add(modStrada);
		modLotto.setSelected(true);
		panel.add(modStrada);
		panel.add(modLotto);
		return panel;
	}
	
	private JPanel visualSectorSelectioned(Settore sect) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.add(sectPanel(sect));
		return panel;
	}
	
	private JPanel infoText() {
		JPanel panel = new JPanel();
		text = new JTextArea(1, 30);
		JScrollPane scroll = new JScrollPane(text);
		JLabel label = new JLabel("Azione Eseguita: ");
		panel.add(label);
		panel.add(scroll);
		return panel;
	}
	
	private JPanel sectPanel(Settore sect) {
		JPanel sectPanel = new JPanel();
		sectPanel.setLayout(new GridLayout(Settore.ROWS, Settore.COLS));
		sectPanel.setBorder(new EtchedBorder());
		sectPanel.setBackground(Color.green);
		for(int i = 0; i < Settore.ROWS; i++)
			for(int j = 0; j < Settore.COLS; j++) {
				sectPanel.add(lottoPanel(sect.getLotto(i, j), i, j));
			}
		return sectPanel;
	}
	
	private JPanel lottoPanel(Lotto lot, int i, int j) {
		LottoPanel lottoPanel = new LottoPanel(lot, i, j);
		lottoPanel.setBorder(new EtchedBorder());
		if(lot.getEdificio() instanceof Ed_Pubblico) {
			lottoPanel.setBackground(Color.RED);
		}
		else if(lot.getEdificio() instanceof Ed_Privato) {
			lottoPanel.setBackground(Color.green);
		}
		else if(lot.getEdificio() instanceof Strada) {
			lottoPanel.setBackground(Color.ORANGE);
		}
		return lottoPanel;
	}
	
	public void rework() {
		disegno.removeAll();
		disegno = visualSectorSelectioned(settore);
		this.add(disegno);
		this.validate();
	}
	
	class LottoPanel extends JPanel{

		private static final long serialVersionUID = 1L;
		private int x;
		private int y;
		private Lotto lotto;
	
		public LottoPanel(Lotto lot, int x, int y) {
			this.lotto = lot;
			this.x = x;
			this.y = y;
			addMouseListener(new MousePath());
		}

		public Lotto getLotto() {
			return lotto;
		}

		public int getRow() {
			return this.x;
		}
		
		public int getCol() {
			return this.y;
		}
		
		public void setRow(int x) {
			this.x = x;
		}
		
		public void setCol(int y) {
			this.y = y;
		}
		
		public void setLotto(Lotto lotto) {
			this.lotto = lotto;
		}
		
		class MousePath implements MouseListener{
			public void mouseClicked(MouseEvent e) {
				if(modLotto.isSelected()) {
					x1 = getRow();
					y1 = getCol();
					ModificaLottoFrame modLottoFrame = new ModificaLottoFrame(settore, settore.getLotto(x1, y1));
					modLottoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					modLottoFrame.setAlwaysOnTop(true);
					modLottoFrame.setSize(1000, 400);
					modLottoFrame.setVisible(true);
					dispose();
				}
				else if(modStrada.isSelected() && flag == true) {
					x1 = getRow();
					y1 = getCol();
					flag = false;
					text.setText("Seleziona il Secondo Lotto");
				}
				else if(modStrada.isSelected() && flag == false) {
					x2 = getRow();
					y2 = getCol();
					try {
						flag = true;
						settore.costruzionePercorsoStradale(x1, y1, x2, y2);
						text.setText("Strada Costruita con Successo");
						rework();
					} catch (CostruzionePercorsoStradaleException f) {
						text.setText("Costruzione Percorso Stradale Non Effettuato");
					}
				}
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		}
	}
}
