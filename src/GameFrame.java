import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonListener;

public class GameFrame extends JFrame{

	private GamePanel gp=new GamePanel();
	
	public GamePanel getGP() {
		return gp;
	}

	public GameFrame() {
		super();
		
		add(gp,BorderLayout.CENTER);
		
		gp.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		Dimension dim=new Dimension(max(0,gp.getSize().width+10),gp.getSize().height+75);
		//Dimension dim = gp.getSize();
		setSize(dim);
	}
	
	int max(int a, int b) {
		if (a>b) return a;
		return b;
	}
}
