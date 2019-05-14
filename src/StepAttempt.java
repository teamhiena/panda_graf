

public class StepAttempt {
    Animal animal;
    Tile tile;

    public StepAttempt(Animal a,Tile t){
        tile=t;
        animal=a;
    }
    public Steppable getAnimal(){return animal;}
    public Tile getTIle(){return tile;}
    //public Game getGame() {return game;}

    public boolean tryToStep(){
        boolean success=false;
        if(animal.getTile().getAnimal()==null) { //ha meghalt pl de ezt ellenorizd
            success=animal.spawn(tile);
        }else {
            success=animal.step(tile);
            animal.setDirection(null);
        }
        return success;
    }

    @Override
    public boolean equals(Object param){
        StepAttempt paramSA=(StepAttempt) param;
        return (paramSA.getAnimal()==animal);
    }
}
