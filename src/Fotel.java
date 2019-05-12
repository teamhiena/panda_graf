import java.util.ArrayList;
import java.util.Random;

/**
 * A fotelt megvalosito osztaly. Ha egy faradekony panda elhalad mellette, akkor leul.
 */
public class Fotel extends Entity implements MakeEffect{
	private Tile enteredFrom=null; //Errol a mezorol lehet belepni a fotelre.
	private long timeLeft=100; //Ennyi ido van meg hatra.(eddig ul meg ott a panda)

	//METODUSOK
	/**
	 * Egy orangutan megprobal belepni a fotelbe. Ez termeszetesen nem lehetseges.
	 * @param o
	 * @return
	 */
	public boolean stepIn(Orangutan o) {
		return false;
	}

	/**
	 * Egy panda megprobal belepni a fotelbe. Implementacios okobol neki kell elvegezni a "magara helyezest".
	 * @param p
	 * @return
	 */
	public boolean stepIn(Panda p) {
		if (tile.getAnimal()!= null) return false; //ha pl ul valaki benne es bele akar ulni megegy
		return p.affectedBy(this);
	}

	/**
	 * Megmondja, hogy van-e valaki a fotelban.
	 */
	public boolean isEmpty() {
		return (tile.getAnimal() == null);
	}
	public  void setEnteredFrom(Tile t){
		enteredFrom=t;
	}
	/**
	 * Csokkenti a hatra levo idot.
	 */
	public void setTimeLeft(int n){
		timeLeft=n;
	}

	public void decrTimeLeft() {
		if(!isEmpty()) timeLeft--;
		if(timeLeft<=0) {
			boolean success;
			do {
				success=tile.getAnimal().step(enteredFrom);
			} while(!success);
			enteredFrom=null;
		}
	}
	/**
	 * Ujrainditja az ido szamlalojat.
	 */
	public void resetTimeLeft() { timeLeft = 100; }
	/**
	 * Visszater egy random pandaval. Azert jo, mert lehet hogy
	 * tobb panda van egyszerre fotel mellett, ilyenkor az egyik ul csak bele.
	 */
	public Panda getRandomSubbedPanda(){
		int a = tile.getSubbedPandas().size();
		Random vel = new Random();
		Panda ret  = tile.getSubbedPandas().get(a);
		return ret;
	}

	@Override
	public void makeEffect() {
		Panda p = getRandomSubbedPanda();
		p.step(tile);
	}

	public Tile getEnteredFrom(){ return enteredFrom; }
	public long getTimeLeft() { return timeLeft; }

	@Override
	public void drawSelf() {
		// más az ikon ha ülnek benne
		imageholder.setBounds(tile.getCenter()[0], tile.getCenter()[1], 30, 30);
	}
}

