package edu.iastate.cs228.hw1;

/**
 *  
 * @author hannahashley
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.
 *
 */

public class MergeSorter extends AbstractSorter {

	/**
	 * Constructor takes an array of points. It invokes the superclass constructor,
	 * and also set the instance variables algorithm in the superclass.
	 * 
	 * @param pts input array of integers
	 */
	public MergeSorter(Point[] pts) {

		// call to parent constructor
		super(pts);
		algorithm = "Merge Sort";

	}

	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter.
	 * 
	 */
	@Override
	public void sort() {

		mergeSortRec(points);

	}

	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of
	 * points. One way is to make copies of the two halves of pts[], recursively
	 * call mergeSort on them, and merge the two sorted subarrays into pts[].
	 * 
	 * @param pts point array
	 */
	private void mergeSortRec(Point[] arr) {

		int n = arr.length;

		// base case for recursion: array doesn't need to be sorted further
		if (n <= 1) {
			return;
		}

		int mid = n / 2;

		Point[] leftArray = new Point[mid];

		Point[] rightArray = new Point[n - mid];

		// assign values of leftArray
		for (int i = 0; i < leftArray.length; i++) {
			leftArray[i] = arr[i];
		}

		// assign values of rightArray
		for (int i = 0; i < rightArray.length; i++) {
			rightArray[i] = arr[leftArray.length + i];
		}

		// recursively "split apart" each sub-array and call mergeSortRec on them until
		// their length is equal to 1
		mergeSortRec(leftArray);
		mergeSortRec(rightArray);

		// merges each sub-array together
		merge(arr, leftArray, rightArray);

	}

	/**
	 * Merges two sub-arrays together into a newly formed empty array Compares the
	 * paralleling indexes of the two sub-arrays and then inserts the values into
	 * the array mergedNums. at the end of the method, the values of mergedNums are
	 * copied into their respective locations in the given array
	 * 
	 * @author hannahashley
	 * 
	 * @param arr
	 * @param left
	 * @param right
	 */
	private void merge(Point[] arr, Point[] left, Point[] right) {

		int leftLength = left.length;
		int rightLength = right.length;

		// creates new empty array
		Point[] mergedNums = new Point[leftLength + rightLength];

		int leftIndex = 0;
		int rightIndex = 0;
		int indexOfMerged = 0;

		// while both subarrays still have more items left to compare
		while (leftIndex < leftLength && rightIndex < rightLength) {

			// if left[i] < right[i], add left[i] to mergedNums
			if ((pointComparator.compare(left[leftIndex], right[rightIndex])) < 1
					|| pointComparator.compare(left[leftIndex], right[rightIndex]) == 0) {
				mergedNums[indexOfMerged] = left[leftIndex];
				leftIndex++;
				indexOfMerged++;
			}

			// if right[i] < left[i], add right[i] to mergedNums
			else {
				mergedNums[indexOfMerged] = right[rightIndex];
				rightIndex++;
				indexOfMerged++;
			}
		}

		// append rest of right array to MergedNums
		if (leftIndex >= leftLength) {
			for (int m = rightIndex; m < rightLength; m++) {
				mergedNums[indexOfMerged] = right[m];
				indexOfMerged++;
			}
		}

		// append rest of left array to MergedNums
		else {
			for (int m = leftIndex; m < leftLength; m++) {
				mergedNums[indexOfMerged] = left[m];
				indexOfMerged++;
			}
		}

		// copy mergedNums values into given array
		for (int i = 0; i < arr.length; i++) {
			arr[i] = mergedNums[i];

		}
	}
}
