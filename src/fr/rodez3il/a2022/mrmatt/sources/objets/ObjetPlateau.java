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
	// qui  renvoie  le  caractère  correspondant  à  l’objet.  Cette  méthode  sera  redéfinie  dans  chaque  objet  à  implan- ter
	public abstract char afficher();

	// qui  renvoie  si  l’objet  est  vide
	public boolean estVide(){
		return false;
	}

	// qui   renvoie   si   l’objet   est   marchable (c’est  à  dire  que  le  joueur  peut  s’y  déplacer)
	public boolean estMarchable(){
		return false;
	}

	//qui renvoie si l’objet est poussable (c’est à dire que le joueur peut le pousser horizontalement en se déplaçant dans sa direction)
	public boolean estPoussable(){
		return false;
	}

	//  qui renvoie si l’objet est glissant (c’est à dire qu’un rocher tombant dessus glissera à gauche ou à droite pour tomber)
	public boolean estGlissant() {
		return false;
	}

	// qui  implémente  le  patron  Visiteur  pour  calculer  l’état  suivant  du  niveau  en  cours
	public void visiterPlateauCalculEtatSuivant(Niveau niveau, int x, int y){
		
	}
}
