import java.util.*;

public class Kakero {

	/**
	 * Finds the nth largest number in an array. It's implemented based on quick
	 * sort's algorithm
	 *
	 * @param numbers
	 *            an array of integers
	 * @param low
	 *            start index
	 * @param high
	 *            end index
	 * @param order
	 *            n: the order we are looking for
	 */
	public int nthOrderNumber(int[] numbers, int low, int high, int order) {
		int lowIndex = low;
		int highIndex = high;
		int pivot = numbers[low];
		// int pivot = numbers[low + (high - low)/2];
		while (lowIndex <= highIndex) {
			while (numbers[lowIndex] < pivot) {
				lowIndex++;
			}
			while (numbers[highIndex] > pivot) {
				highIndex--;
			}
			if (lowIndex <= highIndex) {
				int temp = numbers[lowIndex];
				numbers[lowIndex] = numbers[highIndex];
				numbers[highIndex] = temp;
				lowIndex++;
				highIndex--;
			}
		}
		if (highIndex + 2 == order) {
			return numbers[highIndex + 1];
		} else if (highIndex + 2 < order) {
			return nthOrderNumber(numbers, lowIndex, high, order);
		} else {
			return nthOrderNumber(numbers, low, highIndex, order);
		}
	}

	/**
	 * Classic interview question: given an array of integers and a number,
	 * figure out if any two numbers in the array sums up to the input number.
	 * This method solves the question in O(n) runtime; however, it also uses
	 * O(n) space since I use a HashSet to store the differences
	 *
	 * @param numbers
	 *            an array of integers
	 * @param input
	 *            an integer
	 * 
	 */
	public boolean doesTwoNumbersSumUpToInputUsingHash(int[] numbers, int input) {
		Set<Integer> difference = new HashSet<Integer>();
		for (int number : numbers) {
			difference.add(input - number);
		}
		for (int number : numbers) {
			if (difference.contains(number)) {
				System.out.println(number);
				System.out.println(input - number);
				return true;
			}
		}
		return false;
	}

	/**
	 * Classic interview question: given an array of integers and a number,
	 * figure out if any two numbers in the array sums up to the input number.
	 * This method solves the question in O(nLogn) runtime (since I sort the
	 * array first) but it is more efficient it terms space if quick sort or
	 * some in place sorting algorithm is used.
	 *
	 * @param numbers
	 *            an array of integers
	 * @param input
	 *            an integer
	 * 
	 */
	public boolean doesTwoNumbersSumUpToInputNoDuplicate(int[] numbers,
			int input) {
		int j = numbers.length - 1;
		int i = 0;
		Sort.quickSort(numbers, 0, numbers.length - 1);
		while (i < j) {
			if (numbers[i] + numbers[j] == input) {
				System.out.println(numbers[i]);
				System.out.println(numbers[j]);
				return true;
			} else if (numbers[i] + numbers[j] > input) {
				j--;
			} else {
				i++;
			}
		}
		return false;
	}

	/**
	 * Count inversions problem
	 *
	 * @param numbers
	 *            an array of integers
	 * 
	 */
	public int countInversions(int[] numbers) {
		// TODO: Implementation!
		return 0;
	}

	/**
	 * Find shortest string in the dictionary that includes all the giving
	 * strings
	 *
	 * @param word
	 *            a String
	 * @param dictionary
	 *            an array of Strings
	 * 
	 */
	public String shortestWordIncludingStrings(String word, String[] dictionary) {
		if (word.length() <= 1) {
			return word;
		}
		int length = -1;
		String result = "";
		for (int i = 0; i < dictionary.length; i++) {
			if (isStringContainsAnother(dictionary[i], word)) {
				if (length == -1 || dictionary[i].length() < length) {
					length = dictionary[i].length();
					result = dictionary[i];
				}
			}
		}
		if (length == -1) {
			return "No word found";
		} else {
			return result;
		}
	}

	/**
	 * Binary search
	 *
	 * @param numbers
	 *            an array of sorted integers
	 * @param key
	 *            key value we would like to look up
	 */
	public boolean binarySearch(int[] numbers, int key) {
		int low = 0;
		int high = numbers.length - 1;
		while (high >= low) {
			int middle = low + (high - low) / 2;
			if (numbers[middle] == key) {
				return true;
			} else if (numbers[middle] > key) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		return false;
	}

	/**
	 * Returns true if a given reference contains another String. The difference
	 * between this and Java's Contains function is that this doesn't consider
	 * the exact character ordering of the key
	 *
	 * @param reference
	 *            reference String
	 * @param key
	 *            key String that we want to lookup
	 */
	public boolean isStringContainsAnother(String reference, String key) {
		for (int i = 0; i < key.length(); i++) {
			int index = reference.indexOf(key.charAt(i));
			if (index == -1) {
				return false;
			}
			reference = reference.substring(0, index)
					+ reference.substring(index + 1);
		}
		return true;
	}

	/**
	 * Calculates nth Fibonacci number using Binet's formula
	 *
	 * @param n
	 *            nth Fibonacci number index
	 */
	public int fibonacci(int n) {
		Double fib = new Double((Math.pow((1 + Math.sqrt(5)), n) - Math.pow(
				(1 - Math.sqrt(5)), n)) / (Math.pow(2, n) * Math.sqrt(5)));
		System.out.println(fib.intValue());
		return fib.intValue();
	}

	/**
	 * Calculates nth Fibonacci number using matrix multiplication
	 *
	 * @param n
	 *            nth Fibonacci number index
	 */
	public int fibonacciMatrix(int n) {
		if (n == 0) {
			System.out.println(0);
			return 0;
		} else if (n == 1) {
			System.out.println(1);
			return 1;
		} else {
			double[][] matrix = { { 1, 1 }, { 1, 0 } };
			double[][] result = Matrix.power(matrix, n - 1);
			Double fib = new Double(result[0][0]);
			System.out.println(fib.intValue());
			return fib.intValue();
		}
	}

	/**
	 * Prints out an array of integers in one line separated by comma
	 *
	 * @param array
	 *            an array of integers
	 */
	public void printArray(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out.println(array[array.length - 1]);
	}

	/**
	 * Prints out an array of integers in one line separated by comma
	 *
	 * @param words
	 *            an array of integers
	 */
	public void printArray(String[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out.println(array[array.length - 1]);
	}

	/**
	 * Playground!
	 *
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		Kakero test = new Kakero();
		int[] numbers = { 10, 49, 18, 7, 6, 35, 4, 3, 2, 2, 21 };
		String[] dictionary = { "cats", "tac", "feedback", "tarzanz",
				"computer", "okonomiyaki", "test", "back", "cabana", "nicest",
				"cab" };
		String word = "bac";

		// test.printArray(Sort.mergeSort(numbers));

		// Sort.quickSort(numbers, 0, 10);
		// test.printArray(numbers);

		// System.out.println(test.nthOrderNumber(numbers, 0, 9, 1));

		// Sort.buildMaxHeap(numbers, numbers.length);

		// Sort.heapSort(numbers);

		// test.printArray(numbers);

		// test.doesTwoNumbersSumUpToInputNoDuplicate(numbers, 7);

		// System.out.println(test.shortestWordIncludingStrings(word,
		// dictionary));

		// Sort.quickSort(numbers, 0, 9);
		// System.out.println(test.binarySearch(numbers, 10));
		// System.out.println(test.binarySearch(numbers, 666));

		// System.out.println(test.isStringContainsAnother("bcabz", "abbc"));

		// test.fibonacci(0);
		// test.fibonacci(1);
		// test.fibonacci(2);
		// test.fibonacci(3);
		// test.fibonacci(4);
		// test.fibonacci(5);
		// test.fibonacci(6);
		// test.fibonacci(40);

		// test.fibonacciMatrix(0);
		// test.fibonacciMatrix(1);
		// test.fibonacciMatrix(2);
		// test.fibonacciMatrix(3);
		// test.fibonacciMatrix(4);
		// test.fibonacciMatrix(5);
		// test.fibonacciMatrix(6);
		// test.fibonacciMatrix(40);
	}

}
