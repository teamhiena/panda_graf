import java.util.Random;

/**
 * A faradekony pandat(TiredPanda) megvalosito osztaly.
 */
public class TiredPanda extends Panda {

	private int stepCounter=5;
	public void incrementStepCounter(){stepCounter++;}
	public void resetStepCounter(){stepCounter=0;}
	public int getStepCounter(){return stepCounter;}

	//KONSTRUKTOROK
	public TiredPanda(GameMap gm) {
		hatesEntity=GameMap.Key.Fotel;
		map = gm;
	}

    /*public TiredPanda() {

    }*/

	//METODUSOK

	/**
	 * A Panda egy f Fotel hatasa ala kerul.
	 */
	public boolean affectedBy(Fotel f) {
		//TODO mi van ha followoljak???

		//f.getTile().setAnimal(this);
		//tile.setAnimal(this);
		//tile=f.getTile();

		if(stepCounter<5) return false;
		if (isFollowing()){
			Panda p = this.followedBy;
			this.getFollowing().setFollowedBy(null);
			releasePandas();
		}
		resetStepCounter();
		f.resetTimeLeft();
		f.setEnteredFrom(tile);
		return true;
	}

	@Override
	public boolean step(){
		boolean ret=false;
		if(!isFollowing()){
			//80% valoszinuseggel leptet egy szomszedra
			Random rng=new Random();
			boolean doAStep =rng.nextInt(10)>1;
			if(doAStep){
				if(tile != null){
					int bound=tile.getNeighbors().size();
					//System.out.println("bound :"+bound);
					//System.out.println("ez lett a bound: "+rng.nextInt(bound));
					ret=step(tile.getNeighbors().get(rng.nextInt(bound)));
				}
			}
		}
		stepCounter++;
		return ret;
	}
}
