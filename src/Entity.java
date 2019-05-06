import java.util.ArrayList;

/**
 * Egy entitast megvalosito absztrakt ososztaly.
 */
public abstract class Entity {
	protected Tile tile;
	public abstract boolean stepIn(Orangutan a);
	public abstract boolean stepIn(Panda p);

	/**
	 * tile adattag setter fuggvenye
	 */
	public void setTile(Tile t) {
		tile=t;
	}
	public Tile getTile(){ return tile; }
}