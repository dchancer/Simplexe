package Vue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import Controleur.Controleur;

public class PanelIndications extends JPanel{

	JButton indice;
	GridBagConstraints contrainte = new GridBagConstraints();
	
	public PanelIndications() {
		this.setLayout(new GridBagLayout());
		contrainte.insets = new Insets(15,5,15,5);
		contrainte.gridx = 0;
		contrainte.gridy = 0;
		indice=new JButton("?");
		indice.setToolTipText("Quand vous ne savez pas quoi faire, on vous donne un indice");
		this.add(indice,contrainte);
		contrainte.gridx++;
		this.add(new LabelIndications("Obtenir une Indication"),contrainte);
	}
	
	public PanelIndications(String enonce) {
		this.setLayout(new GridBagLayout());
		removeAll();
		contrainte.insets = new Insets(15,5,15,5);
		contrainte.gridx = 0;
		contrainte.gridy = 0;
		indice=new JButton("?");
		indice.setToolTipText("Quand vous ne savez pas quoi faire, on vous donne un indice");
		this.add(indice,contrainte);
		contrainte.gridx++;
		this.add(new LabelIndications(enonce),contrainte);
	}
	
	public void enregistreEcouteur(Controleur parControleur) {
		indice.setActionCommand("indice");
		indice.addActionListener(parControleur);
	}

}
