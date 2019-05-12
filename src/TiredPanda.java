import java.util.ArrayList;

/**
 * A faradekony pandat(TiredPanda) megvalosito osztaly.
 */
public class TiredPanda extends Panda {

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

		if (isFollowing()){
			Panda p = this.followedBy;
			this.getFollowing().setFollowedBy(null);
			release();
			while(p != null){
				Panda b = p.followedBy;
				p.release();
				p = b;
			}
		}

		f.resetTimeLeft();
		f.setEnteredFrom(tile);
		return true;
	}

	@Override
	public void drawSelf() {
		// TODO ?
		imageholder.setBounds(tile.getCenter()[0], tile.getCenter()[1], 30, 30);
	}
}
