package chess;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.*;

import java.util.ArrayList;
public class Board implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String letters="ABCDEFGH";
private Field[][] tabla;
private Field selected;
private ArrayList<Piece> WPieces=new ArrayList<>();
private ArrayList<Piece> BPieces=new ArrayList<>();
private char activeColor;
private BoardLogger log=new BoardLogger();
ImageIcon bw=new ImageIcon(getClass().getResource("/black_wins.png"));
ImageIcon ww=new ImageIcon(getClass().getResource("/white_wins.png"));
ImageIcon sm=new ImageIcon(getClass().getResource("/stale_mate.png"));
ImageIcon ch=new ImageIcon(getClass().getResource("/check.png"));
public Board() {
	char ch;
	tabla=new Field[8][8];
	for(int i=0;i<8;i++) {
		for(int j=0;j<8;j++) {
			if((i + j) % 2 == 1) ch='B';
			else ch='W';
			this.tabla[i][j]=new Field(j+1,8-i,ch);
		}
	}
initBoardv2();
activeColor='W';
}
public Board(boolean ures) {
	char ch;
	tabla=new Field[8][8];
	for(int i=0;i<8;i++) {
		for(int j=0;j<8;j++) {
			if((i + j) % 2 == 1) ch='B';
			else ch='W';
			this.tabla[i][j]=new Field(j+1,8-i,ch);
		}
	}
	activeColor='W';
}
public void addPieceToBoard(int r, int c, char bjel, char szin) {
	if(r>=0 || r<8 || c>=0 || c<8) {
		if(tabla[r][c].getEmpty()) {
		switch(bjel){
		case 'R':
			Piece p=new Rook(tabla[r][c],this,szin);
			tabla[r][c].addPiece(p);
			if(szin=='W') WPieces.add(p);
			if(szin=='B') BPieces.add(p);
			break;
		case 'N':
			Piece p1=new Knight(tabla[r][c],this,szin);
			tabla[r][c].addPiece(p1);
			if(szin=='W') WPieces.add(p1);
			if(szin=='B') BPieces.add(p1);
			break;
		case 'B':
			Piece p2=new Bishop(tabla[r][c],this,szin);
			tabla[r][c].addPiece(p2);
			if(szin=='W') WPieces.add(p2);
			if(szin=='B') BPieces.add(p2);
			break;
		case 'K':
			Piece p3=new King(tabla[r][c],this,szin);
			tabla[r][c].addPiece(p3);
			if(szin=='W') WPieces.add(p3);
			if(szin=='B') BPieces.add(p3);
			break;
		case 'Q':
			Piece p4=new Queen(tabla[r][c],this,szin);
			tabla[r][c].addPiece(p4);
			if(szin=='W') WPieces.add(p4);
			if(szin=='B') BPieces.add(p4);
			break;
		case 'P':
			Piece p5=new Pawn(tabla[r][c],this,szin);
			tabla[r][c].addPiece(p5);
			if(szin=='W') WPieces.add(p5);
			if(szin=='B') BPieces.add(p5);
			break;
					}
		}
	}
}

public void initBoardv2() {
	String pieces="RNBQKBNR";
	for(int i=0;i<8;i++) {
		addPieceToBoard(0,i,pieces.charAt(i),'B');
		addPieceToBoard(1,i,'P','B');
		addPieceToBoard(6,i,'P','W');
		addPieceToBoard(7,i,pieces.charAt(i),'W');
	}
}

public void printBoard() {
	for(int i=0;i<8;i++) {
		for(int j=0;j<8;j++) {
			if(tabla[i][j].getPiece()!=null) {
				System.out.print(tabla[i][j].getPiece().getCol());
				System.out.print(tabla[i][j].getPiece().getLetter());
				System.out.print(' ');
			}
			else {
				System.out.print(tabla[i][j].getX());
				System.out.print(tabla[i][j].getY());
				System.out.print(' ');
				}
			if(j%7==0 && j!=0)System.out.print('\n');
		}
	}
}

public Field getFieldByIndexes(int x, int y) {
	if(x<0 || x>7 || y<0 || y>7) return null;
	return tabla[x][y];
}
public boolean isCastleAvailable(char szin, char irany) {
	Piece temp=this.findKing(szin).getPiece();
	int x=temp.getX();
	int y=temp.getY();
	if(!temp.isMoved() && temp.getX()==5) {
		if(irany=='B') {
			if(this.getFieldByIndexes(8-y, x-2).getEmpty() &&
					this.getFieldByIndexes(8-y, x-3).getEmpty() &&
					this.getFieldByIndexes(8-y, x-4).getEmpty()&&
					!this.getFieldByIndexes(8-y, x-5).getEmpty()) {
				if(!this.getFieldByIndexes(8-y, x-5).getPiece().isMoved()) return true;
			}
		}
		else if(irany=='J') {
			if(this.getFieldByIndexes(8-y, x).getEmpty() &&
					this.getFieldByIndexes(8-y, x+1).getEmpty() &&
					!this.getFieldByIndexes(8-y, x+2).getEmpty()) {
				if(!this.getFieldByIndexes(8-y, x+2).getPiece().isMoved()) {
					return true;
				}
			}
		}
	}
	return false;
}
public void castleKing(char szin,char irany) {
	Piece tempK=this.findKing(szin).getPiece();
	int x=tempK.getX();
	int y=tempK.getY();
	Piece tempR;
	if(irany=='B') {
		tempR=this.getFieldByIndexes(8-y, x-5).getPiece();
		this.movePieceTo(tempR.getX()+3, tempR.getY(), tempR);
		this.movePieceTo(tempK.getX()-2, tempK.getY(), tempK);
	}
	else if(irany=='J') {
		tempR=this.getFieldByIndexes(8-y, x+2).getPiece();
		this.movePieceTo(tempR.getX()-2, tempR.getY(), tempR);
		this.movePieceTo(tempK.getX()+2, tempK.getY(), tempK);
	}
	
}
public void draw(Graphics g) {
	for(int i=0;i<8;i++) {
		for(int j=0;j<8;j++) {
			tabla[i][j].draw(g);
			}
		}
	if(selected!=null && !selected.getEmpty()) {
	selected.getPiece().showMoves(g);	
	}
	this.promotePawns();
	if(this.inCheck(activeColor)) {
		if(this.checkMate(g)) {try {
			log.serializeBoardLog();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		else {
			g.drawImage(ch.getImage(), 200, 200, null);
		}
	}
}
public void drawReplay(Graphics g) {
	for(int i=0;i<8;i++) {
		for(int j=0;j<8;j++) {
			tabla[i][j].draw(g);
			}
		}
}
public boolean moveSelected(int x, int y) {
	if(tabla[8-selected.getY()][selected.getX()-1].getPiece().move(x+1, 8-y))return true;
	else {
		selectField(x,y);
		return false;
	}
}
public boolean selectField(int x, int y) {
	if(!tabla[y][x].getEmpty()) { 
	if(tabla[y][x].getPiece().getCol()==activeColor) {
		selected=tabla[y][x];
		validateMoves(selected.getPiece());
		return true;
		}
	}
	return false;
}
public void promotePawns() {
	for(int i=0;i<8;i++) {
			if(!tabla[0][i].getEmpty()) {
				if(tabla[0][i].getPiece().getName()=="Pawn" && tabla[0][i].getPiece().getCol()=='W') {
					this.pieceCaptured(tabla[0][i].getPiece());
					tabla[0][i].removePiece(tabla[0][i].getPiece());
					this.addPieceToBoard(0, i, 'Q', 'W');
					}
				}
			if(!tabla[7][i].getEmpty()) {
				if(tabla[7][i].getPiece().getName()=="Pawn" && tabla[7][i].getPiece().getCol()=='B') {
					this.pieceCaptured(tabla[7][i].getPiece());
					tabla[7][i].removePiece(tabla[7][i].getPiece());
					this.addPieceToBoard(7, i, 'Q', 'B');
					}
				}
			}

}
public boolean checkMate(Graphics g) {
	ArrayList<Move> totalMovesBlack=new ArrayList<>();
	ArrayList<Move> totalMovesWhite=new ArrayList<>();
	for(Piece p:BPieces) {
		validateMoves(p);
		totalMovesBlack.addAll(p.getMoves());
		if(!totalMovesBlack.isEmpty()) break;
	}
	
	for(Piece p:WPieces) {
		validateMoves(p);
		totalMovesWhite.addAll(p.getMoves());
		if(!totalMovesWhite.isEmpty()) break;
	}
	if(totalMovesWhite.isEmpty() && totalMovesBlack.isEmpty()) {
		g.drawImage(sm.getImage(), 200, 200, null);
		return true;
	} else if(totalMovesBlack.isEmpty()) {
		g.drawImage(ww.getImage(), 200, 200, null);
		return true;
	} else if(totalMovesWhite.isEmpty()) {
		g.drawImage(bw.getImage(), 200, 200, null);
		return true;
	}
	return false;
}
public void changeActiveColor() {
	if(activeColor=='W') activeColor='B';
	else activeColor='W';
	log.addBoardPosToLog(this);
}
public void pieceCaptured(Piece p) {
	if(p.getCol()=='W') WPieces.remove(p);
	else BPieces.remove(p);
}
public Field findKing(char jel) {
	for(int i=0;i<8;i++) {
		for(int j=0;j<8;j++) {
			if(!tabla[i][j].getEmpty()) {
				if(tabla[i][j].getPiece().getName()=="King"&& tabla[i][j].getPiece().getCol()==jel) {
					return tabla[i][j];
				}
			}
		}
	}	
return null;
}
public void wipeBoard() {
	for(int i=0;i<8;i++) {
		for(int j=0;j<8;j++) {
			if(!tabla[i][j].getEmpty()) {
				pieceCaptured(tabla[i][j].getPiece());
				tabla[i][j].removePiece(tabla[i][j].getPiece());
			}
		}
	}
}
public void copyBoardTo(Board b) {
	for(int i=0;i<8;i++) {
		for(int j=0;j<8;j++) {
			if(!tabla[i][j].getEmpty()) {
				b.addPieceToBoard(i, j, tabla[i][j].getPiece().getLetter(), tabla[i][j].getPiece().getCol());
				b.getFieldByIndexes(i, j).getPiece().setMoved(tabla[i][j].getPiece().isMoved());
			}
		}
	}
	b.setActiveColor(activeColor);
}
public boolean inCheck(char szin) {
	Field f=this.findKing(szin);
	ArrayList<Move> mo=new ArrayList<>();
	if(szin=='W') {
		for(Piece p : BPieces) {
			if(p.isAlive()) {
				validateMoves(p);
				mo=p.getMoves();
					for(Move m:mo) {
						if(f.getX()==m.getToX() && f.getY()== m.getToY()) {
							return true;}
					}
			}
		}
	}
	else if(szin=='B') {
		for(Piece p : WPieces) {
			if(p.isAlive()) {
				validateMoves(p);
				mo=p.getMoves();
				for(Move m:mo) {
					if(f.getX()==m.getToX() && f.getY()== m.getToY()) {
					return true;
					}
				}
			}
		}
	}
	return false;
}
public void movePieceTo(int x, int y, Piece p) {
	if(this.getFieldByIndexes(8-y, x-1)!=null) {
		if(!this.getFieldByIndexes(8-y, x-1).getEmpty()) {
			pieceCaptured(this.getFieldByIndexes(8-y, x-1).getPiece());
			this.getFieldByIndexes(8-y, x-1).removePiece(this.getFieldByIndexes(8-y, x-1).getPiece());
			p.getField().removePiece(p);
			this.getFieldByIndexes(8-y, x-1).addPiece(p);
		}
		else {
			p.getField().removePiece(p);
			this.getFieldByIndexes(8-y, x-1).addPiece(p);
		}
	}
}
public void validateMoves(Piece p) {
p.updateMoves();
ArrayList<Move> moves=new ArrayList<>();

ArrayList<Move> badMoves=new ArrayList<>();

Board temp=new Board(true);
this.copyBoardTo(temp);

Piece tempPiece=temp.getFieldByIndexes(8-p.getY(), p.getX()-1).getPiece();
tempPiece.updateMoves();
moves=tempPiece.getMoves();
	for(Move m: moves) {
		this.copyBoardTo(temp);
		tempPiece=temp.getFieldByIndexes(8-p.getY(), p.getX()-1).getPiece();
		temp.movePieceTo(m.getToX(), m.getToY(), tempPiece);
		
		ArrayList<Piece> enemy = new ArrayList<Piece>();
		Piece king = null;

		if (p.getCol()=='W') {
			enemy = temp.getBPieces();
			king = temp.findKing('W').getPiece();
		} else {
			enemy = temp.getWPieces();
			king = temp.findKing('B').getPiece();
		}
		for (Piece enemyP : enemy) {
			enemyP.updateMoves();
			for (Move badMove : enemyP.getMoves()) {
				if (badMove.getToX() == king.getX() && badMove.getToY() == king.getY()) {
					badMoves.add(m);
				} else if (tempPiece.getName()=="King") {
					if (badMove.getToX() == tempPiece.getX() && badMove.getToY() == tempPiece.getY()) {
						badMoves.add(m);
					}
				}
			}

		}
		temp.wipeBoard();
	}
for(Move m:badMoves) {
	moves.remove(m);
}
p.setMoves(moves);
}

public ArrayList<Piece> getWPieces() {
	return WPieces;
}
public void setWPieces(ArrayList<Piece> wPieces) {
	WPieces = wPieces;
}
public ArrayList<Piece> getBPieces() {
	return BPieces;
}
public void setBPieces(ArrayList<Piece> bPieces) {
	BPieces = bPieces;
}
public char getActiveColor() {
	return activeColor;
}
public void setActiveColor(char activeColor) {
	this.activeColor = activeColor;
}

}
