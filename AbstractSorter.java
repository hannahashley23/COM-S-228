package edu.iastate.cs228.hw1;

/**
 *  
 * @author hannahashley
 *
 */

import java.util.Comparator;

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort,
 * and QuickSort. It stores the input (later the sorted) sequence.
 *
 */
public abstract class AbstractSorter {

	/**
	 * array of points operated on by a sorting algorithm. stores ordered points
	 * after a call to sort().
	 */
	protected Point[] points;

	/**
	 * "selection sort", "insertion sort", "mergesort", or "quicksort". Initialized
	 * by a subclass constructor
	 */
	protected String algorithm = null;

	/**
	 * Comparator object that implements comparator interface and compares two point
	 * objects by either their x or y value depending the boolean value of the xORy
	 * variable in Point class
	 */
	protected Comparator<Point> pointComparator = null;

	/**
	 * This constructor accepts an array of points as input. Copy the points into
	 * the array points[].
	 * 
	 * @param pts input array of points
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException {
		points = new Point[pts.length];

		if (pts.length <= 0) {
			throw new IllegalArgumentException("Given file is empty");
		}

		// store given points array in this abstract sorter object
		for (int i = 0; i < pts.length; i++) {
			points[i] = new Point(pts[i]);
		}
	}

	/**
	 * Generates a comparator on the fly that compares by x-coordinate if order ==
	 * 0, by y-coordinate if order == 1. Assign the comparator to the variable
	 * pointComparator.
	 * 
	 * 
	 * Need to create an object of the PolarAngleComparator class and call the
	 * compareTo() method in the Point class.
	 * 
	 * @param order 0 by x-coordinate 1 by y-coordinate
	 * 
	 * 
	 * @throws IllegalArgumentException if order is less than 0 or greater than 1
	 * 
	 */
	public void setComparator(int order) throws IllegalArgumentException {

		// create new point comparator
		pointComparator = new PolarAngleComparator();

		// set comparator to sort points by x-values
		if (order == 0) {
			Point.setXorY(true);
		}

		// set comparator to sort points by y-values
		if (order == 1) {
			Point.setXorY(false);
		}

		if (order < 0 || order > 1) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Use the created pointComparator to conduct sorting.
	 * 
	 * Ought to be protected. Made public for testing.
	 */
	public abstract void sort();

	/**
	 * Obtain the point in the array points[] that has median index
	 * 
	 * @return median point
	 */
	public Point getMedian() {
		return points[points.length / 2];
	}

	/**
	 * Copies the array points[] onto the array pts[].
	 * 
	 * @param pts
	 */
	public void getPoints(Point[] pts) {

		Point[] givenArr = pts;

		if (pts == null || pts.length != points.length) {
			givenArr = new Point[points.length];

		}

		// makes a deep copy
		for (int i = 0; i < points.length; i++) {
			givenArr[i] = new Point(points[i]);
		}
	}

	/**
	 * Swaps the two elements indexed at i and j respectively in the array points[].
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j) {

		Point temp = points[i];
		points[i] = points[j];
		points[j] = temp;
	}

	/**
	 * Class within AbstractSorter that implements Comparator interface and calls
	 * the compare method from point class to compare two points by either their x
	 * or y values
	 * 
	 * @author hannahashley
	 *
	 */
	public class PolarAngleComparator implements Comparator<Point> {

		public PolarAngleComparator() {
		}

		// calls compare method from Point class
		@Override
		public int compare(Point p1, Point p2) {
			return p1.compareTo(p2);
		}

	}
}
