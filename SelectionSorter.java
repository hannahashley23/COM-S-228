package edu.iastate.cs228.hw1;

/**
 *  
 * @author hannahashley
 *
 */

/**
 * 
 * This class implements selection sort.
 *
 */

public class SelectionSorter extends AbstractSorter {

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts
	 */
	public SelectionSorter(Point[] pts) {
		super(pts);
		algorithm = "Selection Sort";
	}

	/**
	 * Apply selection sort on the array points[] of the parent class
	 * AbstractSorter.
	 * 
	 */
	@Override
	public void sort() {
		int currentIndex;
		int smallestIndex = 0;

		for (int i = 0; i < points.length; i++) {

			// keeps track of sorting index
			currentIndex = i;
			smallestIndex = i;

			for (int j = i + 1; j < points.length; j++) {

				if ((pointComparator.compare(points[smallestIndex], points[j]) > 0)) {

					// keep track of index of smallest point found so far
					smallestIndex = j;
				}
			}

			// swaps smallest point in array with the current point in array
			if (currentIndex < smallestIndex) {
				swap(currentIndex, smallestIndex);
			}
		}
	}
}
