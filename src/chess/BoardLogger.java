package chess;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
public class BoardLogger implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private ArrayList<Board> boardLog=new ArrayList<>();
private int it;
public BoardLogger() {
	it=0;
}
public void addBoardPosToLog(Board b) {
	Board temp=new Board();
	temp.wipeBoard();
	b.copyBoardTo(temp);
	boardLog.add(temp);
}
public void serializeBoardLog() throws IOException {
	 FileOutputStream fileOut = new FileOutputStream("mentett.txt");
	 ObjectOutputStream out = new ObjectOutputStream(fileOut);
	 out.writeObject(this);
	 out.close();
}
public void deserializeBoardLog() throws IOException, ClassNotFoundException {
	File f = new File("mentett.txt");
	if(f.exists() && !f.isDirectory()) {
	FileInputStream fileIn = new FileInputStream("mentett.txt");
	ObjectInputStream in = new ObjectInputStream(fileIn);
	BoardLogger temp=(BoardLogger)in.readObject();
	in.close();
	this.setBoardLog(temp.getBoardLog());
	it=temp.getIt();
	}
}
public ArrayList<Board> getBoardLog() {
	return boardLog;
}
public void setBoardLog(ArrayList<Board> boardLog) {
	this.boardLog = boardLog;
}
public Board returnNextBoard() {
	if(boardLog.isEmpty())return new Board();
	if(it<boardLog.size()-1)it++;
	return boardLog.get(it);
}
public Board returnPreviousBoard() {
	if(boardLog.isEmpty())return new Board();
	if(it>0)it--;
	return boardLog.get(it);
}
public Board returnFirstBoard() {
	if(boardLog.isEmpty())return new Board();
	return boardLog.get(0);
}
public Board returnLatestBoard() {
	if(boardLog.isEmpty())return new Board();
	return boardLog.get(boardLog.size()-1);
}
public int getIt() {
	return it;
}

}
