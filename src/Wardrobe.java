import java.util.ArrayList;

/**
 * A szekrenyt(Wardrobe) megvalosito osztaly.
 */
public class Wardrobe extends Entity {
	private Tile entrance;
	private GameMap map; //TODO: inicializalni

	/**
	 *
	 * @param e : bejarat!!!
	 * @param gm : gamemap
	 */
	public Wardrobe(Tile e, GameMap gm) {
		entrance=e;
		map = gm;
		//gm.addSpecificTile(entrance, GameMap.Key.WardrobeExit);
		//gm.addSpecificTile(tile, GameMap.Key.Wardrobe);
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
		Tile exit;
		do{
			do{
				exit = map.getRandomWardrobeExitTile();
			}while(entrance==exit);

			if(exit==null) //ha csak egy szekrény van, nem lehet belelepni
				return false;

			success = o.step(exit);
			/*if(success&&o.followedBy!=null) { //csak akkor áll át hogyha követik
				previousExitTile=exit;
			}*/

		}while(!success);
		return false; //ez azért false mert a hivo(receiveAnimal) atallit egy csomo sarsagot ha ez true
	}


	/**
	 * Egy panda belep a szekrenybe.
	 */
	@Override
	public boolean stepIn(Panda p) {
		if(p.getTile()!= entrance) {
			return false;
		}
		boolean success=false;
		Tile exit;
		do {
			if(!p.isFollowing()){ //tulajdonkepp mindegy aki beleõ ugyse followol

				do{
					exit = map.getRandomWardrobeExitTile();
				}while(entrance==exit);

				if(exit==null) //ha csak egy szekrény van, nem lehet belelepni
					return false;

				success = p.step(exit);
			}

			/*else {
				success = p.step(previousExitTile);
			}
			if(!p.isFollowedBy())
				previousExitTile=null;*/

		}while(!success);
		return false; //ez azért false mert a hivo(receiveAnimal) atallit egy csomo sarsagot ha ez true
	}

	public void setEntrance(Tile t){entrance=t;}
	public Tile getEntrance(){ return entrance; }

	@Override
	public void drawSelf() {
		imageholder.setBounds(tile.getCenter()[0], tile.getCenter()[1], 30, 30);
		
	}
}
