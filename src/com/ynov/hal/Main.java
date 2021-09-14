// "package" indique le chemin de dossiers pour acc�der � la classe :
// com/ynov/hal/Main.class
package com.ynov.hal;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
// "import" indique dans quel chemin de dossier trouver la classe qu'on veut
// utiliser : java/util/Scanner.class (dans le module "java.base" du JRE)
import java.util.Scanner;

// D�claration de la classe "Main"
// (comme elle est "public" le nom du fichier doit �tre le m�me : Main.java)
public class Main {

	// "context" est une structure de donn�e permettant de stocker des
	// couples cl�/valeur. Ici la cl� est de type "String" et la valeur
	// de type "Object".
	// (voir : https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/Map.html)
	public static Map<String,Object> context;
	
	// Point de d�part de l'ex�cution du programme dont chaque mot et signe
	// est � �crire tel quel (sauf "args")
	public static void main(String[] args) {
		
		Main.showTitle();
		
		// "Appel" de la m�thode "readString" de la classe "Main"
		// - pour cet appel, le param�tre est "Nom premier joueur : "
		// - le r�sultat en retour de cet appel sera stock� dans "player1"
		String player1 = Main.readString("Nom premier joueur : ");
		// "System.out.println" permet d'afficher du texte dans la console
		// - le "plus" permet de concat�ner (joindre) deux textes
		System.out.println("Bonjour " + player1 + " !!!");
		String player2 = Main.readString("Nom deuxi�me joueur : ");
		System.out.println("Bonjour " + player2 + " !!!");
		
		String white = Main.chooseWhites(player1, player2);
		System.out.println(white + " joue les blancs...");
		
		// INSTANCIATION de deux joueurs : "new" cr�e la place en m�moire pour
		// un objet de la classe "Player"
		Player object1 = new Player(player1, player1.equals(white));
		Player object2 = new Player(player2, player2.equals(white));
		
		// On acc�de aux informations contenues dans chaque objet gr�ce aux
		// "getter". Ici "getName" et "isWhite"
		System.out.println(object1.getName() + " joue avec les "
				+ ( object1.isWhite() ? "blancs" : "noirs" ));
		// l'op�rateur ternaire " ? : " peut remplacer le "if" quand on veut 
		// juste faire un choix entre deux valeurs
		System.out.println(object2.getName() + " joue avec les "
				+ ( object2.isWhite() ? "blancs" : "noirs" ));

		// "display" n'est pas "static" et il faut donc cr�er une instance
		// de "Chessboard" (en utilisant "new") pour pouvoir l'appeler...
		Chessboard board = new Chessboard();
		board.display();
		// ... si "display" avait �t� "static", il n'y avait pas besoin de 
		// cr�er d'instance et une ligne aurait suffit :
		// 
		//  Chessboard.display();
		//
		// -> une m�thode "static" peut �tre appel�e sans faire de "new" mais 
		//    ne pourra lire ou modifier que des donn�es "static" (globales et
		//    uniques sur tout le projet).
		//
		// -> une m�thode NON "static" n�cessite un "new" avant et permet de 
		//    lire et modifier tous les types de donn�es ("static" ou pas).
		//    Ainsi ces m�thodes peuvent acc�der aux attributs dont les valeurs
		//    changent pour chacune des copies d'objets d'une m�me classe.
		
		// Initialisation des couples cl�s/valeurs d�finissant le contexte 
		// d'ex�cution du programme
		//
		// Il y a plusieurs impl�mentations possible de "Map" en fonctions des 
		// besoins des diff�rents projets. L'impl�mentation la plus courante
		// est "HashMap".
		Main.context = new HashMap<>();
		Main.context.put("whites", object1.isWhite() ? object1 : object2);
		Main.context.put("blacks", object2.isWhite() ? object1 : object2);
		Main.context.put("board", board);
		
		Game game = new Game();
		game.play();
	}
	
	// D�claration de la m�thode "readString"
	// - une "m�thode" est une fonction d�clar�e � l'int�rieur d'une classe
	// - "public static" indique que cette m�thode est unique et appelable  
	//   � partir de n'importe quel code du projet en �crivant :
	//     Main.readName("xxx")
	// - "prompt" permet de stocker le param�tre donn� lors de l'appel
	//   ("prompt" contiendra "Nom premier joueur : " si appel� depuis ligne 23)
	// - le r�sultat retourn� apr�s l'ex�cution de la m�thode est de type String
	//            VVVVVV
	public static String readString(String prompt) {
		System.out.print(prompt);
		// "Scanner" permet de r�cup�rer des donn�es du clavier
		// - utilisable gr�ce � l' "import" de la ligne 7
		// - on peut utiliser plusieurs copies de la classe "Scanner" gr�ce
		//   � "new" qui cr�e une copie et la stocke dans la varialbe "entry"
		// - cette copie va analyser "System.in" qui est l'acc�s au clavier
		Scanner entry = new Scanner(System.in);
		String nom = entry.nextLine();
		// La variable "nom" est recopi�e dans le r�sultat de la m�thode
		return nom;
	}
	
	public static double readDouble(String prompt) {
		boolean ok = false;
		double value = 0;
		while ( ! ok ) {
			// R�cup�ration du TEXTE du nombre saisi
			String text = Main.readString(prompt);
			// Bloc "try" o� se trouve l'erreur � g�rer
			try {
				// Conversion du texte en une valeur de type num�rique "double"
				//
				// "parseDouble" peut g�n�rer une EXCEPTION (erreur) si le texte
				// ne correspond pas � un nombre ("z�ro" au lieu de "0")
				value = Double.parseDouble(text);
				
				// ... en cas d'erreur sur "parseDouble" le programme saute
				//     directement au bloc "catch"
				
				// S'il n'y a pas d'erreur, la ligne suivante est ex�cut�e et 
				// "ok" passe � vrai ce qui permet de sortir de la boucle
				ok = true;
			}
			// "NumberFormatException" indique le type d'erreur � g�rer. Tous
			// les autres types d'erreur continuent � "planter" le programme
			catch (java.lang.NumberFormatException exception) {
				// Un message d'avertissement est affich� en cas d'erreur
				// - le "\n" dans le texte indique un passage � la ligne dans
				//   l'affichage
				System.out.println
					("Erreur de saisie : \n il faut " +
					"saisir un nombre d�cimal (exp: 0.5)");
				// "ok" est maintenu � "false" pour rester dans la boucle
				ok = false;
			}
		}
		return value;
	}
	
	public static void showChessboard() {
		
		// Initialisation de la fen�tre graphique "StdDraw"
		StdDraw.setCanvasSize(600,600);
		StdDraw.setXscale(0, 8);
		StdDraw.setYscale(0, 8);
		StdDraw.setPenColor(StdDraw.BLACK);
		
		// La structure de contr�le "for(;;)" permet de r�p�ter l'ex�cution
		// du bloc "{...}" en se basant sur la gestion d'un compteur
		for (int x = 0; x < 8; x++) {
			// Il est possible d'imbriquer des structures de contr�le les unes
			// dans les autres et c'est ce qui permet de r�aliser des 
			// algorithmes qui peuvent �tre complexes tout en ne n�c�ssitant 
			// que peu de lignes
			for (int y = 0; y < 8; y++) {
				
				// La structure de contr�le "if()" permet de n'ex�cuter 
				// le bloc "{...}" que si le test situ� entre les 
				// parenth�ses est vrai
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
		// Il est tout � fait possible d'appeler des m�thodes d'une classe
		// depuis une autre m�thode de la m�me classe
		Main.showChessboard();
		Main.showPieces();
		// Code StdDraw permettant d'afficher du texte
		Font font = new Font("Arial", Font.BOLD, 80);
		StdDraw.setFont(font);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(4, 4, "JEU D'ECHEC");
	}

	// Fonction "chooseWhites" (pubic static) qui prend 2 noms en 
	// param�tres et retourne le nom du joueur qui a les blancs
	public static String chooseWhites(String name1, String name2) {
		// Nombre tir� au hasard par l'ordinateur
		double target = Math.random();
		// R�cup�ration des nombres saisis par les joueurs
		double value1 = Main.readDouble(name1 + " valeur entre 0 et 1 : ");
		double value2 = Main.readDouble(name2 + " valeur entre 0 et 1 : ");
		// D�termine quel est le joueur le plus proche de "target"
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
	-> les op�rateurs du langage Java
	(recherche Google "java operateurs")
	
https://introcs.cs.princeton.edu/java/stdlib/StdDraw.java.html
	-> Documentation de la classe graphique "StdDraw" bas�e sur "Swing" qui est
	   une biblioth�que graphique plus complexe fournie en standard par Java

* Documents de r�f�rence :

https://docs.oracle.com/en/java/javase/16/
	-> la "Javadoc" de r�f�rence de toutes les classes fournies en standard

https://docs.oracle.com/javase/specs/
	-> la documentation de r�f�rence sur la syntaxe du langage Java et sur le 
	   fonctionnement de l'interpr�teur JVM (Java Virtual Machine)
	   
https://docs.oracle.com/en/java/javase/16/docs/specs/man/javac.html
	-> la documentation du "compilateur" qui analyse le code Java (.java) pour 
	   le transformer en "ByteCode" (.class) ex�cutable par la JVM

*/