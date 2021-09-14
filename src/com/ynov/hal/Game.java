package com.ynov.hal;

import com.ynov.hal.pieces.Piece;

public class Game {

	public void play() {
		
		boolean isPlaying = true;
		
		// Structure de contr�le "while" permet de r�p�ter le code tant que 
		// le test entre panth�ses est vrai
		while (isPlaying) {

			Cell origin, target;
			
			origin = null;
			target = null;
			while (target == null) {
				
				// "get" permet de retrouver la valeur associ�e � la cl� "whites".
				//
				// Comme la valeur est de type "Object", on est oblig� de r�aliser
				// un TRANSTYPAGE (cast) avec la syntaxe "... (Player) ..." pour 
				// transformer la valeur de type "Object" en une valeur 
				// de type "Player"
				Player whites = (Player) Main.context.get("whites");
				
				System.out.println(whites.getName() + " choisit sa pi�ce...");
				origin = this.waitForPiece(ChessColor.WHITE);
				
				System.out.println("les blancs d�placent la pi�ce...");
				target = this.waitForTarget();
			}

			this.movePiece(origin, target);
			
			origin = null;
			target = null;
			while (target == null) {
				Player blacks = (Player) Main.context.get("blacks");
				System.out.println(blacks.getName() + " choisit sa pi�ce...");
				origin = this.waitForPiece(ChessColor.BLACK);
				System.out.println("les noirs d�placent la pi�ce...");
				target = this.waitForTarget();
			}
			this.movePiece(origin, target);

		}
	}

	private void waitForClick() {
		while ( ! StdDraw.isMousePressed() ) {
			// La m�thode "pause" permet de bloquer l'ex�cution pendant 100 ms
			// pour ne pas surcharger le processeur avec une boucle s'ex�cutant
			// trop vite
			StdDraw.pause(100);
		}
		while ( StdDraw.isMousePressed() ) {
			StdDraw.pause(100);
		}
		
	}

	private Cell waitForPiece(ChessColor white) {
		this.waitForClick();
		int x = (int) StdDraw.mouseX();
		int y = (int) StdDraw.mouseY();
		Chessboard board = (Chessboard) Main.context.get("board");
		Cell cell = board.getCell(x,y);
		
		Piece piece = cell.getPiece();
		System.out.println("peice selected"+piece.toString());
		if(piece.getColor()==white) {
			cell.select();
			board.display();
			return cell;
		} 
		return cell;
	}

	private Cell waitForTarget() {
		this.waitForClick();
		int x = (int) StdDraw.mouseX();
		int y = (int) StdDraw.mouseY();
		Chessboard board = (Chessboard) Main.context.get("board");
		Cell cell = board.getCell(x,y);
		cell.select();
		board.display();
		return cell;
	}

	private void movePiece(Cell origin, Cell target) {
		target.setPiece(origin.getPiece());
		origin.setPiece(null);
		origin.unselect();
		target.unselect();
		Chessboard board = (Chessboard) Main.context.get("board");
		board.display();
	}
}
