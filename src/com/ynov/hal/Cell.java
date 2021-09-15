package com.ynov.hal;

import com.ynov.hal.pieces.Piece;

// La classe Cell "implémente" l'interface "Selectable". Elle doit donc fournir
// un code pour toutes les méthodes de "Selectable".
public class Cell implements Selectable {

	private boolean selected;
	
	private Piece piece;
	
	// La couleur de la case peut être, au choix, blanche ou noire. Pour
	// choisir parmi ces deux possibilités spécifiques et uniquement celle-ci,
	// le type "CellColor" a été créé. 
	private ChessColor color;
	private int x;
	private int y;
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Piece getPiece() {
		return this.piece;
	}

	public Cell(ChessColor color) {
		this.color = color;
		
		// L'attribut "piece" n'a pas de valeur reçue en paramètre mais on lui
		// donne quand même une valeur "null" indiquant que la case est vide
		// lors de sa création.
		this.piece = null;
		
		this.selected = false;
	}
	
	public void display(int x, int y) {
		if (this.color == ChessColor.BLACK) {
			StdDraw.setPenColor(StdDraw.DARK_GRAY);
		} else {
			StdDraw.setPenColor(StdDraw.BOOK_RED);
		}
		StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5 );
		
		if (this.isSelected()) {
			StdDraw.setPenColor(StdDraw.BOOK_BLUE);
			StdDraw.filledSquare(x + 0.5, y + 0.5, 0.4 );
		}
		
		if (this.piece != null) {
			this.piece.display(x,y);
		}
	}

	// Implémentation des méthodes de l'interface "Selectable". Pour  
	// fonctionner,  cette implémentation a nécessité de créer un nouvel
	// attribut "selected" qu'il a fallu initialiser à "fasle" dans le
	// constructeur.
	
	@Override
	public void select() {
		this.selected = true;
	}

	@Override
	public void unselect() {
		this.selected = false;
	}

	@Override
	public boolean isSelected() {
		return this.selected;
	}
	
}
