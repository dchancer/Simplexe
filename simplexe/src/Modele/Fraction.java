package Modele;

import java.io.Serializable;

public class Fraction implements Serializable{
	private int numerateur;
	private int denominateur;
	
	//FRACTION
	//une fraction composee d'une numerateur et d'un denominateur
	public Fraction(int parNum, int parDen) {
		if(parDen == 0) {
			throw new RuntimeException("Division par zero");
		}
		numerateur = parNum;
		denominateur = parDen;
		reduire();   //A voir si on veut que la fraction soit reduite directement ou non
	}
	//si la fraction est un nombre entier
	public Fraction(int parVal) {
		numerateur = parVal;
		denominateur = 1;
		reduire();
	}
	//copie d'une fraction
	public Fraction(Fraction parFac) {
		numerateur = parFac.getNumerateur();
		denominateur = parFac.getDenominateur();
		reduire();
	}
	
	//GETTER
	public int getNumerateur() {
		return numerateur;
	}
	
	public int getDenominateur() {
		return denominateur;
	}
	
	//SETTER (ne pas oublier de recreer la fraction apres pour qu'elle soit r�duite)
	public void setNumerateur(int parNum) {
		this.numerateur = parNum;
	}
	
	public void setDenominateur(int parDen) {
		this.denominateur = parDen;
	}
	
	//CALCUL PGCD
	//calcul le pgcd entre deux nombres ; utilise pour reduire une fraction
	public int CalculPGCD(int Num, int Den) {
		if(Num % Den == 0) {
			return Den;
		}
		return CalculPGCD(Den, Num % Den);
	}
	
	//REDUIRE reduit de facon definitive
	//reduit la fraction jusqu'a ce qu'elle soit irreductible
	void reduire() {
		if(denominateur < 0) { //pour mettre le - devant
			numerateur *= -1;
			denominateur *= -1;
		}
		int pgcd = Math.abs(CalculPGCD(numerateur,denominateur));
		this.setNumerateur(numerateur/pgcd);
		this.setDenominateur(denominateur/pgcd);
	}
	
	//REDUIREV2 reduit juste pour l'affichage
	//reduit la fraction jusqu'a ce qu'elle soit irreductible -- ne sert a rien si "reduire()" est activ� dans les constructeur "Fraction"
	public Fraction reduireV2(Fraction frac) {
		int pgcd = CalculPGCD(frac.getNumerateur(),frac.getDenominateur());
		frac.setNumerateur(frac.getNumerateur()/pgcd);
		frac.setDenominateur(frac.getDenominateur()/pgcd);
		return new Fraction(frac.getNumerateur(), frac.getDenominateur());
	}
	
	//ECRITURE NON FRACTIONNELLE (voir si utilise float ou double)
	//ecrit une fraction sous le format a virgule (float : 7 chiffres apres la virgule || double = 15 chiffres apres la virgule)
	public double FMath() {
		double nume = this.getNumerateur();
		double deno = this.getDenominateur();
		double resultat = nume / deno;
		return resultat;
	}
	
	public Fraction swap() {
		int denominateur = this.denominateur;
		int numerateur = this.numerateur;
		return new Fraction(denominateur,numerateur);
	}
	
	//COMPARE
	//compare deux fractions entre elles
	public boolean FCompare(Fraction f) {
		if((this.getNumerateur() == f.getNumerateur()) && (this.getDenominateur() == f.getDenominateur())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//donne l'ordre de deux fractions ( < ou > )  true si sup
	public boolean FSup(Fraction f) {
		if(this.FMath() > f.FMath()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//ADDITION
	//addition deux fractions entre elles
	public Fraction FAddition(Fraction frac) {
		int num = ( (numerateur * frac.getDenominateur()) + (frac.getNumerateur() * denominateur) );
		int den = denominateur * frac.getDenominateur();
		return new Fraction(num, den);
	}
	
	//SOUSTRACTION
	//soustrait deux fractions entre elles
	public Fraction FSoustraction(Fraction frac) {
		int num = ( (numerateur * frac.denominateur) - (frac.numerateur * denominateur) );
		int den = denominateur * frac.denominateur;
		return new Fraction(num, den);
	}
	
	//MULTIPLICATION
	//multiplie deux fractions entre elles
	public Fraction FMultiplication(Fraction frac) {
		int num = numerateur * frac.getNumerateur();
		int den = denominateur * frac.getDenominateur();
		return new Fraction(num, den);
	}
	
	//DIVISION
	//divise deux fractions entre elles
	public Fraction FDivision(Fraction frac) {
		int num = numerateur * frac.getDenominateur();
		int den = denominateur * frac.getNumerateur();
		return new Fraction(num, den);
	}
	
	//TOSTRING
	//ecrit une fraction
	public String toString() {
		if(denominateur == 1) {
			return Integer.toString(numerateur);
		}
		return numerateur + "/" + denominateur ;
	}
	
	//TEST_MAIN
	public static void main(String[] args) {
		
		//FRACTION
		System.out.println();
		System.out.println("Fractions utilis�es dans la suite du programme");
		Fraction f1 = new Fraction(30,1);
		System.out.println("f1 = 30/(1) = " + f1.toString());
		Fraction f2 = new Fraction(-2,26);
		System.out.println("f2 = (-2)/26 = " + f2.toString());
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println();
		
		//OPERATION
		System.out.println("PREMICE : f3 repr�sentera le resultat de l'op�ration entre f1 et f2");
		System.out.println();
		
		//ADDITION
		System.out.println("Addition de deux fractions :");
		System.out.println("f3 = f1 + f2");
		System.out.println("f3 = (" + f1.toString() + ") + (" + f2.toString() + ")");
		Fraction f3 = f1.FAddition(f2);
		System.out.println("f3 = " + f3.toString());
		System.out.println();
		
		//SOUSTRACTION
		System.out.println("Soustraction de deux fractions :");
		System.out.println("f3 = f1 - f2");
		System.out.println("f3 = (" + f1.toString() + ") - (" + f2.toString() + ")");
		f3 = f1.FSoustraction(f2);
		System.out.println("f3 = " + f3.toString());
		System.out.println();
		
		//MULTIPLICATION
		System.out.println("Multiplication de deux fractions :");
		System.out.println("f3 = f1 * f2");
		System.out.println("f3 = (" + f1.toString() + ") * (" + f2.toString() + ")");
		f3 = f1.FMultiplication(f2);
		System.out.println("f3 = " + f3.toString());
		System.out.println();
		
		//DIVISION
		System.out.println("Division de deux fractions :");
		System.out.println("f3 = f1 / f2");
		System.out.println("f3 = (" + f1.toString() + ") / (" + f2.toString() + ")");
		f3 = f1.FDivision(f2);
		System.out.println("f3 = " + f3.toString());
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println();
		
		//REDUCTION
		System.out.println("Test de r�duction d'une fraction :");
		Fraction f4 = new Fraction(30,10);
		System.out.println("f4 = 30/10");
		System.out.println("f4 = " + f4.toString());
		Fraction f4bis = new Fraction(2051280,915750);
		System.out.println("f4bis = 2051280 / 915750");
		System.out.println("f4bis = " + f4bis.toString());
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println();
		
		//ECRITURE NON FRACTIONNELLE (MATH)
		System.out.println("Test �criture d�cimale d'une fraction :");
		System.out.println("Ecriture d�cimale de f1 = "+ f1.toString() + " = " + f1.FMath());
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println();
		
		//COMPARE
		System.out.println("Test de comparaison de deux fractions ( 'true' si oui, 'false' sinon ) :");
		System.out.println("f1 et f2 sont-elles �gales ? r�sultat = " + f1.FCompare(f2));
		System.out.println("f1 et -30/13 sont-elles �gales ? r�sultat = " + f1.FCompare(new Fraction(-30,13)));
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println();
		
		//ORDRE
		System.out.println("Test de l'ordre de deux fractions ( > ou <= ):");
		Fraction f5 = new Fraction(10/2);
		Fraction f6 = new Fraction(10/3);
		System.out.println("f5 = 10/2 = " + f5.toString() + " et f6 = 10/3 = " + f6.toString());
		System.out.println("f5 > f6 ? ( 'true' si oui, 'false' sinon ) : r�sultat = " + f5.FSup(f6));
		System.out.println("f6 > f5 ? ( 'true' si oui, 'false' sinon ) : r�sultat = " + f6.FSup(f5));
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println();
		
		//AUTRE
		System.out.println("Test getter de fraction :");
		System.out.println("f1 = 30/(-13) = " + f1.toString());
		System.out.println("f2 = (-2)/26 = " + f2.toString());
		System.out.println("Num�rateur de f1 = "+ f1.getNumerateur() + " et d�nominateur de f1 = " + f1.getDenominateur());
		System.out.println("Num�rateur de f2 = "+ f2.getNumerateur() + " et d�nominateur de f2 = " + f2.getDenominateur());
		System.out.println();
		
		System.out.println("Test setter de fraction :");
		Fraction f7 = new Fraction(325,235);
		System.out.println("f7 = 325/235 = " + f7.toString());
		System.out.println("On remplace le num�rateur de f7 par 282");
		f7.setNumerateur(282);
		f7 = new Fraction(f7);
		System.out.println("Maintenant f7 = 282/47 = " + f7.toString());
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println();
		
		System.out.println("Test calcul PGCD d'une fraction :");
		Fraction ex = new Fraction(0);
		int pgcd = Math.abs(ex.CalculPGCD(125,475));
		System.out.println("PGCD de 125 et 475 = " + pgcd);
		System.out.println();
		System.out.println("-----------------------------");
	}

}