import java.util.ArrayList;

public abstract class Panda extends Animal{
	private ArrayList<Tile> subbedTiles=new ArrayList<Tile>();
	protected GameMap map; //TODO:inicializalni
	protected GameMap.Key hatesEntity;

	//METODUSOK
	public void affectedBy(Entity e) {
		//el tudom lepzelni hogy ennek semmi ertelme mert ugyis csak ugyanolyan parameterrel lehet overrideolni (G)
	}

	public void affectedBy(Arcade a){ }
	public void affectedBy(Automat a) { }
	public void affectedBy(Fotel f) { }
	/**
	 * Hozzaad egy csempet a panda subbedTiles listajahoz.
	 */

	public void addSubbedTile(Tile t) { subbedTiles.add(t); }

	/**
	 * Kitorli a feliratkozott csempek listajat.
	 */
	public void clearSubbedTiles() { subbedTiles.clear(); }

	/**
	 * A tile adattag getter fuggvenye.
	 */
	public Tile getTile() { return tile; }

	/**
	 * A pandat a parameterben megadott mezore mozgatjuk.
	 */
	@Override
	public boolean step(Tile newTile) {
		boolean success = newTile.receiveAnimal(this);
		if(success) {
			tile.removePandaFromNeighborSubbedPandas(this); //Panda eltavolitasa a szomszedokrol.
			subbedTiles.clear(); //Panda feliratkozasainak torlese
			for(Tile newTileNeighbor:newTile.getNeighbors()) { 
				if(map.getSpecificTiles(hatesEntity).contains(newTileNeighbor)) {
					addSubbedTile(newTileNeighbor); //Az uj helyen szomszedok felirasa pandara
					newTileNeighbor.addSubbedPanda(this); //Az uj helyen szomszedokra feliratkozasok
				}			
			}
		}
		return success;
	}

	/**
	 * A pandat "elkapja" a parameterben megadott orangutan.
	 * followedby-okat, following-okat alli
	 */
	@Override
	public boolean getCaughtBy(Orangutan o) {
		if(isFollowing()) //Mar elkapott pandat nem kapunk el
		{
			return false;
		}

		if(o.isFollowedBy()){
			setFollowedBy(o.followedBy);
			followedBy.setFollowing(this);			
		}
		setFollowing(o);
		o.setFollowedBy(this);
		return true;
	}
	/**
	 * Elengedi az ot koveto ill. annak a pandanak a kezet akit o kovet.
	 */
	public void release()
	{
		following = null;
		nextTile = null;
		if(followedBy != null){
			followedBy.release();
		}
		followedBy = null;
	}
	
	public void setNextTile(Tile t) {
		nextTile = t;
	}
}
