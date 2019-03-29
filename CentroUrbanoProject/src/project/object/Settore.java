package project.object;

import java.io.Serializable;
import project.exception.CostruzionePercorsoStradaleException;
import project.exception.LottoNonDisponibileException;
import project.exception.ZonaNonDemolibileException;
import project.strutture.Ed_Pubblico;
import project.strutture.Edificio;
import project.strutture.Strada;

public class Settore implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	public static final int ROWS = 3;
	public static final int COLS = 4;
	private Lotto[][] insiemeLotti;
	private double valoreEdPubbliciCostruiti;
	
	/**
	 * Costruttore di un settore. inizializza al suo interno un insieme di lotti vuoti.
	 */
	public Settore() {
		insiemeLotti = new Lotto[ROWS][COLS];
		for(int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLS; j++) {
				Lotto lot = new Lotto();
				this.insiemeLotti[i][j] = lot;
			}
		this.valoreEdPubbliciCostruiti = 0;
	}
	
	/*METODI MODIFICATORI*/
	/**
	 * restaura i lotti al suo interno.
	 */
	public void restaurazioneSettore() {
		for(int i = 0; i < ROWS; i++) 
			for(int j = 0; j < COLS; j++)
				insiemeLotti[i][j].restaurazioneLotto();
	}
	
	/**
	 * incrementa il valore degli edifici privati al suo interno.
	 * @param value è il valore di incremento.
	 */
	public void incrementaValoreEdificiPrivati(double value) {
		for(int i = 0; i < ROWS; i++)
			for(int j = 0; j < COLS; j++) {
				insiemeLotti[i][j].incrementoValoreEdificio(value);
			}
		setValoreEdPubbliciCostruiti(0);
				
	}
	
	/**
	 * costruisce un edificio all'interno di un lotto.
	 * @param x è la prima coordinata del settore
	 * @param y è la seconda coordinata del settore
	 * @param edif è l'edificio da costruitre
	 */
	public void costruzioneEdificioInLotto(int x, int y, Edificio edif){
		try {
			if(edif instanceof Ed_Pubblico) {
				this.valoreEdPubbliciCostruiti += (edif.getValore() / 100) * 15;
			}
			this.insiemeLotti[x][y].costruzioneEdificio(edif);
		}
		catch (LottoNonDisponibileException e) {
			System.out.println("Il lotto non è disponibile");
		}
	}
	
	/**
	 * inserisce un lotto al suo interno.
	 * @param x è la coordinata prima del settore.
	 * @param y è la coordinata seconda del settore.
	 * @param z è il lotto da inserire al suo interno
	 */
	public void setLotto(int x, int y, Lotto z) {
		this.insiemeLotti[x][y] = z;
	}
	
	/**
	 * è un valore totale degli edifici pubblici costruiti all'interno del settore.
	 * @param value è il valore del edificio pubblico costruito
	 */
	public void setValoreEdPubbliciCostruiti(double value) {
		this.valoreEdPubbliciCostruiti = value;
	}
	
	/**
	 * invecchia gli edifici all'interno di ogni lotto.
	 */
	public void invecchiamento() {
		for(int i = 0; i < ROWS; i++) 
			for(int j = 0; j < COLS; j++)
				if(insiemeLotti[i][j].getEdificio() != null) 
					insiemeLotti[i][j].invecchiamento();
	}
	
	/**
	 *danneggia l'edificio all'interno di un lotto.
	 * @param x è la coordinata prima di un lotto del settore.
	 * @param y è la coordinata seconda di un lotto del settore.
	 * @param danno è il danno che l'edificio del lotto subisce.
	 */
	public void danneggiamentoLotto(int x, int y, int danno) {
		 this.insiemeLotti[(x+ROWS)%ROWS][(y+COLS)%COLS].danneggiamentoEdificio(danno);
	}
	
	/**
	 * demolisce un edificio in un lotto.
	 * @param x è la coordinata prima di un lotto del settore.
	 * @param y è la coordinata seconda di un lotto del settore.
	 */
	public void demolizioneLotto(int x, int y) {
		try {	
			this.insiemeLotti[x][y].demolizioneEdificio();
		}
		catch (ZonaNonDemolibileException e) {
			System.out.println(e + "test");
		}
	}
	/*COSTRUZIONE PERCORSO STRADALE*/
	/**
	 * costruisce un percorso stradale da un lotto ad un altro
	 * @param xIniz è la prima coordinata del primo lotto del settore.
	 * @param yIniz è la seconda coordinata del secondo lotto del settore.
	 * @param xFine è la prima coordinata del secondo lotto del settore.
	 * @param yFine è la seconda coordinata del secondo lotto del settore.
	 * @throws CostruzionePercorsoStradaleException se non è possibile costuire il percorso
	 */
	public void costruzionePercorsoStradale(int xIniz, int yIniz, int xFine, int yFine) throws CostruzionePercorsoStradaleException{
		int temp;
		try {
			if(xIniz != xFine && yIniz != yFine)
				throw new CostruzionePercorsoStradaleException();
			if(xIniz == xFine && yIniz == yFine) {
				insiemeLotti[xIniz][yIniz].costruzioneEdificio(new Strada(100, 10, 1000));
				incrementaValoreLottiServiti(100, xIniz, yIniz);
			}
			else if(xIniz == xFine) {
					if(yIniz < yFine) {
						temp = yIniz;
						for(;temp <= yFine; temp++)
							if(insiemeLotti[xIniz][temp].isEmpty());
					    for(;yIniz <= yFine; yIniz++) {
							insiemeLotti[xIniz][yIniz].costruzioneEdificio(new Strada(100, 10, 1000));
							incrementaValoreLottiServiti(100, xIniz, yIniz);
					    }
					}
					else{
						temp = yFine;
						for(;temp <= yIniz; temp++)
							if(insiemeLotti[xIniz][temp].isEmpty());
						for(;yFine <= yIniz; yFine++) {
							insiemeLotti[xIniz][yFine].costruzioneEdificio(new Strada(100, 10, 1000));
							incrementaValoreLottiServiti(100, xIniz, yFine);
						}
					}
				}
			else if(yIniz == yFine) {
					if(xIniz < xFine) {
						temp = xIniz;
						for(;temp <= xFine; temp++)
							if(insiemeLotti[temp][yIniz].isEmpty());
						for(;xIniz <= xFine; xIniz++) {
							insiemeLotti[xIniz][yIniz].costruzioneEdificio(new Strada(100, 10, 1000));
							incrementaValoreLottiServiti(100, xIniz, yIniz);
						}
					}
					else{
						temp = xFine;
						for(;temp <= xIniz; temp++)
							if(insiemeLotti[temp][yIniz].isEmpty());
						for(;xFine <= xIniz; xFine++) {
							insiemeLotti[xFine][yIniz].costruzioneEdificio(new Strada(100, 10, 1000));
							incrementaValoreLottiServiti(100, xFine, yIniz);
						}
					}
			}
		}
		catch (LottoNonDisponibileException e) {
			System.out.println("Lotti Non Liberi Informazioni Sbagliate");
		}
	}
	
	/*PER STRADE*/
	/**
	 * incrementa il valore dei lotti serviti dalle strade
	 * @param value è il valore da incrementare
	 * @param x è la coordinata del lotto contenente la strada.
	 * @param y è la coordinata del lotto contenente la strada.
	 */
	public void incrementaValoreLottiServiti(double value, int x, int y) {
		int tempX = x-1, tempY = y-1;
		for(; tempX <= x+1; tempX = tempX+2)
			if(tempX >= 0 && tempX < ROWS)
				this.insiemeLotti[tempX][y].incrementoValoreEdificio(value);
		for(; tempY <= y+1; tempY = tempY+2)
			if(tempY >= 0 && tempY < COLS)
				this.insiemeLotti[x][tempY].incrementoValoreEdificio(value);
	}
	
	/*METODI DI ACCESSO*/
	/**
	 * Interroga il settore chiedento a quanto ammonta il valore degli edifici pubblici nel settore
	 * @return un double che corripondee al valore degli edifici pubblici costruiti nel settore.
	 */
	public double getValoreEdPubbliciCostruiti() {
		return this.valoreEdPubbliciCostruiti;
	}
	
	/**
	 * Prende un lotto all'interno di un settore.
	 * @param x è la coordinata del lotto da prendere.
	 * @param y è la seconda coordinata del lotto da prendere.
	 * @return un lotto.
	 */
	public Lotto getLotto(int x, int y) {
		return this.insiemeLotti[x][y];
	}
	
	/**
	 * conta il numero di lotti del settore.
	 * @return un intero che corrisponde al numero di lotti nel settore.
	 */
	public int contaLotti() {
		return ROWS*COLS;
	}
}
