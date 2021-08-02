package edu.iastate.cs228.hw1;

/**
 *  
 * @author hannahashley
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class CompareSorters {
	/**
	 * Repeatedly take integer sequences either randomly generated or read from
	 * files. Use them as coordinates to construct points. Scan these points with
	 * respect to their median coordinate point four times, each time using a
	 * different sorting algorithm.
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException {

		System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
		System.out.println();

		System.out.println("keys:  1 (random integers)  2 (file input)  3 (exit)");

		// scan for key number entered by user
		Scanner scnr = new Scanner(System.in);

		int givenNum = scnr.nextInt();

		int numTrials = 0;

		PointScanner[] scanners = new PointScanner[4];

		if (givenNum == 1) {

			numTrials++;

			System.out.println("Trial " + numTrials + ": " + givenNum);
			System.out.print("Enter a number of random points: ");
			System.out.println();

			int numPts = scnr.nextInt();

			Random generator = new Random();

			// creates randomly generated array of integer points
			Point[] unsorted = generateRandomPoints(numPts, generator);

			// sets each index of scanners array to a create a separate abstract sorter with
			// a
			// runtime type of
			// the four different sorting algorithms
			scanners[0] = new PointScanner(unsorted, Algorithm.SelectionSort);
			scanners[1] = new PointScanner(unsorted, Algorithm.InsertionSort);
			scanners[2] = new PointScanner(unsorted, Algorithm.MergeSort);
			scanners[3] = new PointScanner(unsorted, Algorithm.QuickSort);

		}

		else if (givenNum == 2) {

			numTrials++;

			System.out.println("Trial " + numTrials + ": " + givenNum);
			System.out.println("Points from a file");
			System.out.println("File name: ");

			String givenFile = scnr.next();

			System.out.println();

			// sets each index of scanners array to a create a separate abstract sorter with
			// a runtime type of the four different sorting algorithms
			scanners[0] = new PointScanner(givenFile, Algorithm.SelectionSort);
			scanners[1] = new PointScanner(givenFile, Algorithm.InsertionSort);
			scanners[2] = new PointScanner(givenFile, Algorithm.MergeSort);
			scanners[3] = new PointScanner(givenFile, Algorithm.QuickSort);

		}

		else if (givenNum == 3) {
			scnr.close();
			System.out.println("Done with program");
			return;
		}

		else {
			System.out.println("Please enter 1, 2, or 3");
			scnr.close();
			return;
		}

		// calls scan method in PointScanner class which executes sorting of the point
		// array by calling the sort
		// method for each of the runtime types of aSorter, measures scanTime, and
		// determines MCP
		for (int i = 0; i < scanners.length; i++) {
			scanners[i].scan();
		}

		System.out.println("Algorithm\t" + "Size\t" + "Time (ns)\t");
		System.out.println("----------------------------------");

		// prints out stats for each PointScanner object in scanners[];
		for (int i = 0; i < scanners.length; i++) {
			System.out.println(scanners[i].stats());
		}

		System.out.println("----------------------------------");
		System.out.println();

		scnr.close();

	}

	/**
	 * This method generates a given number of random points. The coordinates of
	 * these points are pseudo-random numbers within the range [-50,50] � [-50,50].
	 * Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing.
	 * 
	 * @param numPts number of points
	 * @param rand   Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException {
		if (numPts < 1) {
			throw new IllegalArgumentException();
		}

		// create new empty array of length determined by user
		Point[] tempArray = new Point[numPts];

		for (int i = 0; i < numPts; i++) {

			// generates random integers between -50 and 50 inclusively
			int x = rand.nextInt(101) - 50;
			int y = rand.nextInt(101) - 50;

			// forms Point objects from the randomly generated x and y values
			Point randPoint = new Point(x, y);

			// assign these random values to each index of tempArray respectively
			tempArray[i] = randPoint;
		}

		return tempArray;
	}

}
