package chess;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bishop extends Piece {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Bishop(Field f,Board b,char c) {
	super(f,b,c);
	name="Bishop";
	letter='B';
	String path;
	path="/"+name.toLowerCase()+"_"+col+".png";
	image=new ImageIcon(getClass().getResource(path));
}
@Override
public void updateMoves() {
	moves.removeAll(moves);
	this.canMove(x, y,1,1,8);
	this.canMove(x, y, 1, -1,8);
	this.canMove(x, y, -1, -1,8);
	this.canMove(x, y, -1, 1,8);
}

public ArrayList<Move> getMoves() {
	return moves;
}
}
