package com.ynov.hal.pieces;

import com.ynov.hal.ChessColor;
import com.ynov.hal.Chessboard;
import com.ynov.hal.StdDraw;

public class Knight extends Piece {

	public Knight(ChessColor color) {
		super(color);
	}

	@Override
	public void display(int x, int y) {
		String image = this.color == ChessColor.BLACK ? 
				"images/Cn.png" : "images/Cb.png";
		StdDraw.picture(x + 0.5, y + 0.5, image, 1, 1);
	}

	@Override
	public String toString() {
		return "C" + super.toString();
	}

	@Override
	public boolean checkMove( int originX, int originY, int targetX, int targetY, boolean toKill, Chessboard board) {
		int x = Math.abs(originX - targetX);
        int y = Math.abs(originY - targetY);
        System.out.println("[CALC] x="+x+", y="+y+", checkMove="+(x*y)+" - Knight");
        return x * y == 2;
	}
}
