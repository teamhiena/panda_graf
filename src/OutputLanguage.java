import java.util.*;
import java.util.stream.Collectors;

//Kene valami cucc(pl. mainben) ami meghivja majd ennek az osztalynak a megfelelo writeOutput-jat.
public class OutputLanguage {


    HashMap<String,Object> variables2=new HashMap<String,Object>();

    public static <T, E, A> String getKeysByValueList(Map<T, E> map, ArrayList<A> list) {
        Set<T> keys = new HashSet<T>();
        for (Map.Entry<T, E> entry : map.entrySet()) {
            for (int i = 0; i < list.size(); i++) {
                if (Objects.equals(list.get(i), entry.getValue())) {
                    keys.add(entry.getKey());
                }
            }
        }
        int l = keys.toString().length();
        String str2 = keys.toString().substring(1, l-1);
        if(str2.equals("")) str2 = "null";
        return str2;
    }
    public static <T, E> String getKeysByValue(Map<T, E> map, E value) {
        Set<T> keys = new HashSet<T>();
        String str;
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                keys.add(entry.getKey());
            }
        }
        int l = keys.toString().length();
        str = keys.toString().substring(1, l-1);
        if(str.equals("")) str = "null";
        return str;
    }

    public void writeTile(Tile t){
        System.out.println("stats of: "+getKeysByValue(variables2,t)+"\n"
                +"entity: "+getKeysByValue(variables2,t.getEntity())+"\n"
                +"animal: "+getKeysByValue(variables2,t.getAnimal())+"\n"
                +"subbedPandas: "+getKeysByValueList(variables2,t.getSubbedPandas())+"\n"
                +"neighbors: "+getKeysByValueList(variables2,t.getNeighbors()));
    }
    public void writeWeakTile(WeakTile t){
        System.out.println("stats of: "+getKeysByValue(variables2,t)+"\n"
                +"entity: "+getKeysByValue(variables2,t.getEntity())+"\n"
                +"animal: "+getKeysByValue(variables2,t.getAnimal())+"\n"
                +"subbedPandas: "+getKeysByValueList(variables2,t.getSubbedPandas())+"\n"
                +"neighbors: "+getKeysByValueList(variables2,t.getNeighbors())+"\n"
                +"numOfSteps: "+t.getNumOfSteps()+"\n"
                +"isBroken: "+ Boolean.toString(t.isBroken()));
    }
    public void writeOrangutan(Orangutan o){
        System.out.println("stats of: "+getKeysByValue(variables2,o)+"\n"
                +"tile: "+getKeysByValue(variables2,o.getTile())+"\n"
                +"nextTile: "+getKeysByValue(variables2,o.getNextTile())+"\n"
                +"followedBy: "+getKeysByValue(variables2,o.getFollowedBy())+"\n"
                +"following: "+getKeysByValue(variables2,o.getFollowing())+"\n"
                +"score: "+o.getScore());
    }
    public void writePanda(Panda p)
    {
        System.out.println("stats of: "+getKeysByValue(variables2,p)+"\n"
                +"tile: "+getKeysByValue(variables2,p.getTile())+"\n"
                +"nextTile: "+getKeysByValue(variables2,p.getNextTile())+"\n"
                +"followedBy: "+getKeysByValue(variables2,p.getFollowedBy())+"\n"
                +"following: "+getKeysByValue(variables2,p.getFollowing()));
    }
    public void writeNonEnterableEntity(NonEnterableEntity e){
        System.out.println("stats of: "+getKeysByValue(variables2,e)+"\n"
                +"tile: "+getKeysByValue(variables2,e.getTile()));
    }
    public void writeFotel(Fotel f){
        System.out.println("stats of: "+getKeysByValue(variables2,f)+"\n"
                +"tile: "+getKeysByValue(variables2,f.getTile())+"\n"
                +"enteredFrom: "+getKeysByValue(variables2,f.getEnteredFrom())+"\n"
                +"timeleft: "+f.getTimeLeft());
    }
    public void writeWardrobe(Wardrobe w){
        System.out.println("stats of: "+getKeysByValue(variables2,w)+"\n"
                +"tile: "+getKeysByValue(variables2,w.getTile())+"\n"
                +"previousExitTile: "+getKeysByValue(variables2,w.getPreviousExitTile())+"\n"
                +"entrance: "+getKeysByValue(variables2,w.getEntrance()));
    }
}


