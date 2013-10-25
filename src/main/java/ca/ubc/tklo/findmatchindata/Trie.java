package ca.ubc.tklo.findmatchindata;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Trie {
	Point head;
	Map<Point, Trie> expansions;
	public Trie(Point head) {
		this.head = head;		
	}
	public Set<Trie> expand(DataDimension dd) {
		if (this.expansions != null) {
			return new HashSet<Trie>(this.expansions.values());
		}
		Set<Trie> expansions = new HashSet<Trie>();
		Set<Point> neighbors = head.getNeighbors(dd);
		Iterator<Point> itr_pt = neighbors.iterator();
		while (itr_pt.hasNext()) {
			Point candidate = itr_pt.next();
			if (dd.check(candidate)) {
				Trie expansion = new Trie(candidate);
				expansions.add(expansion);
				this.expansions.put(candidate, expansion);
			}
		}
		return expansions;	
	}
	public int countLeaves() {
		if (this.expansions == null || this.expansions.size() == 0) {
			return 1;
		}
		int sum = 0;	
		Iterator<Point> itr_pt = this.expansions.keySet().iterator();
		while (itr_pt.hasNext()) {
			Trie trie = this.expansions.get(itr_pt.next());
			if (trie instanceof Trie) {
				sum += this.expansions.get(trie).countLeaves();
			}
		}
		return sum;
	}
}
