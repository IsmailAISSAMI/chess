package com.ynov.hal.pieces;

import com.ynov.hal.ChessColor;
import com.ynov.hal.Chessboard;
import com.ynov.hal.StdDraw;

public class King extends Piece {

	public King(ChessColor color) {
		super(color);
	}

	@Override
	public void display(int x, int y) {
		String image = this.color == ChessColor.BLACK ? 
				"images/Rn.png" : "images/Rb.png";
		StdDraw.picture(x + 0.5, y + 0.5, image, 1, 1);
	}

	@Override
	public String toString() {
		return "R" + super.toString();
	}

	@Override
	public boolean checkMove( int originX, int originY, int targetX, int targetY, boolean toKill, Chessboard board) {
		int x = Math.abs(originX - targetX);
        int y = Math.abs(originY - targetY);
        
        // Le roi peut se déplacer par une case dans toutes les directions.
        // Faut vérifier qu'il sera pas en cas d'exhec et mat
        if((x<2 && y<2) && (x==1 || y==1)) {
            return true;
        }
        
        
    	return false;
	}
}
