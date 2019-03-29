package project.strutture;

import java.awt.Color;

public class Ed_Pubblico extends Edificio{
	
	private static final long serialVersionUID = 1L;
	private boolean inDemolizione;
	private int cicloPerDemolizione;
	
	/*COSTRUTTORI*/
	public Ed_Pubblico() {
		super();
		this.inDemolizione = false;
		this.cicloPerDemolizione = 0;	
	}
	
	/**
	 * Esegue la costruzione di un edificio pubblico
	 * @param nome è il nome dell'edificio pubblico
	 * @param coeffE è l'efficienza dell'edificio pubblico
	 * @param coeffI è il coefficiente di invecchiamento dell'edificio pubblico
	 * @param value è il valore dell'edificio pubblico
	 */
	public Ed_Pubblico(String nome, int coeffE, int coeffI, double value) {
		super(nome, coeffE, coeffI, value);
		this.cicloPerDemolizione = 0;
		this.inDemolizione = false;
	}
	
	/**
	 * Si informa sullo stato di demolizione dell'edificio pubblico.
	 * @return
	 */
	/*METODI DI ACCESSO*/
	public boolean getInDemolizione() {
		return this.inDemolizione;
	}
	/**
	 * si informa sul numero di giorni mancanti per la demolizione.
	 * @return
	 */
	public int getCicloPerDemolizione() {
		return this.cicloPerDemolizione;
	}
	
	/*METODI MODIFICATORI*/
	/**
	 * incrementa i giorni per la demolizione
	 */
	public void incrementaCicloPerDemolizione() {
		this.cicloPerDemolizione++;
	}
	
	/**
	 * setta lo stato dell'edificio in demolizione
	 */
	public void setInDemolizione() {
		this.inDemolizione = true;
	}
	
	/*STRING-EQUALS-CLONE*/
	public String toString() {
		return super.toString() + "[InDemolizione= " + inDemolizione + "]";
	}
	
	public boolean equals(Object otherObject) {
		if(!super.equals(otherObject)) 
			return false;
		Ed_Pubblico other = (Ed_Pubblico)otherObject;
		return other.inDemolizione == inDemolizione && other.cicloPerDemolizione == cicloPerDemolizione;
	}
	
	public Ed_Pubblico clone() {
		Ed_Pubblico cloned;
		cloned = (Ed_Pubblico)super.clone();
		return cloned;
	}
	
	public Color getColor() {
		return Color.RED;
	}
}