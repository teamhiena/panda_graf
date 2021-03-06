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
	protected Game g;
	private View v;
	protected GamePanel gp;
	private JLabel lab;
	private ResultPanel rp;
	private Timer timer;


	public GameFrame(GameMap gamemap, Game game, View view, Timer t) {
		super();
		gm = gamemap;
		g = game;
		v = view;
		timer = t;
		gp = new GamePanel(gm, g, v, t,this);
		add(gp,BorderLayout.CENTER);

		gp.setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Dimension dim=new Dimension(max(0,gp.getSize().width+10),gp.getSize().height+80);
		setSize(dim);

		addKeyListener(new OrangutanKeyListener(game));
	}

	//Egyszeru maximumkereses
	int max(int a, int b) {
		if (a>b) return a;
		return b;
	}

	//Getter/setter fuggvenyek
	public void setRp(ResultPanel r){ rp = r; }
	public ResultPanel getRP(){return rp;}
	public Timer getTimer() { return timer; }
	public GamePanel getGp(){return gp; }
	public View getV() {
		return v;
	}

	/**
	 * Ez az osztaly kezeli a nyomogombokat
	 */
	public class OrangutanKeyListener implements java.awt.event.KeyListener{
		private Game game;

		//KONSTRUKTOR
		public OrangutanKeyListener(Game g){game=g;}

		@Override
		public void keyTyped(KeyEvent e) {}

		/**
		 * Az atadott gombtol fuggoen atallitja az orangutan iranyat / elengedi a pandakat
		 */
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
				}else if(keyCode == KeyEvent.VK_Q) { //space megnyomva
					if(game.getOrangutans().get(0).isFollowedBy()){ //csak akkor ha koveti valaki
						game.getOrangutans().get(0).releasePandas();
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
				}else if(keyCode == KeyEvent.VK_SPACE) { //space megnyomva
					if(game.getOrangutans().get(1).isFollowedBy()){ //csak akkor ha koveti valaki
						game.getOrangutans().get(1).releasePandas();
					}
				}
			}
			o.setDirection(direction);
		}

		/**
		 * Ebbol hivunk at az iranyokat allito fuggvenybe
		 */
		@Override
		public void keyPressed(KeyEvent e)
		{
			Orangutan o=game.getOrangutans().get(0);
			setOrangutanDirection(o,e.getKeyCode());

			if(game.getOrangutans().size()>1){
				o=game.getOrangutans().get(1);
				setOrangutanDirection(o,e.getKeyCode());
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {}
	}
}
