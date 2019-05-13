import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonListener;

public class GameFrame extends JFrame{
	protected GameMap gm;
	private Game g;
	private View v;
	private ScorePanel scorePanel;
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
		gp =new GamePanel(gm, g, v, t,this);
		add(gp,BorderLayout.CENTER);
   		scorePanel = new ScorePanel(gm,g,v,t);
        add(scorePanel, BorderLayout.NORTH);
        scorePanel.setVisible(true);
		gp.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setVisible(true);

		Dimension dim=new Dimension(max(0,gp.getSize().width+10),gp.getSize().height+80);
		//Dimension dim = gp.getSize();
		setSize(dim);

		addKeyListener(new OrangutanKeyListener(game));
	}
	
	int max(int a, int b) {
		if (a>b) return a;
		return b;
	}


	public class OrangutanKeyListener implements java.awt.event.KeyListener{
		@Override
		public void keyTyped(KeyEvent e) {}

		//TODO resetelodjon a direction step utan

		private void setOrangutanDirection(Orangutan o,int keyCode){
			Orangutan.Controls controls=o.getControls();
			Game.Direction direction=o.getDirection();
			if(controls== Orangutan.Controls.wasd){ //ha wasd a kontrol
				if(keyCode==KeyEvent.VK_W){ //w megnyomva
					if(direction== Game.Direction.Right){ //wd
						direction= Game.Direction.TopRight;
					}else if(direction== Game.Direction.Left){ //wa
						direction= Game.Direction.TopLeft;
					}else if(direction!= Game.Direction.TopRight&&direction!= Game.Direction.TopLeft){ //w
						direction= Game.Direction.Top;
					}
				}else if (keyCode==KeyEvent.VK_A) { //a megnyomva
					if(direction== Game.Direction.Top){ //wa
						direction= Game.Direction.TopLeft;
					}else if(direction== Game.Direction.Bottom){ //sa
						direction= Game.Direction.BottomLeft;
					}else if(direction!= Game.Direction.BottomLeft&&direction!= Game.Direction.TopLeft){ //a
						direction= Game.Direction.Left;
					}
				}else if (keyCode==KeyEvent.VK_S) { //s megnyomva
					if(direction== Game.Direction.Right){ //sd
						direction= Game.Direction.BottomRight;
					}else if(direction== Game.Direction.Left){ //sa
						direction= Game.Direction.BottomLeft;
					}else if(direction!= Game.Direction.BottomLeft&&direction!= Game.Direction.BottomRight){ //s
						direction= Game.Direction.Bottom;
					}
				}else if (keyCode==KeyEvent.VK_D){ //d megnyomva
					if(direction== Game.Direction.Top){ //wd
						direction= Game.Direction.TopRight;
					}else if(direction== Game.Direction.Bottom){ //sd
						direction= Game.Direction.BottomRight;
					}else if(direction!= Game.Direction.TopRight&&direction!= Game.Direction.BottomRight){ //d
						direction= Game.Direction.Right;
					}
				}
			}
			else if(controls== Orangutan.Controls.arrows){ //mas nem is lehet de latszodjon
				if(keyCode==KeyEvent.VK_UP){ //w megnyomva
					if(direction== Game.Direction.Right){ //wd
						direction= Game.Direction.TopRight;
					}else if(direction== Game.Direction.Left){ //wa
						direction= Game.Direction.TopLeft;
					}else if(direction!= Game.Direction.TopRight&&direction!= Game.Direction.TopLeft){ //w
						direction= Game.Direction.Top;
					}
				}else if (keyCode==KeyEvent.VK_LEFT) { //a megnyomva
					if(direction== Game.Direction.Top){ //wa
						direction= Game.Direction.TopLeft;
					}else if(direction== Game.Direction.Bottom){ //sa
						direction= Game.Direction.BottomLeft;
					}else if(direction!= Game.Direction.BottomLeft&&direction!= Game.Direction.TopLeft){ //a
						direction= Game.Direction.Left;
					}
				}else if (keyCode==KeyEvent.VK_DOWN) { //s megnyomva
					if(direction== Game.Direction.Right){ //sd
						direction= Game.Direction.BottomRight;
					}else if(direction== Game.Direction.Left){ //sa
						direction= Game.Direction.BottomLeft;
					}else if(direction!= Game.Direction.BottomLeft&&direction!= Game.Direction.TopLeft){ //s
						direction= Game.Direction.Bottom;
					}
				}else if (keyCode==KeyEvent.VK_RIGHT){ //d megnyomva
					if(direction== Game.Direction.Top){ //wd
						direction= Game.Direction.TopRight;
					}else if(direction== Game.Direction.Bottom){ //sd
						direction= Game.Direction.BottomRight;
					}else if(direction!= Game.Direction.TopRight&&direction!= Game.Direction.BottomRight){ //d
						direction= Game.Direction.Right;
					}
				}
			}
			o.setDirection(direction);
		}//TODO jelenleg mindket orangutan wasd
		private Game game;

		public OrangutanKeyListener(Game g){game=g;}


		@Override
		public void keyPressed(KeyEvent e)
		{
			Orangutan o=game.getOrangutans().get(0);
			setOrangutanDirection(o,e.getKeyCode());

			if(false&&game.getOrangutans().size()>1){
				o=game.getOrangutans().get(1);
				setOrangutanDirection(o,e.getKeyCode());
			}


		}

		@Override
		public void keyReleased(KeyEvent e) {}
	}
}
