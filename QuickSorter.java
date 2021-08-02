package edu.iastate.cs228.hw1;

/**
 *  
 * @author hannahashley
 *
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the
 * lecture.
 *
 */

public class QuickSorter extends AbstractSorter {

	/**
	 * Private instance variable that keeps track of pivot for partitioning and
	 * sorting
	 */
	private Point pivot;

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts input array of integers
	 */
	public QuickSorter(Point[] pts) {

		// call to parent constructor
		super(pts);
		algorithm = "Quick Sort";
	}

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.
	 * 
	 */
	@Override
	public void sort() {

		quickSortRec(0, points.length - 1);

	}

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first starting index of the subarray
	 * @param last  ending index of the subarray
	 */
	private void quickSortRec(int first, int last) {
		if (first >= last) {
			return;
		}

		int p = partition(first, last);

		// calls same method for the subarrays on both sides of pivot
		quickSortRec(first, p - 1);
		quickSortRec(p + 1, last);
	}

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last) {
		// assign pivot to last element in point array
		pivot = points[last];
		int i = first - 1;

		for (int j = first; j < last; j++) {

			// swap points around pivot if the pivot is > or < the point at index j
			if (pointComparator.compare(points[j], pivot) <= 0) {
				i++;
				swap(i, j);
			}
		}
		// Now put pivot in position i+1.
		swap(i + 1, last);
		return i + 1;

	}

}
