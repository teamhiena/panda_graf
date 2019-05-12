import java.util.ArrayList;

import javax.swing.JLabel;

/**
 * Egy entitast megvalosito absztrakt ososztaly.
 */
public abstract class Entity extends IDrawable{
	protected Tile tile;
	public abstract boolean stepIn(Orangutan a);
	public abstract boolean stepIn(Panda p);
	
	JLabel imageholder;

	/**
	 * tile adattag setter fuggvenye
	 */
	public void setTile(Tile t) {
		tile=t;
	}
	public Tile getTile(){ return tile; }
	public void setImageHolder(JLabel l) {imageholder = l;}
}