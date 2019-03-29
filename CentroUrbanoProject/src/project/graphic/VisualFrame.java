package project.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import project.exception.DimensioniEccessiException;
import project.object.C_Urbano;
import project.object.Settore;
import project.strutture.Edificio;

public class VisualFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private C_Urbano centroUrbano;
	private int dim = 60;
	private CUrbanoPanel cUrbanoPanel;
	private JScrollPane scroll;
	
	public VisualFrame(C_Urbano centro) {
		this.centroUrbano = centro;
		setJMenuBar(bar());
		panel = new JPanel();
		cUrbanoPanel = new CUrbanoPanel();
		panel.setPreferredSize(cUrbanoPanel.getPreferredSize());
		panel.add(cUrbanoPanel);
		scroll = new JScrollPane(panel);
		add(scroll);
		JLabel label = new JLabel("Strada= Arancione    \t                                                 Ed Pubblico= Rosso                                                 Ed Privato= Verde");
		add(label, BorderLayout.SOUTH);
		
	}
	
	private JMenuBar bar() {
		JMenuBar menu = new JMenuBar();
		menu.add(opzioni());
		return menu;
	}

	private JMenu opzioni() {
		JMenu opzioni = new JMenu("Zoom");
		opzioni.add(zoomIn());
		opzioni.add(zoomOut());
		return opzioni;
	}
	
	private JMenuItem zoomIn() {
		JMenuItem zoomIn = new JMenuItem("ZoomIn");
		zoomIn.addActionListener((y)->{
			if(this.dim >= 1500)
				throw new DimensioniEccessiException();
			else {
				this.dim += 10;
				zoom();
			}
		});
		return zoomIn;
	}
	
	private JMenuItem zoomOut() {
		JMenuItem zoomOut = new JMenuItem("ZoomOut");
		zoomOut.addActionListener((y)->{
			if(this.dim <= 0)
				throw new DimensioniEccessiException("Dimensioni Limite Superata");
			else {
				this.dim -= 10;
				zoom();
			}
		});
		return zoomOut;
	}
	
	private void zoom() {
		remove(scroll);
		panel.removeAll();
		cUrbanoPanel = new CUrbanoPanel();
		panel.setPreferredSize(cUrbanoPanel.getPreferredSize());
		panel.add(cUrbanoPanel);
		add(scroll);
		repaint();
		validate();
	}
	
	class CUrbanoPanel extends JPanel{
		
		private static final long serialVersionUID = 1L;

		public CUrbanoPanel() {
			Dimension x = new Dimension(dim * 12 + 1, dim * 6 + 1);
			setPreferredSize(x);
		}
		
		public void paintComponent(Graphics g) {
			
			Graphics2D g2 = (Graphics2D)g;
			for(int x = 0; x < C_Urbano.ROWS; x++)
				for(int y = 0; y < C_Urbano.COLS; y++)
					for(int w = 0; w < Settore.ROWS; w++)
						for(int z = 0; z < Settore.COLS; z++) {
							Rectangle c = new Rectangle((z*dim) + (y * dim * 4), (w*dim) + (x * dim * 3), dim, dim);
								disegno(centroUrbano.getSettore(x, y).getLotto(w, z).getEdificio(), g2, c);
						}
		}
		
		public void disegno(Edificio ed, Graphics2D g, Rectangle c) {
			if(ed != null) {
				g.setColor(ed.getColor());
				g.fill(c);
				g.draw(c);
				g.setColor(Color.black);
				g.draw(c);
			}
			else{
				g.draw(c);
				g.setColor(Color.WHITE);
				g.fill(c);
				g.setColor(Color.black);
				g.draw(c);
			}
		}
	}
		
}

