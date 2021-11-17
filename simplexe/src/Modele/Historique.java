package Modele;

import java.io.Serializable;
import java.util.LinkedList;

import Modele.Simplexe;

public class Historique implements Serializable{
	
	private LinkedList <Simplexe> listeSimplexe;
	
	public LinkedList<Simplexe> getListeSimplexe() {
		return listeSimplexe;
	}
	
	public void add(Simplexe simplexe) {
		listeSimplexe.add(simplexe);
	}
	
	public String toString() {
		String chaineFinale= new String();
		for(int i=0;i<listeSimplexe.size();i++) {
			chaineFinale+=listeSimplexe.get(i).toString2() +"\n";
		}
		return chaineFinale;
	}

	public void setListeSimplexe(LinkedList<Simplexe> listeSimplexe) {
		this.listeSimplexe = listeSimplexe;
	}
	
	public void etapePrecedente() {
		if(listeSimplexe.size()>1) {
			listeSimplexe.removeLast();
		}
		
	}

	public Historique(LinkedList<Simplexe> liste) {
		listeSimplexe = liste;
	}
	
	public Historique(Historique histo) {
		listeSimplexe=new LinkedList();
		
		for(int i=0;i<histo.listeSimplexe.size();i++) {
			listeSimplexe.add(new Simplexe(histo.listeSimplexe.get(i)));
		}
	}
	
	public Historique() {
		listeSimplexe = new LinkedList<Simplexe>();
	}
	
}

