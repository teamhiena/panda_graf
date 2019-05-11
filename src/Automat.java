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
		for(Panda sp: tile.getSubbedPandas())
			sp.affectedBy(this);
	}

	@Override
	public void drawSelf() {
		imageholder.setBounds(tile.getCenter()[0], tile.getCenter()[1], 30, 30);
	}
}
