import java.util.ArrayList;

/**
 * A "kilepo" csempe.
 */
public class ExitTile extends Tile {
    @Override
    public boolean receiveAnimal(Orangutan o) {
        if(o.followedBy != null) {
            o.increaseScore(10*o.getPandaNum());
            o.goToEntry();//  ha ez egyaltalan meg lesz csinalva

            Panda a = o.followedBy;
            if (a != null) {
                while (a != null) {
                    Panda b = a.followedBy;
                    a.setFollowing(null);
                    a.die();
                    a = b;
                }
            }
            o.releasePandas();
            //mi tortenik
            return false;
        }
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
    @Override
    public boolean receiveAnimal(Panda p) {
        if(p.following != null) {
            p.die();
            p.release();
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
