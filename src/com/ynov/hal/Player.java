package com.ynov.hal;

// La classe "Player" regroupe dans un m�me conteneur les deux informations
// importantes � connaitre pour chaque joueur : son nom et s'il joue les blancs
public class Player {

	// "private" indique que les ATTRIBUTS "name" et "white" sont utilisables
	// qu'a l'int�rieur du bloc "{...}" de "class Player"
	private String name;
	private boolean white;
	
	// Accesseur "getName" qui permet de lire "name" depuis l'ext�rieur 
	// de "class Player"
	public String getName() {
		return this.name;
	}
	// Accesseur "isWhite" qui permet de tester "white" depuis l'ext�rieur 
	// de "class Player"
	public boolean isWhite() {
		return this.white;
	}
	// Les accesseurs "setName" et "setWhite" qui permettraient de modifier
	// ces attributs ne sont pas fournis. C'est une s�curit� qui permet d'�tre
	// s�r que ces valeurs ne risquent pas de changer au cours de l'ex�cution
	// du programme.
	
	// CONSTRUCTEUR utilis� par "new" quand un nouvel objet de type Player
	// doit �tre cr�� en m�moire. Ce constructeur prend 2 param�tres qui
	// permettent d'initialiser le nom et la couleur du joueur.
	// Un constructeur est diff�rent d'une m�thode car il n'a pas de type
	// de retour et son nom est "Player" (le nom de la classe)
	public Player(String name, boolean white) {
		this.name = name;
		this.white = white;
	}
}

/* Ressources Internet utiles :
   ==========================

https://koor.fr/Java/Tutorial/Visibilite.wp
	-> plus de pr�cisions sur "public" et "private" ... et autres
	(Recherche Google "java visibilit�")

*/