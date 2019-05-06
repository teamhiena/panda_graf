import java.util.ArrayList;

/**
 * A szekrenyt(Wardrobe) megvalosito osztaly.
 */
public class Wardrobe extends Entity {
	private Tile previousExitTile=null; 
	private Tile entrance;
	private GameMap map; //TODO: inicializalni
	 
	public Wardrobe(Tile e, GameMap gm) {
		entrance=e;
		map = gm;
		gm.addSpecificTile(entrance, GameMap.Key.WardrobeExit);
		gm.addSpecificTile(tile, GameMap.Key.Wardrobe);
	}

	public Wardrobe() {

	}

	/**
	 * A parameterben megadott orangutan belep a szekrenybe.
	 */
	@Override
	public boolean stepIn(Orangutan o) {
		if(o.getTile()!= entrance) {
			return false;
		}
		boolean success;
		do{
			Tile exit = map.getRandomWardrobeExitTile();
			success = o.step(exit);
			if(success&&o.followedBy!=null) {
				previousExitTile=exit;				
			}
				
		}while(!success);
		return success;
	}

	/**
	 * Egy panda belep a szekrenybe.
	 */
	@Override
	public boolean stepIn(Panda p) {
		if(p.getTile()!= entrance) {
			return false;
		}
		boolean success;
		do {
			if(p.isFollowing())
				success = p.step(previousExitTile);
			else {
				success = p.step(map.getRandomWardrobeExitTile());
				previousExitTile=null;
			}
		}while(!success);
		return true;
	}

	/**
	 * A previousExitTile adattag setter fuggvenye.
	 */
	public void setPreviousExitTile(Tile t) {
		previousExitTile=t;
	}

	public void setEntrance(Tile t){entrance=t;}

	public Tile getPreviousExitTile(){ return previousExitTile; }
	public Tile getEntrance(){ return entrance; }
}
