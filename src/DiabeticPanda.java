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
		//TODO
    }
}
