package project.object;

import project.interfaces.Selectable;
import project.strutture.Edificio;

public class Selezionatore {

	public static class SelezionatoreValore implements Selectable<Edificio>{

		public boolean seleziona(Edificio other, int x) {
			if(other.getValore() > x)
				return true;
			else
				return false;
		}
	}
	
	public static class SelezionatoreEfficienza implements Selectable<Edificio>{

		public boolean seleziona(Edificio other, int x) {
			if(other.getCoeffEfficienza() > x)
				return true;
			else
				return false;
		}
	}
	
	public static class SelezionatoreDanneggiamento implements Selectable<Edificio>{

		public boolean seleziona(Edificio other, int x) {
			if(other.getDannoRicevuto() > x)
				return true;
			else
				return false;
		}
	}

}
