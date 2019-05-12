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
	private String scoreboard;
	private GamePanel gp;
	private JLabel lab;
	private ResultPanel rp;

	public void setRp(ResultPanel r){
		rp=r;
	}
	public ResultPanel getRP(){return rp;}

	public GameFrame(GameMap gamemap, Game game, View view) {
		super();
		gm = gamemap;
		g = game;
		v = view;
		gp =new GamePanel(gm, g, v);
		add(gp,BorderLayout.CENTER);
        JPanel jp = new JPanel();
        String scoreboard1 = "";
        scoreboard1 += "Player1: "; //+ g.getOrangutans().get(0).getScore(); NINCSENEK ORANGUTANOK BETOLTVE
        JLabel score1 = new JLabel(scoreboard1);
        score1.setPreferredSize(new Dimension((gp.getSize().width+10)/3, 50));
		String scoreboard2 = "";
        if(g.getOrangutans().size() ==2)
        scoreboard2 += "Player2: "; // + g.getOrangutans().get(1).getScore();
        JLabel score2 = new JLabel(scoreboard2);
        score2.setPreferredSize(new Dimension((gp.getSize().width+10)/3, 50));
        String time = "" + "Idokiiras"; //kene valami idő getter
        JLabel time1ab = new JLabel(time);
        time1ab.setPreferredSize(new Dimension((gp.getSize().width+10)/3, 50));
        jp.setLayout(new BorderLayout());
        jp.add(score1, BorderLayout.WEST);
        jp.add(score2, BorderLayout.EAST);
        jp.add(time1ab, BorderLayout.CENTER);
        add(jp, BorderLayout.NORTH);
        jp.setVisible(true);
		gp.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setVisible(true);

		Dimension dim=new Dimension(max(0,gp.getSize().width+10),gp.getSize().height+80);
		//Dimension dim = gp.getSize();
		setSize(dim);
	}
	
	int max(int a, int b) {
		if (a>b) return a;
		return b;
	}
}
