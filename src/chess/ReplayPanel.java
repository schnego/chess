package chess;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.*;
public class ReplayPanel extends JPanel implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BoardLogger log;
	Board board;
	MenuPanel mp;
	JFrame fr;
	public ReplayPanel(BoardLogger l, MenuPanel p, JFrame jf) {
		log=l;
		mp=p;
		fr=jf;
		try {
			log.deserializeBoardLog();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		board=log.returnFirstBoard();
		this.setPreferredSize(new Dimension(800,800));
		this.setFocusable(true);
		this.addKeyListener(this);
	}
	public void paint(Graphics g) {
		board.drawReplay(g);
			
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
			board=log.returnNextBoard();
			repaint();
		} else if(e.getKeyCode()== KeyEvent.VK_LEFT) {
			board=log.returnPreviousBoard();
			repaint();
		} else if(e.getKeyCode()== KeyEvent.VK_B) {
			fr.add(mp);
			fr.pack();
			fr.remove(this);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
}
