import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Orangutan extends Animal {
    private int score = 0;
    private Game game;

    private Game.Direction direction=null;

    public Orangutan(Game g) {
        g.addOrangutan(this);
        game = g;
    }

    /*public Orangutan() {

    }*/

    //METODUSOK
    /**
     * @param t(Tile): Errre a mezore szeretnenk leptetni az orangutant.
     * @return Megadja, hogy sikerult-e a muvelet.
     */
    @Override
    public boolean step(Tile t) {
        //ha elkap valakit akkor nem kell lepni a tobbi pandanak
        boolean success=t.receiveAnimal(this);
        /*if(success)
        {
        	if(followedBy!=null)
        		followedBy.setNextTile(tile);
        }*/
        return success;
    }

    /**
     * A megadott mertekben noveli a jatekos(orangutan) pontszamat.
     */
    public void increaseScore(int p) {
        score += p;
        //Minden novelesnel megnezzuk, hogy elertuk-e a gyozelem szukseges pandaszamot.
        if(score >= 25 && game.getSelectedMode() == Game.GameMode.FinitPanda){
            //Ha elertuk, szolunk a jateknak hogy vege.
            game.SaveHighScore(score);
            game.gameOver();
        }
    }

    public int getPandaNum() {
        //return mindfuck recursive fuggveny(?) nem hinnem hogy szukseg van most ra (M)
        //TODO
        int ret = 0;
        return ret;
    }

    /**
     * A bejarathoz helyezi az orangutant.
     */
    public void goToEntry() {
        this.step(GameMap.instance().getEntryTile());
    }

    /**
     * A score adattag getter fuggvenye.
     */
    public int getScore() { return score; }

    /**
     * Ertelemszeruen, egy orangutan nem kaphat el egy masik orangutant.
     */
    @Override
    public boolean getCaughtBy(Orangutan o) {
        return false;
    }

    /**
     * A game adattag setter fuggvenye.
     */
    public void setGame(Game game) {
        this.game = game;
    }
    /**
     * Elengedi az ot koveto pandak kezet.
     */
    public void releasePandas()
    {
        if(followedBy!=null)
        {
            followedBy.release();
            followedBy = null;
        }
    }

	@Override
	public void drawSelf() {
		// TODO multiplayerben esetleg megkulonboztetni a 2 jatekost ikonokkal
		imageholder.setBounds(tile.getCenter()[0], tile.getCenter()[1], 30, 30);
	}

	//--------KEYBOARD-------

    enum Controls{
        arrows,wasd
    }

    public Controls controls =Controls.wasd;

    public void setControls(Controls controls) {this.controls = controls;}
    public Controls getControls() {return controls;}

    public class KeyListener implements java.awt.event.KeyListener{


        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode=e.getKeyCode();
            if(controls==Controls.wasd){
                if(keyCode==KeyEvent.VK_W){

                }
            }
            else if(controls==Controls.arrows){ //mas nem is lehet de latszodjon

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
