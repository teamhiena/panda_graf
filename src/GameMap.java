import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A jatekban hasznalt terkepet megvalosito osztaly.
 */

public class GameMap {
	private static GameMap instance = null;
	private HashMap<GameMap.Key,ArrayList<Tile>> listGetterMap=new HashMap<GameMap.Key,ArrayList<Tile>>();
	private Tile entry;
	private ExitTile exit = new ExitTile();

	//KONSTRUKTOROK
	public GameMap() {
		listGetterMap.put(Key.WeakTile, new ArrayList<Tile>());
		listGetterMap.put(Key.Arcade, new ArrayList<Tile>());
		listGetterMap.put(Key.Automat, new ArrayList<Tile>());
		listGetterMap.put(Key.Fotel, new ArrayList<Tile>());
		listGetterMap.put(Key.Wardrobe, new ArrayList<Tile>());
		listGetterMap.put(Key.WardrobeExit, new ArrayList<Tile>());
	}

	//Singleton miatt szukseges
	static public GameMap instance() {
		if (instance == null) instance = new GameMap();
		return instance;
	}

	public void finalize() {
		instance = null;
	}

	//A mapban tarolt elemek azonositasara hasznalt kulcsok enumja
	enum Key{
		WeakTile,
		Arcade,
		Automat,
		Fotel,
		Wardrobe,
		WardrobeExit,
	}

	/**
	 * Visszater egy veletlenszeruen kivalasztott szekreny kijarattal.
	 */
	public Tile getRandomWardrobeExitTile() {
		Random rng = new Random();
		ArrayList<Tile> exits=getSpecificTiles(Key.WardrobeExit);
		if (exits.size()<2)
			return null;
		Integer idx=rng.nextInt(exits.size());
		Tile ret=exits.get(idx);
		return ret;
	}

	/**
	 * Visszaadja az parameterkent kapott tipusu csempeket.
	 */
	public ArrayList<Tile> getSpecificTiles(GameMap.Key key){
		ArrayList<Tile> ret = listGetterMap.get(key);
		return ret;
	}

	//Hozzaad egy specialis dolgot a maphoz
	public void addSpecificTile(Tile t,Key key) {
		listGetterMap.get(key).add(t);
	}

	/**
	 * Getter/setter fuggvenyek
	 */
	public void setEntry(Tile e){
		entry = e;
	}
	public void setExit(ExitTile e) {exit=e;}
	public Tile getExitTile() { return exit; }
	public Tile getEntryTile() { return entry; }
}
