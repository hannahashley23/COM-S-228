package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.PrintWriter;

/**
 * 
 * @author hannahashley
 *
 */

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * This class sorts all the points in an array of 2D points to determine a
 * reference point whose x and y coordinates are respectively the medians of the
 * x and y coordinates of the original points.
 * 
 * It records the employed sorting algorithm as well as the sorting time for
 * comparison.
 *
 */
public class PointScanner {
	/**
	 * Point array that is sorted by each 4 sorting methods
	 */
	private Point[] points;

	/**
	 * point whose x and y coordinates are respectively the medians of the x
	 * coordinates and y coordinates of those points in the array points[].
	 * 
	 */
	private Point medianCoordinatePoint;

	/**
	 * enum that represents the specific sorting algorithm for each abstract sorter
	 * object
	 */
	private Algorithm sortingAlgorithm;

	/**
	 * execution time in nanoseconds.
	 */
	protected long scanTime;

	/**
	 * keeps track of given file name for use in writeMCPToFile() method
	 */
	protected String givenFile;

	/**
	 * This constructor accepts an array of points and one of the four sorting
	 * algorithms as input. Copy the points into the array points[].
	 * 
	 * @param pts input array of points
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException {

		sortingAlgorithm = algo;

		if (pts.length <= 0) {
			throw new IllegalArgumentException();
		}

		points = new Point[pts.length];

		// makes a deep copy
		for (int i = 0; i < pts.length; i++) {
			points[i] = new Point(pts[i]);
		}
	}

	/**
	 * This constructor reads points from a file.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException if the input file contains an odd number of
	 *                                integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException {

		givenFile = inputFileName;
		sortingAlgorithm = algo;
		File givenFile = new File(inputFileName);

		// check to make sure file exists
		if (givenFile.exists() == false) {
			throw new FileNotFoundException();
		}

		Scanner scnr = new Scanner(givenFile);

		int numIntegers = 0;

		// counts number of integers in file
		while (scnr.hasNextInt()) {
			numIntegers++;
			scnr.next();
		}

		if (numIntegers % 2 != 0) {
			scnr.close();
			throw new InputMismatchException();
		}

		scnr.close();

		points = new Point[numIntegers / 2];

		// reassign scanner to actually assign values to points array
		scnr = new Scanner(new File(inputFileName));

		for (int i = 0; i < numIntegers / 2; i++) {
			int x = scnr.nextInt();
			int y = scnr.nextInt();
			Point newPoint = new Point(x, y);
			points[i] = newPoint;
		}

		scnr.close();
	}

	/**
	 * Carry out two rounds of sorting using the algorithm designated by
	 * sortingAlgorithm as follows:
	 * 
	 * a) Sort points[] by the x-coordinate to get the median x-coordinate. b) Sort
	 * points[] again by the y-coordinate to get the median y-coordinate. c)
	 * Construct medianCoordinatePoint using the obtained median x- and
	 * y-coordinates.
	 * 
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter,
	 * InsertionSorter, MergeSorter, or QuickSorter to carry out sorting.
	 * 
	 * @param algo
	 * @return
	 */
	public void scan() {
		AbstractSorter aSorter;

		if (sortingAlgorithm.equals(Algorithm.SelectionSort)) {
			aSorter = new SelectionSorter(points);
		}

		else if (sortingAlgorithm.equals(Algorithm.InsertionSort)) {
			aSorter = new InsertionSorter(points);
		}

		else if (sortingAlgorithm.equals(Algorithm.MergeSort)) {
			aSorter = new MergeSorter(points);
		}

		else if (sortingAlgorithm.equals(Algorithm.QuickSort)) {
			aSorter = new QuickSorter(points);
		}

		else {
			return;
		}

		// SORTING X-VALUES:

		// set comparator to sort x values
		aSorter.setComparator(0);
		long timeBeforeX = System.nanoTime();
		aSorter.sort();

		// copies sorted values into points array
		aSorter.getPoints(points);
		long timeAfterX = System.nanoTime();

		// sets median x value
		Point medianByX = new Point(aSorter.getMedian());
		int xValue = medianByX.getX();

		// SORTING Y-VALUES:

		// set comparator to sort x values
		aSorter.setComparator(1);
		long timeBeforeY = System.nanoTime();
		aSorter.sort();

		// copies sorted values into points array
		aSorter.getPoints(points);
		long timeAfterY = System.nanoTime();

		// sets median y value
		Point medianByY = new Point(aSorter.getMedian());
		int yValue = medianByY.getY();

		// sums up scan time taken by both scans
		scanTime = (timeAfterX - timeBeforeX) + (timeAfterY - timeBeforeY);

		// sets value of MCP for each sorter
		medianCoordinatePoint = new Point(xValue, yValue);

	}

	/**
	 * Outputs performance statistics in the format:
	 * 
	 * <sorting algorithm> <size> <time>
	 * 
	 * For instance,
	 * 
	 * selection sort 1000 9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description.
	 */
	public String stats() {

		String stat;

		if (sortingAlgorithm.equals(Algorithm.SelectionSort) || sortingAlgorithm.equals(Algorithm.InsertionSort)) {
			stat = new String(sortingAlgorithm + "    " + (points.length) + "       " + scanTime);
		}

		else {
			stat = new String(sortingAlgorithm + "        " + (points.length) + "       " + scanTime);
		}

		return stat;
	}

	/**
	 * Write MCP after a call to scan(), in the format "MCP: (x, y)" The x and y
	 * coordinates of the point are displayed on the same line with exactly one
	 * blank space in between.
	 */
	@Override
	public String toString() {

		String mcp = "MCP: (" + medianCoordinatePoint.getX() + ", " + medianCoordinatePoint.getY() + ")";

		return mcp;
	}

	/**
	 * 
	 * This method, called after scanning, writes point data into a file by
	 * outputFileName. The format of data in the file is the same as printed out
	 * from toString(). The file can help you verify the full correctness of a
	 * sorting result and debug the underlying algorithm.
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException {

		File f = null;

		try {
			f = new File(givenFile);
			PrintWriter pw = new PrintWriter(f);

			// return properly formatted points to file
			for (int i = 0; i < points.length; i++) {
				pw.println(points[i].toString());
			}

			// prints MCP below points in file
			pw.print("MCP: " + medianCoordinatePoint.toString());
			pw.close();
		}

		catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		}

	}

}
