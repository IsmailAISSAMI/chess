package com.ynov.hal.pieces;

import com.ynov.hal.Cell;
import com.ynov.hal.ChessColor;
import com.ynov.hal.Chessboard;
import com.ynov.hal.StdDraw;

public class Queen extends Piece {

	public Queen(ChessColor color) {
		super(color);
	}

	@Override
	public void display(int x, int y) {
		String image = this.color == ChessColor.BLACK ? 
				"images/Dn.png" : "images/Db.png";
		StdDraw.picture(x + 0.5, y + 0.5, image, 1, 1);
	}

	@Override
	public String toString() {
		return "D" + super.toString();
	}

	@Override
	public boolean checkMove( int originX, int originY, int targetX, int targetY, boolean toKill, Chessboard board) {
		int x = Math.abs(originX - targetX);
        int y = Math.abs(originY - targetY);
        Cell cell;
        // verification du mouvement horizontal et vertical de la Dame
        if(x!=0 && y==0) {
        	System.out.println("[INFO] Après la vérification des mouvements possibles par la tour.");
        	//verification d'obstacles (horizontalement).
        	if(originX<targetX) {
        		for(int i=originX+1;i<targetX;i++) {
        			cell = board.getCell(i,originY);
        			if(cell.getPiece()!=null) {
        				System.out.println("[ERROR] Votre toure est bloqué par une autre piece, précisément =="+cell.getPiece());
        				return false;
        			}
    			}
        	}
        	else { //originX>targetX
        		for(int i=originX-1;i>targetX;i--) {
        			cell = board.getCell(i,originY);
        			if(cell.getPiece()!=null) {
        				System.out.println("[ERROR] Votre toure est bloqué par une autre piece, précisément =="+cell.getPiece());
        				return false;
        			}
    			}
        	}
        	
        	return true;
        } else if(x==0 && y!=0) {
        	System.out.println("[INFO] Après la vérification des mouvements possibles par la tour.");
        	//verification d'obstacles (verticalement).
        	if(originY<targetY) {
        		for(int i=originY+1;i<targetY;i++) {
        			cell = board.getCell(originX,i);
        			if(cell.getPiece()!=null) {
        				System.out.println("[ERROR] Votre toure est bloqué par une autre piece, précisément =="+cell.getPiece());
        				return false;
        			}
    			}
        	}
        	else { //originY>targetY
        		for(int i=originY-1;i>targetY;i--) {
        			cell = board.getCell(originX,i);
        			if(cell.getPiece()!=null) {
        				System.out.println("[ERROR] Votre toure est bloqué par une autre piece, précisément =="+cell.getPiece());
        				return false;
        			}
    			}
        	}
        	
        	return true;
        }
        
        int i,j;
        // Mouvement en diagonale de la Dame.
        if(x==y) { 
        	while(originX!=targetX && originY!=targetY && originX<8 && originY<8 && originX>=0 && originY>=0) {
        		if(originX<targetX) {
        			originX++;
        		} 
 
        		if(originX>targetX) {
        			originX--;
        		}

        		if(originY<targetY) {
        			originY++;
        		}
        		
        		if(originY>targetY) {
        			originY--;
        		}
        		
        		cell = board.getCell(originX,originY);
        		if(cell.getPiece()!=null) {
        			System.out.println("[ERROR] Votre Dame est bloqué par une autre piece pendant son mouvement sur le diag, précisément par =="+cell.getPiece());
        			return false;
        		}
        		
        	}
        	return true;
        	
        }
        
		return false;
	}
}
