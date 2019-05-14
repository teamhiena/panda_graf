import java.util.ArrayList;

/**
 * A csokiautomatat(Automat) megvalosito osztaly. Az osztalyba nem lehet belepni.
 */
public class Automat extends NonEnterableEntity{
	/**
	 * Minden, a kornyezo mezokon talalhato pandanak meghivja az affectedBy() fuggvenyet.
	 */
	@Override
	public void makeEffect() {
		ArrayList<Tile> al = this.getTile().getNeighbors();
		for(int i = 0; i < al.size(); i++){
			Animal p =  al.get(i).getAnimal();
			if(al.get(i).getAnimal() != null) p.affectedBy(this);
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
