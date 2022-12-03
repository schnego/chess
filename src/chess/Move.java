package chess;



public class Move {
protected int fromX;
protected int fromY;
protected int toX;
protected int toY;
Piece piece;
public Move(int fX,int fY, int tX, int tY,Piece p) {
	fromX=fX;
	fromY=fY;
	toX=tX;
	toY=tY;
	piece=p;
}
public String toString() {
	String letters="ABCDEFGH";
	return letters.charAt(toX-1)+""+toY+"("+toX+""+toY+")";
}
public int getFromX() {
	return fromX;
}
public void setFromX(int fromX) {
	this.fromX = fromX;
}
public int getFromY() {
	return fromY;
}
public void setFromY(int fromY) {
	this.fromY = fromY;
}
public int getToX() {
	return toX;
}
public void setToX(int toX) {
	this.toX = toX;
}
public int getToY() {
	return toY;
}
public void setToY(int toY) {
	this.toY = toY;
}
public Piece getPiece() {
	return piece;
}
public void setPiece(Piece piece) {
	this.piece = piece;
}

public boolean equalsTo(Move o) {
	if(toX == o.getToX() && toY == o.getToY()) {
		return true;
	}
	return false;
}
}
