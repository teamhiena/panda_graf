import java.util.ArrayList;

/**
 * A cukorbeteg pandat(DiabeticPanda) megvalosito osztaly. A cukorbeteg panda megijed a csokiautomata(Automat) sipolasatol,
 * es ugrik egyet amivel az alatta levo WeakTile elettartama eggyel csokken.
 */
public class DiabeticPanda extends Panda {

	//KONSTRUKTOROK
	public DiabeticPanda(GameMap gm) {
		map = gm;
		hatesEntity=GameMap.Key.Automat;
	}

    /*public DiabeticPanda() {

    }*/

    //METÓDUSOK
	/**
	 * A pandat megijeszti egy csokiautomata.
	 */
	public void affectedBy(Automat a) {
		if(map.getSpecificTiles(GameMap.Key.WeakTile).contains(this.getTile())){
			WeakTile t = (WeakTile)this.getTile();
			for(int i =0; i < 10; i++)
				t.reduceNumOfSteps();
			if(t.isBroken()) t.getAnimal().die();
		}
	}

	/*@Override felraktam pandaba
	public void drawSelf() {
		// TODO �thelyezi az imageholderj�t arra a Tile-ra ahol van, m�s az ikonja ha megijedos.
		imageholder.setBounds(tile.getCenter()[0]-24, tile.getCenter()[1]-24, 48, 48);
	}*/
}
