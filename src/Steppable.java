/**
 * A step() fuggvenyt megvalosito osztalyok interfesze.
 */
public interface Steppable {
    public boolean step(Tile t);
    public boolean step();
    public void setGameFrame(GameFrame g);
    public void animate(Tile from, Tile to);
}
