import java.awt.*;
import java.util.ArrayList;

public class WeakTile extends Tile{
	private int numOfSteps=5;
	private boolean isBroken=false;

	//METODUSOK:
	public void reduceNumOfSteps() {
		numOfSteps--;
		if(numOfSteps<=0){
			isBroken=true;
			setColor(Color.WHITE);
		}
	}

	public void setNumOfSteps(int s){
		numOfSteps=s;
	}

	/**
	 * ha broken, die()-t hiv
	 * ha entity, stepIn()-t hiv
	 * ha allat, getCaught()-t hiv
	 */
	@Override
	public boolean receiveAnimal(Orangutan o) {
		boolean success=true;
		reduceNumOfSteps();
		if(isBroken()) {
			o.die();
			setAnimal(null);
			return false;
		}

		if(entity!=null)//ha van ott entiy akk megprobalok belelepni
			success=entity.stepIn(o); //ha nem enterable vagy panda ul benne akk false
		else if(animal!=null) {
			success=animal.getCaughtBy(o);
		}if(success) {
			this.setAnimal(o);
			//o.getTile().setAnimal(o.followedBy); GOMBA szombat 12:18 szerintem ez kurja el a stepeket
			o.getTile().setAnimal(null); //GOMBA szombat 12:24 ez a fix
			o.setTile(this);
		}

		//Nincs ott allat de olyan entity van amibe (most) nem lehet belelepni
		//pl nonenterableentity vagy egy hasznalatban levo fotel
		return success;
	}


	/**
	 * Az isBroken adattag getter fuggvenye.
	 */
	public boolean isBroken() { return isBroken; }
	/**
	 * ha broken, die()-t hiv
	 * ha allat, return false
	 * ha entity, stepIn()-t hiv
	 */
	@Override
	public boolean receiveAnimal(Panda p) {
		reduceNumOfSteps();
		if(isBroken()) {p.die();
			setAnimal(null);
			return false;
		}
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
    public int getNumOfSteps(){ return numOfSteps; }
}
