import javax.swing.*;
import java.util.ArrayList;

public class Game{
	private int numberofplayers = 1;

	private GameMap map;
	private GameFrame gameFrame;

	private ArrayList<Orangutan> orangutans=new ArrayList<Orangutan>();
	private ArrayList<Panda> pandas  = new ArrayList<Panda>();

	private ResultPanel resultPanel;

	/**
	 * A directionök enumja
	 */
	enum Direction{
		TopLeft, Top, TopRight, Right,
		BottomRight, Bottom, BottomLeft, Left
	}

	/**
	 * Listakat kezelo fuggvenyek.
	 */
	public void addOrangutan(Orangutan o) { orangutans.add(o); }
	public void addPanda(Panda p) { pandas.add(p); }
	public void removePanda(Panda p) {pandas.remove(p);}
	public ArrayList<Orangutan> getOrangutans(){ return orangutans; }
	public ArrayList<Panda> getPandas(){return pandas; }

	/**
	 * Kezeli az orangutan kilepeset.
	 */
	public void exiting(Orangutan o) {
		int num = o.getPandaNum(); //Az orangutant koveto pandak szama.
		if (num >= 5)               //Ha tobb mint 5.
			this.reward();
		o.increaseScore(num);       //Noveli az orangutan pontjait.
	}

	/**
	 * Kezeli a jutalmat 5 kivitt panda utan.
	 */
	public void reward() {
		Timer.instance().increaseTime(5);
	}

	/**
	 * A jatek vege --> Megjelenik az eredmenyeket megjelenito ablak
	 */
	public void gameOver() {
		JOptionPane jop = new JOptionPane();
		if(gameFrame.g.getOrangutans().size() == 1) {
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
								gameFrame.g.getOrangutans().get(0).getScore() + "\n" +
								"Player2: "+
								gameFrame.g.getOrangutans().get(1).getScore(),
						"Game Over!",
						JOptionPane.PLAIN_MESSAGE
				);
			}

		gameFrame.add(jop);
		System.exit(0);
	}

	//Getter/Setter fuggvenyek
    public void setResultPanel(ResultPanel rp){resultPanel = rp;}
    public void setGameFrame(GameFrame gf){gameFrame = gf;}
    public GameFrame getGameFrame(){return gameFrame;}
	public int getNumberofplayers() {return numberofplayers;}
	public void setNumberofplayers(int n) {numberofplayers=n;}
}
