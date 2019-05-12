import javax.swing.*;

/**
 * Az önamgukat kirajzolni képes drawSelf() függvénnyel rendelkezõ osztályok közös interfésze
 */
public abstract class IDrawable {
	public abstract void drawSelf();
	protected JLabel imageholder;
}
