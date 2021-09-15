package com.ynov.hal.pieces;

import com.ynov.hal.ChessColor;
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
	public boolean checkMove( int originX, int originY, int targetX, int targetY, boolean toKill) {
		int x = Math.abs(originX - targetX);
        int y = Math.abs(originY - targetY);
        int specialCase = Math.abs(originY - targetY);
        
        // Cas spéciale, puisque au début de la partie, nous avons la possibilité d'avancer avec 2 cases ou 1 seule
        // selon le choix du joueur. 
        System.out.println("[CALC] x="+x+", y="+y+" - Pawn");
        if(specialCase==2  && (originY==1||originY==6)) {
        	return true;
        }
        
        // avancement d'une seule case (CAS NORMALE)
        else if(y==1 && x==0) {
        	return true;
        }
        
        return false;
	}

}
