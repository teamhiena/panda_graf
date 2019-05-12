import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Orangutan extends Animal {
    private int score = 0;
    private int stepCounter = 4;
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
        //TODO a nexttile resetjén gondolkozni
        Tile temp=tile;
        boolean success = t.receiveAnimal(this);

        if(success){
            stepCounter++;
            if(isFollowedBy()){
                followedBy.step(temp);
            }
        }
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
        Panda a = this.followedBy;
        int num = 1;
        if (a != null) {
            while (a.followedBy != null) {
                num++;
                a = a.followedBy;
            }
            return num;
        }
        else return 0;
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
    public boolean getCaughtBy(Orangutan o)
    {
        if(o.getFollowedBy() != null || followedBy == null) return false; //Ha az elkaponak vannak pandai vagy nekunk nincsenek pandaink --> nem tortenik semmi

        //Az orangutanok helyet cserelnek.
        Tile temp = tile; //Az aktualis orangutan Tile-ja megy a temp-be.
        tile = o.getTile();
        o.setTile(temp);
        o.getTile().setAnimal(o);
        tile.setAnimal(this);

        //Atadjuk a pandakat.
        o.setFollowedBy(followedBy);
        followedBy.setFollowing(o);
        followedBy = null;

        //Nullaznunk kell a lepesszamlalot.
        stepCounter = 0;

        return false; //Ha eddig eljutottunk, mindenkeppen sikeres a belepes.
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
    public int getStepCounter(){return stepCounter;}
    public void increaseCounter(){stepCounter++;}

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
