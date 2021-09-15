package com.ynov.hal.pieces;

import com.ynov.hal.ChessColor;

// "Piece" est une classe ABSTRAITE car il n'est pas possible d'utiliser "new"
// pour créer un objet de cette classe. En effet, un objet de cette classe
// aurait une erreur sur la méthode "display" car celle-ci n'a pas de code. 
public abstract class Piece {

	// "protected" rend "color" utilisable dans les classes dérivées (filles) de 
	// la classe "Piece"
	protected ChessColor color;

	public Piece(ChessColor color) {
		this.color = color;
	}
	
	public ChessColor getColor() {
		return this.color;
	}

	// La méthode "display" est ABSTRAITE car il doit être possible d'afficher
	// une pièce, mais, comme la forme des pièces change, il n'est pas possible
	// de donner le code d'affichage dans la super-classe (classe mère).
	public abstract void display(int x, int y);
	
	// La méthode "checkMove" est ABSTRAITE,
	// puisque pour chaque pièce nous avant des règles de mouvement different.
	public abstract boolean checkMove( int originX, int originY, int targetX, int targetY, boolean toKill);

	// On SURCHARGE la méthode "toString" pour qu'elle retourne juste un caractère
	//
	// l' ANNOTATION "@Override" peut être ajoutée pour améliorer la fiabilité du 
	// code : le compilateur "javac" va vérifier que "toString" est bien une méthode 
	// héritée et que sa syntaxe est respectée par rapport à la classe mère.
	@Override
	public String toString() {
		return this.color == ChessColor.BLACK ? "n" : "b";
	}
}
