package project.strutture;

import java.awt.Color;

public class Ed_Privato extends Edificio{
	
	private static final long serialVersionUID = 1L;
	private double prezzo;

	public Ed_Privato(){
		super();
		this.prezzo = 0.0;
	}
	
	/**
	 * Costruttore di un edificio privato.
	 * @param nome è il nome dell'edificio privato.
	 * @param coeffE è l'efficienza dell'edificio privato.
	 * @param coeffI è l'invecchiamento dell'edificio privato.
	 * @param value è il valore dell'edificio privato.
	 */
	public Ed_Privato(String nome, int coeffE, int coeffI, double value) {
		super(nome, coeffE, coeffI, value);
		this.prezzo = value;
	}
	
	/*METODI DI ACCESSO*/
	/**
	 * Cede il prezzo di vendita dell'edificio privato.
	 * @return
	 */
	public double getPrezzoDiVendita() {
		return this.prezzo;
	}
	
	/*METODI MODIFICATORI*/
	/**
	 * imposta il prezzo di vendita dell'edificio privato.
	 */
	public void setPrezzoDiVendita() {
		this.prezzo = getValore() + ((getValore() / 100) * getCoeffEfficienza()) ;
	}
	
	/*STRING-EQUALS-CLONE*/
	public String toString() {
		return super.toString() + "[Prezzo= " + prezzo + "]";
	}
	
	public boolean equals(Object otherObject) {
		if(!super.equals(otherObject)) 
			return false;
		Ed_Privato other = (Ed_Privato)otherObject;
		return other.prezzo == prezzo;
	}
	
	public Ed_Privato clone() {
		Ed_Privato cloned;
		cloned = (Ed_Privato)super.clone();
		return cloned;
	}
	
	public Color getColor() {
		return Color.GREEN;
	}
}
