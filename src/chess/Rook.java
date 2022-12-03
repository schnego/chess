package chess;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Rook extends Piece {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Rook(Field f,Board b,char c) {
		super(f,b,c);
		name="Rook";
		letter='R';
		String path;
		path="/"+name.toLowerCase()+"_"+col+".png";
		image=new ImageIcon(getClass().getResource(path));
	}
	@Override
	public void updateMoves() {
		moves.removeAll(moves);
		this.canMove(x, y, -1, 0,8);
		this.canMove(x, y, 1, 0,8);
		this.canMove(x, y, 0, 1,8);
		this.canMove(x, y, 0, -1,8);
	}
	public ArrayList<Move> getMoves() {
		return moves;
	}
}
