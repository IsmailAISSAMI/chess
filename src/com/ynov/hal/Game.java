package com.ynov.hal;

import com.ynov.hal.pieces.Piece;

public class Game {

	public void play() {
		
		boolean isPlaying = true;
		
		// Structure de contrôle "while" permet de répéter le code tant que 
		// le test entre panthèses est vrai
		while (isPlaying) {

			Cell origin, target;
			
			origin = null;
			target = null;
			while (origin == null || target == null) {
				
				// "get" permet de retrouver la valeur associée à la clé "whites".
				//
				// Comme la valeur est de type "Object", on est obligé de réaliser
				// un TRANSTYPAGE (cast) avec la syntaxe "... (Player) ..." pour 
				// transformer la valeur de type "Object" en une valeur 
				// de type "Player"
				Player whites = (Player) Main.context.get("whites");
				
				System.out.println(whites.getName() + " choisit sa pièce...");
				origin = this.waitForPiece(ChessColor.WHITE);
				
				System.out.println("les blancs déplacent la pièce...");
				target = this.waitForTarget(origin);
			}

			this.movePiece(origin, target);
			
			origin = null;
			target = null;
			while (origin == null || target == null) {
				
				Player blacks = (Player) Main.context.get("blacks");
				System.out.println(blacks.getName() + " choisit sa pièce...");
				
				origin = this.waitForPiece(ChessColor.BLACK);
				System.out.println("les noirs déplacent la pièce...");
				target = this.waitForTarget(origin);
			}
			this.movePiece(origin, target);

		}
	}

	private void waitForClick() {
		while ( ! StdDraw.isMousePressed() ) {
			// La méthode "pause" permet de bloquer l'exécution pendant 100 ms
			// pour ne pas surcharger le processeur avec une boucle s'exécutant
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
		
		// Vérifie si le joueur a bien choisi une pièce, pas juste une case vide. 
		Piece piece = cell.getPiece();
		if(piece==null) {
			System.out.println("[ERROR] Aucune pièce n'était sélectionnée");
			return null;
		} else {
			System.out.println("[INFO] Pièce sélectionnée "+piece.toString());
		}
		
		// autorise que les pièces de la couleur passée en paramètre. 
		if(piece.getColor()==white) {
			cell.select();
			cell.setX(x);
			cell.setY(y);
			board.display();
			return cell;
		} 
		// Retourne null si ce n'est pas le cas.
		System.out.println("[ERROR] Choisissez une pièce "+white);
		return null;
	}

	private Cell waitForTarget(Cell origin) {
		this.waitForClick();
		int x = (int) StdDraw.mouseX();
		int y = (int) StdDraw.mouseY();
		Chessboard board = (Chessboard) Main.context.get("board");
		Cell cell = board.getCell(x,y);
		
		Piece cellTarget = cell.getPiece();
		boolean toKill = (cellTarget==null)?false:true;
		
		if(origin==null) {
			return null;
		} 
		// Nous ne pouvons pas déplacer la pièce à un endroit qui a une piece de la meme couleur
		else if (cell.getPiece()!=null && origin.getPiece().getColor() == cell.getPiece().getColor()) {
			System.out.println("[ERROR] Nous ne pouvons pas déplacer la pièce sur cette case car elle contient votre pièce.");
            return null;
        }
		else {
			boolean canMove = origin.getPiece().checkMove(origin.getX(), origin.getY(), x, y, toKill);
			if(canMove) {
				cell.select();
				board.display();
				return cell;
			} else {
				System.out.println("[ERROR] Faux mouvement.");
				return null;
			}
			
		}
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
