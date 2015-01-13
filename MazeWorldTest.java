

import static org.junit.Assert.*; 

import java.util.*; 
  
import javalib.worldimages.*; 
  
import org.junit.Test; 
  
public class MazeWorldTest { 
    Point p1 = new Point(1, 1); 
    Point p2 = new Point(5, 2); 
    Point p3 = new Point(5, 2); 
    Link l12 = new Link(p1, p2); 
    Node n1 = new Node(10, 10); 
    Node n2 = new Node(5, 2); 
    UnionFind uf = new UnionFind(4, 4); 
    MazeWorld world = new MazeWorld(4, 4);
    MazeWorld world2 = new MazeWorld(100,50);
    
    NewWorld nworld = new NewWorld();
    
  
    @Test
    public void testMazeWorld() { 
        assertEquals(this.p1.x, 1); 
        assertEquals(this.p1.y, 1); 
        assertEquals(this.p2, this.p3); 
        assertEquals(this.p1.equals(this.p2), false); 
        assertEquals(this.p2.hashCode(), 5); 
        this.world2.bigBang(3000, 3000, 0.0);
        //this.nworld.bigBang(1000, 1000);
    } 
  
//    @Test
//    public void testMaze() { 
//  
//    } 
//  
//    @Test
//    public void testGraph() { 
//        assertEquals(this.n1.neighbors.contains(this.n2), false); 
//        this.n1.addNeighbor(this.n2); 
//        assertEquals(this.n1.neighbors.contains(this.n2), true); 
//        assertEquals(this.n2.neighbors.contains(this.n1), true); 
//        this.uf.createLinkList(); 
//        assertEquals(this.uf.links.size(), 24); 
//        assertEquals( 
//                this.uf.links.toString(), 
//                "[(0,0)+(0,1), (0,0)+(1,0), (0,1)+(0,2), (0,1)+(1,1), "
//                        + "(0,2)+(0,3), (0,2)+(1,2), (0,3)+(1,3), (1,0)+(1,1), "
//                        + "(1,0)+(2,0), (1,1)+(1,2), (1,1)+(2,1), (1,2)+(1,3), "
//                        + "(1,2)+(2,2), (1,3)+(2,3), (2,0)+(2,1), (2,0)+(3,0), "
//                        + "(2,1)+(2,2), (2,1)+(3,1), (2,2)+(2,3), (2,2)+(3,2), "
//                        + "(2,3)+(3,3), (3,0)+(3,1), (3,1)+(3,2), (3,2)+(3,3)]"); 
//        assertEquals(this.uf.roots.isEmpty(), true); 
//        this.world.graph.uf.populateNeighbors(this.world.graph.map); 
//        assertEquals(this.world.graph.uf.roots.isEmpty(), false); 
//        assertEquals(this.world.graph.uf.findRootEnd((new Point(0, 0))), 
//                this.world.graph.uf.findRootEnd(new Point(1, 1))); 
//        assertEquals(this.world.graph.uf.findRootEnd((new Point(0, 0))), 
//                this.world.graph.uf.findRootEnd(new Point(3, 0))); 
//        assertEquals(this.world.graph.uf.findRootEnd((new Point(3, 0))), 
//                this.world.graph.uf.findRootEnd(new Point(1, 1))); 
//        assertEquals(this.world.graph.uf.wouldCycle(new Point(0, 0), 
//                new Point(1, 1)), true); 
//        assertEquals(this.world.graph.uf.wouldCycle(new Point(0, 0), 
//                new Point(2, 2)), true); 
//        assertEquals(this.world.graph.uf.wouldCycle(new Point(0, 0), 
//                new Point(2, 3)), true); 
//        assertEquals(this.world.graph.nodeToCell(this.n1) instanceof Cell, 
//                true); 
//        assertEquals(this.world.graph.nodeToCell(this.n1).p 
//                .equals(new Point(200, 200)), true); 
//        assertEquals(this.world.graph.nodeToCell(this.n1).left, false); 
//        assertEquals(this.world.graph.nodeToCell(this.n1).right, false); 
//        assertEquals(this.world.graph.nodeToCell(this.n1).up, false); 
//        assertEquals(this.world.graph.nodeToCell(this.n1).down, false); 
//        assertEquals(this.world.graph.toMaze().map.isEmpty(), false); 
//        assertEquals(this.world.graph.toMaze().map.size(), 
//                this.world.height * this.world.width); 
//        assertEquals(this.world.graph.toMaze().map.get(new Point(0, 0)).p, 
//                new Point(0, 0)); 
//        assertEquals(this.world.graph.toMaze().map.get(new Point(1, 1)).p, 
//                new Point(20, 20)); 
//        assertEquals(this.world.graph.toMaze().map.get(new Point(2, 3)).p, 
//                new Point(40, 60)); 
//        //System.out.println(this.world.graph.map.toString()); 
//    } 
//  
//    @Test
//    public void testSolver() { 
//        HashMap<Point, Cell> emptyMap = new HashMap<Point, Cell>(); 
//        Solver solver1 = new Solver(emptyMap); 
//        assertEquals(solver1.hasNext(), false); 
//        solver1.addToWork(new Cell(this.p1, true, true, false, true)); 
//        solver1.addToWork(new Cell(this.p2, true, true, true, true)); 
//        assertEquals(solver1.work.get(0).p, new Point(100, 40)); 
//        solver1 = new Solver(emptyMap); 
//        solver1.depth = false; 
//        solver1.addToWork(new Cell(this.p1, true, true, false, true)); 
//        solver1.addToWork(new Cell(this.p2, true, true, true, true)); 
//        assertEquals(solver1.work.get(1).p, new Point(5, 2)); 
//        assertEquals(solver1.hasNext(), true); 
//    } 
  
} 