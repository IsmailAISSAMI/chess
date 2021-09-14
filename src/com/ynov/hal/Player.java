package com.ynov.hal;

// La classe "Player" regroupe dans un même conteneur les deux informations
// importantes à connaitre pour chaque joueur : son nom et s'il joue les blancs
public class Player {

	// "private" indique que les ATTRIBUTS "name" et "white" sont utilisables
	// qu'a l'intérieur du bloc "{...}" de "class Player"
	private String name;
	private boolean white;
	
	// Accesseur "getName" qui permet de lire "name" depuis l'extérieur 
	// de "class Player"
	public String getName() {
		return this.name;
	}
	// Accesseur "isWhite" qui permet de tester "white" depuis l'extérieur 
	// de "class Player"
	public boolean isWhite() {
		return this.white;
	}
	// Les accesseurs "setName" et "setWhite" qui permettraient de modifier
	// ces attributs ne sont pas fournis. C'est une sécurité qui permet d'être
	// sûr que ces valeurs ne risquent pas de changer au cours de l'exécution
	// du programme.
	
	// CONSTRUCTEUR utilisé par "new" quand un nouvel objet de type Player
	// doit être créé en mémoire. Ce constructeur prend 2 paramètres qui
	// permettent d'initialiser le nom et la couleur du joueur.
	// Un constructeur est différent d'une méthode car il n'a pas de type
	// de retour et son nom est "Player" (le nom de la classe)
	public Player(String name, boolean white) {
		this.name = name;
		this.white = white;
	}
}

/* Ressources Internet utiles :
   ==========================

https://koor.fr/Java/Tutorial/Visibilite.wp
	-> plus de précisions sur "public" et "private" ... et autres
	(Recherche Google "java visibilité")

*/