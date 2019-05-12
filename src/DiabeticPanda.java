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

    //METÃ“DUSOK
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

	@Override
	public void drawSelf() {
		// TODO áthelyezi magát,más ha épp megáll cukrozni
		imageholder.setBounds(tile.getCenter()[0], tile.getCenter()[1], 30, 30);
	}
}
