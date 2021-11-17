package Vue;

import java.awt.CardLayout;

import javax.swing.JPanel;

import Controleur.Controleur;

public class PanelFichier extends JPanel{
	private PanelFormulaire panelFormulaire;
	private PanelChargerSimplexe panelCharger;
	private CardLayout gestionnaireDeCartes;

	public PanelFichier(){
		
		//Création des panel fils
		
		panelFormulaire =new PanelFormulaire();
		panelCharger=new PanelChargerSimplexe();
		
		//Layout
		
		gestionnaireDeCartes = new CardLayout(3,3);
		this.setLayout(gestionnaireDeCartes);
		
		//Ajout des Layout
		
		this.add(panelFormulaire, "Nouveau Simplexe");
		this.add(panelCharger, "Charger Simplexe");

	}
	

	public CardLayout getGestionnaireDeCartes() {
		return gestionnaireDeCartes;
	}
	
	//EnregistreEcouteur qui appelle les enregistreEcouteurs des panelFils
	/**
	 * permet de mettre le controleur en paramètre à l'écoute de tous les panels fils
	 * @param parControleur:Controleur
	 */
	public void enregistreEcouteur(Controleur parControleur){
		panelFormulaire.enregistreEcouteur(parControleur);
		panelCharger.enregistreEcouteur(parControleur);
		
	}


	public PanelFormulaire getPanelFormulaire() {
		return panelFormulaire;
	}


	public PanelChargerSimplexe getPanelCharger() {
		return panelCharger;
	}


	public void setPanelCharger(PanelChargerSimplexe panelCharger) {
		this.panelCharger = panelCharger;
	}


	public void setPanelFormulaire(PanelFormulaire panelFormulaire) {
		this.panelFormulaire = panelFormulaire;
	}
}
