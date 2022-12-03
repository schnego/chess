package chess;
import javax.swing.*;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MenuPanel mp;
	Frame(){
	MenuPanel mp=new MenuPanel(this);
	this.add(mp);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle("Sakk jatek");
	this.setResizable(false);
	this.setVisible(rootPaneCheckingEnabled);
	this.pack();
	

	}
}
