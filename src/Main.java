import java.util.HashMap;
import java.util.Scanner;

/**
 * A prototipushoz tartozo Main osztaly, mely tartalmazza a main() fuggvenyt.
 */
public class Main {
	public static void main(String[] args) {

		Game game = new Game();
		GameFrame gf = new GameFrame();
		GameMap gameMap = new GameMap();

		Menu menu = new Menu(game, gf);

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