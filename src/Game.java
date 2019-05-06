import java.util.ArrayList;

public class Game{
	private int numberofplayers = 1;
	private GameMode selectedMode;
	private GameMap map;
	private ArrayList<Orangutan> orangutans=new ArrayList<Orangutan>();

	/**
	 * A jatekmodok enumja.
	 */
	enum GameMode{
		FinitPanda,
		FinitTime
	}

	public void addOrangutan(Orangutan o) { orangutans.add(o); }
	public ArrayList<Orangutan> getOrangutans(){ return orangutans; }

	/**
	 * Jelenleg nincsen funkcioja.
	 */
	public void main() {
		//TODO
	}

	/**
	 * Kezeli az orangutan kilepeset.
	 */
	//TODO: ez csak kilepteti az orangutant, de az ot koveto pandakat nem kezeli
	public void exiting(Orangutan o) {
		int num = o.getPandaNum(); //Az orangutant koveto pandak szama.
		if (num >= 5)               //Ha tobb mint 5.
			this.reward();
		o.increaseScore(num);       //Noveli az orangutan pontjait.
		o.goToEntry();               //A bejarathoz helyezi az orangutant.
	}

	/**
	 * Kezeli a jutalmat 5 kivitt panda utan.
	 */
	public void reward() {
		this.weakTilesAddlife(); 				  //Noveli a gyenge csempek eleterejet.

		//Jatekmodtol fuggoen vagy csokkenti, vagy noveli az idot.
		if(selectedMode == GameMode.FinitPanda)
			Timer.instance().decreaseTime(5);
		if(selectedMode == GameMode.FinitTime)
			Timer.instance().increaseTime(5);
	}

	/**
	 * Az elert pontszamot menti el.
	 */
	public void SaveHighScore(int s) {
		//Itt meg csak kiirja a pontszamot.
		System.out.println("Current score: " + s);
	}

	/**
	 * A jatek vege, visszalep a fomenube.
	 */
	public void gameOver() {
		//TODO
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

	/**
	 * Visszaadja a valasztott jatekmodot. A selectedMode adattag gettter fuggvenye.
	 */
	public GameMode getSelectedMode() { return selectedMode; }

	/**
	 * Beallitja a valaszott jatekmodot. A selectedMode adattag setter fuggvenye.
	 */
	public void setSelectedMode(GameMode selectedMode) { this.selectedMode = selectedMode; }
}
