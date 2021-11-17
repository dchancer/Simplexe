package Modele;

import java.io.Serializable;

public class Monome implements Serializable{
	Fraction coefficient;
	String inconnue;
	
	public Monome(Fraction coeff, String inconnue) {
		coefficient=coeff;
		this.inconnue=inconnue;
	}
	
	public Monome(Monome monome) {
		coefficient=new Fraction(monome.getCoefficient());
		inconnue=monome.getInconnue();
	}

	public String toString() {
		if(coefficient.toString().equals("1") && inconnue.equals(" ")==false) {
			return inconnue;
		}
		
		if(coefficient.toString().equals("-1") && inconnue.equals(" ")==false) {
			return "-"+inconnue;
		}
		
		else if(coefficient.toString().equals("0")) {
			return "";
		}
		else {
			return coefficient + inconnue;
		}
		
		
	}
	
	public void additionner(Monome m) {
		this.coefficient = this.coefficient.FAddition(m.getCoefficient());
		
	}

	public void multiplier(Fraction constante) { // ADD FRACTION
		this.coefficient.FMultiplication(constante);
	}
	
	public Fraction getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Fraction coefficient) {
		this.coefficient = coefficient;
	}

	public String getInconnue() {
		return inconnue;
	}

	public void setInconnue(String inconnue) {
		this.inconnue = inconnue;
	}
}
