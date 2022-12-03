package chess;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Knight extends Piece {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Knight(Field f,Board b,char c) {
		super(f,b,c);
		name="horse";
		letter='N';
		String path;
		path="/"+name.toLowerCase()+"_"+col+".png";
		image=new ImageIcon(getClass().getResource(path));
	}
	@Override
	public void updateMoves() {
		moves.removeAll(moves);
		this.canMove(x, y,2,1,2);
		this.canMove(x, y, 2, -1,2);
		this.canMove(x, y, -2, 1,2);
		this.canMove(x, y, -2, -1,2);
		this.canMove(x, y, -1, -2,2);
		this.canMove(x, y, -1, 2,2);
		this.canMove(x, y, 1, 2,2);
		this.canMove(x, y, 1, -2,2);
	}
	public ArrayList<Move> getMoves() {
		return moves;
	}
}
