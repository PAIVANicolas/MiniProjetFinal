package fr.rodez3il.a2022.mrmatt.sources.objets;

public class Rocher extends ObjetPlateau {
    
    @Override
    public abstract char afficher(){
        return "*";
    }


	// qui   renvoie   si   l’objet   est   marchable (c’est  à  dire  que  le  joueur  peut  s’y  déplacer)
    @Override
	public boolean estPoussable(){
		return true;
	}
}
