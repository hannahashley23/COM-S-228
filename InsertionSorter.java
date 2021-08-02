package edu.iastate.cs228.hw1;

/**
 *  
 * @author hannahashley
 *
 */

/**
 * 
 * This class implements insertion sort.
 *
 */

public class InsertionSorter extends AbstractSorter {

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts
	 */
	public InsertionSorter(Point[] pts) {
		// call to parent constructor
		super(pts);
		algorithm = "Insertion Sort";
	}

	/**
	 * Perform insertion sort on the array points[] of the parent class
	 * AbstractSorter.
	 */
	@Override
	public void sort() {
		// starting index
		int i = 0;

		// scanning index
		int j = 0;

		// sets current index and scanning index equal to each other at the start of
		// each round
		for (i = 1; i < points.length; i++) {
			j = i;

			// finds closest point that is less than the point at the current index and then
			// continuously swaps the point with each lower index until it reaches it's
			// correct position
			// in the sorted part of the array
			while (j > 0 && pointComparator.compare(points[j], points[j - 1]) == -1) {
				swap(j, j - 1);
				j = j - 1;
			}
		}
	}
}
