import java.util.ArrayList;
import java.util.HashMap;

public class InputLanguage {

    OutputLanguage outputLanguage = new OutputLanguage();
    private Game game;
    private GameMap gameMap;

    public InputLanguage(Game g,GameMap gm){
        game=g;
        gameMap=gm;
        init();
    }

    public HashMap<String,Object> variables=new HashMap<String,Object>();

    public void addVariable(String key, Object object){
        if(variables.get(key)!=null) {
            System.out.println("A variable with the same name already exists");
        }
        else
            variables.put(key, object);
    }

    public Object getVariable(String key){
        Object ret=null;
        try{
            ret=variables.get(key);
        }
        catch(Exception e){
        }
        if (ret==null){

            System.out.println("Variable '"+key+"'does not exist");
            return null;
        }
        return  ret;

    }

    class Node{

        private String description="";
        //Type type;
        private HashMap<String,Node> next=new HashMap<String,Node>();

        public Node(String d){
            description=d;

        }
        public Node() {};

        public void addNext(Node n) {
            next.put(n.description,n);
        }

        public Object search(String key) {
            Object ret;
            //System.out.println("Looking for "+key+" in "+description);
            try{
                ret=next.get(key);
            }catch (Exception e){
                return null;
            }
            return ret;
        }

        public Object execute(Object p){return null;}
    }

    class Leaf extends Node{
        public Object execute(Object o_param){return null;}
        public Leaf(String p){
            super(p);
        }
    }

    Node root=new Node("root");


    public Node init() {

        Node tmp;
        Node newNode;
        //INIT ROOT
        tmp=new Node("new");
        root.addNext(tmp);
        root.addNext(new Node("step"));
        root.addNext(new Node("set"));
        root.addNext(new Node("stat"));
        root.addNext(new Node("release"));
        root.addNext(new Node("makeeffect"));
        root.addNext(new Node("output"));
        //root.addNext(new Node("test"));

            //INIT NEW
            newNode = new Node("tile");
            tmp.addNext(newNode);
            tmp.addNext(new Leaf("orangutan") {
            public Object execute(Object o_param) {
                String[] parameters=(String[]) o_param;

                Orangutan o=new Orangutan(game);
                Tile t=(Tile)getVariable(parameters[3]);
                if(t==null){
                    return null;
                }
                o.setTile(t);
                t.setAnimal(o);
                addVariable(parameters[2],o);
                //System.out.println("CREATING ORANGUTAN ; name="+parameters[3]);

                return o;
            }
            });
            tmp.addNext(new Node("panda"));
            tmp.addNext(new Node("entity"));
            tmp=newNode;

                //INIT NEW/TILE
                newNode=new Leaf("weak"){
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;

                        WeakTile t=new WeakTile();
                        addVariable(parameters[3],t);
                        //System.out.println("CREATING WEAK TILE ; name="+parameters[3]);
                        return t;
                    }
                };
                tmp.addNext(newNode);
                tmp.addNext(new Leaf("empty") {
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;

                        Tile t=new Tile();
                        addVariable(parameters[3],t);
                        //System.out.println("CREATING TILE ; name="+parameters[3]);
                        return t;
                    }
                });
                tmp.addNext(new Leaf("entry") {
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;

                        EntryTile t=new EntryTile();
                        addVariable(parameters[3],t);
                        //System.out.println("CREATING ENTRY TILE ; name="+parameters[3]);
                        return t;
                    }
                });
                tmp.addNext(new Leaf("exit") {
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;

                        ExitTile t=new ExitTile();
                        addVariable(parameters[3],t);
                        ///System.out.println("CREATING EXIT TILE ; name="+parameters[3]);
                        return t;
                    }
                });

                //INIT NEW/ENTITY
                tmp=root.next.get("new").next.get("entity");
                tmp.addNext(new Leaf("wardrobe") {
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;

                        Wardrobe w=new Wardrobe();
                        Tile t=(Tile)getVariable(parameters[4]);
                        if(t==null){
                            return null;
                        }
                        w.setTile(t);
                        t.setEntity(w);
                        t=(Tile)getVariable(parameters[5]);
                        if(t==null){
                            return null;
                        }

                        w.setEntrance(t);
                        addVariable(parameters[3],w);
                        //System.out.println("CREATING WARDROBE ENTITY ; name="+parameters[3]);
                        return w;
                    }
                });
                tmp.addNext(new Leaf("arcade") {
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;

                        Arcade a=new Arcade();Tile t=(Tile)getVariable(parameters[4]);
                        if(t==null){
                            return null;
                        }
                        a.setTile(t);
                        t.setEntity(a);
                        addVariable(parameters[3],a);
                        //System.out.println("CREATING ARCADE ENTITY ; name="+parameters[3]);
                        return a;
                    }
                });
                tmp.addNext(new Leaf("automat") {
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;

                        Automat a=new Automat();Tile t=(Tile)getVariable(parameters[4]);
                        if(t==null){
                            return null;
                        }
                        a.setTile(t);
                        t.setEntity(a);
                        addVariable(parameters[3],a);
                        //System.out.println("CREATING AUTOMAT ENTITY ; name="+parameters[3]);
                        return a;
                    }
                });

                tmp.addNext(new Leaf("fotel") {
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;

                        Fotel f=new Fotel();
                        Tile t=(Tile)getVariable(parameters[4]);
                        if(t==null){
                            return null;
                        }
                        f.setTile(t);
                        t.setEntity(f);
                        addVariable(parameters[3],f);
                        //System.out.println("CREATING ARCADE ENTITY ; name="+parameters[3]);
                        return f;
                    }
                });

                //INIT NEW/PANDA
                tmp=root.next.get("new").next.get("panda");
                tmp.addNext(new Leaf("diabetic") {
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;

                        DiabeticPanda p=new DiabeticPanda(gameMap);
                        Tile t=(Tile)getVariable(parameters[4]);
                        if(t==null){
                            return null;
                        }
                        p.setTile(t);
                        t.setAnimal(p);
                        addVariable(parameters[3],p);
                        //System.out.println("CREATING DIABETIC PANDA ; name="+parameters[3]);
                        return p;
                    }
                });
                tmp.addNext(new Leaf("tired") {
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;

                        TiredPanda p=new TiredPanda(gameMap);
                        Tile t=(Tile)getVariable(parameters[4]);
                        if(t==null){
                            return null;
                        }
                        p.setTile(t);
                        t.setAnimal(p);
                        addVariable(parameters[3],p);
                        //System.out.println("CREATING TIRED PANDA ; name="+parameters[3]);
                        return p;
                    }
                });
                tmp.addNext(new Leaf("afraid") {
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;
                        AfraidPanda p=new AfraidPanda(gameMap);
                        Tile t=(Tile)getVariable(parameters[4]);
                        if(t==null){
                            return null;
                        }
                        p.setTile(t);
                        t.setAnimal(p);
                        addVariable(parameters[3],p);
                        //System.out.println("CREATING AFRAID PANDA ; name="+parameters[3]);
                        return p;
                    }
                });

            //INIT STEP
            tmp=root.next.get("step");
            tmp.addNext(new Leaf("panda") {
                public Object execute(Object o_param) {
                    String[] parameters=(String[]) o_param;
                    Panda p=(Panda)getVariable(parameters[2]);
                    if(p==null){
                        return null;
                    }
                    Tile t=(Tile)getVariable(parameters[3]);
                    if(t==null){
                        return null;
                    }
                    //System.out.println("STEPPING "+parameters[2]+" to "+parameters[3]);
                    p.step(t);
                    return p;
                }
            });
            tmp.addNext(new Leaf("orangutan") {
                public Object execute(Object o_param) {
                    String[] parameters=(String[]) o_param;
                    Orangutan o=(Orangutan)getVariable(parameters[2]);
                    if(o==null){
                        System.out.println("Variable '"+parameters[2]+"' does not exist");
                        return null;
                    }
                    Tile t=(Tile)getVariable(parameters[3]);
                    if(t==null){
                        System.out.println("Variable '"+parameters[3]+"' does not exist");
                        return null;
                    }
                    //System.out.println("STEPPING "+parameters[2]+" to "+parameters[3]);
                    o.step(t);
                    return o;
                }
            });

            //INIT SET
            tmp=root.next.get("set");
            tmp.addNext(new Leaf("neighbor") {
                public Object execute(Object o_param) {
                    String[] parameters=(String[]) o_param;

                    Tile t=(Tile)getVariable(parameters[2]);
                    if(t==null){
                        return null;
                    }
                    Tile nt;
                    //System.out.println("SETTING NEIGHBORS OF : "+parameters[2]);
                    for (int i=3;i<parameters.length;i++){
                        nt=(Tile)getVariable(parameters[i]);
                        if(nt==null){
                            return null;
                        }
                        else{
                            t.addNeighbor(nt);
                            nt.addNeighbor(t);
                        }
                    }
                    return t;
                }
            });
            tmp.addNext(new Leaf("numOfSteps") {
                public Object execute(Object o_param) {
                    String[] parameters=(String[]) o_param;

                    WeakTile t=(WeakTile)getVariable(parameters[2]);
                    if(t==null){
                        return null;
                    }
                    t.setNumOfSteps(Integer.parseInt(parameters[3]));
                    //System.out.println("SETTING NUMOFSTEPS OF : "+parameters[2]+" to : "+parameters[3]);
                    return t;
                }
            });

            tmp.addNext(new Leaf("fotelTimeLeft") {
                public Object execute(Object o_param) {
                    String descr=(String) o_param;
                    String[] parameters=(String[]) o_param;

                    Fotel f=(Fotel)getVariable(parameters[2]);
                    if(f==null){
                        return null;
                    }
                    f.setTimeLeft(Integer.parseInt(parameters[3]));
                    //System.out.println("SETTING TIMELEFT OF : "+parameters[2]+" to : "+parameters[3]);
                    return f;
                }
            });

            //INIT RELEASE
            tmp=root.next.get("release");
            tmp.addNext(new Leaf("orangutan") {
                public Object execute(Object o_param) {
                    String[] parameters=(String[]) o_param;

                    Orangutan o=(Orangutan)getVariable(parameters[2]);
                    if(o==null){
                        return null;
                    }
                    o.releasePandas();
                    //System.out.println("RELEASING PANDAS OF "+parameters[2]);
                    return o;
                }
            });

            //INIT MAKEEFFECT
            tmp=root.next.get("makeeffect");
            tmp.addNext(new Leaf("arcade") {
                public Object execute(Object o_param) {
                    String[] parameters=(String[]) o_param;

                    Arcade a=(Arcade)getVariable(parameters[2]);
                    a.makeEffect();

                    return null;
                }
            });
            tmp.addNext(new Leaf("automat") {
                public Object execute(Object o_param) {
                    String[] parameters=(String[]) o_param;

                    Automat a=(Automat)getVariable(parameters[2]);
                    a.makeEffect();
                    return null;
                }
            });

            tmp.addNext(new Leaf("fotel") {
                public Object execute(Object o_param) {
                    String[] parameters=(String[]) o_param;

                    Fotel f=(Fotel)getVariable(parameters[2]);
                    f.makeEffect();
                    return null;
                }
            });

            //INIT OUTPUT
            tmp=root.next.get("output");
            tmp.addNext(new Leaf("console") {
                public Object execute(Object o_param) {

                    //TODO output a console
                    return null;
                }
            });
            tmp.addNext(new Leaf("file") {
                public Object execute(Object o_param) {
                    String descr=(String) o_param;
                    //TODO output egy fajl, el van picit rejtve egy uj node;
                    return null;
                }
            });

            //INIT TEST
            /*tmp=root.next.get("test");
            tmp.addNext(new Leaf("all") {
                public Object execute(Object o_param) {
                    return null;
                }
            });
            tmp.addNext(new Leaf("number") {
                public Object execute(Object o_param) {
                    return null;
                }
            });*/

            //INIT STAT
            tmp=root.next.get("stat");
            tmp.addNext(new Leaf("panda") {
                public Object execute(Object o_param) {
                    String[] parameters=(String[]) o_param;

                    outputLanguage.writePanda((Panda) getVariable(parameters[2]));
                    return null;
                }
            });
            tmp.addNext(new Leaf("orangutan") {
                public Object execute(Object o_param) {
                    String[] parameters=(String[]) o_param;

                    outputLanguage.writeOrangutan((Orangutan)getVariable(parameters[2]));
                    return null;
                }
            });
            tmp.addNext(new Node("entity") {
                public Object execute(Object o_param) {
                    return null;
                }
            });
            tmp=root.next.get("stat").next.get("entity");
                //INIT STAT/ENTITY__________________________________________
                newNode=new Leaf("arcade") {
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;

                        outputLanguage.writeNonEnterableEntity((Arcade)getVariable(parameters[3]));
                        return null;
                    }
                };
                tmp.addNext(newNode);
                tmp.addNext(new Leaf("automat") {
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;

                        outputLanguage.writeNonEnterableEntity((Automat)getVariable(parameters[3]));
                        return null;
                    }
                });
                tmp.addNext(new Leaf("fotel") {
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;

                        outputLanguage.writeFotel((Fotel)getVariable(parameters[3]));
                        return null;
                    }
                });
                tmp.addNext(new Leaf("wardrobe") {
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;

                        outputLanguage.writeWardrobe((Wardrobe) getVariable(parameters[3]));
                        return null;
                    }
                });

                //INIT STAT/TILE
                tmp=root.next.get("stat");
                newNode=new Node("tile");
                tmp.addNext(newNode);
                newNode.addNext(new Leaf("strong") {
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;

                        outputLanguage.writeTile((Tile)getVariable(parameters[3]));
                        return null;
                    }
                });
                newNode.addNext(new Leaf("weak") {
                    public Object execute(Object o_param) {
                        String[] parameters=(String[]) o_param;

                        outputLanguage.writeWeakTile((WeakTile) getVariable(parameters[3]));
                        return null;
                    }
                });
                outputLanguage.variables2 = variables;
        return root;
    }

    public Object compile(String[] param) {
        //System.out.println("compiling...");
        Object ret=null;
        try{
            int i=0;
            //ELMEGYUNK A LEAFHEZ
            Node n=root.next.get(param[i]);
            while (n!=null&&n.next.size()!=0){
                //System.out.print(n.description+" / ");
                n=(Node)(n.search(param[++i]));
            }
            //LEAFET TALALTUNK
            //System.out.println(n.description);
            if(n!=null)
                ret=n.execute(param);
            else
                System.out.println("Unknown keyword: '"+param[i]+"'");
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Not enough parameters");
        }
        catch (ClassCastException e){
            System.out.println("Invalid parameter type");
        }
        catch (NullPointerException e){
            System.out.println("Something went wrong :( <NullPtrExc>");
        }
        return ret;
    }
}
