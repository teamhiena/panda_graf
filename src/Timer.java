import java.util.ArrayList;
import java.util.Random;

public class Timer {
	ArrayList<NonEnterableEntity> Entities = new ArrayList<>();
	ArrayList<Steppable> Steppable = new ArrayList<>();
	private static Timer instance = null;
	private int elapsedTime = 0;
	private Game game;
	private GameMap gamemap;
	Random random = new Random();
	int vel = random.nextInt(100);
	//TODO a foteleket is decreselni kell
	private View v;
	
	private boolean running = true;
	
	static public Timer instance() {
		if (instance == null) instance = new Timer();
		return instance;
	}

	public void finalize() {
		instance = null;
	}

	public void Tick() {
		while(running) {
			
			//TODO: Steppel�s, (Make)Effectel�s 
			System.out.println("Tick!");
			for (IDrawable id : v.getDrawables()) {
				id.drawSelf();
				System.out.println("drawself!");
			}
				
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Timer Tick() failed");
				e.printStackTrace();
			}
			
			elapsedTime++;
		}
	}

	public int getTime() { return elapsedTime; }

	public void addSteppable(Steppable s) {
		Steppable.add(s);
	}

	public void removeSteppable(Steppable s) {
		Steppable.remove(s);
	}

	public void addEntity(NonEnterableEntity e) {
		Entities.add(e);
	}

	//Csokkenti az idot parameterkent kapott masodperccel.
	public void decreaseTime(int t) {
		if (elapsedTime < t)
			elapsedTime = 0;
		else
			elapsedTime -= t;
	}

	/**Noveli az idot parameterkent kapott masodperccel es megnezi lejart-e az ido, ha igen cselekszik
	 */
	public void increaseTime(int t) {
		elapsedTime += t;

		//Minden eltelt Tick-re pollingoljuk, hogy lejart-e az ido hogy nyert-e az Orangutan
		if (elapsedTime >= 60 && game.getSelectedMode() == Game.GameMode.FinitTime) {
			if (game.getOrangutans().size()>1){

				int[] scores=new int[2];
				scores[0]=game.getOrangutans().get(0).getScore() ;
				scores[1]=game.getOrangutans().get(1).getScore() ;

				game.SaveHighScore(scores[0]>scores[1] ? scores[0] : scores[1]);
			} else {
				game.SaveHighScore(game.getOrangutans().get(0).getScore());
			}

			//Es vegul lejart az ido, game-over.
			game.gameOver();
		}
	}


	//Visszaadja a NonEnterableEntity interfeszu entitasokat
	public ArrayList<NonEnterableEntity> getEntities() {
		return Entities;
	}

	//Visszaadja a steppable interfeszu entitasokat
	public ArrayList<Steppable> getSteppables() {
		return Steppable;
	}

	/**
	 * A gamemap valtozo setter illetve getter fuggvenyei.
	 */
	public void setGamemap(GameMap gamemap) {
		this.gamemap = gamemap;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public void setView(View view) {
		v = view;
	}
}
