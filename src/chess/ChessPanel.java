package chess;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ChessPanel extends JPanel implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Board board;
	MenuPanel p;
	JFrame fr;
	int x=1, y=1;
	boolean active=false;
	public ChessPanel(Board b,JFrame jf,MenuPanel mp){
		fr=jf;
		board=b;
		p=mp;
		this.setFocusable(true);
		this.setPreferredSize(new Dimension(800,800));
		this.addKeyListener(this);
		this.addMouseListener(new mLis());
		
	}
	public void paint(Graphics g) {
		board.draw(g);
			
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_S) {
			fr.add(p);
			fr.pack();
			fr.remove(this);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	private class mLis extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
			x=e.getX()/100;
			y=e.getY()/100;
			//System.out.println(x+" "+y);
			if(!active) {
				active=board.selectField(x,y);
			
			
			} else {
				
				active=!board.moveSelected(x, y);
			}
			revalidate();
			repaint();
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			revalidate();
			repaint();
		}
	}

	
}
