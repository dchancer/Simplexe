package Modele;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ContrainteExplicite implements Serializable{
	String nom;
	Map monomes;
	Fraction inferieurA;
	int nombreInconnues;
	
	public ContrainteExplicite(Fraction limite, String nom){
		this.monomes = new HashMap();
		inferieurA=limite;
		nombreInconnues=0;
		this.nom=nom;
	}
	
	public ContrainteExplicite(ContrainteExplicite ce) {
		// TODO Auto-generated constructor stub
		this.nom = ce.nom;
		this.inferieurA = new Fraction(ce.inferieurA);
		this.nombreInconnues = ce.nombreInconnues;
		monomes = new HashMap();
		for (Iterator i = ce.monomes.keySet().iterator(); i.hasNext(); ) {
			String clé = (String) i.next();
			this.ajouterMonome(new Monome((Monome)ce.monomes.get(clé)));;
		}
	}

	public void ajouterMonome(Monome m) {
		monomes.put(m.getInconnue(), m);
	}
	
	public void passageDico1() {
		for (Iterator i = monomes.keySet().iterator(); i.hasNext(); ) {
			String clé = (String) i.next();
			((Monome)monomes.get(clé)).setCoefficient((((Monome)monomes.get(clé)).getCoefficient().FMultiplication(new Fraction(-1,1))));
		}
		Monome m = new Monome(inferieurA, " ");
		this.ajouterMonome(m);
	}
	
	/* toString pour le dictionnaire 0 (sans les "=", seulement les contraintes avec les "<=")*/
	
	public String toStringDico1() {
		String chaineFinale = new String();
		Iterator i = monomes.keySet().iterator(); 
		if(i.hasNext()) {
			String clé = (String) i.next();
			chaineFinale+=((Monome) monomes.get(clé)).toString();
		}
		
		while(i.hasNext()) {
			String clé = (String) i.next();
			if(((Monome)monomes.get(clé)).getCoefficient().getNumerateur()>0) {
				chaineFinale+=" + ";
			}
			chaineFinale += ((Monome) monomes.get(clé)).toString();
		}
		chaineFinale+= " <= "+ this.inferieurA;
		return chaineFinale;
	}
	
	/* toString pour les autres dictionnaires (avec les "=")*/
	
	public String toString() {
		String chaineFinale = new String();
		chaineFinale +=  this.nom + " = ";
		Iterator i = monomes.keySet().iterator(); 
		if(i.hasNext()) {
			String clé = (String) i.next();
			chaineFinale+=((Monome) monomes.get(clé)).toString();
		}
		
		while(i.hasNext()) {
			String clé = (String) i.next();
			if(((Monome)monomes.get(clé)).getCoefficient().getNumerateur()>0) {
				chaineFinale+=" +";
			}
			chaineFinale += " " + ((Monome) monomes.get(clé)).toString();
		}
		return chaineFinale;

	}
	
	/*Parcourt la HashMap <nom de l'inconnue, Monome> de this. Additionne chaque Monome à celui ayant la même clé dans la HashMap de la 
	 * Contrainte explicite en paramètre
	 */
	
	public void additionnerLigne(ContrainteExplicite ce) {
		Iterator i = monomes.keySet().iterator(); 
		while(i.hasNext()) {
			String clé = (String) i.next();
			((Monome) monomes.get(clé)).additionner(((Monome)ce.getMonomes().get(clé)));
		}
	}
	
	public void rentrerBase(String inconnue) {
		Iterator i = monomes.keySet().iterator();
		while(i.hasNext()) { // On parcours notre contrainte
			String clé = (String) i.next();
			if (clé.equals(inconnue)) { // on tombe sur le bon Xi
				Fraction coeff = ((Monome)monomes.get(clé)).getCoefficient(); // on récupère le coefficient du monome à switch
				//String tmp = this.nom;
				Monome switched = new Monome(new Fraction(-1,1),this.nom); // on met -1 car on le switch donc son coeff devient négatif. Il va ensuite être divisé par le coeff de m
				this.nom = ((Monome)monomes.get(clé)).getInconnue(); // On remplace le nom de la contrainte par l'inconnue de m
				monomes.remove(clé);
				monomes.put(switched.getInconnue(),switched);
				division(coeff); //ADD FRACTION
				break;
			}
		}
	}
	
	
	public void echanger(ContrainteExplicite ce, String inconnue) {
		Monome aEchanger = ((Monome)monomes.get(inconnue));
		Fraction coeff = aEchanger.getCoefficient(); //FRACTION
		monomes.remove(inconnue);
		
		for (Iterator i = ce.getMonomes().keySet().iterator(); i.hasNext();) {
			String clé = (String) i.next();
			Monome temp = new Monome(((Monome) ce.getMonomes().get(clé)).getCoefficient().FMultiplication(coeff), ((Monome) ce.getMonomes().get(clé)).getInconnue());
			if(monomes.get(clé)!=null) {
				((Monome)monomes.get(clé)).additionner(temp);
			}
			else {
				Monome ajout = new Monome(coeff.FMultiplication(((Monome)ce.getMonomes().get(clé)).getCoefficient()), clé);
				monomes.put(clé, ajout);
			}
			
		}
		
	}
	
	public double majorant(String inconnue) {
		double constante = ((Monome)monomes.get(" ")).getCoefficient().FMath();
		double coeffInconnue = ((Monome)monomes.get(inconnue)).getCoefficient().FMath();
		return Math.abs(constante/coeffInconnue);
		
		
	}
	
	public void division(Fraction coeff) { //ADD FRACTION
		Iterator i = monomes.keySet().iterator();
		coeff.setNumerateur(-coeff.getNumerateur());
		while(i.hasNext()) {
			String clé = (String) i.next();
			((Monome) monomes.get(clé)).setCoefficient(((Monome) monomes.get(clé)).getCoefficient().FDivision(coeff)); 
		}
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Map getMonomes() {
		return monomes;
	}

	public void setMonomes(Map monomes) {
		this.monomes = monomes;
	}

	public Fraction getInferieurA() {
		return inferieurA;
	}

	public void setInferieurA(Fraction inferieurA) {
		this.inferieurA = inferieurA;
	}

	public int getNombreInconnues() {
		return nombreInconnues;
	}

	public void setNombreInconnues(int nombreInconnues) {
		this.nombreInconnues = nombreInconnues;
	}
	
}
