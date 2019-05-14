import java.util.Random;

/**
 * A fotelt megvalosito osztaly. Ha egy faradekony panda elhalad mellette, akkor leul.
 */
public class Fotel extends Entity implements MakeEffect{
	private Tile enteredFrom=null; //Errol a mezorol lehet belepni a fotelre.
	private Game game;
	private int restTime=5;
	private long timeLeft=restTime; //Ennyi ido van meg hatra.(eddig ul meg ott a panda)

	public void setGame(Game g){game=g;}

	public Fotel(Game g){
		game=g;
	}

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
		boolean success=p.affectedBy(this);
		if(success) {
			setEnteredFrom(p.getTile());
			game.removePanda(p);
		}
		return success;
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
		if(!isEmpty())
			timeLeft--;
		if(timeLeft<=0) {
			Panda p=(Panda) tile.getAnimal();
			boolean success=true;
			if(p!=null)
				{success=p.step(enteredFrom);} //TODO ez valamiert null
			tile.getSubbedPandas().clear();
			if(!success)
				p.getGameFrame().getTimer().addStepAttempt(new StepAttempt(p,tile));
			/*do {
				success=tile.getAnimal().step(enteredFrom);
			} while(!success);*/
			if(!game.getPandas().contains(p))
				game.addPanda(p);
			enteredFrom=null;
			resetTimeLeft();
		}
	}
	/**
	 * Ujrainditja az ido szamlalojat.
	 */
	public void resetTimeLeft() { timeLeft = restTime; }
	/**
	 * Visszater egy random pandaval. Azert jo, mert lehet hogy
	 * tobb panda van egyszerre fotel mellett, ilyenkor az egyik ul csak bele.
	 */
	public Panda getRandomSubbedPanda(){
		Random vel = new Random();
		int size=tile.getSubbedPandas().size();
		if(size==0)
			return null;
		int idx=vel.nextInt(size);
		Panda ret=null;
		if(tile.getSubbedPandas()!=null && tile.getSubbedPandas().get(idx)!=null)
			ret  = tile.getSubbedPandas().get(idx);
		return ret;
	}

	@Override
	public void makeEffect() {
		Panda p = getRandomSubbedPanda();
		if(p!=null)
			p.step(tile);
		for(Panda panda:tile.getSubbedPandas()){
			System.out.println(panda);
		}
	}

	public Tile getEnteredFrom(){ return enteredFrom; }
	public long getTimeLeft() { return timeLeft; }

	@Override
	public void drawSelf() {
		// mas az ikon ha ulnek benne
		imageholder.setBounds(tile.getCenter()[0]-24, tile.getCenter()[1]-24, 48, 48);
	}
}

