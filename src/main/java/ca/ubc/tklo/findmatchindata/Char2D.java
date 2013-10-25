package ca.ubc.tklo.findmatchindata;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
// extensibility: 2D->3D, char->int or other datatype

//Given a 2D matrix of characters, and a string, find all matches of string inside matrix
public class Char2D implements DataDimension {
	protected char [][] data;
	protected char current;
	protected boolean isCurrentDefined = false;
	public Char2D(char[][] data) {
		this.data = data;
	}
	public boolean isWithinRange(Point point) {
		if (data == null) {
			return false;
		}
		Point2D point2D = (Point2D) point;
		int y = point2D.getY();
		if (y < 0 || y >= this.data.length) {
			return false;
		}
		int x = point2D.getX();
		if (x < 0 || x >= this.data[y].length) {
			return false;
		}
		return true;
	}
	public boolean check(Point point) {
		if ((point instanceof Point2D) && this.isWithinRange(point) && isCurrentDefined) {
			Point2D p2d = (Point2D) point;
			return current == data[p2d.getY()][p2d.getX()];				
		}
		return false;
	}
	public Set<Point> find(char target) {
		Set<Point> found = new HashSet<Point>();
		if (data == null) {
			return found;
		}
		int rows = data.length;	
		for (int i=0; i < rows; ++i) {
			if (data[i] == null) {
				continue;
			}
			char[] row = data[i];
			int cols = row.length;
			for (int j=0; j < cols; ++j) {
				if (row[j] == target) {
					found.add(new Point2D(i, j));
				}
			}
		}
		return found;
	}
	public Set<Trie> match(String str) {
		Set<Trie> matches = new HashSet<Trie>();
		if (data == null || data.length == 0 || str == null) {
			return matches;
		}
		int strlen = str.length();
		if (strlen == 0) {
			return matches;
		}
		char head = str.charAt(0);
		Set<Point> points = this.find(head);
		if (points.size() == 0) {
			return matches;	
		}
		Iterator<Point> itr_pt = points.iterator();
		while (itr_pt.hasNext()) {
			Point next = itr_pt.next();
			Trie candidate = new Trie(next);
			Set<Trie> leaves = new HashSet<Trie>();
			leaves.add(new Trie(next));
			for (int k=1; k < strlen; ++k) {
				if (leaves.size() == 0) {
					break;
				}
				Set<Trie> nextLeaves = new HashSet<Trie>();
				this.current = str.charAt(k);
				this.isCurrentDefined = true;
				Iterator<Trie> itr_trie = leaves.iterator();
				while (itr_trie.hasNext()) {
					Trie trie = itr_trie.next();
					nextLeaves.addAll(trie.expand(this));
				}
				this.current = 0;
				this.isCurrentDefined = false;
				leaves = nextLeaves;
			}
			if (leaves.size() > 0) {
				matches.add(candidate);
			}
		}
		return matches;
	}
}


