import java.util.ArrayList;

/**
 * Azon osztalyokhoz tartozo absztrakt ososztaly, amelyekbe nem lehet belepni.
 */
public abstract class NonEnterableEntity extends Entity implements MakeEffect{

	/**
	 * Ertelemszeruen, ha egy ilyen osztalyba egy orangutan/panda probal belepni, ez nem lesz lehetseges.
	 */
	@Override
	public boolean stepIn(Orangutan o) {
		return false;
	}
	@Override
	public boolean stepIn(Panda p) {
		return false;
	}
}
