import java.util.ArrayList;

/**
 * A rajzol�f�ggv�nyeket tartalmaz� oszt�ly
 */
public class View {
	private ArrayList<IDrawable> drawables = new ArrayList<IDrawable>();
	
	public void addDrawable(IDrawable i) { drawables.add(i);}
	public ArrayList<IDrawable> getDrawables(){ return drawables;}
}
