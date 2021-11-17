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
			String cl� = (String) i.next();
			this.ajouterMonome(new Monome((Monome)fonctionEco.monomes.get(cl�)));;
		}
	}
	public void ajouterMonome(Monome m) {
		monomes.put(m.getInconnue(), m); // transformer pour que �a s'adapte � la map
	}
	
	public String toString() {
		String chaineFinale = new String();
		chaineFinale +=  "z = ";
		Iterator i = monomes.keySet().iterator(); 
		if(i.hasNext()) {
			String cl� = (String) i.next();
			chaineFinale+=((Monome) monomes.get(cl�)).toString();
		}
		
		while(i.hasNext()) {
			String cl� = (String) i.next();
			if(((Monome)monomes.get(cl�)).getCoefficient().getNumerateur()>0) {
				chaineFinale+=" +";
			}
			chaineFinale += " " + ((Monome) monomes.get(cl�)).toString();
		}
		return chaineFinale;

	}
	
	public void echanger(ContrainteExplicite ce, String inconnue) {
		Monome aEchanger = ((Monome)monomes.get(inconnue));
		Fraction coeff = aEchanger.getCoefficient(); //FRACTION
		monomes.remove(inconnue);
		
		for (Iterator i = ce.getMonomes().keySet().iterator(); i.hasNext();) {
			String cl� = (String) i.next();
			Monome temp = new Monome(((Monome) ce.getMonomes().get(cl�)).getCoefficient().FMultiplication(coeff), ((Monome) ce.getMonomes().get(cl�)).getInconnue());
			if(monomes.get(cl�)!=null) {
				((Monome)monomes.get(cl�)).additionner(temp);
			}
			else {
				Monome ajout = new Monome(coeff.FMultiplication(((Monome)ce.getMonomes().get(cl�)).getCoefficient()), cl�);
				monomes.put(cl�, ajout);
			}
			
		}
		
	}
	
	public String monomeCoeffMax() {
		Fraction max = new Fraction(0);
		String res=new String();
		
		for (Iterator i = monomes.keySet().iterator(); i.hasNext();) {
			String cl� = (String) i.next();
			if(((Monome)monomes.get(cl�)).getCoefficient().FSup(max) && !((Monome)monomes.get(cl�)).getInconnue().equals(" ")) {
				max=new Fraction(((Monome)monomes.get(cl�)).getCoefficient());
				res=((Monome)monomes.get(cl�)).getInconnue();
			}
			
		}
		return res;
		
	}
	
	public Map getMonomes() {
		return monomes;
	}
}
