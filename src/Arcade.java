import java.util.ArrayList;

/**
 * A Jatekgepet(Arcade) megvalosito osztaly. Az objektumba nem lehet belepni.
 */
public class Arcade extends NonEnterableEntity{

	/**
	 * Minden, a kornyezo mezokon talalhato pandanak meghivja az affectedBy() fuggvenyet.
	 */
	@Override
	public void makeEffect() {
		ArrayList<Tile> al = this.getTile().getNeighbors();
		for(Tile t : al) {
			if(t != null) {
				Animal p = t.getAnimal();
				if (p != null)
					p.affectedBy(this);

			}
		}
	}

	/**
	 * Kirajzolja sajat magat
	 */
	@Override
	public void drawSelf() {
		imageholder.setBounds(tile.getCenter()[0]-24, tile.getCenter()[1]-24, 48, 48);
	}

}
