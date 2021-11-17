package Vue;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import Controleur.Controleur;
import Modele.Historique;

public class PanelGeneralSimplex extends JPanel{
	
	PanelSimplex panelSimp;
	PanelHistorique panelH;
	PanelIndications panelIndi;
	Historique historique;
	


	public PanelGeneralSimplex(Historique historique) {
		this.setLayout(new BorderLayout(20,5));
		this.historique=historique;
		
		
		panelH = new PanelHistorique(historique);
		panelIndi = new PanelIndications();
		
		if(historique.getListeSimplexe().size() == 0) {
			panelSimp = new PanelSimplex();
			
		}
		else {
			panelSimp = new PanelSimplex(historique.getListeSimplexe().get(historique.getListeSimplexe().size()-1));
		}
		

		this.add(panelSimp,BorderLayout.CENTER);

		this.add(panelH, BorderLayout.EAST);
		
		this.add(panelIndi,BorderLayout.SOUTH);

		
	}

	public PanelSimplex getPanelSimp() {
		return panelSimp;
	}
	
	public PanelIndications getPanelIndi() {
		return panelIndi;
	}

	public void setPanelIndi(PanelIndications panelIndi) {
		this.panelIndi = panelIndi;
	}
	public void setPanelIndi(String enonce) {
		this.remove(panelIndi);
		panelIndi=new PanelIndications(enonce);
		this.add(panelIndi, BorderLayout.SOUTH);
		
	}
	
	public void setPanelSimp(PanelSimplex panelSimp) {
		this.panelSimp = panelSimp;
	}


	public PanelHistorique getPanelH() {
		return panelH;
	}

	public void setPanelH(PanelHistorique panelH) {
		this.panelH = panelH;
	}

	public Historique getHistorique() {
		return historique;
	}

	public void setHistorique(Historique historique) {
		this.historique = historique;
	}

	public void enregistreEcouteur(Controleur controleur) {
		panelSimp.enregistreEcouteur(controleur);
		panelIndi.enregistreEcouteur(controleur);
	}
	
}

