import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;


public class GamePanel extends JPanel {
	
	private Dimension size=new Dimension(800,550);
	
	private int stepvalue=13;
	private int step=(size.height-20)/stepvalue;

	private int tilesperrow = 18;
	private int tilespercol = 13;
	
	private Triangle[][][] triangles=new Triangle[tilespercol][tilesperrow][4];
	private ArrayList<Tile> tiles=new ArrayList<Tile>();
	
	public ArrayList<Tile> getTiles(){
		return tiles;
	}
	
	public GamePanel() {
		super();
		setPreferredSize(size);
		setBackground(Color.WHITE);
		initTriangles();
		
		/**
		 * ezt a sort kell kikommentezni, hogy eltûnjön a csúnya hiba.
		 * **/
		loadMap("testData");
		
		repaint();

	}
	public void loadMap(String filename) {
		try
        {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
 
            tiles = (ArrayList<Tile>) ois.readObject();
 
            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
	}
	
	
	public Dimension getSize() {
		return size;
	}
	
	public void addTile(Tile t) {
		tiles.add(t);
	}
	
	public Triangle getTriangleAt(int i, int j, int k) {
		return triangles[i][j][k];
	}
	
	public void initTriangles(){ //top: 0;right: 1; bottom: 2; left: 3
		Dimension base=new Dimension(0,0);
		int[] x4=new int[4];
		int[] y4=new int[4];
		int[] x3=new int[3];
		int[] y3=new int[3];
		for(int i=0;i<tilespercol;i++) {
			base.width=0;
			for(int j=0;j<tilesperrow;j++) {
				x4[0]=base.width;
				y4[0]=base.height;
				x4[1]=base.width+step;
				y4[1]=base.height;
				x3[2]=base.width+step/2;
				y3[2]=base.height+step/2;	
				x4[3]=base.width;
				y4[3]=base.height+step;
				x4[2]=base.width+step;
				y4[2]=base.height+step;

				for(int k=0;k<4;k++){
					x3[0]=x4[k];
					y3[0]=y4[k];
					x3[1]=x4[(k+1)%4];
					y3[1]=y4[(k+1)%4];
					triangles[i][j][k]=new Triangle(x3,y3);
				}
				base.width+=step;
				//System.out.println(i+" "+j);
			}
			base.height+=step;
		}
	}
	
	//háromszögek körvonalukkal
	@Override
	public void paintComponent(Graphics g) {
		//minden triangle körvonalat megrajzol, majd filleli a kész tileokat.
		super.paintComponent(g);
		for(Triangle[][] t3: triangles)
			for(Triangle[] t2:t3)
				for(Triangle t:t2)
					t.draw(g);
		
		for(Tile t: tiles)
			t.fill(g);		
	}

}

