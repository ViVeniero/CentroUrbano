package project.strutture;

import java.awt.Color;
import java.io.Serializable;
import java.util.Random;

public abstract class Edificio implements Cloneable, Serializable{
	/**
	 * è l'astrazione di un edificio pubblico.
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private int coeffEfficienza;
	private int coeffInvecchiamento;
	private double valore;
	private int danno;
	public Edificio() {
		this("", 100, 10, 1000);
		this.danno = 0;
	}
	/**
	 * E' il costruttore dell'edificio.
	 * @param nome è il nome dell'edificio.
	 * @param coeffE è il coeff di efficienza dell'edificio.
	 * @param coeffI è il coeff di invecchiamento dell'edificio.
	 * @param value è il valore dell'edificio.
	 */
	public Edificio(String nome, int coeffE, int coeffI, double value) {
		this.nome = nome;
		this.coeffEfficienza = coeffE;
		this.coeffInvecchiamento = coeffI;
		this.valore = value;
		this.danno = 0;
	}
	
	/**
	 * imposta il nome dell'edificio
	 * @param nome è il nome.
	 */
	/*METODI MODIFICATORI*/
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * imposta il coefficiente di efficienza all'edificio
	 * @param coeffE è l'efficienza.
	 */
	public void setCoeffEfficienza(int coeffE) {
		this.coeffEfficienza = coeffE;
	}
	/**
	 * imposta il coefficiente di invecchiamento all'edificio
	 * @param coeffI è il coeff di invecchiamento.
	 */
	public void setCoeffInvecchiamento(int coeffI) {
		this.coeffInvecchiamento = coeffI;
	}
	
	/**
	 * imposta il valore all'edificio.
	 * @param value è il valore.
	 */
	public void setValore(int value) {
		this.valore = value;
	}
	
	/**
	 * Restaura l'edificio.
	 */
	public void restaurazioneEdificio() {
		Random rand = new Random();
		int x;
		x = rand.nextInt(50) + 31;
		if(this.coeffEfficienza + x < 100)
			this.coeffEfficienza += x;
		else
			this.coeffEfficienza = 100;
		x = rand.nextInt(5) + 3;
		if(this.coeffInvecchiamento + x < 10)
			this.coeffInvecchiamento += x;
		else
			this.coeffInvecchiamento = 10;
	}
	
	/**
	 * invecchia l'edificio.
	 */
	public void invecchiamento() {
		if(this.coeffInvecchiamento > 0)
			this.coeffInvecchiamento--;
		else
			setCoeffEfficienza(0);
	}
	
	/**
	 * danneggia l'edificio
	 * @param danno è il danno subito dall'edificio.
	 */
	public void danneggiamento(int danno) {
		this.danno += danno;
		System.out.println("danno =" + danno);
		if(this.coeffEfficienza - danno < 0)
			this.coeffEfficienza = 0;
		else
			this.coeffEfficienza -= danno;
	}
	
	/**
	 * aumenta il valore dell'edificio.
	 * @param valore è l'incremento del valore dell'edificio.
	 */
	public void incrementaValore(double valore) {
		this.valore += valore;
	}
	/*METODI DI ACCESSO*/
	/**
	 * restituisce l'informazione sul nome dell'edificio
	 * @return
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * restituisce l'informazione sul coeff di efficienza dell'edificio.
	 * @return
	 */
	public int getCoeffEfficienza() {
		return this.coeffEfficienza;
	}
	
	/**
	 * restituisce l'informazione sul coeff di invecchiamento dell'edificio.
	 * @return
	 */
	public int getCoeffInvecchiamento() {
		return this.coeffInvecchiamento;
	}
	
	/**
	 * restituisce il danno ricevuto dell'edificio.
	 * @return
	 */
	public int getDannoRicevuto() {
		return this.danno;
	}
	
	/**
	 * restituisce l'informazione sul valore dell'edificio.
	 * @return
	 */
	public double getValore() {
		return this.valore;
	}
	
	public abstract Color getColor();
	
	/*STRING-EQUALS-CLONE*/
	public String toString() {
		return getClass().getName() + "[Nome= " + nome + ", CoeffEfficienza= " + coeffEfficienza + ", CoeffInvecchiamento= " + coeffInvecchiamento + ", Valore= " + valore +", Danno= " + danno + "]";
	}
	
	public boolean equals(Object otherObject) {
		if(otherObject == null)
			return false;
		if(otherObject.getClass() != getClass())
			return false;
		Edificio other = (Edificio)otherObject;
		return other.nome.equals(nome) && other.coeffEfficienza == coeffEfficienza && other.coeffInvecchiamento == coeffInvecchiamento && other.valore == valore && other.danno == danno;
	}
	
	public Edificio clone() {
		Edificio cloned;
		try {
			cloned = (Edificio)super.clone();
		}
		catch (CloneNotSupportedException e) {
			cloned = null;
		}
		return cloned;
	}	
}