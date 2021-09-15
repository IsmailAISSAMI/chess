package com.ynov.hal.pieces;

import com.ynov.hal.ChessColor;
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
	public boolean checkMove( int originX, int originY, int targetX, int targetY, boolean toKill) {
		return false;
	}
}
