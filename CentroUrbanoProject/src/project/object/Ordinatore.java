package project.object;

import java.util.Comparator;

import project.strutture.Edificio;

public class Ordinatore {
	
	public static class OrdinatoreCoeffInvecchiamento implements Comparator<Edificio>{

		public int compare(Edificio o1, Edificio o2) {
			if(o1.getCoeffInvecchiamento() > o2.getCoeffInvecchiamento())
				return 1;
			else
				return -1;
		}
	}

	public static class OrdinatoreCoeffEfficienza implements Comparator<Edificio>{
	
		public int compare(Edificio o1, Edificio o2) {
			if(o1.getCoeffEfficienza() > o2.getCoeffEfficienza())
				return 1;
			else
				return -1;
		}
	}

	public static class OrdinatoreValore implements Comparator<Edificio>{
			
		public int compare(Edificio o1, Edificio o2) {
			if(o1.getValore() > o2.getValore())
				return 1;
			else
				return -1;
		}
	}
}
