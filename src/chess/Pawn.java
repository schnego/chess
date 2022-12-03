package chess;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Pawn extends Piece {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Pawn(Field f,Board b ,char c) {
		super(f,b,c);
		name="Pawn";
		letter='P';
		String path;
		path="/"+name.toLowerCase()+"_"+col+".png";
		image=new ImageIcon(getClass().getResource(path));
	}
	@Override
	public void updateMoves() {
		moves.removeAll(moves);
		if(col=='W') {
			if(board.getFieldByIndexes(8-y-1, x-1)!=null) {
				if(board.getFieldByIndexes(8-y-1, x-1).getEmpty()) {
					this.canMove(x, y, 0, 1,2);
					}
				}
			if(board.getFieldByIndexes(8-y-2, x-1)!=null) {
				if(board.getFieldByIndexes(8-y-2, x-1).getEmpty() && y==2) {
					this.canMove(x, y, 0, 1,3);
				}
			}
			if(board.getFieldByIndexes(8-y-1, x)!=null) {
				if(!board.getFieldByIndexes(8-y-1, x).getEmpty()&& board.getFieldByIndexes(8-y-1, x).getPiece().getCol()!=col) {
					this.canMove(x, y, 1, 1,2);
				}
			}
			if(board.getFieldByIndexes(8-y-1, x-2)!=null) {
				if(!board.getFieldByIndexes(8-y-1, x-2).getEmpty()&& board.getFieldByIndexes(8-y-1, x-2).getPiece().getCol()!=col) {
					this.canMove(x, y, -1, 1,2);
				}
			}
			}
		if(col=='B') {
			if(board.getFieldByIndexes(8-y+1, x-1)!=null) {
				if(board.getFieldByIndexes(8-y+1, x-1).getEmpty())this.canMove(x, y, 0, -1,2);
				}
			if(board.getFieldByIndexes(8-y+2, x-1)!=null) {
				if(board.getFieldByIndexes(8-y+2, x-1).getEmpty() && y==7) {
					this.canMove(x, y, 0, -1,3);
				}
			}
			if(board.getFieldByIndexes(8-y+1, x)!=null) {
				if(!board.getFieldByIndexes(8-y+1, x).getEmpty()&& board.getFieldByIndexes(8-y+1, x).getPiece().getCol()!=col) {
					this.canMove(x, y, 1, -1,2);
					}
			}
				if(board.getFieldByIndexes(8-y+1, x-2)!=null) {
					if(!board.getFieldByIndexes(8-y+1, x-2).getEmpty()&& board.getFieldByIndexes(8-y+1, x-2).getPiece().getCol()!=col) {
						this.canMove(x, y, -1, -1,2);
						}
				}
		
			}
		}
	public ArrayList<Move> getMoves() {
		return moves;
	}
	}
