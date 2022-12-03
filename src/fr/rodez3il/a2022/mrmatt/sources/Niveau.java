package fr.rodez3il.a2022.mrmatt.sources;

import fr.rodez3il.a2022.mrmatt.sources.objets.ObjetPlateau;
import fr.rodez3il.a2022.mrmatt.sources.objets.Rocher;

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
		this.plateau = new ObjetPlateau[TAILLE_VERTICALE][TAILLE_HORIZONTALE];

		//aide de Mr ADAMI
		int compteurLignePlateau = 0;
		int compteurColonnePlateau = 0;

		for(int xColonne=2; xColonne<TAILLE_VERTICALE+2; xColonne++){

			for(int yLigne=0; yLigne<TAILLE_HORIZONTALE; yLigne++){


				char caractereCourrant = taillePlateau[xColonne].charAt(compteurLignePlateau);

				if(caractereCourrant=='*'||caractereCourrant=='+'||caractereCourrant=='H'
						||caractereCourrant=='-'||caractereCourrant=='#'||caractereCourrant==' '){
					this.plateau[compteurColonnePlateau][yLigne]= ObjetPlateau.depuisCaractere(caractereCourrant);
					if(caractereCourrant=='H'){
						this.joueurX=compteurColonnePlateau;
						this.joueurY=yLigne;

					}

					if(caractereCourrant=='+'){
						this.pommesRestantes++;
					}
				}
				compteurLignePlateau++;

			}
			compteurColonnePlateau++;
			compteurLignePlateau=0;

		}

}

	/**
	 * échange l’objet en position (sourceX, sourceY) avec celui en position (destinationX, destinationY)
	 */
	private void echanger(int sourceX, int sourceY, int destinationX, int destinationY) {
		ObjetPlateau objetPlateau = this.plateau[sourceX][sourceY];
		this.plateau[sourceX][sourceY] = this.plateau[destinationX][destinationY];
		this.plateau[destinationX][destinationY] = objetPlateau;
	}

	/**
	 * Produit une sortie du niveau sur la sortie standard.
	 * ................
	 */
	public void afficher() {
		// aide de Mr ADAMI
		for(int xVertical=0; xVertical<TAILLE_VERTICALE; xVertical++){
			String ligne ="";
			for(int yHorizontal=0; yHorizontal<TAILLE_HORIZONTALE; yHorizontal++) {
				if(this.getPlateau()[xVertical][yHorizontal]!=null){
					ligne+=this.getPlateau()[xVertical][yHorizontal].afficher();
				}
			}
			System.out.println(ligne);
		}
	}

  // TODO : patron visiteur du Rocher...
	public void etatSuivantVisiteur(Rocher r, int x, int y) {

	}

	/**
	 * Calcule l'état suivant du niveau.
	 * ........
	 * @author 
	 */
	public void etatSuivant() {
    // TODO
	}


  // Illustrez les Javadocs manquantes lorsque vous coderez ces méthodes !
  
	public boolean enCours() {
		return false;
	}

	/**
	 * Test que le déplacement dx et dy ne sort pas du plateau et que le déplacement est marchable
	 */
	private boolean deplacementPossible(int dx, int dy){
		return dx<=17&&dx>=0&&dy<=30&&dy>=0&&this.getPlateau()[dx][dy].estMarchable();
	}

	/**
	 *
	 */
	public void deplacer(int deltaX, int deltaY){
		this.echanger( this.joueurX,this.joueurY,deltaX,deltaY);
		this.joueurX = deltaX;
		this.joueurY = deltaY;
	}

  // Joue la commande C passée en paramètres
	public boolean jouer(Commande c) {
		switch (c){
			case BAS: this.echanger(this.joueurX,this.joueurY,this.joueurX++,this.joueurY);
				this.nombresDeplacements++;
				return true;
			case HAUT:this.echanger(this.joueurX,this.joueurY,this.joueurX-1,this.joueurY);
				this.nombresDeplacements++;
				return true;
			case DROITE:this.echanger(this.joueurX,this.joueurY,this.joueurX,this.joueurY++);
				this.nombresDeplacements++;
				return true;
			case GAUCHE:this.echanger(this.joueurX,this.joueurY,this.joueurX,this.joueurY-1);
				this.nombresDeplacements++;
				return true;
		}
		return false;
	}

	/**
	 * Affiche l'état final (gagné ou perdu) une fois le jeu terminé.
	 */
	public void afficherEtatFinal() {

	}

	/**
	 */
	public boolean estIntermediaire() {
		return false;
	}
}
