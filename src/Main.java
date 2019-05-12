import java.util.HashMap;
import java.util.Scanner;

/**
 * A prototipushoz tartozo Main osztaly, mely tartalmazza a main() fuggvenyt.
 */
public class Main {
	public static void main(String[] args) {

		//TODO legyen singleton amit tud
		View view = new View();
		Game game = new Game();
		GameMap gameMap = new GameMap();
		GameFrame gf = new GameFrame(gameMap, game, view);
		
		Menu menu = new Menu(game, gf);
		ResultPanel rp=new ResultPanel();
		gf.setRp(rp);
		rp.setMenu(menu);
		rp.setGf(gf);

		Timer timer = Timer.instance();
		timer.setView(view);
		timer.Tick();

	}
}