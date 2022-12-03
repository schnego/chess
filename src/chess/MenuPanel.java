package chess;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MenuPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton newGame;
	JButton continueGame;
	JButton matchReplays;
	ReplayPanel rp;
	ChessPanel p;
	Board b;
	JFrame fr;
	public MenuPanel(JFrame jf){
		fr=jf;
		rp=new ReplayPanel(new BoardLogger(),this,fr);
		Board b=new Board();
		p=new ChessPanel(b,jf,this);
		this.setPreferredSize(new Dimension(200,200));
		newGame=new JButton("New Game");
		this.add(newGame);
		newGame.addActionListener(this);
		continueGame=new JButton("Continue Game");
		this.add(continueGame);
		continueGame.addActionListener(this);
		matchReplays=new JButton("Replay latest");
		this.add(matchReplays);
		matchReplays.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==newGame) {
			b=new Board();
			p=new ChessPanel(b,fr,this);
			fr.add(p);
			fr.pack();
			fr.remove(this);
		}	
		else if(ae.getSource()==continueGame) {
			fr.add(p);
			fr.pack();
			fr.remove(this);
			}
		else if(ae.getSource()==matchReplays) {
			rp=new ReplayPanel(new BoardLogger(),this,fr);
			fr.add(rp);
			fr.pack();
			fr.remove(this);
		}
		
	}

	
}
