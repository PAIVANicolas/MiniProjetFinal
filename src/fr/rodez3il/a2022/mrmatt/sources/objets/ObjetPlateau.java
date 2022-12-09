package fr.rodez3il.a2022.mrmatt.sources.objets;

import fr.rodez3il.a2022.mrmatt.sources.Niveau;

public abstract class ObjetPlateau {
	/**
	 * Fabrique des objets
	 * @param chr le symbole à produire
	 * @return la classe ObjetPlateau correspondante
	 */
	public static ObjetPlateau depuisCaractere(char chr) {
		ObjetPlateau nouveau = null;
		switch(chr) {
		case '-':
			nouveau = new Herbe();
			break;
		case '+':
			nouveau = new Pomme();
			break;
		case '*':
			nouveau = new Rocher();
			break;
		case ' ':
			nouveau = new Vide();
			break;
		case '#':
			nouveau = new Mur();
			break;
		case 'H':
			nouveau = new Joueur();
			break;
		}
		return nouveau;
	}
	/**
	 * Cette  méthode  sera  redéfinie  dans  chaque  objet  à  implanter
	 * @return le  caractère  correspondant  à  l’objet.
	 * @author PAIVA Nicolas
	 */
	public abstract char afficher();

	/**
	 * @return si l’objet est vide
	 * @author PAIVA Nicolas
	 */
	public boolean estVide(){
		return false;
	}

	/**
	 * @return si l’objet est marchable
	 * @author PAIVA Nicolas
	 */
	public boolean estMarchable(){
		return false;
	}

	/**
	 * @return si l’objet est poussable
	 * c’est à dire que le joueur peut le pousser horizontalement en se déplaçant dans sa direction
	 * @author PAIVA Nicolas
	 */
	public boolean estPoussable(){
		return false;
	}

	/**
	 * @return si l’objet est glissant
	 * c’est à dire qu’un rocher tombant dessus glissera à gauche ou à droite pour tomber
	 * @author PAIVA Nicolas
	 */
	public boolean estGlissant() {
		return false;
	}

	/**
	 * qui  implémente  le  patron  Visiteur  pour  calculer  l’état  suivant  du  niveau  en  cours
	 * @param niveau,x,y
	 * @author PAIVA NIcolas
	 */
	public void visiterPlateauCalculEtatSuivant(Niveau niveau, int x, int y){
		
	}
}
