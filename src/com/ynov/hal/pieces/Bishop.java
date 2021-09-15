// Pour clarifier le projet, toutes les classes associ�es aux pi�ces du jeu d'�chec
// ont �t� regroup�e dans un "package" s�par� qui est un sous-dossier du dossier "hal"
package com.ynov.hal.pieces;

import com.ynov.hal.ChessColor;
import com.ynov.hal.StdDraw;

// La classe "Bishop" HERITE des attributs et des m�thodes de la classe "Piece" 
// grace au mot-cl� "extends"
public class Bishop extends Piece {

	// La classe "Bishop" est oblig�e d'avoir un constructeur avec le param�tre
	// de type "ChessColor" car sa classe m�re en a un avec cette signature.
	public Bishop(ChessColor color) {
		// Ici, la seule instruction est d'appeler le constructeur correspondant 
		// dans la classe m�re (super-classe) 
		super(color);
	}

	// La m�thode "display" est surcharg�e pour afficher le dessin du fou
	@Override
	public void display(int x, int y) {
		String image = this.color == ChessColor.BLACK ? 
				"images/Fn.png" : "images/Fb.png";
		StdDraw.picture(x + 0.5, y + 0.5, image, 1, 1);
	}

	// La m�thode "toString" est surcharg�e pour retourner "Fn" ou "Fb"
	@Override
	public String toString() {
		// La couleur "n" ou "b" est retourn�e par la m�thode "toString" de la
		// classe m�re
		return "F" + super.toString();
	}
	
	@Override
	public boolean checkMove( int originX, int originY, int targetX, int targetY, boolean toKill) {
		return false;
	}

}
