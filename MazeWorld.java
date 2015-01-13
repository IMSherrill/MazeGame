

import javalib.colors.Black;
import javalib.colors.Green;
import javalib.colors.IColor;
import javalib.colors.Red;
import javalib.colors.White;
import javalib.funworld.World;
import javalib.worldimages.LineImage;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.OverlayImagesXY;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;
 
 
/**
 * Represents the current state of the world
 * 
 * @author Greg & Isaac
 * 
 */
public class MazeWorld extends World{
    Maze maze;
    Solver solver;
    Graph graph;
    int height;
    int width;
    int cellSize = 10;
 
    MazeWorld(int width, int height) {
        if (width <= 1 || height <= 1) {
            throw new RuntimeException("Invalid Dimensions");
        }
        this.width = width;
        this.height = height;
        this.graph = new Graph(this.width, this.height);
        this.maze = this.graph.toMaze();
        this.solver = new Solver(this.maze.map);
    }
    
    public MazeWorld onTick(){
    	return this;
    }
    
 
    WorldImage drawCell(Cell cell) {
        IColor clr = new White();
        if (cell.visited) {
            clr = new Green();
        }
        
       // WorldImage base = new RectangleImage(new Point(0,0), this.cellSize, this.cellSize, clr);
        WorldImage base = new RectangleImage(new Posn(cell.p.x*this.cellSize + this.cellSize/2, cell.p.y*this.cellSize+ this.cellSize/2),
        				this.cellSize, this.cellSize, clr);
       
        int s = this.cellSize;
        
        
        WorldImage leftWall =
                new LineImage(new Posn(s*cell.p.x,s*cell.p.y), new Posn(s*cell.p.x,s*cell.p.y+s), new Black());
        WorldImage upWall =
                new LineImage(new Posn(s*cell.p.x,s*cell.p.y), new Posn(s*cell.p.x+s,s*cell.p.y), new Black());
        WorldImage rightWall =
                new LineImage(new Posn(s*cell.p.x+s,s*cell.p.y), new Posn(s*cell.p.x+s,s*cell.p.y+s), new Black());
        WorldImage downWall =
                new LineImage(new Posn(s*cell.p.x,s*cell.p.y+s), new Posn(s*cell.p.x+s,s*cell.p.y+s), new Black());

        
        if (!cell.left) {
            base = new OverlayImages(base, leftWall);
        }
 
        if (!cell.up) {
        	 base = new OverlayImages(base, upWall);
        }
 
        if (!cell.right) {
        	 base = new OverlayImages(base, rightWall);
        }
 
        if (!cell.down) {
        	 base = new OverlayImages(base, downWall);
        }
      return base;
    }
 
    WorldImage drawMaze() {
        WorldImage base = new RectangleImage(new Posn((this.width)*this.cellSize/2,(this.height)*this.cellSize/2), (this.width)*this.cellSize, (this.height)*this.cellSize, new Red());
        for (int x = 0; x < this.width; x += 1) {
            for(int y = 0; y < this.height; y += 1) {
                base = new OverlayImages(base, this.drawCell(maze.map.get(new Point(x,y))));
            }
        }
        return base;
    }
 
    @Override
    public WorldImage makeImage() {
        return this.drawMaze();
 //  	WorldImage base = new RectangleImage(new Posn(175,150), 300 , 300, new Red());
//    	base = new OverlayImages(base, 
//    			new RectangleImage(new Posn(100,50), 50 ,50, new Black())
//    				);
    //	return base;
    	
    	
    }

}
 
/**
 * Represents a position
 * 
 * @author Greg & Isaac
 * 
 */
class Point extends Posn {
 
    Point(int x, int y) {
        super(x, y);
    }
 
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        else {
            Point nPoint = (Point) obj;
            return nPoint.x == this.x && nPoint.y == this.y;
        }
    }
 
    public int hashCode() {
        return this.x;
    }
}


 class NewWorld extends World{
	Posn p = new Posn(10,10);

	@Override
	public WorldImage makeImage() {
		WorldImage base = new RectangleImage(new Posn(175,150), 300 , 300, new Red());
    	base = new OverlayImages(base, 
    			new RectangleImage(new Posn(100,50), 50 ,50, new Black())
    	);
    	return base;
	}
}