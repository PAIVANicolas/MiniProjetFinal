package fr.rodez3il.a2022.mrmatt.sources;

import fr.rodez3il.a2022.mrmatt.sources.objets.EtatRocher;
import fr.rodez3il.a2022.mrmatt.sources.objets.ObjetPlateau;
import fr.rodez3il.a2022.mrmatt.sources.objets.Rocher;
import fr.rodez3il.a2022.mrmatt.sources.objets.EtatRocher;
import fr.rodez3il.a2022.mrmatt.sources.objets.Vide;

import static fr.rodez3il.a2022.mrmatt.sources.objets.EtatRocher.CHUTE;
import static fr.rodez3il.a2022.mrmatt.sources.objets.EtatRocher.FIXE;

public class Niveau {
	
	// Les objets sur le plateau du niveau
	private ObjetPlateau[][] plateau;
	// Position du joueur
	private int joueurX;
	private int joueurY;
	// Autres attributs que vous jugerez nécessaires...
	private int pommesRestantes;
	private int nombresDeplacements;
	private int TAILLE_HORIZONTALE;
	private int TAILLE_VERTICALE;
	private boolean intermediaire;
	private boolean gagner = false;
	private boolean perdu = false;
	

  
	/**
	 * Constructeur public : crée un niveau depuis un fichier.
	 * @param chemin .....
	 * @author .............
	 */
	public Niveau(String chemin) {
		this.chargerNiveau(chemin);
		this.plateau = new ObjetPlateau[0][0];
		this.pommesRestantes = 0;
		this.nombresDeplacements = 0;
		this.chargerNiveau(chemin);

	}

	public ObjetPlateau[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(ObjetPlateau[][] plateau) {
		this.plateau = plateau;
	}

	/**
	 *
	 */
	private void chargerNiveau(String chemin) {
		String cheminNiveau = Utils.lireFichier(chemin);
		String[] taillePlateau = cheminNiveau.split("\n");
		TAILLE_HORIZONTALE = Integer.valueOf(taillePlateau[0]);
		TAILLE_VERTICALE = Integer.valueOf(taillePlateau[1]);
		this.plateau = new ObjetPlateau[TAILLE_HORIZONTALE][TAILLE_VERTICALE];

		//aide de Mr ADAMI
		int compteurLignePlateau = 0;
		int compteurColonnePlateau = 0;

		for(int yLigne=2; yLigne<TAILLE_VERTICALE+2; yLigne++){

			for(int xColonne=0; xColonne<TAILLE_HORIZONTALE; xColonne++){

				char caractereCourrant = taillePlateau[yLigne].charAt(xColonne);
					this.plateau[compteurColonnePlateau][compteurLignePlateau]= ObjetPlateau.depuisCaractere(caractereCourrant);
					if(caractereCourrant=='H'){
						this.joueurY=compteurLignePlateau;
						this.joueurX=compteurColonnePlateau;
					}
					if(caractereCourrant=='+'){
						this.pommesRestantes++;
					}
				compteurColonnePlateau++;
				}
				compteurLignePlateau++;
				compteurColonnePlateau=0;
			}
}

	/**
	 * échange l’objet en position (sourceX, sourceY) avec celui en position (destinationX, destinationY)
	 */
	private void echanger(int sourceX, int sourceY, int destinationX, int destinationY) {
		ObjetPlateau objetPlateau = this.plateau[sourceX][sourceY];

		if(this.plateau[destinationX][destinationY].estMarchable()){
			if(this.plateau[destinationX][destinationY].afficher()=='+'){
				this.pommesRestantes--;
			}
			this.plateau[sourceX][sourceY]=new Vide();
			this.plateau[destinationX][destinationY]=objetPlateau;
		}else{

			this.plateau[sourceX][sourceY]=this.plateau[destinationX][destinationY];
			this.plateau[destinationX][destinationY]=objetPlateau;
		}

	}
	/**
	 * Produit une sortie du niveau sur la sortie standard.
	 * ................
	 */
	public void afficher() {
		// aide de Mr ADAMI
		System.out.println(TAILLE_HORIZONTALE);
		System.out.println(TAILLE_VERTICALE);

		for(int yVertical=0; yVertical<TAILLE_VERTICALE; yVertical++){
			String ligne ="";
			for(int xHorizontal=0; xHorizontal<TAILLE_HORIZONTALE; xHorizontal++) {
					ligne+=this.getPlateau()[xHorizontal][yVertical].afficher();
			}
			System.out.println(ligne);
		}
		System.out.println("Nombre de pommes restantes : " + pommesRestantes);
		System.out.println("Nombre de deplacements : " + nombresDeplacements);
	}

  // TODO : patron visiteur du Rocher...
	public void etatSuivantVisiteur(Rocher r, int x, int y) {
		switch (r.getEtatRocher()){
			case FIXE :
				if(this.plateau[x][y].estVide()){
					r.setEtatRocher(CHUTE);

				}
			break;
			case CHUTE :
				if(x==this.plateau.length){
					r.setEtatRocher(FIXE);
				}
				return;
		}



	}

	/**
	 * Calcule l'état suivant du niveau.
	 * ........
	 * @author 
	 */
	public void etatSuivant() {
    // TODO
		for (int x = plateau.length - 1; x >= 0; x--) {
			for (int y = plateau[x].length - 1; y >= 0; y--) { plateau[x][y].visiterPlateauCalculEtatSuivant(this, x, y);
			}
			this.gagner = pommesRestantes == 0;
		}
		this.gagner = pommesRestantes == 0;
	}


  // Illustrez les Javadocs manquantes lorsque vous coderez ces méthodes !
  
	public boolean enCours() {
		this.gagner = this.pommesRestantes==0;

		if(!this.gagner && !this.perdu){
			return true;
		}else {
			return  false;
		}

	}

	/**
	 * Test que le déplacement dx et dy ne sort pas du plateau et que le déplacement est marchable
	 */
	private boolean deplacementPossible(int dx, int dy) {
		int destinationX = joueurX + dx;
		int destinationY = joueurY + dy;
		if (destinationX >= 0 && destinationX < TAILLE_HORIZONTALE && destinationY >= 0 && destinationY < TAILLE_VERTICALE && this.plateau[destinationX][destinationY].estMarchable()) {
			return true;
		}
		return false;
	}


	/**
	 *
	 */
	public void deplacer(int deltaX, int deltaY){
		if(deltaX == 0){
			if(deltaX + joueurY >= 0 && deltaX + joueurY < this.plateau.length )
				if(this.plateau[joueurX][joueurY + deltaX].estPoussable() && this.plateau[joueurX][joueurY+ 2*deltaY].estVide()){
					this.echanger(joueurX, joueurY  + deltaY,joueurX, joueurY  + 2*deltaY);
					this.echanger(joueurX, joueurY + deltaY, joueurX, joueurY);
					}
		}
		this.echanger( this.joueurX,this.joueurY,joueurX + deltaX,joueurY +deltaY);
		this.joueurX += deltaX;
		this.joueurY += deltaY;
	}

  // Joue la commande C passée en paramètres
	public boolean jouer(Commande c) {
		int dx = 0, dy = 0;

		switch (c){
			case QUITTER:
				this.perdu = true;
				break;
			case ERREUR:
				return false;
			case BAS:
				dy = 1;
				break;
			case HAUT:
				dy = -1;
				break;
			case DROITE:
				dx = 1;
				break;
			case GAUCHE:
				dx = -1;
				break;
		}
		if(dx!=0 || dy !=0){
			if (deplacementPossible(dx, dy)) {
				deplacer(dx,dy);
				nombresDeplacements++;
			}
		}
		return false;
	}


	/**
	 * Affiche l'état final (gagné ou perdu) une fois le jeu terminé.
	 */
	public void afficherEtatFinal() {
		if(this.gagner){
			System.out.println("Vous avez gagné !");
		}else if (this.perdu){
			System.out.println("Vous avez perdu !");
		}
	}

	/**
	 */
	public boolean estIntermediaire() {
		return this.intermediaire&&this.enCours();
	}
}
