package chess;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class King extends Piece {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public King(Field f,Board b,char c) {
		super(f,b,c);
		name="King";
		letter='K';
		String path;
		path="/"+name.toLowerCase()+"_"+col+".png";
		image=new ImageIcon(getClass().getResource(path));
	}
	@Override
	public void updateMoves() {
		moves.removeAll(moves);
		this.canMove(x, y,1,1,2);
		this.canMove(x, y, 1, -1,2);
		this.canMove(x, y, -1, -1,2);
		this.canMove(x, y, -1, 1,2);
		this.canMove(x, y, -1, 0,2);
		this.canMove(x, y, 1, 0,2);
		this.canMove(x, y, 0, 1,2);
		this.canMove(x, y, 0, -1,2);
		if(board.isCastleAvailable(this.col, 'J')&& !moved) {
			Move m=new Move(x,y,x+2,y,this);
			moves.add(m);
		}
		if(board.isCastleAvailable(this.col, 'B')&& !moved) {
			Move m=new Move(x,y,x-2,y,this);
			moves.add(m);
		}
		
	}
	public boolean move(int tX, int tY) {
		Move m=new Move(x,y,tX,tY,this);
		for(Move mo: moves) {
			if(m.equalsTo(mo)) {
				if(m.getToX()==x+2 && m.getToY()==y && moved==false) {
					board.castleKing(col, 'J');
					moved=true;
					board.changeActiveColor();
					return true;
				}
				if(m.getToX()==x-2 && m.getToY()==y && moved==false) {
					board.castleKing(col, 'B');
					moved=true;
					board.changeActiveColor();
					return true;
				}
				if(!board.getFieldByIndexes(8-tY, tX-1).getEmpty() && board.getFieldByIndexes(8-tY, tX-1).getPiece().getCol()!=this.col) {
					board.pieceCaptured(board.getFieldByIndexes(8-tY, tX-1).getPiece());
					board.getFieldByIndexes(8-tY, tX-1).removePiece(board.getFieldByIndexes(8-tY, tX-1).getPiece());
					board.getFieldByIndexes(8-y, x-1).removePiece(this);
					board.getFieldByIndexes(8-tY, tX-1).addPiece(this);
					board.changeActiveColor();
					moved=true;
					return true;
				} else {			
				board.getFieldByIndexes(8-y, x-1).removePiece(this);
				board.getFieldByIndexes(8-tY, tX-1).addPiece(this);
				board.changeActiveColor();
				moved=true;
				return true;
				}

				
			}
		}
		return false;
	};
	public ArrayList<Move> getMoves() {
		return moves;
	}
}
