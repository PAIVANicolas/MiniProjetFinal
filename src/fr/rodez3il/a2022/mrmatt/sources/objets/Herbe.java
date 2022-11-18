package fr.rodez3il.a2022.mrmatt.sources.objets;

public class Herbe extends ObjetPlateau {
    
    @Override
    public char afficher(){
        return '-';
    }


	// qui   renvoie   si   l’objet   est   marchable (c’est  à  dire  que  le  joueur  peut  s’y  déplacer)
    @Override
	public boolean estMarchable(){
		return true;
	}

}
