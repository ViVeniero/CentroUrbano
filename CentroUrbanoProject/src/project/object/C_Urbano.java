package project.object;

import java.io.Serializable;
import java.util.Random;

public class C_Urbano implements Serializable{

	private static final long serialVersionUID = 1L;
	public final static int ROWS = 2;
	public final static int COLS = 3;
	private Settore[][] insiemeSettori;
	
	/*COSTRUTTORE*/
	/**
	 * E' il costrutto del centro urbano che effettua la creazione di un insieme di settori.
	 */
	public C_Urbano() {
		insiemeSettori = new Settore[ROWS][COLS];
		for(int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLS; j++) {
				Settore sect = new Settore();
				this.insiemeSettori[i][j] = sect;
			}
	}
	
	/*METODO MODIFICATORE*/
	/**
	 * inserisce un settore in una data posizione del centro urbano.
	 * @param x è la prima coordinata del settore.
	 * @param y è la seconda coordinata del settore.
	 * @param sett è il settore.
	 */
	public void setSettore(int x, int y, Settore sett) {
		this.insiemeSettori[x][y] = sett;
	}
	
	/**
	 * simula il passare del tempo.
	 */
	public void simulazioneTempo() {
		for(int i = 0; i < ROWS; i++) 
			for(int j = 0; j < COLS; j++) 
				insiemeSettori[i][j].invecchiamento();
	}
	
	/**
	 * simula una catastrofe.
	 */
	public void catastrofe() {
		int a, b, x, y, danno, tempx, tempy;
		Random rand = new Random();
		a = rand.nextInt(ROWS);
		b = rand.nextInt(COLS);
		x = rand.nextInt(3);
		y = rand.nextInt(4);
		danno = rand.nextInt(20) + 31;
		insiemeSettori[a][b].danneggiamentoLotto(x, y, danno);
		for(tempx = x-1; tempx <= x+1; tempx++)
			for(tempy = y-1; tempy <= y+1; tempy++) {
				if(tempx < 0 && tempy < 0 && a-1 >= 0 && b-1 >= 0)			/*NORD-OVEST*/
					insiemeSettori[a-1][b-1].danneggiamentoLotto(tempx, tempy, danno-15);
				else if(tempx < 0 && tempy == 4 && a-1 >= 0 && b+1 < COLS) 	/*NORD-EST*/
					insiemeSettori[a-1][b+1].danneggiamentoLotto(tempx, tempy, danno-15);
				else if(tempx == 3 && tempy < 0 && a+1 < ROWS && b-1 >= 0) 	/*SUD-OVEST*/
					insiemeSettori[a+1][b-1].danneggiamentoLotto(tempx, tempy, danno-15);
				else if(tempx == 3 && tempy == 4 && a+1 < ROWS && b+1 < COLS) 	/*SUD-EST*/
					insiemeSettori[a+1][b+1].danneggiamentoLotto(tempx, tempy, danno-15);
				else if(tempx < 0 && a-1 >= 0)								/*NORD*/
					insiemeSettori[a-1][b].danneggiamentoLotto(tempx, tempy, danno-15);
				else if(tempx == 3 && a+1 < ROWS) 								/*SUD*/
					insiemeSettori[a+1][b].danneggiamentoLotto(tempx, tempy, danno-15);
				else if(tempy < 0 && b-1 >= 0) 								/*OVEST*/
					insiemeSettori[a][b-1].danneggiamentoLotto(tempx, tempy, danno-15);
				else if(tempy == 4 && b+1 < COLS) 								/*EST*/
					insiemeSettori[a][b+1].danneggiamentoLotto(tempx, tempy, danno-15);
				else if(tempx >= 0 && tempx < 4 && tempy >= 0 && tempy < 3 && a >= 0 && a < ROWS && b >= 0 && b < COLS)		 
					insiemeSettori[a][b].danneggiamentoLotto(tempx, tempy, danno-15);
				else if(tempx == x && tempy == y);
			}
	}
	
	/*METODI DI ACCESSO*/
	/**
	 * prende un settore all'interno del centro urbano.
	 * @param x è la prima coordinata del settore.
	 * @param y è la seconda coordinata del settore.
	 * @return il settore aventi le coordinate descritte.
	 */
	public Settore getSettore(int x, int y) {
		return this.insiemeSettori[x][y];
	}
	
	public int contaSettori() {
		return ROWS * COLS;
	}
}