package com.ynov.hal;

import com.ynov.hal.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import com.ynov.hal.pieces.Bishop;
import com.ynov.hal.pieces.King;
import com.ynov.hal.pieces.Knight;
import com.ynov.hal.pieces.Pawn;
import com.ynov.hal.pieces.Queen;
import com.ynov.hal.pieces.Rook;

public class Chessboard {
	
	// Pour pouvoir stocker les 64 cases de l'�chiquier, on utilise un tableau.
	// La contrainte du tableau est qu'il ne peut pas changer de taille. Par
	// contre la manipulation des tableaux plus rapide que toutes les autres
	// structures de donn�es.
	// - "[][]" indique que ce tableau est � deux dimensions (lignes et colonnes)
	// 
	// ATTENTION : l'attribut "matrix" contient la valeur "null" par d�faut.
	//   C'est � dire qu'il est possible d'y stocker un tableau mais qu'il
	//   n'y a rien pour l'instant.
	private Cell[][] matrix;

	public Chessboard() {
		// On commence par cr�er en m�moire un tableau de 8x8
		//
		// ATTENTION: les cases du tableau cr�� en m�moire contiennent "null".
		this.matrix = new Cell[8][8];
		// Il faut donc parcourir toutes les cases pour y cr�er une instance
		// de type "Cell"
		//
		// Remarque : ce deuxi�me niveau de "new" est important pour des cases
		//   de type objet (comme "Cell") mais inutile pour des cases de type
		//   primitif (comme "int", "double"...)
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if ((x + y) % 2 == 0) {
					this.matrix[x][y] = new Cell(ChessColor.BLACK);
				} else {
					this.matrix[x][y] = new Cell(ChessColor.WHITE);
				}
			}
		}
		
		this.buildPieces();
	}
	
	private void buildPieces() {
		
		// La structure de donn�es "List" permet de stocker un ensemble ordonn�
		// d'�l�ments accessibles par un index comme dans un tableau. La diff�rence 
		// par rapport au tableau est que cette structure est dynamique : il est
		// possible d'y ajouter ou d'en retirer des �l�ments.
		// (voir : https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/List.html )
		//
		// - entre "<" et ">" est indiqu� le type des �l�ments dans cette liste
		// - "List" donne les m�thodes pour g�rer les �l�ments mais, comme il y a
		//    plusieurs impl�mentations possibles de ces m�thodes, il n'est pas 
		//    possible de faire un "new" d'une "List".
		// - Ici, on a choisi l'impl�mentation "ArrayList" car c'est 
		//   l'impl�mentation la plus courante pour les listes.
		List<Piece> pieces = new ArrayList<Piece>();
		// Comme la liste g�re des �l�ments de classe "Piece", on peut aussi y 
		// stocker des �l�ments des classes "filles" ("King", "Queen"...). Cela
		// s'appelle le POLYMORPHISME.
		pieces.add( new King(ChessColor.WHITE));
		pieces.add( new King(ChessColor.BLACK));
		pieces.add( new Queen(ChessColor.WHITE));
		pieces.add( new Queen(ChessColor.BLACK));
		pieces.add( new Bishop(ChessColor.WHITE));
		pieces.add( new Bishop(ChessColor.BLACK));
		pieces.add( new Knight(ChessColor.WHITE));
		pieces.add( new Knight(ChessColor.BLACK));
		pieces.add( new Rook(ChessColor.WHITE));
		pieces.add( new Rook(ChessColor.BLACK));
		pieces.add( new Pawn(ChessColor.WHITE));
		pieces.add( new Pawn(ChessColor.BLACK));
		for(Piece elem : pieces) {
			// "elem" n'est pas de type "String" et devrait provoquer une erreur
			// avec "println" qui ne fonctionne qu'avec "String".
			//
			// Il n'y a pas d'erreur car, dans ce cas, Java fait automatiquement 
			// appel � la m�thode de conversion "toString" qui existe dans toutes 
			// les classes. En effet "toString" est d�fini dans la classe "Object"
			// dont h�ritent toutes les classes dans le langage Java.
			System.out.println(elem);
		}
		
		// "{...}" permet d'initialiser directement le contenu d'un tableau.
		// Ici, la syntaxe est "{ {...}, {...}, ...}" car on initialise une matrice  
		// qui est un tableau (lignes) de tableaux (colonnes)
		String[][] positions = {
				{"Tn","Cn","Fn","Dn","Rn","Fn","Cn","Tn"},
				{"Pn","Pn","Pn","Pn","Pn","Pn","Pn","Pn"},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{"Pb","Pb","Pb","Pb","Pb","Pb","Pb","Pb"},
				{"Tb","Cb","Fb","Db","Rb","Fb","Cb","Tb"}
		};
		
		// Algorithme permettant d'initialiser les pi�ces sur l'�chiquer en faisant
		// correspondre un des �l�ments de la liste "pieces" avec chaque case 
		// du plan "positions" 
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				for (Piece elem : pieces) {
					// "toString" retourne par d�faut le nom de la classe et 
					// l'adresse de l'instance dans la m�moire.
					//
					// Ici, "toString" a �t� red�fini pour retourner un code 
					// correspondant au plan "position" (cette red�finition
					// s'appelle la SURCHARGE des m�thodes)
					if (elem.toString().equals(positions[7-y][x])) {
						this.matrix[x][y].setPiece(elem);
					}
				}
			}
		}
	}

	public void display() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				// "matrix[x][y]" donne acc�s � une des cases du tableau
				this.matrix[x][y].display(x,y);
			}
		}
	}

	public Cell getCell(int x, int y) {
		return this.matrix[x][y];
	}
}

/* Ressources Internet utiles :
   ==========================

https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/Arrays.html
	-> "Arrays" est une classe qui fourni des m�thodes simplifiant la 
	   manipulation des tableaux

https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/lang/Integer.html
	-> Dans le m�me ordre d'id�e, "Integer", "Double", "Boolean"... sont des
	   classes "wrapper" qui fournissent des m�thodes utiles pour la 
	   manipulation des type primitifs

*/