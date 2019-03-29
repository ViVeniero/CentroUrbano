package project.object;

import java.io.Serializable;

import project.exception.LottoNonDisponibileException;
import project.exception.ZonaNonDemolibileException;
import project.strutture.Ed_Privato;
import project.strutture.Ed_Pubblico;
import project.strutture.Edificio;

public class Lotto implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Edificio edificio;
	
	public Lotto() {
		this.edificio = null;
	}
	
	public Lotto(Edificio ed) {
		this.edificio = ed;
	}

	/*METODI MODIFICATORE*/
	/**
	 * Inserisce all'interno del lotto un edificio.
	 * @param ed è l'edificio che viene inserito all'interno del lotto.
	 */
	public void insEdificio(Edificio ed) {
		this.edificio = ed;
	}
	
	/*DEMOLIZIONE*/
	/**
	 * Demolisce un edificio all'interno del lotto.
	 * @throws ZonaNonDemolibileException se edificio è null.
	 */
	public void demolizioneEdificio() throws ZonaNonDemolibileException {
		if(this.edificio != null) {
			Ed_Pubblico ex = new Ed_Pubblico();
			if(this.edificio.getClass().equals(ex.getClass())) {
				ex = (Ed_Pubblico)this.edificio;
				ex.setInDemolizione();
			}
			else
				this.edificio = null;
		}
		else throw new ZonaNonDemolibileException("Zona Non Demolibile"); /*CONTROLLARE SE PASSA SU IL TESTO*/
	}
	
	/*RESTAURAZIONE*/
	/**
	 * Restaura l'edificio del lotto.
	 */
	public void restaurazioneLotto(){
		if(this.edificio != null)
			this.edificio.restaurazioneEdificio();
	}
	
	/*COSTRUZIONE*/
	/**
	 * Costruisce un edificio nel lotto.
	 * @param edificio è l'edificio che viene edificato nel lotto.
	 * @throws LottoNonDisponibileException se il lotto è occupato 
	 */
	public void costruzioneEdificio(Edificio edificio) throws LottoNonDisponibileException{
		if(this.edificio == null)
			this.edificio = edificio;
		else
			throw new LottoNonDisponibileException();
	}
	
	/**
	 * Invecchia gli edifici nel lotto.
	 */
	public void invecchiamento() {
		if(this.edificio instanceof Ed_Pubblico) {
			if(((Ed_Pubblico)this.edificio).getInDemolizione())
				if(((Ed_Pubblico) this.edificio).getCicloPerDemolizione() == 1)
					this.edificio = null;
				else
					((Ed_Pubblico) this.edificio).incrementaCicloPerDemolizione();
		}
		this.edificio.invecchiamento();
	}
	
	/**
	 * Danneggia l'edificio nel lotto.
	 * @param danno è il danno subito dall'edificio.
	 */
	public void danneggiamentoEdificio(int danno) {
		if(this.edificio != null)
			this.edificio.danneggiamento(danno);
	}
	
	/**
	 * incrementa il valore dell'edificio presente nel lotto.
	 * @param value
	 */
	public void incrementoValoreEdificio(double value) {
		if(this.edificio != null && this.edificio instanceof Ed_Pubblico || this.edificio instanceof Ed_Privato)
			this.edificio.incrementaValore(value);
	}
	/*METODI DI ACCESSO*/
	/**
	 * Prende l'edificio nel lotto.
	 * @return l'edificio contenuto nel lotto.
	 */
	public Edificio getEdificio() {
		return this.edificio;
	}
	
	/**
	 * Controlla se il lotto è vuoto.
	 * @return un valore booleano.
	 * @throws LottoNonDisponibileException se l'edificio nel lotto è uguale al null.
	 */
	public boolean isEmpty() throws LottoNonDisponibileException{
		if(this.edificio != null)
			throw new LottoNonDisponibileException();
		return true;
	}
	
	/**
	 * constolla se il lotto è pieno.
	 * @return un valore booleano.
	 */
	public boolean lottoPieno() {
		if(this.edificio != null)
			return true;
		else 
			return false;
	}
	
	/*STRING-EQUALS-CLONE*/
	public String toString() {
		return getClass().getName() + "[Edificio " + edificio + "]";
	}
	
	public boolean equals(Object otherObject) {
		if(otherObject == null)
			return false;
		if(otherObject.getClass() != getClass())
			return false;
		Lotto other = (Lotto)otherObject;
		return other.edificio.equals(edificio);
	}
	
	public Lotto clone() {
		Lotto cloned;
		try {
			cloned = (Lotto)super.clone();
			if(edificio != null)
				cloned.edificio = edificio.clone();
		}
		catch (CloneNotSupportedException e) {
			cloned = null;
		}
		return cloned;
	}
}