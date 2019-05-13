import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonListener;
public class GameFrame extends JFrame{
	private GameMap gm;
	private Game g;
	private View v;
	protected ScorePanel scorePanel;
	private GamePanel gp;
	private JLabel lab;
	private ResultPanel rp;

	public void setRp(ResultPanel r){
		rp=r;
	}
	public ResultPanel getRP(){return rp;}

	public GameFrame(GameMap gamemap, Game game, View view, Timer t) {
		super();
		gm = gamemap;
		g = game;
		v = view;
		gp =new GamePanel(gm, g, v, t);
		add(gp,BorderLayout.CENTER);
		scorePanel = new ScorePanel(gm,g,v,t);
		add(scorePanel,BorderLayout.NORTH);
        scorePanel.setVisible(true);
		gp.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setVisible(true);

		Dimension dim=new Dimension(max(0,gp.getSize().width+10),gp.getSize().height+80);
		//Dimension dim = gp.getSize();
		setSize(dim);
		repaint();
	}
	
	int max(int a, int b) {
		if (a>b) return a;
		return b;
	}
}
