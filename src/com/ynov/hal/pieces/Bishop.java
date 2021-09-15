// Pour clarifier le projet, toutes les classes associées aux pièces du jeu d'échec
// ont été regroupée dans un "package" séparé qui est un sous-dossier du dossier "hal"
package com.ynov.hal.pieces;

import com.ynov.hal.ChessColor;
import com.ynov.hal.StdDraw;

// La classe "Bishop" HERITE des attributs et des méthodes de la classe "Piece" 
// grace au mot-clé "extends"
public class Bishop extends Piece {

	// La classe "Bishop" est obligée d'avoir un constructeur avec le paramètre
	// de type "ChessColor" car sa classe mère en a un avec cette signature.
	public Bishop(ChessColor color) {
		// Ici, la seule instruction est d'appeler le constructeur correspondant 
		// dans la classe mère (super-classe) 
		super(color);
	}

	// La méthode "display" est surchargée pour afficher le dessin du fou
	@Override
	public void display(int x, int y) {
		String image = this.color == ChessColor.BLACK ? 
				"images/Fn.png" : "images/Fb.png";
		StdDraw.picture(x + 0.5, y + 0.5, image, 1, 1);
	}

	// La méthode "toString" est surchargée pour retourner "Fn" ou "Fb"
	@Override
	public String toString() {
		// La couleur "n" ou "b" est retournée par la méthode "toString" de la
		// classe mère
		return "F" + super.toString();
	}
	
	@Override
	public boolean checkMove( int originX, int originY, int targetX, int targetY, boolean toKill) {
		return false;
	}

}
