
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
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GamePanel extends JPanel {

    View v;
    GameMap gm;
    Game g;
    Timer timer;
    GameFrame gf;
    public void setGameFrame(GameFrame g) {gf=g;}

    private Dimension size = new Dimension(800, 550);

    private int stepvalue = 13;
    private int step = (size.height - 20) / stepvalue;

    private int tilesperrow = 18;
    private int tilespercol = 13;

    private Triangle[][][] triangles = new Triangle[tilespercol][tilesperrow][4];
    private ArrayList<Tile> tiles = new ArrayList<Tile>();

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public GamePanel(GameMap gamemap, Game game, View view, Timer t, GameFrame pgf) {
        super();
        gm = gamemap;
        g = game;
        v = view;
        timer = t;
        gf=pgf;
        setPreferredSize(size);
        setBackground(Color.WHITE);
        initTriangles();

        loadMap("pandaMap.txt");
        repaint();

    }

    public void loadMap(String filename) {

        class Connection{
            String from;
            Game.Direction from_dir;
            String to;

            public Connection(String f, Game.Direction fd, String t){
                from = f; from_dir=fd; to = t;
            }
        }

        try {
            File file = new File("pandaMap.txt");
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(file));

            String row;
            ArrayList<Connection> connections = new ArrayList<>();
            HashMap<String, Tile> tilecodes = new HashMap<>();

            while ((row = br.readLine()) != null) {

                //Elsõ kommentelt sorok olvasása
                if (row.contains("#")) {
                    System.out.println("Comment: " + row);
                } else {
                    Tile newTile = new Tile();
                    String[] row_split = row.split(" ");

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
                        default:
                            break;
                    }

                    //Entity / Animal / Weak-Entry-Exit Tile beállítása

                    switch (row_split[2]) {
                        case "wa":
                            newTile.entity = new Wardrobe();
                            newTile.entity.setImageHolder(new JLabel(new ImageIcon("png/wardrobe.png")));
                            //newTile.getEntity().imageholder.setSize(new Dimension(48,48));
                            gm.addSpecificTile(newTile, GameMap.Key.Wardrobe);
                            break;
                        case "j":
                            Arcade arcade = new Arcade();
                            newTile.entity = arcade;
                            newTile.entity.setImageHolder(new JLabel(new ImageIcon("png/arcade.png")));
                            //newTile.getEntity().imageholder.setSize(new Dimension(48,48));
                            timer .addEntity(arcade);
                            gm.addSpecificTile(newTile, GameMap.Key.Arcade);
                            break;
                        case "cs":
                            Automat automat = new Automat();
                            newTile.entity = automat;
                            newTile.entity.setImageHolder(new JLabel(new ImageIcon("png/automat.png")));
                            //newTile.getEntity().imageholder.setSize(new Dimension(48,48));
                            timer.addEntity(automat);
                            gm.addSpecificTile(newTile, GameMap.Key.Automat);
                            break;
                        case "f":
                            Fotel fotel = new Fotel();
                            newTile.entity = fotel;
                            newTile.entity.setImageHolder(new JLabel(new ImageIcon("png/fotel.png")));
                            //newTile.getEntity().imageholder.setSize(new Dimension(48,48));
                            timer.addEntity(fotel);
                            gm.addSpecificTile(newTile, GameMap.Key.Fotel);
                            break;
                        case "ap":
                            AfraidPanda afraidPanda = new AfraidPanda(gm);
                            afraidPanda.setGameFrame(gf);
                            afraidPanda.spawn(newTile);
                            newTile.animal.setImageHolder(new JLabel(new ImageIcon("png/brownpanda.png")));
                            //newTile.getAnimal().imageholder.setSize(new Dimension(48,48));
                            g.addPanda(afraidPanda);
                            afraidPanda.map = gm;
                            break;
                        case "dp":
                            DiabeticPanda diabeticPanda = new DiabeticPanda(gm);
                            diabeticPanda.setGameFrame(gf);
                            diabeticPanda.spawn(newTile);
                            newTile.animal.setImageHolder(new JLabel(new ImageIcon("png/blackpanda.png")));
                            //newTile.getAnimal().imageholder.setSize(new Dimension(48,48));
                            g.addPanda(diabeticPanda);
                            diabeticPanda.map = gm;
                            break;
                        case "tp":
                            TiredPanda tiredPanda = new TiredPanda(gm);
                            tiredPanda.setGameFrame(gf);
                            tiredPanda.spawn(newTile);
                            newTile.animal.setImageHolder(new JLabel(new ImageIcon("png/redpanda.png")));
                            //newTile.getAnimal().imageholder.setSize(new Dimension(48,48));
                            g.addPanda(tiredPanda);
                            tiredPanda.map = gm;
                            break;
                        case "o":
                            Orangutan orangutan = new Orangutan(g);
                            orangutan.setGameFrame(gf);
                            orangutan.spawn(newTile);
                            newTile.animal.setImageHolder(new JLabel(new ImageIcon("png/orangutan.png")));
                            //newTile.getAnimal().imageholder.setSize(new Dimension(48,48));
                            g.addOrangutan(orangutan);
                            break;
                        case "w":
                            WeakTile new_WeakTile = new WeakTile();
                            new_WeakTile.setColor(newTile.getColor());
                            newTile = new_WeakTile;
                            gm.addSpecificTile(newTile, GameMap.Key.WeakTile);
                            break;
                        case "en":
                            Tile new_EntryTile = new Tile();
                            new_EntryTile.setColor(newTile.getColor());
                            newTile = new_EntryTile;
                            gm.setEntry(newTile);
                            break;
                        case "ex":
                            ExitTile new_ExitTile = new ExitTile();
                            new_ExitTile.setColor(newTile.getColor());
                            newTile = new_ExitTile;
                            gm.setExit(new_ExitTile);
                            break;

                        default:
                            System.out.println("Deafultra futott az Entity / Animal / Weak-Entry-Exit Tile beállítása");
                            break;
                    }

                    //Szomszédságok beállítása (iranyitas szempontjabol erdekes)
                    if(row_split.length>3){
                        for(int i =3;i<row_split.length;i++) {
                            String[] conn_split = row_split[i].split("-");
                            switch (conn_split[0]) {
                                case "tr":
                                    connections.add(new Connection(row_split[0], Game.Direction.TopRight, conn_split[1]));
                                    break;
                                case "tl":
                                    connections.add(new Connection(row_split[0], Game.Direction.TopLeft, conn_split[1]));
                                    break;
                                case "t":
                                    connections.add(new Connection(row_split[0], Game.Direction.Top, conn_split[1]));
                                    break;
                                case "r":
                                    connections.add(new Connection(row_split[0], Game.Direction.Right, conn_split[1]));
                                    break;
                                case "br":
                                    connections.add(new Connection(row_split[0], Game.Direction.BottomRight, conn_split[1]));
                                    break;
                                case "b":
                                    connections.add(new Connection(row_split[0], Game.Direction.Bottom, conn_split[1]));
                                    break;
                                case "bl":
                                    connections.add(new Connection(row_split[0], Game.Direction.BottomLeft, conn_split[1]));
                                    break;
                                case "l":
                                    connections.add(new Connection(row_split[0], Game.Direction.Left, conn_split[1]));
                                    break;
                            }
                        }
                    }


                    // Entity, Animal -> View drawables
                    if (newTile.entity != null) {
                        v.addDrawable((IDrawable) newTile.entity);
                        newTile.entity.tile = newTile;
                        add(newTile.entity.imageholder);
                    } else if (newTile.animal != null) {
                        v.addDrawable((IDrawable) newTile.animal);
                        newTile.animal.tile = newTile;
                        add(newTile.animal.imageholder);
                    }


                    //triangles beállítása
                    String trianglerow;
                    while (!(trianglerow = br.readLine()).equals("-")) {
                        String[] trianglerow_split = trianglerow.split(" ");

                        int coln = Integer.parseInt(trianglerow_split[1]);
                        int rown = Integer.parseInt(trianglerow_split[0]);
                        if (trianglerow_split[2].equals("full")) {
                            for (int i = 0; i < 4; i++) {
                                newTile.addTriangle(triangles[coln][rown][i]);
                            }
                        } else {
                            int index_lower = Integer.parseInt(trianglerow_split[2]) % 10;
                            int index_upper = Integer.parseInt(trianglerow_split[2]) / 10;
                            if (trianglerow_split[2].length() > 1) {
                                newTile.addTriangle(triangles[coln][rown][index_lower]);
                                newTile.addTriangle(triangles[coln][rown][index_upper]);
                            } else {
                                newTile.addTriangle(triangles[coln][rown][index_lower]);
                            }
                        }
                    }

                    tilecodes.put(row_split[0],newTile);
                    tiles.add(newTile);
                }
            }


            //Connections beallitasa
            for (String code : tilecodes.keySet()) {
                for (Connection c: connections) {
                    if(c.from.equals(code)){
                        tilecodes.get(code).addToNeighborMap(c.from_dir,tilecodes.get(c.to));
                        tilecodes.get(code).addNeighbor(tilecodes.get(c.to));
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
            e.printStackTrace();
        } catch (IOException e) {
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

    public void initTriangles() { //top: 0;right: 1; bottom: 2; left: 3
        Dimension base = new Dimension(0, 0);
        int[] x4 = new int[4];
        int[] y4 = new int[4];
        int[] x3 = new int[3];
        int[] y3 = new int[3];
        for (int i = 0; i < tilespercol; i++) {
            base.width = 0;
            for (int j = 0; j < tilesperrow; j++) {
                x4[0] = base.width;
                y4[0] = base.height;
                x4[1] = base.width + step;
                y4[1] = base.height;
                x3[2] = base.width + step / 2;
                y3[2] = base.height + step / 2;
                x4[3] = base.width;
                y4[3] = base.height + step;
                x4[2] = base.width + step;
                y4[2] = base.height + step;

                for (int k = 0; k < 4; k++) {
                    x3[0] = x4[k];
                    y3[0] = y4[k];
                    x3[1] = x4[(k + 1) % 4];
                    y3[1] = y4[(k + 1) % 4];
                    Triangle t = new Triangle(x3, y3);
                    triangles[i][j][k] = t;
                }
                base.width += step;
                //System.out.println(i+" "+j);
            }
            base.height += step;
        }
    }

    //háromszögek körvonalukkal
    @Override
    public void paintComponent(Graphics g) {
        //minden triangle körvonalat megrajzol, majd filleli a kész tileokat.
        super.paintComponent(g);
        for (Triangle[][] t3 : triangles)
            for (Triangle[] t2 : t3)
                for (Triangle t : t2)
                    t.draw(g);

        for (Tile t : tiles)
            t.fill(g);
    }
}

