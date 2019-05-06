import java.util.ArrayList;

public class WeakTile extends Tile{
	private int numOfSteps=20;
	private boolean isBroken=false;

	//METODUSOK:
	public void reduceNumOfSteps() {
		numOfSteps--;
		if(numOfSteps<=0)
			isBroken=true;
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
		reduceNumOfSteps();
		if(isBroken()) {
    		o.die();
    		return false;
    	}

    	boolean success=false;
    	if(entity!=null)//ha van ott entiy akk megprobalok belelepni
    		success=entity.stepIn(o); //ha nem enterable vagy panda ul benne akk false
    	else if(animal!=null) {
    		success=animal.getCaughtBy(o);
    	}
    	return false;
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
		if(isBroken()) {
    		p.die();
    		return false;
    	}

		boolean success=false;

		if(!isBroken){
			if(animal!=null) //ha van ott allat akk fix off
			{
				return false;
			}
			else if (entity!=null) { //ha van ott entity akkor attol fugg
				if (entity.stepIn(p)) { //bele lehet lepni
					animal=p;
				}
				else{
					return false;
				}
			}

			//Ilyenkor mar mindenkeppen be tudtunk lepni a csempere.
			this.reduceNumOfSteps();
			return true;
		}
		else if(entity!=null) { //ha van ott entity akkor attol fugg
    		success=entity.stepIn(p);		
    	}
        else{
            p.die();

            return false;
        }
		return success;

    }
    public int getNumOfSteps(){ return numOfSteps; }
}
