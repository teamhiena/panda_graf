/**
 * A "kilepo" csempe.
 */
public class ExitTile extends Tile {
    @Override
    public boolean receiveAnimal(Orangutan o) {
        boolean success=true;
        //if(entity != null)//Ha van ott entiy akk megprobalok belelepni. (ami nem fotel az return false)
        //   success = entity.stepIn(o);
        if(animal != null && o.getStepCounter() >= 4) {
            success = animal.getCaughtBy(o);
        }

        if(success) {
            o.increaseScore(10*o.getPandaNum());
            o.dieWithFollowers();
        }
        return false; //mindenkepp elore akarunk menni nem akarunk tile allitgatasokat ha visszaterunk a stepbe
    }
    @Override
    public boolean receiveAnimal(Panda p) {
        if(p.following != null) {
           // p.die();
            p.releasePandas();
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
}
