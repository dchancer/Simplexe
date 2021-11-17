package Modele;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FonctionEco implements Serializable{
	Map monomes;
	
	public FonctionEco() {
		monomes = new HashMap(); //changer en map
	}
	public FonctionEco(FonctionEco fonctionEco) {
		monomes = new HashMap();
		for (Iterator i = fonctionEco.monomes.keySet().iterator(); i.hasNext(); ) {
			String clé = (String) i.next();
			this.ajouterMonome(new Monome((Monome)fonctionEco.monomes.get(clé)));;
		}
	}
	public void ajouterMonome(Monome m) {
		monomes.put(m.getInconnue(), m); // transformer pour que ça s'adapte à la map
	}
	
	public String toString() {
		String chaineFinale = new String();
		chaineFinale +=  "z = ";
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
	
	public String monomeCoeffMax() {
		Fraction max = new Fraction(0);
		String res=new String();
		
		for (Iterator i = monomes.keySet().iterator(); i.hasNext();) {
			String clé = (String) i.next();
			if(((Monome)monomes.get(clé)).getCoefficient().FSup(max) && !((Monome)monomes.get(clé)).getInconnue().equals(" ")) {
				max=new Fraction(((Monome)monomes.get(clé)).getCoefficient());
				res=((Monome)monomes.get(clé)).getInconnue();
			}
			
		}
		return res;
		
	}
	
	public Map getMonomes() {
		return monomes;
	}
}
