import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
		
		
		 //ezt a sort kell kikommentezni, hogy eltûnjön a csúnya hiba.

		loadMap("pandaMap.txt");
		
		repaint();

	}
	public void loadMap(String filename) {
		
		try {
			File file = new File("pandaMap.txt"); 
			BufferedReader br = null;
			br = new BufferedReader(new FileReader(file));
			
			String row; 
			while ((row = br.readLine()) != null) {
				String[] codes = {"t","#"};
				
				
				//Elsõ kommentelt sorok olvasása
				if(row.contains("#")) {
					System.out.println("Comment: "+row);
				}
				else {
					Tile newTile = new Tile();
					String[] row_split = row.split(" ");
					for (String string : row_split) {
						//System.out.println(string);
					}
					//System.out.println(row);

					//Hozzáadandó Tile színének beállítása
					
					switch (row_split[1]) {
					case "k":
						newTile.setColor(Color.BLUE);
						break;
					case "z":
						newTile.setColor(Color.GREEN);
						break;
					case "p":
						newTile.setColor(Color.RED);
						break;
					case "s":
						newTile.setColor(Color.YELLOW);
						break;
					default:	break;
					}
				
					//Entity / Animal beállítása

					//TODO
					
					
					//triangles beállítása
					String trianglerow;
					while (!(trianglerow = br.readLine()).equals("-")) {
						String[] trianglerow_split = trianglerow.split(" ");
						
						int coln = Integer.parseInt(trianglerow_split[1]);
						int rown = Integer.parseInt(trianglerow_split[0]);
						if(trianglerow_split[2].equals("full")) {
							for (int i = 0; i < 4; i++) {
								
								newTile.addTriangle(triangles[coln][rown][i]);
							}	
						}
						else {
							//HIBÁS, MERT NEM MINDIG 2 TRIANGLE VAN A BEMENTNEL
							int index_lower = Integer.parseInt(trianglerow_split[2]) % 10;
							int index_upper = Integer.parseInt(trianglerow_split[2]) / 10;
							newTile.addTriangle(triangles[coln][rown][index_lower]);
							newTile.addTriangle(triangles[coln][rown][index_upper]);
						}
					}
					
					tiles.add(newTile);
				}
			}
		} 
		catch (FileNotFoundException e) {
			System.out.println("File was not found!");
			e.printStackTrace();
		} 
		catch (IOException e) {
			System.out.println("Other IOException!");
			e.printStackTrace();
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
					Triangle t = new Triangle(x3,y3);
					triangles[i][j][k]= t;
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

