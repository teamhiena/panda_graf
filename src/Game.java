import javax.swing.*;
import java.util.ArrayList;

public class Game{
	private int numberofplayers = 1;

	private GameMap map;
	private GameFrame gameFrame;

	private ArrayList<Orangutan> orangutans=new ArrayList<Orangutan>();
	private ArrayList<Panda> pandas  = new ArrayList<Panda>();
	public int getNumberofplayers() {return numberofplayers;}
	public void setNumberofplayers(int n) {numberofplayers=n;}
	private ResultPanel resultPanel;

	/**
	 *
	 * A directionök enumja
	 */
	enum Direction{
		TopLeft, Top, TopRight, Right,
		BottomRight, Bottom, BottomLeft, Left
	}


	public void addOrangutan(Orangutan o) { orangutans.add(o); }
	public void addPanda(Panda p) { pandas.add(p); }
	public void removePanda(Panda p) {pandas.remove(p);}
	public ArrayList<Orangutan> getOrangutans(){ return orangutans; }
	public ArrayList<Panda> getPandas(){return pandas; }
	/**
	 * Kezeli az orangutan kilepeset.
	 */
	//TODO: ez csak kilepteti az orangutant, de az ot koveto pandakat nem kezeli
	public void exiting(Orangutan o) {
		int num = o.getPandaNum(); //Az orangutant koveto pandak szama.
		if (num >= 5)               //Ha tobb mint 5.
			this.reward();
		o.increaseScore(num);       //Noveli az orangutan pontjait.
		//o.goToEntry();               //A bejarathoz helyezi az orangutant.
	}

	/**
	 * Kezeli a jutalmat 5 kivitt panda utan.
	 */
	public void reward() {
		this.weakTilesAddlife(); 				  //Noveli a gyenge csempek eleterejet.

		//Jatekmodtol fuggoen vagy csokkenti, vagy noveli az idot.
		Timer.instance().increaseTime(5);
	}

	/**
	 * A jatek vege, visszalep a fomenube.
	 */
	public void gameOver() {
        //ResultPanel resultPanel = new ResultPanel(gameFrame, this);
		JOptionPane jop = new JOptionPane();
		if(numberofplayers == 1) {
			jop.showMessageDialog(gameFrame,
					"Player1: " +
							gameFrame.g.getOrangutans().get(0).getScore(),
					"Game Over!",
					JOptionPane.PLAIN_MESSAGE
			);
		}
			else {
				jop.showMessageDialog(gameFrame,
						"Player1: " +
								gameFrame.getTimer().getTime() + "s",
						"Game Over!",
						JOptionPane.PLAIN_MESSAGE
				);
			}

		gameFrame.add(jop);
		System.exit(0);
	}

	/**
	 * Noveli a gyenge csempek eleterejet.
	 */
	public void weakTilesAddlife() {
		/*ArrayList<WeakTile> weakTiles = GameMap.instance().getWeakTiles();
		for (WeakTile tl: weakTiles) {
			tl.reduceNumOfSteps();
		}*/

	}

	//Getter/Setter fuggvenyek
    public void setResultPanel(ResultPanel rp){resultPanel = rp;}
    public void setGameFrame(GameFrame gf){gameFrame = gf;}
}
