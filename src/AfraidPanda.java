/**
 * Az ijedos pandat(AfraidPanda) megvalosito osztaly. Megijed a jatekgep csilingelsesetol es elengedi a mogotte allo
 * pandak mancsat, igy felbomlik a sor.
 */
public class AfraidPanda extends Panda {

	//KONSTRUKTOROK
	public AfraidPanda(GameMap gm){
		map = gm;
		hatesEntity = GameMap.Key.Arcade;
	}
	//METODUSOK
	/**
	 * A pandat megijeszti egy jatekgep.
	 */
	public void affectedBy(Arcade a) {
		releasePandas();
	}
}
 