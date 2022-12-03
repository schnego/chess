package chess;

import static org.junit.Assert.*;

import org.junit.Test;


public class ChessTest {

	@Test
	public void testPawnMoveTwoSquare() {
	Board b=new Board();
	b.getFieldByIndexes(6, 0).getPiece().updateMoves();
	assertTrue(b.getFieldByIndexes(6, 0).getPiece().move(1, 4));
	}
	@Test
	public void testQueenMoveAsHorse() {
		Board b=new Board();
		assertFalse(b.getFieldByIndexes(7, 3).getPiece().move(3, 3));
	}
	@Test
	public void testCastleInPos() {
		Board b=new Board();
		assertFalse(b.isCastleAvailable('W', 'J'));
		assertFalse(b.isCastleAvailable('W', 'B'));
	}
	@Test
	public void testPawnTakesAnother() {
		Board b=new Board(true);
		b.addPieceToBoard(4, 5, 'P', 'W');
		b.addPieceToBoard(3, 4, 'P', 'B');
		b.getFieldByIndexes(4, 5).getPiece().updateMoves();
		b.getFieldByIndexes(4, 5).getPiece().move(5, 5);
		assertEquals(b.getFieldByIndexes(3, 4).getPiece().getCol(),'W');
	}
	@Test
	public void testInCheck() {
		Board b=new Board();
		assertFalse(b.inCheck('W'));
	}
	@Test
	public void testInCheckTrue() {
		Board b=new Board(true);
		b.addPieceToBoard(4, 5, 'K', 'W');
		b.addPieceToBoard(3, 4, 'P', 'B');
		b.addPieceToBoard(1, 1, 'K', 'B');
		assertTrue(b.inCheck('W'));
	}
	@Test
	public void testAddPieceToBoard() {
		Board b=new Board(true);
		b.addPieceToBoard(3, 4, 'P', 'B');
		assertEquals(b.getFieldByIndexes(3, 4).getPiece().getCol(),'B');
		assertEquals(b.getFieldByIndexes(3, 4).getPiece().getLetter(),'P');
	}
	@Test
	public void testCastle() {
		Board b=new Board(true);
		b.addPieceToBoard(4, 5, 'K', 'W');
		b.addPieceToBoard(0, 0, 'R', 'B');
		b.addPieceToBoard(0, 4, 'K', 'B');
		b.addPieceToBoard(0, 7, 'K', 'B');
		b.castleKing('B', 'J');
		assertFalse(b.getFieldByIndexes(0,6).getEmpty());
		assertFalse(b.getFieldByIndexes(0,5).getEmpty());
	}
	@Test
	public void testPromotePawns() {
		Board b=new Board(true);
		b.addPieceToBoard(0, 7, 'P', 'W');
		b.promotePawns();
		assertEquals(b.getFieldByIndexes(0, 7).getPiece().getLetter(),'Q');
	}
	@Test
	public void testCopyBoard() {
		Board b=new Board();
		Board temp=new Board(true);
		b.copyBoardTo(temp);
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				assertEquals(b.getFieldByIndexes(i, j).getEmpty(),temp.getFieldByIndexes(i, j).getEmpty());
				}
			}
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(!b.getFieldByIndexes(i, j).getEmpty()) {
				assertEquals(b.getFieldByIndexes(i, j).getPiece().getLetter(),temp.getFieldByIndexes(i, j).getPiece().getLetter());
				assertEquals(b.getFieldByIndexes(i, j).getPiece().getCol(),temp.getFieldByIndexes(i, j).getPiece().getCol());
				}
			}
		}
	}
	@Test
	public void testWipeBoard() {
		Board b=new Board();
		b.wipeBoard();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				assertTrue(b.getFieldByIndexes(i, j).getEmpty());
			}
		}
	}

}
