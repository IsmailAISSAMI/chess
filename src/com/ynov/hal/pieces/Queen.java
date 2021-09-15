package com.ynov.hal.pieces;

import com.ynov.hal.ChessColor;
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
	public boolean checkMove( int originX, int originY, int targetX, int targetY, boolean toKill) {
		return false;
	}
}
