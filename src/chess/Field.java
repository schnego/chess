package chess;
import java.awt.*;
import java.io.Serializable;

public class Field implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Piece piece;
	private char let;
	private int num;
	protected int x;
	protected int y;
	private char col;
	private boolean isEmpty;
	private static int size=100;
	String letters="ABCDEFGH";
	public Field(int tX,int tY,char ch) {
		x=tX;
		let=letters.charAt(tX-1);
		y=tY;
		piece=null;
		isEmpty=true;
		col=ch;
	}
	public void draw(Graphics g) {
		if(col=='B')g.setColor(new Color(115,150,86));
		else g.setColor(new Color(238,238,210));
		g.fillRect((x-1)*size, 800-y*size, size, size);
		if(!isEmpty) piece.draw(g);
		
	}
	public static int getSize() {
		return size;
	}
	public Piece getPiece() {
		return piece;
	}
	public void addPiece(Piece p) {
		piece=p;
		p.setField(this);
		p.setX(x);
		p.setY(y);
		p.setAlive(true);
		isEmpty=false;
	}
	public void removePiece(Piece p) {
		piece=null;
		isEmpty=true;
		p.setAlive(false);
		p.setField(null);
	}
	public char getLet() {
		return let;
	}
	public int getNum() {
		return num;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean getEmpty() {return isEmpty;}
	
}
