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
		for(Panda sp: tile.getSubbedPandas())
			sp.affectedBy(this);
	}

}
