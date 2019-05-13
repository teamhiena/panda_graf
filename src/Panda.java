import java.util.ArrayList;
import java.util.Random;

public abstract class Panda extends Animal{
	private ArrayList<Tile> subbedTiles=new ArrayList<Tile>();
	protected GameMap map;
	protected GameMap.Key hatesEntity;

	//METODUSOK
	//public void affectedBy(Arcade a){ }
    //public void affectedBy(Automat a) { }

	/**
	 * stepIn hivja meg, �s a timer, a timer azert,
	 * ha esetleg ketto tiredpanda egyszerre van a szomszedban, ekkor random
	 * @param f fotel amibe beleul
	 * @return sikeult-e belelepnie
	 */
	public boolean affectedBy(Fotel f) { return false;}

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
		if(newTile==null) return false;
		Tile temp=tile;

		boolean success = newTile.receiveAnimal(this); //NULLPTR
		if(success) {
			tile.removePandaFromNeighborSubbedPandas(this); //Panda eltavolitasa a szomszedokrol.
			subbedTiles.clear(); //Panda feliratkozasainak torlese
			for(Tile newTileNeighbor:newTile.getNeighbors()) {
				if(map.getSpecificTiles(hatesEntity).contains(newTileNeighbor)) {
					addSubbedTile(newTileNeighbor); //Az uj helyen szomszedok felirasa pandara
					newTileNeighbor.addSubbedPanda(this); //Az uj helyen szomszedokra feliratkozasok
				}
			}
			//animate(temp,newTile);
			if(isFollowedBy())
				followedBy.step(temp);
		}

		return success;
	}

	//ezt hivja az orangutan diewithfollowers /belelep a lyukba
	public void die(){
		boolean success = false;
		tile.setAnimal(null);
		releasePandas();

		while(!success){
			Random rng = new Random();
			Integer idx =rng.nextInt(gf.gp.getTiles().size());
			if(gf.gp.getTiles().get(idx).getAnimal() == null && gf.gp.getTiles().get(idx).getEntity() == null) {
				success = spawn(gf.gp.getTiles().get(idx)); break;
			}
		}
	}


	/**
	 *  freeroamolo pandakat leptet random helyre
	 * @return sikerult-e odalepni
	 */
	@Override
	public boolean step(){
		boolean ret=false;
		if(!isFollowing()){
			//80% valoszinuseggel leptet egy szomszedra
			Random rng=new Random();
			boolean doAStep =rng.nextInt(10)>1;
			if(doAStep){
				if(tile != null){
				int bound=tile.getNeighbors().size();
				//System.out.println("bound :"+bound);
				//System.out.println("ez lett a bound: "+rng.nextInt(bound));
				ret=step(tile.getNeighbors().get(rng.nextInt(bound)));
				}
			}
		}
		return ret;
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




	@Override
	public void drawSelf() {
		// TODO ?
		if(tile!=null) //nullexc volt
		imageholder.setBounds(tile.getCenter()[0]-24, tile.getCenter()[1]-24, 48, 48);

	}
}
