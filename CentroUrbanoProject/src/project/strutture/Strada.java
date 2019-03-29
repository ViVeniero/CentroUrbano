package project.strutture;

import java.awt.Color;

public class Strada extends Edificio{

	private static final long serialVersionUID = 1L;
	private double valoreIncrementale;
	
	public Strada(){
		super();
		this.valoreIncrementale = 0.0;
	}
	
	/**
	 * Costruttore di una strada.
	 * @param coeffE è il coefficiente di efficienza della strada.
	 * @param coeffI è il coefficiente di invecchiamento della strada.
	 * @param value è il valore della strada.
	 */
	public Strada(int coeffE, int coeffI, double value) {
		super("Strada", coeffE, coeffI, value);
		this.valoreIncrementale += value * 0.1;
	}
	
	/*METODI DI ACCESSO*/
	/**
	 * raccoglie l'informazione del valore di incremento dei lotti adiacenti.
	 * @return
	 */
	public double getValoreIncrementale() {
		return this.valoreIncrementale;
	}
	
	/*METODI MODIFICATORI*/
	/**
	 * imposta il valore di incremento degli edifici adiacenti.
	 * @param valoreIncrementale
	 */
	public void setValoreIncrementale(double valoreIncrementale) {
		this.valoreIncrementale = valoreIncrementale;
	}
	
	/*STRING-EQUALS-CLONE*/
	public String toString() {
		return super.toString() + "[ValoreIncrementale= " + valoreIncrementale + "]";
	}
	
	public boolean equals(Object otherObject) {
		if(!super.equals(otherObject)) 
			return false;
		Strada other = (Strada)otherObject;
		return other.valoreIncrementale == valoreIncrementale;
	}
	
	public Strada clone() {
		Strada cloned;
		cloned = (Strada)super.clone();
		return cloned;
	}

	public Color getColor() {
		return Color.ORANGE;
	}
	
	
}
