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
		Panda p = this.followedBy;
		this.getFollowing().setFollowedBy(null);
		releasePandas();
		while(p != null){
			Panda b = p.followedBy;
			p.releasePandas();
			p = b;
		}
	}
	/*@Override felraktam pandaba
	public void drawSelf() {
		// TODO �thelyezi az imageholderj�t arra a Tile-ra ahol van, m�s az ikonja ha megijedos.
		imageholder.setBounds(tile.getCenter()[0]-24, tile.getCenter()[1]-24, 48, 48);
	}*/
}
 