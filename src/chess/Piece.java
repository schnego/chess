package chess;
import java.util.ArrayList;
import java.awt.*;
import java.io.Serializable;

import javax.swing.*;
public abstract class Piece implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected char letter;
	protected char col;
	protected String name;
	protected int x;
	protected int y;
	protected Board board;
	protected Field field;
	protected boolean alive;
	protected boolean moved;
	protected ArrayList<Move> moves;
	ImageIcon image;
	public Piece(Field f, Board b,char c) {
		moves=new ArrayList<>();
		field=f;
		board=b;
		col=c;
		x=field.getX();
		y=field.getY();
		alive=true;
		
	}
	public boolean move(int tX, int tY) {
		Move m=new Move(x,y,tX,tY,this);
		for(Move mo: moves) {
			if(m.equalsTo(mo)) {
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
	public void setBoard(Board b) {
	board=b;
	}
	public void setField(Field f) {
	field=f;
	}
	public void canMove(int tX,int tY, int posA, int posB,int seq) {
		if(tX<=8 && tX>0 && tY<=8 && tY>0 && seq>0) {
			seq--;
			Move m=new Move(x,y,tX,tY,this);
			if((m.getFromX()==m.getToX() && m.getFromY()==m.getToY())||moves.contains(m));
			else {moves.add(m);}
			tX=tX+posA;
			tY=tY+posB;
			if(board.getFieldByIndexes(8-tY, tX-1)!=null) {
				if(board.getFieldByIndexes(8-tY, tX-1).getEmpty()) {
					canMove(tX,tY,posA,posB,seq);
					} else if(board.getFieldByIndexes(8-tY, tX-1).getPiece().getCol()!=this.getCol() && seq>=1) {moves.add(new Move(x,y,tX,tY,this)); return;}
				}
		}
		
	}
	public void updateMoves() {
		
	}
	@SuppressWarnings("static-access")
	public void showMoves(Graphics g) {
		if(!moves.isEmpty()) {
			for (Move m : moves) {
				g.setColor(new Color(130,0,0));
				g.fillOval((m.getToX()-1)*field.getSize()+30, 800-m.getToY()*field.getSize()+30, 40, 40);
				g.drawRect((x-1)*field.getSize()+5, 800-y*field.getSize()+5, field.getSize()-10, field.getSize()-10);
			}
		}
	}

	public void printMoves() {
		System.out.println("The given piece "+this.name+" at "+x+this.field.getY()+" can move to:");
		for(Move m : moves) {System.out.println(m);}
	}
	@SuppressWarnings("static-access")
	public void draw(Graphics g) {
		g.drawImage(image.getImage(), ((field.getX()-1)*field.getSize())+5, 800-(field.getY()*field.getSize())+5, null);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public char getCol() {
		return col;
	}
	public ArrayList<Move> getMoves() {
		return moves;
	}
	public Board getBoard() {
		return board;
	}
	public Field getField() {
		return field;
	}
	public void setMoves(ArrayList<Move> moves) {
		this.moves = moves;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public void setCol(char col) {
		this.col = col;
	}
	public char getLetter() {
		return letter;
	}
	public void setLetter(char letter) {
		this.letter = letter;
	}
	public boolean isMoved() {
		return moved;
	}
	public void setMoved(boolean moved) {
		this.moved = moved;
	}
	
	
}
