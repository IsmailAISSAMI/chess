package com.ynov.hal.pieces;

import com.ynov.hal.ChessColor;

// "Piece" est une classe ABSTRAITE car il n'est pas possible d'utiliser "new"
// pour cr�er un objet de cette classe. En effet, un objet de cette classe
// aurait une erreur sur la m�thode "display" car celle-ci n'a pas de code. 
public abstract class Piece {

	// "protected" rend "color" utilisable dans les classes d�riv�es (filles) de 
	// la classe "Piece"
	protected ChessColor color;

	public Piece(ChessColor color) {
		this.color = color;
	}
	
	public ChessColor getColor() {
		return this.color;
	}

	// La m�thode "display" est ABSTRAITE car il doit �tre possible d'afficher
	// une pi�ce, mais, comme la forme des pi�ces change, il n'est pas possible
	// de donner le code d'affichage dans la super-classe (classe m�re).
	public abstract void display(int x, int y);
	
	// La m�thode "checkMove" est ABSTRAITE,
	// puisque pour chaque pi�ce nous avant des r�gles de mouvement different.
	public abstract boolean checkMove( int originX, int originY, int targetX, int targetY, boolean toKill);

	// On SURCHARGE la m�thode "toString" pour qu'elle retourne juste un caract�re
	//
	// l' ANNOTATION "@Override" peut �tre ajout�e pour am�liorer la fiabilit� du 
	// code : le compilateur "javac" va v�rifier que "toString" est bien une m�thode 
	// h�rit�e et que sa syntaxe est respect�e par rapport � la classe m�re.
	@Override
	public String toString() {
		return this.color == ChessColor.BLACK ? "n" : "b";
	}
}
