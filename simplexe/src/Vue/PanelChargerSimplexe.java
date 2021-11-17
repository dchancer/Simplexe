package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Controleur.Controleur;

public class PanelChargerSimplexe extends JPanel{
	private JButton charger;
	private JComboBox fichiers;
	private String[] listeFichiers;
	
	public PanelChargerSimplexe() {
		
		//GridBagLayout
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints contrainteEvenement = new GridBagConstraints();
		contrainteEvenement.insets = new Insets(3,3,3,3);
		
		//Cr�ation d'une etiquette
	
		JLabel nomDuFichier=new JLabel("Nom du fichier");
		
		//Cr�ation d'un fichier qui va indiquer o� chercher les chronologies enregistr�es
		File repertoire = new File("simplexes");
		
		//Tableau instanci� avec les fichiers dans le File d�termin� avant
		listeFichiers = repertoire.list();
		
		//Instantiation d'une JComboBox � partir de ce tableau
		fichiers = new JComboBox(listeFichiers);
		
		//Bouton et ActionCommand
		charger=new JButton("Charger");	
		charger.setActionCommand("Charger");
		

		
		
		//Ajout des �l�ments dans le GridBagLayout
		contrainteEvenement.gridx=0;
		contrainteEvenement.gridy=0;
		this.add(nomDuFichier,contrainteEvenement);
		
		contrainteEvenement.gridx=1;
		contrainteEvenement.gridy=0;
		this.add(fichiers,contrainteEvenement);
		
		contrainteEvenement.gridx=0;
		contrainteEvenement.gridy=1;
		contrainteEvenement.insets = new Insets(20,3,3,3);
		this.add(charger,contrainteEvenement);
		
	}
	
	
	//getter
	/**
	 * <font color="red">Renvoie le nom du fichier choisi dans la JComboBox</font>
	 * @return String
	 */
	public String getNomFichier(){
		int i = fichiers.getSelectedIndex();
		return (String) fichiers.getItemAt(i);
		
	}
	
	
	
	//ActionListener sur le bouton ajouter
	/**
	 * Met le controleur en param�tre � l'�coute du bouton Charger 
	 * @param parControleur
	 */
	public void enregistreEcouteur(Controleur parControleur){
		charger.addActionListener(parControleur);
	}
}
