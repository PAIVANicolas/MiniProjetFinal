package fr.rodez3il.a2022.mrmatt.sources.objets;

public class Vide extends ObjetPlateau {

    @Override
    public  char afficher(){
        return ' ';
    }

    @Override
    // qui  renvoie  si  l’objet  est  vide
	public boolean estVide(){
		return true;
	}

    @Override
	// qui   renvoie   si   l’objet   est   marchable (c’est  à  dire  que  le  joueur  peut  s’y  déplacer)
	public boolean estMarchable(){
		return true;
	}
    
}
