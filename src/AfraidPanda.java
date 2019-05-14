import javax.swing.*;
import java.util.Random;

/**
 * Az ijedos pandat(AfraidPanda) megvalosito osztaly. Megijed a jatekgep csilingelsesetol es elengedi a mogotte allo
 * pandak mancsat, igy felbomlik a sor.
 */
public class AfraidPanda extends Panda {

	//KONSTRUKTOROK
	public AfraidPanda(GameMap gm){
		map = gm;
		hatesEntity = GameMap.Key.Arcade;
	}
	//METODUSOK
	/**
	 * A pandat megijeszti egy jatekgep.
	 */
	public void affectedBy(Arcade a) {
		//imageholder=new JLabel(new ImageIcon("png/veryredpanda.png"));
		//imageholder.setBounds(tile.getCenter()[0]-24, tile.getCenter()[1]-24, 48, 48);
		releasePandas();
	}


}
 