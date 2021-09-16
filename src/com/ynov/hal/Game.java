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
			while (origin == null || target == null) {
				
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
				target = this.waitForTarget(origin);
				
			}

			this.movePiece(origin, target);
			
			origin = null;
			target = null;
			while (origin == null || target == null) {
				
				Player blacks = (Player) Main.context.get("blacks");
				System.out.println(blacks.getName() + " choisit sa pi�ce...");
				
				origin = this.waitForPiece(ChessColor.BLACK);
				System.out.println("les noirs d�placent la pi�ce...");
				target = this.waitForTarget(origin);
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
		
		// V�rifie si le joueur a bien choisi une pi�ce, pas juste une case vide. 
		Piece piece = cell.getPiece();
		if(piece==null) {
			System.out.println("[ERROR] Aucune pi�ce n'�tait s�lectionn�e");
			return null;
		} else {
			System.out.println("[INFO] Pi�ce s�lectionn�e "+piece.toString());
		}
		
		// autorise que les pi�ces de la couleur pass�e en param�tre. 
		if(piece.getColor()==white) {
			cell.select();
			cell.setX(x);
			cell.setY(y);
			board.display();
			return cell;
		} 
		// Retourne null si ce n'est pas le cas.
		System.out.println("[ERROR] Choisissez une pi�ce "+white);
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
		// Nous ne pouvons pas d�placer la pi�ce � un endroit qui a une piece de la meme couleur
		else if (cell.getPiece()!=null && origin.getPiece().getColor() == cell.getPiece().getColor()) {
			System.out.println("[ERROR] Nous ne pouvons pas d�placer la pi�ce sur cette case car elle contient votre pi�ce.");
            return null;
        }
		else {
			
			boolean canMove = origin.getPiece().checkMove(origin.getX(), origin.getY(), x, y, toKill, board);
			
			if(canMove) {
				cell.select();
				board.display();
				// Test
				isKingCheck(origin.getPiece().getColor(), board);
				return cell;
			} else {
				System.out.println("[ERROR] Faux mouvement.");
				return null;
			}
			
		}
	}
	
	// V�rification si le roi est menac� par une pi�ce d'adversaire.
	private boolean isKingCheck(ChessColor white, Chessboard board) {
		Cell lambda=null, bk=null, wk=null; // bk= black king, wk= white king
		int xb=-1, yb=-1, xw=-1, yw=-1, i,j; // si la position des roi est toujours �gale � -1 �a veut dire que j'ai une erreur.
		boolean canMove;
		// Chercher la position des rois sur le plateau du jeu.
		System.out.println("[INFO] Recherche des positions des rois.");
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				lambda = board.getCell(x,y);
				if(lambda.getPiece()!=null) {
					if(lambda.getPiece().toString().equals("Rb")) {
						System.out.println("[INFO] Le roi blanc est trouv�.");
						wk= board.getCell(x,y);
						xw= x;
						yw= y;
					} else if (lambda.getPiece().toString().equals("Rn")) {
						System.out.println("[INFO] Le roi noir est trouv�.");
						bk= board.getCell(x,y);;
						xb= x;
						yb= y;
					}
				}
			}
		}
		System.out.println("[INFO] Le roi blanc est � la position X="+xw+" y="+yw+".");
		System.out.println("[INFO] Le roi noir est � la position X="+xb+" y="+yb+".");

		if(white==ChessColor.WHITE) { // Le cas du roi blanc sous menace. 
			System.out.println("[INFO] V�rification si le Roi BLANC est sous menace.");
			
		}
		else if (white==ChessColor.BLACK) { // Le cas du roi noir sous menace.
			System.out.println("[INFO] V�rification si le Roi NOIR est sous menace.");
			
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					lambda = board.getCell(x,y);
					if(lambda.getPiece()!=null && lambda.getPiece().getColor()==ChessColor.BLACK) {
						// V�rifier si un Pawn noir menace le roi.
						if(lambda.getPiece().toString().equals("Pn")) {
							// V�rifier si le roi est en position Kill du adversaire.
							System.out.println("[INFO] V�rification si le PAWN � la pos=("+x+", "+y+") menace le roi � la pos=("+xw+", "+yw+").");
							/*canMove = lambda.getPiece().checkMove(x, y, xw, yw, true, board);
							if(canMove==true) {
								System.out.println("[RESULT] "+canMove);	
							}*/
							if((x-1==xw && y-1==yw) || (x+1==xw && y-1==yw)) {
								System.out.println("[RESULT] TRUE!!!!!!!!");
							}
						}
					}
				}
			}	
		}
		return false;
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
