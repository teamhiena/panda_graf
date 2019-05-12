import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Tile{
    //A Tile-on all-e valamilyen entitas (arcade, automat stb). Null eseten nem talalhato ilyen.
    protected Entity entity=null;
    //A Tile-on all-e Orangutan/Panda, null eseten nincs rajta semmi.
    protected Animal animal=null;
    //A Tile szomszedos Tile-jait tarolo lista
    private ArrayList<Tile> neighbors=new ArrayList<Tile>();
    //A Tile-ra feliratkozott pandak.
    private ArrayList<Panda> subbedPandas=new ArrayList<Panda>();

    //A csempét alkotó háromszögek
	private ArrayList<Triangle> triangles=new ArrayList<Triangle>();
	//A csempe színe
	private Color color;


	//gombi vasarnap 13:50
	private HashMap<Game.Direction,Tile> neighborsMap =new HashMap<Game.Direction,Tile>();
	public void addToNeighborMap(Game.Direction dir,Tile t){
	    neighborsMap.put(dir,t);
    }
    public HashMap<Game.Direction,Tile> getNeighborsMap(){
	    return neighborsMap;
    }
    public Tile getNeighborAt(Game.Direction key){
	    return neighborsMap.get(key);
    }
    
    //METODUSOK
	/**
	 * Újonnan berakott
	 */
	public Tile() {
		//AUTO
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void addTriangle(Triangle t) {
		triangles.add(t);
		System.out.println("added, new: "+triangles.size());
	}
	
	public void fill(Graphics g) {
		for(Triangle t: triangles)
			t.fill(g, color);
	}
	
	public boolean containsTriangle(Triangle t) {
		return triangles.contains(t);
	}
	
	public Tile(Tile rhs) {
		for(Triangle t:rhs.triangles)
			triangles.add(new Triangle(t));
		color=rhs.color;
	}

	public ArrayList<Triangle> getTriangles() {
		return triangles;
	}
	
    /**
     * A Tile-rol elenged egy Animalt, azaz ezutan ures.
     */
    public void releaseAnimal(){ animal=null; }
    /*
     * Beallitja az Animal-t a Tile-ra.
     */
    public void setAnimal(Animal a) {animal=a;}
    /**
     * Atveszi a Pandat
     * Ha az adott Tile-on all allat, akkor nem veszi at az allatot
     * Ha az adott Tile-on entitas all, akkor az entitastol fugg, mi tortenik ott
     * (Fotel es Wardrobe eseten atveszi)
     * Ha nincs rajta semmi, akkor atveszi
     */
    public boolean receiveAnimal(Panda p) {
        boolean success=true;
        if(animal != null) { //ha van ott allat akk fix off
            return false;
        }
        else if (entity != null) { //ha van ott entity akkor attol fugg
            success = entity.stepIn(p);
        }
        if (success) {
            this.setAnimal(p);
            if(p.getTile().getAnimal()==p)
                p.getTile().setAnimal(null);//lehet hogy elkap egy orangutan es akkor az mar ott van, nem kell kinullazni
            p.setTile(this);
        }
        return success;
    }

    public void setEntity(Entity e) {
        entity=e;
    }


    /**
     * Atveszi a Orangutant
     * Ha az adott Tile-on all allat, akkor nem veszi at az allatot
     * Ha az adott Tile-on entitas all, akkor az entitastol fugg, mi tortenik ott
     * (Fotel es Wardrobe eseten atveszi)
     * Ha nincs rajta semmi, akkor atveszi
     */
    public boolean receiveAnimal(Orangutan o) {
        boolean success=true;
        if(entity != null)//Ha van ott entiy akk megprobalok belelepni. (ami nem fotel az return false)
            success = entity.stepIn(o);
        else if(animal != null && o.getStepCounter() >= 4) {
            success = animal.getCaughtBy(o);
        }

        if(success) {
            this.setAnimal(o);
            //o.getTile().setAnimal(o.followedBy); GOMBA szombat 12:18 szerintem ez kurja el a stepeket
            o.getTile().setAnimal(null); //GOMBA szombat 12:24 ez a fix
            o.setTile(this);
        }

        //Nincs ott allat de olyan entity van amibe (most) nem lehet belelepni
        //pl nonenterableentity vagy egy hasznalatban levo fotel
        return success;
    }

    // Eltavolitja a Pandat a Tile szomszedos Tile-jainak feliratkozoi kozul
    public void removePandaFromNeighborSubbedPandas(Panda p) {
        for(Tile nt:neighbors)
            nt.removeSubbedPanda(p);
    }
    // Panda feliratkozasa a Tile-ra
    public void addSubbedPanda(Panda p) { subbedPandas.add(p); }
    // Eltavolitja a pandat a feliratkozoi kozul
    public void removeSubbedPanda(Panda p) { subbedPandas.remove(p); }
    // Visszaadja az entitast ami rajta all.
    public Entity getEntity() { return entity; }
    // Visszaadja a szomszedos Tile-okat
    public ArrayList<Tile> getNeighbors(){ return neighbors; }
    // Visszaadja a feliratkozott pandakat
    public ArrayList<Panda> getSubbedPandas() { return subbedPandas; }
    // Visszaadja a Tile-on allo allatot.
    public Animal getAnimal() { return animal; }
    // Hozzaad egy szomszedos csempet a szomszedlistahoz
    public void addNeighbor(Tile t) { neighbors.add(t); }
    //Visszadja a feliratkozott pandakat.
    public String writeSubbedPandas(){
        String ret = "";
        for(int i = 0; i < subbedPandas.size(); i++) {
            ret += subbedPandas.get(i).toString() + " ";
        }
        return ret;
    }
    //Visszadja a szomszedokat.
    public String writeNeighbors() {
        String ret = "";
        for (int i = 0; i < neighbors.size(); i++) {
            ret += neighbors.get(i).toString() + " ";
        }
        return ret;
    }

    //Visszaadja a Tile közepét
    public int[] getCenter() {
		int[] center=new int[2];
		center[0]=0;
		center[1]=0;
		
		for(Triangle tr : triangles) {
			for(int xpos : tr.getXPoints())
				center[0]+=xpos;
			for(int ypos : tr.getYPoints())
				center[1]+=ypos;
		}
		center[0]/=triangles.size()*3;
		center[1]/=triangles.size()*3;
		//System.out.println("CENTER "+center[0]+" "+center[1]);
		return center;
	}
}
