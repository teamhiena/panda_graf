import java.util.HashMap;
import java.util.Scanner;

/**
 * A prototipushoz tartozo Main osztaly, mely tartalmazza a main() fuggvenyt.
 */
public class Main {
	public static void main(String[] args) {

		View view = new View();
		Game game = new Game();
		GameMap gameMap = new GameMap();
		GameFrame gf = new GameFrame(gameMap, game, view);
		
		Menu menu = new Menu(game, gf);

		Timer timer = Timer.instance();
		timer.setView(view);
		timer.Tick();
		
		/*
		 * Scanner scanner = new Scanner(System.in);
		 * 
		 * // EZEKNEK SINGLETONNAK KELLENE LENNI CSAK NEM TOM AZT HOGY KELL SORRY
		 * InputLanguage inlang = new InputLanguage(game, gameMap);
		 * 
		 * String input = scanner.nextLine();
		 * 
		 * while (!input.equals("exit")) { inlang.compile(input.split(" ")); input =
		 * scanner.nextLine(); }
		 * 
		 * scanner.close(); System.out.println("Viszlat!!");
		 */
	}
}