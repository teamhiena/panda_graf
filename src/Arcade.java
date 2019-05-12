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
		for (int i = 0; i < al.size(); i++) {
			Animal p = al.get(i).getAnimal();
			if (al.get(i).getAnimal() != null)
				p.affectedBy(this);
		}
	}

	@Override
	public void drawSelf() {
		// más az ikonja ha csilingel mint amikor nem
		imageholder.setBounds(tile.getCenter()[0], tile.getCenter()[1], 30, 30);
	}

}
