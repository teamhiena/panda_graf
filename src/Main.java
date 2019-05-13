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
        timer.setGame(game);
        timer.setView(view);

        GameFrame gf = new GameFrame(gameMap, game, view, timer);
        timer.setGameFrame(gf);
        game.setGameFrame(gf);

        Menu menu = new Menu(game, gf, timer);

        timer.Tick();
	}
}