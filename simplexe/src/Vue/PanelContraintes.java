package Vue;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controleur.Controleur;

public class PanelContraintes extends JPanel {
	
	
	JButton boutonCreer;
	Integer [] box = new Integer[10];
	JTextField[][]zonesEcrituresContraintes;
	JTextField[] zonesEcrituresFonctionEco;
	JTextField[] zonesEcrituresValeursMaxi;
	int nombreMonome;
	
	public PanelContraintes(Integer nombreMonome, Integer nombreContraintes) {
		this.nombreMonome=nombreMonome;
		int ligne = nombreContraintes;
		int colonne = nombreMonome;

		zonesEcrituresContraintes = new JTextField[ligne][colonne];
		zonesEcrituresFonctionEco=new JTextField[colonne];
		zonesEcrituresValeursMaxi=new JTextField[ligne];
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints contrainte=new GridBagConstraints();
		contrainte.fill = GridBagConstraints.BOTH; contrainte.insets = new Insets(10,20,10,20);
		
		for(int i=0; i<10; i++) {
			box[i]=i+1;
		}
		
		for(int i=0;i<ligne;i++) {
			contrainte.gridx = 0; contrainte.gridy = i;
			contrainte.gridheight = 1; contrainte.gridwidth = 1;
			
			this.add(new JLabel("x"+Integer.toString(colonne+1+i)+" = ",JLabel.CENTER),contrainte);
			for(int j=0;j<colonne;j++) {
				contrainte.gridx++; contrainte.gridy = i;
				zonesEcrituresContraintes[i][j]=new JTextField(2);
				this.add(zonesEcrituresContraintes[i][j],contrainte);
				contrainte.gridx++;
				if(j==colonne-1) {
					this.add(new JLabel("x"+Integer.toString(j+1)), contrainte);
				}
				else {
					this.add(new JLabel("x"+Integer.toString(j+1)+ "  +  "), contrainte);
				}
				
				
			}
			contrainte.gridx++;
			this.add(new JLabel("<="), contrainte);
			contrainte.gridx++;
			zonesEcrituresValeursMaxi[i]=new JTextField(5);
			this.add(zonesEcrituresValeursMaxi[i], contrainte);
		}
		
		contrainte.gridx =0;
		contrainte.gridy++;
		
		/*FONCTION ECO*/
		this.add(new JLabel("z = "), contrainte);
		contrainte.gridx++;
		for(int k=0;k<colonne;k++) {
			zonesEcrituresFonctionEco[k]=new JTextField(4);
			this.add(zonesEcrituresFonctionEco[k], contrainte);
			
			contrainte.gridx++;
			if(k==colonne-1) {
				this.add(new JLabel("x"+Integer.toString(k+1)), contrainte);
			}
			else {
				this.add(new JLabel("x"+Integer.toString(k+1)+ "  +  "), contrainte);
			}
			contrainte.gridx++;
		}
		
		contrainte.gridx =1;
		contrainte.gridy+=3;
		contrainte.anchor = GridBagConstraints.CENTER;
		boutonCreer = new JButton("Créer");
		this.add(boutonCreer, contrainte);

	}
	
	public void viderFormulaire() {
		
		for(int i=0; i<zonesEcrituresContraintes.length; i++) {
			for(int j=0; j<zonesEcrituresContraintes[0].length;j++){
				zonesEcrituresContraintes[i][j].setText("");
			}
		}
		
		for(int i=0;i<zonesEcrituresFonctionEco.length;i++) {
			zonesEcrituresFonctionEco[i].setText("");
		}
		for(int i=0;i<zonesEcrituresValeursMaxi.length;i++) {
			zonesEcrituresValeursMaxi[i].setText("");
		}
	}
	
	public int getNombreMonome() {
		return nombreMonome;
	}

	public void setNombreMonome(int nombreMonome) {
		this.nombreMonome = nombreMonome;
	}

	public void enregistreEcouteur(Controleur parControleur) {
		boutonCreer.setActionCommand("Cr");
		boutonCreer.addActionListener(parControleur);
	}
	
	public void desenregistreEcouteur(Controleur parControleur) {
		boutonCreer.removeActionListener(parControleur);
	}

	public JTextField[][] getZonesEcrituresContraintes() {
		return zonesEcrituresContraintes;
	}

	public JTextField[] getZonesEcrituresFonctionEco() {
		return zonesEcrituresFonctionEco;
	}

	public JTextField[] getZonesEcrituresValeursMaxi() {
		return zonesEcrituresValeursMaxi;
	}

}
