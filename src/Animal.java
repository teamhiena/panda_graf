import javax.swing.JLabel;

public abstract class Animal extends IDrawable implements Steppable{
    protected Tile tile; //Ezen all az allat.
    protected Panda followedBy=null; //Ez az allat koveti.
    protected Animal following=null; //Ezt az allatot koveti.
    protected GameFrame gf;

    public GameFrame getGameFrame(){return gf;}

    protected Game.Direction direction=null;

    public void setDirection(Game.Direction dir){direction=dir;}
    public Game.Direction getDirection(){return direction;}

    @Override
    public void setGameFrame(GameFrame g) {gf=g;}

    //KONSTRUKTOROK
    public Animal(Tile t) {
        tile=t;
    }
    public Animal() {
        tile=null;
    }

    //METODUSOKK
    /**
     * Elengedi az ot koveto ill. annak a pandanak a kezet akit o kovet.
     */
    public void releasePandas()
    {
        if(isFollowing())
            following.setFollowedBy(null);

        following = null;

        if(isFollowedBy()){
            followedBy.releasePandas();
        }

    }
    /*
    ** A parameterben kapott Tile-ra ra akarunk spawnolni
     */
    public boolean spawn(Tile t){
        if (t.getAnimal()!=null || t.getEntity()!= null)
            return false;

        t.setAnimal(this);
        setTile(t);
        return true;
    }
    public abstract void die();

    /**
     * Megmondja, hogy kovetunk-e valakit/kovet-e minket valaki.
     */
    public boolean isFollowing() {
        return following != null;
    }
    public boolean isFollowedBy() {
        return followedBy != null;
    }

    /*
     * Az allatot megijeszti egy jatekgep/automata
     */
    public void affectedBy(Arcade a){ }
    public void affectedBy(Automat a) { }
    /**
     * Getter/setter fuggvenyek
     */
	public void setTile(Tile t) { tile = t; }
	public Tile getTile() { return tile; }
	public void setFollowing(Animal p) { following = p; }
    public void setFollowedBy(Panda p) { followedBy = p; }
    public Panda getFollowedBy(){ return followedBy; }
    public Animal getFollowing() { return following; }
    public void setImageHolder(JLabel l) {imageholder = l;}

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

    /*
     * Az allat kirajzolasahoz szukseges
     */
    @Override
    public void animate(Tile from, Tile to){
        if(gf==null) return;
        int distx=from.getCenter()[0]-to.getCenter()[0];
        int disty=from.getCenter()[1]-to.getCenter()[1];
        int stepnr=10;
        double stepx=distx/stepnr;
        double stepy=disty/stepnr;
        for(int i=1;i<stepnr;i++){
            imageholder.setBounds(tile.getCenter()[0]-24+(int)stepx*i, tile.getCenter()[1]-24+(int)stepy*i, 48, 48);
            gf.repaint();
        }
    }
}
