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
		Timer timer = Timer.instance();
		GameFrame gf = new GameFrame(gameMap, game, view, timer);
		
		Menu menu = new Menu(game, gf);
		ResultPanel rp=new ResultPanel();
		gf.setRp(rp);
		rp.setMenu(menu);
		rp.setGf(gf);

		timer.setGame(game);
		timer.setView(view);
		timer.Tick();

	}
}