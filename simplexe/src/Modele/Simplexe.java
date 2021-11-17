package Modele;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Simplexe implements Serializable{
	List contraintes;
	FonctionEco fonctionEco;
	
	public Simplexe(List contraintes, FonctionEco fonctionEco) {
		this.contraintes=contraintes;
		this.fonctionEco=fonctionEco;
	}
	
	public Simplexe() {
		// TODO Auto-generated constructor stub
	}
	
	public Simplexe(Simplexe simp) {
		List cont = new LinkedList<>();
		for(int i=0;i<simp.contraintes.size();i++) {
			ContrainteExplicite ce = (ContrainteExplicite) simp.contraintes.get(i);
			cont.add(new ContrainteExplicite(ce));
		}
		contraintes=cont;
		
		fonctionEco=new FonctionEco(simp.getFonctionEco());
	}

	public void passageDico1() {
		for(int i = 0; i<contraintes.size();i++) {
			((ContrainteExplicite)contraintes.get(i)).passageDico1();
		}
	}
	
	public String toString() {
		String chaineFinale="";
		for(int i=0; i<contraintes.size(); i++) {
			chaineFinale += "<p>"+((ContrainteExplicite) contraintes.get(i)).toString()+"</p><br>";
		}
		chaineFinale+= "<p>"+fonctionEco.toString()+"</p><br><hr><br>";
		return chaineFinale;
	}
	
	public String toString2() {
		String chaineFinale="";
		for(int i=0; i<contraintes.size(); i++) {
			chaineFinale += ((ContrainteExplicite) contraintes.get(i)).toString() + "\n";
			
		}
		chaineFinale+=fonctionEco.toString();
		return chaineFinale;
	}
	
	public String echangeJudicieux() {
		String inconnueBase = fonctionEco.monomeCoeffMax();
		String inconnueHorsBase = "";
		
		double max=100000000000000.0;
		
		for(int i=0; i<contraintes.size(); i++) {
			
			if(((ContrainteExplicite)contraintes.get(i)).getMonomes().get(inconnueBase)!=null) {
				if(((ContrainteExplicite)contraintes.get(i)).majorant(inconnueBase)<max /*&& ((ContrainteExplicite)contraintes.get(i)*/){
				
				inconnueHorsBase = ((ContrainteExplicite)contraintes.get(i)).getNom();
				max=((ContrainteExplicite)contraintes.get(i)).majorant(inconnueBase);
				}
			}
			
		}
		if(inconnueHorsBase == "") {
			return "Vous avez atteint le bénéfice maximum";
		}
		return "Echange à effectuer :" + inconnueBase + " & " + inconnueHorsBase;
	}
	
	public void echanger(String inconnueHorsBase, String inconnueBase) {
		int mem=-1;
		ContrainteExplicite memCE=new ContrainteExplicite(new Fraction(1,1), "");
		for(int i = 0; i<contraintes.size();i++) {
			if(((ContrainteExplicite) contraintes.get(i)).getNom().equals(inconnueHorsBase)) {
				memCE = ((ContrainteExplicite) contraintes.get(i));
				memCE.rentrerBase(inconnueBase);
				fonctionEco.echanger(memCE, inconnueBase);
				mem=i;
				
			}
		}
		for(int i=0;i<contraintes.size();i++) {
			if(i != mem) {
				((ContrainteExplicite) contraintes.get(i)).echanger(memCE, inconnueBase);
			}
		}
	}

	public List getContraintes() {
		return contraintes;
	}

	public void setContraintes(List contraintes) {
		this.contraintes = contraintes;
	}

	public FonctionEco getFonctionEco() {
		return fonctionEco;
	}

	public void setFonctionEco(FonctionEco fonctionEco) {
		this.fonctionEco = fonctionEco;
	}
	
	
}
