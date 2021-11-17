package Vue;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class API extends JFrame implements ActionListener{

	JButton simp = new JButton("Simplexe");
	JButton mat = new JButton("Matrice");
	
	public GridBagConstraints contrainte = new GridBagConstraints() ;
	
	public API() {
		super("Accueil de l'API");
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		
		contrainte.fill = GridBagConstraints.BOTH; contrainte.insets = new Insets(10,10,10,10);
		contrainte.ipady = contrainte.anchor = GridBagConstraints.CENTER;
		
		simp.addActionListener(this);
		simp.setActionCommand("simplexe");
		
		mat.addActionListener(this);
		mat.setActionCommand("matrice");
		
		contrainte.gridx = 0; contrainte.gridy = 0;
		contrainte.gridheight = 2; contrainte.gridwidth = 2;
		pan.add(new JLabel("Bienvenue dans l'API Simplexe-Matrice",JLabel.CENTER),
				contrainte);
		
		contrainte.gridx = 0; contrainte.gridy = 2;
		contrainte.gridheight = 1; contrainte.gridwidth = 1;
		pan.add(simp,contrainte);
		
		contrainte.gridx = 1; contrainte.gridy = 2;
		contrainte.gridheight = 1; contrainte.gridwidth = 1;
		pan.add(mat,contrainte);
		
		this.add(pan);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800,400);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new API();

	}

	public void actionPerformed(ActionEvent ev) {
		// TODO Auto-generated method stub
		
		if(ev.getActionCommand().equals("simplexe"))
			new FenetreMereSimplex();
		
	}

}