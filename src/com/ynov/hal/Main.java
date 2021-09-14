// "package" indique le chemin de dossiers pour accéder à la classe :
// com/ynov/hal/Main.class
package com.ynov.hal;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
// "import" indique dans quel chemin de dossier trouver la classe qu'on veut
// utiliser : java/util/Scanner.class (dans le module "java.base" du JRE)
import java.util.Scanner;

// Déclaration de la classe "Main"
// (comme elle est "public" le nom du fichier doit être le même : Main.java)
public class Main {

	// "context" est une structure de donnée permettant de stocker des
	// couples clé/valeur. Ici la clé est de type "String" et la valeur
	// de type "Object".
	// (voir : https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/Map.html)
	public static Map<String,Object> context;
	
	// Point de départ de l'exécution du programme dont chaque mot et signe
	// est à écrire tel quel (sauf "args")
	public static void main(String[] args) {
		
		Main.showTitle();
		
		// "Appel" de la méthode "readString" de la classe "Main"
		// - pour cet appel, le paramètre est "Nom premier joueur : "
		// - le résultat en retour de cet appel sera stocké dans "player1"
		String player1 = Main.readString("Nom premier joueur : ");
		// "System.out.println" permet d'afficher du texte dans la console
		// - le "plus" permet de concaténer (joindre) deux textes
		System.out.println("Bonjour " + player1 + " !!!");
		String player2 = Main.readString("Nom deuxième joueur : ");
		System.out.println("Bonjour " + player2 + " !!!");
		
		String white = Main.chooseWhites(player1, player2);
		System.out.println(white + " joue les blancs...");
		
		// INSTANCIATION de deux joueurs : "new" crée la place en mémoire pour
		// un objet de la classe "Player"
		Player object1 = new Player(player1, player1.equals(white));
		Player object2 = new Player(player2, player2.equals(white));
		
		// On accède aux informations contenues dans chaque objet grâce aux
		// "getter". Ici "getName" et "isWhite"
		System.out.println(object1.getName() + " joue avec les "
				+ ( object1.isWhite() ? "blancs" : "noirs" ));
		// l'opérateur ternaire " ? : " peut remplacer le "if" quand on veut 
		// juste faire un choix entre deux valeurs
		System.out.println(object2.getName() + " joue avec les "
				+ ( object2.isWhite() ? "blancs" : "noirs" ));

		// "display" n'est pas "static" et il faut donc créer une instance
		// de "Chessboard" (en utilisant "new") pour pouvoir l'appeler...
		Chessboard board = new Chessboard();
		board.display();
		// ... si "display" avait été "static", il n'y avait pas besoin de 
		// créer d'instance et une ligne aurait suffit :
		// 
		//  Chessboard.display();
		//
		// -> une méthode "static" peut être appelée sans faire de "new" mais 
		//    ne pourra lire ou modifier que des données "static" (globales et
		//    uniques sur tout le projet).
		//
		// -> une méthode NON "static" nécessite un "new" avant et permet de 
		//    lire et modifier tous les types de données ("static" ou pas).
		//    Ainsi ces méthodes peuvent accéder aux attributs dont les valeurs
		//    changent pour chacune des copies d'objets d'une même classe.
		
		// Initialisation des couples clés/valeurs définissant le contexte 
		// d'exécution du programme
		//
		// Il y a plusieurs implémentations possible de "Map" en fonctions des 
		// besoins des différents projets. L'implémentation la plus courante
		// est "HashMap".
		Main.context = new HashMap<>();
		Main.context.put("whites", object1.isWhite() ? object1 : object2);
		Main.context.put("blacks", object2.isWhite() ? object1 : object2);
		Main.context.put("board", board);
		
		Game game = new Game();
		game.play();
	}
	
	// Déclaration de la méthode "readString"
	// - une "méthode" est une fonction déclarée à l'intérieur d'une classe
	// - "public static" indique que cette méthode est unique et appelable  
	//   à partir de n'importe quel code du projet en écrivant :
	//     Main.readName("xxx")
	// - "prompt" permet de stocker le paramètre donné lors de l'appel
	//   ("prompt" contiendra "Nom premier joueur : " si appelé depuis ligne 23)
	// - le résultat retourné après l'exécution de la méthode est de type String
	//            VVVVVV
	public static String readString(String prompt) {
		System.out.print(prompt);
		// "Scanner" permet de récupérer des données du clavier
		// - utilisable grâce à l' "import" de la ligne 7
		// - on peut utiliser plusieurs copies de la classe "Scanner" grâce
		//   à "new" qui crée une copie et la stocke dans la varialbe "entry"
		// - cette copie va analyser "System.in" qui est l'accès au clavier
		Scanner entry = new Scanner(System.in);
		String nom = entry.nextLine();
		// La variable "nom" est recopiée dans le résultat de la méthode
		return nom;
	}
	
	public static double readDouble(String prompt) {
		boolean ok = false;
		double value = 0;
		while ( ! ok ) {
			// Récupération du TEXTE du nombre saisi
			String text = Main.readString(prompt);
			// Bloc "try" où se trouve l'erreur à gérer
			try {
				// Conversion du texte en une valeur de type numérique "double"
				//
				// "parseDouble" peut générer une EXCEPTION (erreur) si le texte
				// ne correspond pas à un nombre ("zéro" au lieu de "0")
				value = Double.parseDouble(text);
				
				// ... en cas d'erreur sur "parseDouble" le programme saute
				//     directement au bloc "catch"
				
				// S'il n'y a pas d'erreur, la ligne suivante est exécutée et 
				// "ok" passe à vrai ce qui permet de sortir de la boucle
				ok = true;
			}
			// "NumberFormatException" indique le type d'erreur à gérer. Tous
			// les autres types d'erreur continuent à "planter" le programme
			catch (java.lang.NumberFormatException exception) {
				// Un message d'avertissement est affiché en cas d'erreur
				// - le "\n" dans le texte indique un passage à la ligne dans
				//   l'affichage
				System.out.println
					("Erreur de saisie : \n il faut " +
					"saisir un nombre décimal (exp: 0.5)");
				// "ok" est maintenu à "false" pour rester dans la boucle
				ok = false;
			}
		}
		return value;
	}
	
	public static void showChessboard() {
		
		// Initialisation de la fenêtre graphique "StdDraw"
		StdDraw.setCanvasSize(600,600);
		StdDraw.setXscale(0, 8);
		StdDraw.setYscale(0, 8);
		StdDraw.setPenColor(StdDraw.BLACK);
		
		// La structure de contrôle "for(;;)" permet de répéter l'exécution
		// du bloc "{...}" en se basant sur la gestion d'un compteur
		for (int x = 0; x < 8; x++) {
			// Il est possible d'imbriquer des structures de contrôle les unes
			// dans les autres et c'est ce qui permet de réaliser des 
			// algorithmes qui peuvent être complexes tout en ne nécéssitant 
			// que peu de lignes
			for (int y = 0; y < 8; y++) {
				
				// La structure de contrôle "if()" permet de n'exécuter 
				// le bloc "{...}" que si le test situé entre les 
				// parenthèses est vrai
				if ((x + y) % 2 == 0) {
					StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
				}
			}
		}
	}

	public static void showPieces() {
		for (int i = 0; i < 8; i++) {
			StdDraw.picture(i + 0.5, i + 0.5, "images/Cb.png", 1, 1);
			StdDraw.picture(i + 0.5, 7 - i + 0.5, "images/Cn.png", 1, 1);
		}
	}
	
	public static void showTitle() {
		// Il est tout à fait possible d'appeler des méthodes d'une classe
		// depuis une autre méthode de la même classe
		Main.showChessboard();
		Main.showPieces();
		// Code StdDraw permettant d'afficher du texte
		Font font = new Font("Arial", Font.BOLD, 80);
		StdDraw.setFont(font);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(4, 4, "JEU D'ECHEC");
	}

	// Fonction "chooseWhites" (pubic static) qui prend 2 noms en 
	// paramètres et retourne le nom du joueur qui a les blancs
	public static String chooseWhites(String name1, String name2) {
		// Nombre tiré au hasard par l'ordinateur
		double target = Math.random();
		// Récupération des nombres saisis par les joueurs
		double value1 = Main.readDouble(name1 + " valeur entre 0 et 1 : ");
		double value2 = Main.readDouble(name2 + " valeur entre 0 et 1 : ");
		// Détermine quel est le joueur le plus proche de "target"
		double delta1 = Math.abs(target - value1);
		double delta2 = Math.abs(target - value2);
		System.out.println("Valeur cible : " + target);
		if (delta1 < delta2) {
			return name1;
		} else if (delta1 == delta2) {
			return name2;
		}
		else {
			return name2;
		}
	}
}

/* Ressources Internet utiles :
   ==========================

https://gayerie.dev/epsi-b3-java/langage_java/types_primitifs.html
    -> les types "primitifs" du langage Java
    (recherche Google "java types primitifs")

https://web.maths.unsw.edu.au/~lafaye/CCM/java/javaop.htm
	-> les opérateurs du langage Java
	(recherche Google "java operateurs")
	
https://introcs.cs.princeton.edu/java/stdlib/StdDraw.java.html
	-> Documentation de la classe graphique "StdDraw" basée sur "Swing" qui est
	   une bibliothèque graphique plus complexe fournie en standard par Java

* Documents de référence :

https://docs.oracle.com/en/java/javase/16/
	-> la "Javadoc" de référence de toutes les classes fournies en standard

https://docs.oracle.com/javase/specs/
	-> la documentation de référence sur la syntaxe du langage Java et sur le 
	   fonctionnement de l'interpréteur JVM (Java Virtual Machine)
	   
https://docs.oracle.com/en/java/javase/16/docs/specs/man/javac.html
	-> la documentation du "compilateur" qui analyse le code Java (.java) pour 
	   le transformer en "ByteCode" (.class) exécutable par la JVM

*/