package ca.ubc.tklo.findmatchindata;

import java.util.HashSet;
import java.util.Set;

public class Point2D implements Point {
	int y = -1;
	int x = -1;
	public Point2D(int y, int x) {
		this.y = y;
		this.x = x;
	}
	public int getY() {
		return this.y;
	}
	public int getX() {
		return this.x;
	}
	public Set<Point> getNeighbors(DataDimension m) {
		Set<Point> neighbors = new HashSet<Point>();
		for (int i=y-1; i < y+1; ++i) {
			for (int j=x-1; j < x+1; ++j) {
				if (i != this.y && j != this.x) {
					Point candidate = new Point2D(i, j);
					if (m.isWithinRange(candidate)) {
						neighbors.add(candidate);
					} 
				}
			}
		}		
		return neighbors;
	}
}
