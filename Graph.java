

import java.util.*;

/**
 * Represents a set of Nodes
 * 
 * @author Greg & Isaac
 * 
 */
public class Graph {
    UnionFind uf;
    HashMap<Point, Node> map = new HashMap<Point, Node>();
    int height;
    int width;

    Graph(int width, int height) {
        this.height = height;
        this.width = width;
        this.uf = new UnionFind(this.width, this.height);
        this.buildEmptyGrid();
        this.uf.createLinkList();
        this.uf.populateNeighbors(this.map);
    }

    /**
     * EFFECT: puts new cells into the map
     */
    public void buildEmptyGrid() {
        for (int x = 0; x < this.width; x += 1) {
            for (int y = 0; y < this.height; y += 1) {
                Point p = new Point(x, y);
                this.map.put(p, new Node(p.x, p.y));
                this.uf.roots.put(p, p);
            }
        }
    }

    /**
     * converts a node to a cell
     */
    public Cell nodeToCell(Node nodual) {
        boolean l = false, r = false, u = false, d = false;
        if (nodual.p.x > 0) {
            Point pleft = new Point(nodual.p.x - 1, nodual.p.y);
            Node nleft = this.map.get(pleft);
            l = nodual.checkNeighbor(nleft);
        }
        if (nodual.p.y > 0) {
            Point pup = new Point(nodual.p.x, nodual.p.y - 1);
            Node nup = this.map.get(pup);
            u = nodual.checkNeighbor(nup);
        }
        if (nodual.p.x < this.width) {
            Point pright = new Point(nodual.p.x + 1, nodual.p.y);
            Node nright = this.map.get(pright);
            r = nodual.checkNeighbor(nright);
        }
        if (nodual.p.x < this.height) {
            Point pdown = new Point(nodual.p.x, nodual.p.y + 1);
            Node ndown = this.map.get(pdown);
            d = nodual.checkNeighbor(ndown);
        }
        
        return new Cell(nodual.p,l,r,u,d);
    }
    
    
    /**
     * converts this graph into a maze
     */
    public Maze toMaze() {
        HashMap<Point, Cell> mz = new HashMap<Point, Cell>();
        for(int x = 0; x < this.width; x += 1) {
            for(int y = 0; y < this.height; y += 1) {
                Point p = new Point(x,y);
                Node temp = this.map.get(p);
                mz.put(p, this.nodeToCell(temp));
            }
        }
        return new Maze(mz);
    }
}

/**
 * Represents
 * 
 * @author Greg & Isaac
 * 
 */
class UnionFind {
    HashMap<Point, Point> roots = new HashMap<Point, Point>();
    ArrayList<Link> links = new ArrayList<Link>();
    int height;
    int width;

    UnionFind(int width, int height) {
        this.height = height;
        this.width = width;
    }

    /**
     * EFFECT: populates links with every possible edge in the gird
     */
    public void createLinkList() {
        for (int x = 0; x < this.width; x += 1) {
            for (int y = 0; y < this.height; y += 1) {
                Point p = new Point(x, y);
                Point right = new Point(x + 1, y);
                Point down = new Point(x, y + 1);
                if (y < this.height - 1) {
                    // this.map.get(p).createLink(this.map.get(down));
                    this.links.add(new Link(p, down));
                }
                if (x < this.width - 1) {
                    // this.map.get(p).createLink(this.map.get(right));
                    this.links.add(new Link(p, right));
                }
            }
        }
    }

    /**
     * Creates the maze EFFECT: empties this.links, adds cells to each node's
     * list of neighbors in this.map and changes this.roots
     * 
     * @param the
     *            map of the graph to be manipulated
     */
    public void populateNeighbors(HashMap<Point, Node> map) {
        // HashMap<Point, Node> hash = new HashMap<Point, Node>();
        Random rand = new Random();
        int temp = 0;
        Link templink;
        while (!this.links.isEmpty()) {
            temp = rand.nextInt(this.links.size());
            //for an organized graph replace next line with:
            // templink = this.links.get(0);
            templink = this.links.get(temp);
            Point p1 = templink.p1;
            Point p2 = templink.p2;
            if (!this.wouldCycle(p1, p2)) {
                map.get(p1).addNeighbor(map.get(p2));
                if (this.findRootEnd(p1).equals(p1)) {
                    this.roots.put(p1, p2);
                }
                else if (this.findRootEnd(p2).equals(p2)) {
                    this.roots.put(p2, p1);
                }
                else {
                    this.roots.put(this.roots.get(this.findRootEnd(p1)),
                            this.findRootEnd(p2));
                    // p1.findRootEnd().root = p2.findRootEnd();
                }
            }

            this.links.remove(templink);
        }
    }

    /**
     * 
     * @param Point p
     * @return Finds the end root of the tree 
     * (links an entire tree together)
     */
    public Point findRootEnd(Point p) {
        if (this.roots.get(p).equals(p)) {
            return p;
        }
        else {
            return this.findRootEnd(this.roots.get(p));
        }
    }

    /**
     * returns whether the two points share a root
     * if they do, the link would create a cycle 
     * @param p1
     * @param p2
     * @return boolean
     */
    boolean wouldCycle(Point p1, Point p2) {
        return (this.findRootEnd(p1).equals(this.findRootEnd(p2)));
        // return (this.p1.findRootEnd().equals(this.p2.findRootEnd()));
    }
}

class Node {
    Point p;
    ArrayList<Node> neighbors = new ArrayList<Node>();

    Node(int x, int y) {
        this.p = new Point(x, y);
    }

    /**
     * adds this node to that's list of neighbors 
     * and adds node that to this nodes list of neighbors
     * 
     * EFFECT: changes this.neighbors and
     * that.neighbors
     * @param neighbor
     */
    public void addNeighbor(Node that) {
        this.neighbors.add(that);
        that.neighbors.add(this);
    }

    /**
     * checks to see whether that node is a neighbor of this node
     * @param c
     * @return
     */
    boolean checkNeighbor(Node that) {
        return this.neighbors.contains(that);
    }
}

/**
 * @author isaacsherrill & gregface
 * represents a link between two nodes 
 * (should be adjacent nodes)
 */
class Link {
    Point p1;
    Point p2;

    Link(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
}