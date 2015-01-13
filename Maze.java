

import java.util.*;

public class Maze {
    HashMap<Point, Cell> map = new HashMap<Point,Cell>();
    Maze(HashMap<Point, Cell> map) {
        this.map = map;
    }
}

class Cell {
    Point p;
    boolean left, right, up, down, visited;

    Cell(Point p, boolean left, boolean right, boolean up, boolean down) {
        this.p = p;
        // determines whether you can go that way (false means wall)
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
        this.visited = false;
    }
}
