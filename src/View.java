import java.util.ArrayList;

/**
 * A rajzolófüggvényeket tartalmazó osztály
 */
public class View {
	private ArrayList<IDrawable> drawables = new ArrayList<IDrawable>();
	
	public void addDrawable(IDrawable i) { drawables.add(i);}
	public ArrayList<IDrawable> getDrawables(){ return drawables;}
}
