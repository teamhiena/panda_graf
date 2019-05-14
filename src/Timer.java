import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

public class Timer {
	ArrayList<MakeEffect> Entities = new ArrayList<MakeEffect>();
	ArrayList<StepAttempt> stepAttempts=new ArrayList<StepAttempt>();
	private static Timer instance = null;
	private int elapsedTime = 0;
	private Game game;
	private GameMap gamemap;
	private int maxTime=60;
	//TODO a foteleket is decreselni kell
	private View v;
	private GameFrame gameFrame;
	
	private boolean running = false;

	public void setRunning(boolean r){running = r;}
	public void addStepAttempt(StepAttempt attempt){stepAttempts.add(attempt);}

	static public Timer instance() {
		if (instance == null) instance = new Timer();
		return instance;
	}

	public void setGameFrame(GameFrame gf) {gameFrame=gf;}

	public void finalize() {
		instance = null;
	}

	public void Tick() {
			if(running) {
				System.out.println("Tick!");
				/*for (IDrawable id : v.getDrawables()) {
					id.drawSelf();
				}*/

				try {
					for (StepAttempt attempt : stepAttempts) {
						boolean success = attempt.tryToStep();
						if (success)
							stepAttempts.remove(attempt);
					}
				}catch (ConcurrentModificationException e){
					System.out.println("budget hibakezeles lol");
				}


				//Orangutanokat stepeljuk
				for (Orangutan o : game.getOrangutans()) {
					if (o != null && !stepAttempts.contains(new StepAttempt(o,new Tile())))
						o.step();
				}

				//Pandaknal csak azokat leptetjuk akik nem kovetnek senkit.
				try{
					for (Panda p : game.getPandas()) {
						//mindjart csinalok parameternelkuli konstruktort
						if (!p.isFollowing()&&!stepAttempts.contains(new StepAttempt(p,new Tile()))) { //vagy a listabol kiszedi
							p.step();
						}
					}
				} catch (ConcurrentModificationException e){
					System.out.println("budget hibakezeles lol");
				}

				//10% esellyel makeEffectelunk
				for (MakeEffect e : Entities) {
					Random rng = new Random();
					if (rng.nextInt(10) % 10 > 7) {
						e.makeEffect();
					}
				}

				for (IDrawable id : v.getDrawables()) {
					id.drawSelf();
				}


				increaseTime(1);
				gameFrame.repaint();
			}
			try {
				Thread.sleep(1000);
				Tick();
			} catch (InterruptedException e) {
				System.out.println("Timer Tick() failed");
				e.printStackTrace();
			}
			System.out.println(gameFrame.gm.getExitTile().getNeighbors().size());
	}


	public int getTime() { return elapsedTime; }

	public void addEntity(MakeEffect e) {
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
		if (elapsedTime >= maxTime) {
			if (game.getOrangutans().size()>1) {
				int[] scores = new int[2];
				scores[0] = game.getOrangutans().get(0).getScore();
				scores[1] = game.getOrangutans().get(1).getScore();
			}
			//Es vegul lejart az ido, game-over.
			game.gameOver();
		}
	}


	//Visszaadja a MakeEffect interfeszu entitasokat
	public ArrayList<MakeEffect> getEntities() {
		return Entities;
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
