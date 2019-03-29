package project.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import project.object.C_Urbano;
import project.object.Settore;

public class GestionFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private C_Urbano cUrbano;
	private int cooX = -1;
	private int cooY = -1;
	private JTextArea settoreSelezionato;
	private boolean flag = false;
	
	public GestionFrame(C_Urbano cUrbano) {
		this.cUrbano = cUrbano;
		add(infoSettSelected(), BorderLayout.NORTH);
		add(sectorPanel());
		add(controlPanel(), BorderLayout.SOUTH);
	}
	
	
	private JPanel infoSettSelected() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Settore Selezionato:");
		settoreSelezionato = new JTextArea(1, 2);
		panel.add(label);
		panel.add(settoreSelezionato);
		return panel; 
	}
	private JPanel sectorPanel() {
		JPanel generalPanel = new JPanel();
		generalPanel.setLayout(new GridLayout(2, 3));
		for(int i = 0; i < C_Urbano.ROWS; i++) 
			for(int j = 0; j < C_Urbano.COLS; j++) {
				SectorPanel sect = new SectorPanel(this.cUrbano.getSettore(i, j), i, j);
				generalPanel.add(sect);
		}
		return generalPanel;
	}
	
	private JPanel controlPanel() {
		JPanel controlPanel = new JPanel();
		controlPanel.add(invecchiamentoButton());
		controlPanel.add(catastrofeButton());
		controlPanel.add(restaurazione());
		controlPanel.add(modificaButton());
		return controlPanel;
	}
	
	private JButton invecchiamentoButton() {
		JButton invecchiamento = new JButton("Invecchiamento");
		invecchiamento.addActionListener((y)->{
			cUrbano.simulazioneTempo();
		});
		return invecchiamento;
	}
	
	private JButton catastrofeButton() {
		JButton catastrofe = new JButton("Catastrofe");
		catastrofe.addActionListener((y)->{
			cUrbano.catastrofe();
		});
		return catastrofe;
	}

	private JButton restaurazione() {
		JButton restaura = new JButton("Restaurazione Settore");
		restaura.addActionListener((z)->{
			if(flag == true)
				cUrbano.getSettore(cooX, cooY).restaurazioneSettore();
			else 
				System.out.println("Settore non Selezionato");
		});
		return restaura;
	}
	
	private JButton modificaButton() {
		JButton modifica = new JButton("Modifica");
		modifica.addActionListener((y)->{
			if(flag) {
				ModificaFrame modificaFrame = new ModificaFrame(cUrbano.getSettore(cooX, cooY));
				modificaFrame.setVisible(true);
				modificaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				modificaFrame.setSize(500, 500);
			}
		});
		return modifica;
	}
	
	class SectorPanel extends JPanel{

		private static final long serialVersionUID = 1L;
		private int x;
		private int y;
		private Settore settore;
		
		public SectorPanel(Settore sect, int x, int y) {
			int z;
			this.settore = sect;
			this.x = x;
			this.y = y;
			if(x != 0) {
				z = (x*Settore.COLS) + y;
			}
			else
				z = y+1;
			JLabel label = new JLabel("" + z);
			add(label);
			setBorder(new EtchedBorder());
			setBackground(Color.red);
			addMouseListener(new MousePath());
		}

		public Settore getLotto() {
			return this.settore;
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
		
		public void setLotto(Settore sect) {
			this.settore = sect;
		}
		
		class MousePath implements MouseListener{
			public void mouseClicked(MouseEvent e) {
					cooX = getRow();
					cooY = getCol();
					flag = true;
					int z;
					if(x != 0) {
						z = (x*Settore.COLS) + y;
					}
					else
						z = y+1;
					
					settoreSelezionato.setText(z+"");
			}
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.green);
			}
			public void mouseExited(MouseEvent e) {
				setBackground(Color.red);
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		}
	}
}
