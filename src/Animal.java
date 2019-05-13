import java.util.ArrayList;
import javax.swing.JLabel;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Animal extends IDrawable implements Steppable{
    protected Tile tile; //Ezen all az allat.
    protected Panda followedBy=null; //Ez az allat koveti.
    protected Animal following=null; //Ezt az allatot koveti.
    protected GameFrame gf;
    @Override
    public void setGameFrame(GameFrame g) {gf=g;}
    
    //JLabel imageholder;

    //KONSTRUKTOROK
    public Animal(Tile t) {
        tile=t;
    }
    public Animal() {
        tile=null;
    }

    //METODUSOKK
    /**
     * Ez a metodus hivodik meg, amikor az allat "meghal".
     */
    //METODUSOKK
    /**
     * Ez a metodus hivodik meg, amikor az allat "meghal".
     */
    public void die(){
        boolean success = false;
        while(!success){
            Random rng = new Random();
            Integer idx =rng.nextInt(gf.gp.getTiles().size());
            if(gf.gp.getTiles().get(idx).getAnimal() == null && gf.gp.getTiles().get(idx).getEntity() == null) {
                success = spawn(gf.gp.getTiles().get(idx)); break;
            }
        }

    }
    public boolean spawn(Tile t){
        if (t.getAnimal()!=null || t.getEntity()!= null)
            return false;

        t.setAnimal(this);
        setTile(t);
        return true;
    }
    /**
     * Tile adattag getter/setter fuggvenye.
     */
	public void setTile(Tile t) { tile = t; }
	public Tile getTile() { return tile; }
    /**
    * Following adattag setter fuggvenye.
    */
	public void setFollowing(Animal p) { following = p; }

    /**
     * isFollowing adattag getter/setter fuggvenye.
     */
    public boolean isFollowing() {
        return following != null;
    }
    /**
     * FollowedBy adattag setter fuggvenye.
     */
    public void setFollowedBy(Panda p) { followedBy = p; }

    /**
     * isFollowedBy adattag getter/setter fuggvenye.
     */
    public boolean isFollowedBy() {
        return followedBy != null;
    }

    public void affectedBy(Arcade a){ } //gombi szombat 22:52
    public void affectedBy(Automat a) { } //gombi szombat 22:52

    /**
     * Az allatot elkapja egy Panda. Valojaban lehetetlen esemeny, de muszaj megvalositani.
     * @param p
     */
    public boolean getCaughtBy(Panda p){
        return false;
    }

    /**
     * Az allatot elkapja egy orangutan. Leszarmazottakban felulirando.
     */
    public abstract boolean getCaughtBy(Orangutan o);
    public Panda getFollowedBy(){ return followedBy; }
    public Animal getFollowing() { return following; }
    public void setImageHolder(JLabel l) {imageholder = l;}


    @Override
    public void animate(Tile from, Tile to){
        if(gf==null) return;
        int distx=from.getCenter()[0]-to.getCenter()[0];
        int disty=from.getCenter()[1]-to.getCenter()[1];
        int stepnr=10;
        double stepx=distx/stepnr;
        double stepy=disty/stepnr;
        //System.out.println("animate");
        for(int i=1;i<stepnr;i++){
            imageholder.setBounds(tile.getCenter()[0]-24+(int)stepx*i, tile.getCenter()[1]-24+(int)stepy*i, 48, 48);
            gf.repaint();
        }
    }
}
