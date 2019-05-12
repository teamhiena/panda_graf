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

    @Override
    public boolean step(){
        if(direction!=null)
            return step(tile.getNeighborAt(direction));
        else
            return false;
    }


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
        /**
         *
         * ha jobbra megy+fel -> jobbrafel
         * ha balra megy+fel -> balrafel
         * ha null+fel -> fel
         *
         * @param e keyevent
         */
        //TODO resetelodjon a direction step utan
        @Override
        public void keyPressed(KeyEvent e)
        {
            int keyCode=e.getKeyCode();
            if(controls==Controls.wasd){ //ha wasd a kontrol
                if(keyCode==KeyEvent.VK_W){ //w megnyomva
                    if(direction== Game.Direction.Right){ //wd
                        direction= Game.Direction.TopRight;
                    }else if(direction== Game.Direction.Left){ //wa
                        direction= Game.Direction.TopLeft;
                    }else if(direction==null){ //w
                        direction= Game.Direction.Top;
                    }
                }else if (keyCode==KeyEvent.VK_A) { //a megnyomva
                    if(direction== Game.Direction.Top){ //wa
                        direction= Game.Direction.TopLeft;
                    }else if(direction== Game.Direction.Bottom){ //sa
                        direction= Game.Direction.TopLeft;
                    }else if(direction==null){ //a
                        direction= Game.Direction.Left;
                    }
                }else if (keyCode==KeyEvent.VK_S) { //s megnyomva
                    if(direction== Game.Direction.Right){ //sd
                        direction= Game.Direction.BottomRight;
                    }else if(direction== Game.Direction.Left){ //sa
                        direction= Game.Direction.BottomLeft;
                    }else if(direction==null){ //s
                        direction= Game.Direction.Bottom;
                    }
                }else if (keyCode==KeyEvent.VK_D){ //d megnyomva
                    if(direction== Game.Direction.Top){ //wd
                        direction= Game.Direction.TopRight;
                    }else if(direction== Game.Direction.Bottom){ //sd
                        direction= Game.Direction.BottomLeft;
                    }else if(direction==null){ //d
                        direction= Game.Direction.Right;
                    }
                }
            }
            else if(controls==Controls.arrows){ //mas nem is lehet de latszodjon
                if(keyCode==KeyEvent.VK_UP){ //w megnyomva
                    if(direction== Game.Direction.Right){ //wd
                        direction= Game.Direction.TopRight;
                    }else if(direction== Game.Direction.Left){ //wa
                        direction= Game.Direction.TopLeft;
                    }else if(direction==null){ //w
                        direction= Game.Direction.Top;
                    }
                }else if (keyCode==KeyEvent.VK_LEFT) { //a megnyomva
                    if(direction== Game.Direction.Top){ //wa
                        direction= Game.Direction.TopLeft;
                    }else if(direction== Game.Direction.Bottom){ //sa
                        direction= Game.Direction.TopLeft;
                    }else if(direction==null){ //a
                        direction= Game.Direction.Left;
                    }
                }else if (keyCode==KeyEvent.VK_DOWN) { //s megnyomva
                    if(direction== Game.Direction.Right){ //sd
                        direction= Game.Direction.BottomRight;
                    }else if(direction== Game.Direction.Left){ //sa
                        direction= Game.Direction.BottomLeft;
                    }else if(direction==null){ //s
                        direction= Game.Direction.Bottom;
                    }
                }else if (keyCode==KeyEvent.VK_RIGHT){ //d megnyomva
                    if(direction== Game.Direction.Top){ //wd
                        direction= Game.Direction.TopRight;
                    }else if(direction== Game.Direction.Bottom){ //sd
                        direction= Game.Direction.BottomLeft;
                    }else if(direction==null){ //d
                        direction= Game.Direction.Right;
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
