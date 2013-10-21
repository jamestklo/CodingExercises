/**
 * 
 */
package ca.ubc.tklo.findmatchindata;

import java.util.Set;

/**
 * @author tklo
 *
 */
public interface DataDimension {
	public boolean isWithinRange(Point point);
	public boolean check(Point point);
	public Set<Point> find(char target);
}
