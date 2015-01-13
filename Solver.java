

import java.util.*;

public class Solver {
    ArrayList<Cell> work = new ArrayList<Cell>();
    boolean depth = true;
    boolean breadth = true;
    HashMap<Point, Cell> map;
    
    Solver (HashMap<Point, Cell> map){
    	this.map = map;
    }
    
    
    /**
     * marks the first element in work as visited
     * adds the neighbors to the work list IF they have not been visited
     * removes the first element from work
     */
    void next() {
    	Cell thisCell = this.work.get(0);
    	
    	thisCell.visited = true;
    	
    	if (thisCell.left) {
    		Point p = new Point(thisCell.p.x-1, thisCell.p.y);
    		Cell tempC = this.map.get(p);
    		if (!tempC.visited) {
    			this.addToWork(tempC);
    		}
    	}
    	if (thisCell.up) {
    		Point p = new Point(thisCell.p.x, thisCell.p.y -1);
    		Cell tempC = this.map.get(p);
    		if (!tempC.visited) {
    			this.addToWork(tempC);
    		}
    	}
    	if (thisCell.right) {
    		Point p = new Point(thisCell.p.x+1, thisCell.p.y);
    		Cell tempC = this.map.get(p);
    		if (!tempC.visited) {
    			this.addToWork(tempC);
    		}
    	}
    	if (thisCell.down) {
    		Point p = new Point(thisCell.p.x, thisCell.p.y+1);
    		Cell tempC = this.map.get(p);
    		if (!tempC.visited) {
    			this.addToWork(tempC);
    		}
    	}
    	
    	this.work.remove(0);
        
    }
    
    boolean hasNext() {
        return !this.work.isEmpty();
    }
    
    void addToWork(Cell c){
    	if (this.depth) {
    		this.work.add(0, c);
    	} else if (this.breadth) {
    		this.work.add(c);
    	}
    }
}
