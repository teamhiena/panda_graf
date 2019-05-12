import java.util.ArrayList;
import javax.swing.JLabel;

public abstract class Animal extends IDrawable implements Steppable{
    protected Tile tile; //Ezen all az allat.
    protected Panda followedBy=null; //Ez az allat koveti.
    protected Animal following=null; //Ezt az allatot koveti.
    
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
        this.getTile().setAnimal(null);
        this.setTile(null);

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
}
