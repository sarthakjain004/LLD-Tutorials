// package Structural.Flyweight;
import java.util.*;

//google maps tree coordinates examples.
// each tree at a coordinate would be an object
// overkill since millions and millions of trees.
// so many objects-> lot of memory.
// flyweight optimisez the memory consumption.

/*
// [1] without flyweight pattern.

class Tree {
    // Attributes that keep on changing 
    private int x;
    private int y;
    
    // Attributes that remain constant
    private String name;
    private String color;
    private String texture;
    
    public Tree(int x, int y, String name, String color, String texture) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.color = color;
        this.texture = texture;
    }
    public void draw() {
        System.out.println("Drawing tree at (" + x + ", " + y + ") with type " + name);
    }
}

class Forest {

    private List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, String color, String texture) {
        Tree tree = new Tree(x, y, name, color, texture);
        trees.add(tree);
    }

    public void draw() {
        for (Tree tree : trees) {
            tree.draw();
        }
    }
}
//The previous implementation created a new Tree object for each of the 1 million 
//trees, even when most of them had identical properties like name, color, and texture. 
//This led to unnecessary 
//duplication of memory for the shared attributes.
*/


class TreeType {
    // Properties that are common among all trees of this type
    private String name;
    private String color;
    private String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw(int x, int y) {
        System.out.println("Drawing " + name + " tree at (" + x + ", " + y + ")");
    }
}

class Tree {
    // Attributes that keep on changing 
    private int x;
    private int y;
    
    // Attributes that remain constant
    private TreeType treeType;
    
    public Tree(int x, int y, TreeType treeType) {
        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }
    
    public void draw() {
        treeType.draw(x, y);
    }
}

class TreeFactory {

    static Map<String, TreeType> treeTypeMap = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + " - " + color + " - " + texture;

        if (!treeTypeMap.containsKey(key)) {
            treeTypeMap.put(key, new TreeType(name, color, texture));
        }
        return treeTypeMap.get(key);
    }
}

class Forest {
    private List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, String color, String texture) {
        Tree tree = new Tree(x, y, TreeFactory.getTreeType(name, color, texture));
        trees.add(tree);
    }

    public void draw() {
        for (Tree tree : trees) {
            tree.draw();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Forest forest = new Forest();
        
        // Planting 1 million trees
        for(int i = 0; i < 1000000; i++) {
            forest.plantTree(i, i, "Oak", "Green", "Rough");//why using memory even though similar trees?
            // the three are constants.
            // reduce attributes.
        }
        
        System.out.println("Planted 1 million trees.");

        //what is it?
        // it is a structural design pattern which is used to minimise memory usage
        // or computational cost by sharing as much data as possible with similar objects.
        // think of it as a adata re-use pattern if many objects are similar, store their common data
        // in one place and share it across instances.

        // intrinsic attributes -> shared attributes like this case, name texture color of trees.
        // extrensic attributes -> which changing, like coordinates here of trees.
        // using combination of factory + flyweight pattern.
        
        //wehn to use?
        // when need to create a large number of similar objects.
        //when memory and perf opt is crucial.
        //when the object's intrinsic prop could be shared independently of its extrinsic properties.

        //pros
        // reduces memory usage when lots of similar objects
        // improves perf in resource constrained env.
        // faster object creation.

        //cons
        // adds complexity(factory + flyweight)
        // harder to debug because of shared state.
        // can lead to tight coupling 

    }
}
