package com.ynov.hal.pieces;

import com.ynov.hal.ChessColor;
import com.ynov.hal.Chessboard;
import com.ynov.hal.StdDraw;

public class Pawn extends Piece {

	public Pawn(ChessColor color) {
		super(color);
	}

	@Override
	public void display(int x, int y) {
		String image = this.color == ChessColor.BLACK ? 
				"images/Pn.png" : "images/Pb.png";
		StdDraw.picture(x + 0.5, y + 0.5, image, 1, 1);
	}

	@Override
	public String toString() {
		return "P" + super.toString();
	}
	
	@Override
	public boolean checkMove( int originX, int originY, int targetX, int targetY, boolean toKill, Chessboard board) {
		int x = Math.abs(originX - targetX);
        int y = originY - targetY;
        int specialCase = Math.abs(originY - targetY);
        
        if(toKill==true) {
        	if(Math.abs(y)==1 && x==1) {
            	return true;
            }
        }
        else {
        	// Cas spéciale, puisque au début de la partie, nous avons la possibilité d'avancer avec 2 cases ou 1 seule
            // selon le choix du joueur. 
            // System.out.println("[CALC] x="+x+", y="+y+" - Pawn");
            if(specialCase==2  && (originY==1||originY==6)) {
            	return true;
            }
            // avancement d'une seule case (CAS NORMALE), pas de retour en arrière.
            else if(y==-1 && x==0 && this.getColor()==ChessColor.WHITE) {
            	return true;
            }
            else if(y==1 && x==0 && this.getColor()==ChessColor.BLACK) {
            	return true;
            }
        }
    	return false;
	}

}
