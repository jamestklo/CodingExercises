/**
 * 
 */
package ca.ubc.tklo.findmatchindata;

import java.util.Set;

/**
 * @author tklo
 *
 */
public interface Point {
	public Set<Point> getNeighbors(DataDimension dd);

}
