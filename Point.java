package edu.iastate.cs228.hw1;

/**
 * 
 * @author hannahashley
 *
 */

public class Point implements Comparable<Point> {
	/**
	 * x variable for Point object
	 */
	private int x;

	/**
	 * y variable for Point object
	 */
	private int y;

	/**
	 * Compare x coordinates if xORy == true and y coordinates otherwise To set its
	 * value, use Point.xORy = true or false.
	 */
	public static boolean xORy;

	/**
	 * default constructor
	 */
	public Point() {
		// x and y get default value 0
	}

	/**
	 * Constructor for point object Assigns instance variable to given values for x
	 * and y
	 * 
	 * @param x
	 * @param y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructs copy of point object
	 * 
	 * @param p
	 */
	public Point(Point p) { // copy constructor
		x = p.getX();
		y = p.getY();
	}

	/**
	 * returns y value for the point object
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * returns y value for the point object
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set the value of the static instance variable xORy.
	 * 
	 * @param xORy
	 */
	public static void setXorY(boolean xORy) {
		Point.xORy = xORy;
	}

	/**
	 * Overrides equals() method to compare two point objects
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		Point other = (Point) obj;
		return x == other.x && y == other.y;
	}

	/**
	 * Compare this point with a second point q depending on the value of the static
	 * variable xORy
	 * 
	 * @param q
	 * @return -1 if (xORy == true && (this.x < q.x || (this.x == q.x && this.y <
	 *         q.y))) || (xORy == false && (this.y < q.y || (this.y == q.y && this.x
	 *         < q.x))) 0 if this.x == q.x && this.y == q.y) 1 otherwise
	 */
	public int compareTo(Point q) {

		if ((xORy == true && (this.x < q.x || (this.x == q.x && this.y < q.y)))
				|| (xORy == false && (this.y < q.y || (this.y == q.y && this.x < q.x)))) {
			return -1;
		}

		if (this.x == q.x && this.y == q.y) {
			return 0;
		}

		return 1;
	}

	/**
	 * Output a point in the standard form (x, y).
	 */
	@Override
	public String toString() {

		return "(" + x + ", " + y + ")";
	}
}
