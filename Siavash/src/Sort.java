import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

public class Sort {

	/**
	 * Merge sort for an array of integers
	 *
	 * @param numbers
	 *            an array of integers
	 */
	public static int[] mergeSort(int[] numbers) {
		if (numbers.length == 1) {
			return numbers;
		}
		int[] sortedFirstHalf = mergeSort(Arrays.copyOfRange(numbers, 0,
				numbers.length / 2));
		int[] sortedSecondHalf = mergeSort(Arrays.copyOfRange(numbers,
				numbers.length / 2, numbers.length));
		int[] merged = merge(sortedFirstHalf, sortedSecondHalf);
		return merged;
	}

	/**
	 * Merge step for merge sort: merges two sorted sub arrays together
	 *
	 * @param firstHalf
	 *            an array of sorted integers
	 * @param secondHalf
	 *            an array of sorted integers
	 */
	public static int[] merge(int[] firstHalf, int[] secondHalf) {
		int[] merged = new int[firstHalf.length + secondHalf.length];
		int firstHalfIndex = 0;
		int secondHalfIndex = 0;
		for (int i = 0; i < merged.length; i++) {
			if (firstHalfIndex < firstHalf.length
					&& secondHalfIndex < secondHalf.length) {
				if (firstHalf[firstHalfIndex] < secondHalf[secondHalfIndex]) {
					merged[i] = firstHalf[firstHalfIndex];
					firstHalfIndex++;
				} else {
					merged[i] = secondHalf[secondHalfIndex];
					secondHalfIndex++;
				}
			} else if (firstHalfIndex < firstHalf.length) {
				merged[i] = firstHalf[firstHalfIndex];
				firstHalfIndex++;
			} else { // secondHalfIndex < secondHalf.length
				merged[i] = secondHalf[secondHalfIndex];
				secondHalfIndex++;
			}
		}
		return merged;
	}

	/**
	 * Quick sort using first element as pivot in each partitioning
	 *
	 * @param numbers
	 *            an array of integers
	 * @param low
	 *            start index
	 * @param high
	 *            end index
	 */
	public static void quickSort(int[] numbers, int low, int high) {
		int lowIndex = low;
		int highIndex = high;
		int pivot = numbers[low + (high - low) / 2];
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
		if (low < highIndex) {
			quickSort(numbers, low, highIndex);
		}
		if (lowIndex < high) {
			quickSort(numbers, lowIndex, high);
		}
	}

	/**
	 * Heap sort for an array of integer
	 *
	 * @param numbers
	 *            an array of integers
	 * 
	 */
	public static void heapSort(int[] numbers) {
		for (int i = numbers.length - 1; i >= 0; i--) {
			buildMaxHeap(numbers, i + 1);
			int temp = numbers[i];
			numbers[i] = numbers[0];
			numbers[0] = temp;
		}
	}

	/**
	 * Rearranges elements in an array of integers to represent a max heap.
	 * Although we wont need to pass the length when making the heap, I added
	 * the parameter since I need it for heap sort
	 *
	 * @param numbers
	 *            an array of integers
	 * @param length
	 *            length of array
	 * 
	 */
	public static void buildMaxHeap(int[] numbers, int length) {
		for (int i = numbers.length - 1; i >= 0; i--) {
			maxHeapify(numbers, i, length);
		}
	}

	/**
	 * Max heapifies an array. Used for buildHeap and heapSort
	 *
	 * @param numbers
	 *            an array of integers
	 * @param index
	 *            specific index that we are visiting and wanting to heapify
	 * @param length
	 *            length of array
	 * 
	 */
	public static void maxHeapify(int[] numbers, int index, int length) {
		int left = 2 * index + 1;
		int right = 2 * index + 2;
		int parent = index;
		if (left < length && numbers[left] > numbers[parent]) {
			parent = left;
		}
		if (right < length && numbers[right] > numbers[parent]) {
			parent = right;
		}
		if (parent != index) {
			int temp = numbers[parent];
			numbers[parent] = numbers[index];
			numbers[index] = temp;
			maxHeapify(numbers, parent, length);
		}
	}

	/**
	 * Rearranges characters in a String and sorts it alphabetically
	 *
	 * @param words
	 *            an array of Strings
	 */
	public static String sortStringAlphabetically(String word) {
		Collator collator = Collator.getInstance(new Locale("en", "EN"));
		String[] wordArray = word.split("");
		Arrays.sort(wordArray, collator);
		String sortedWord = "";
		for (int i = 0; i < wordArray.length; i++) {
			sortedWord += wordArray[i];
		}
		return sortedWord;
	}

	/**
	 * Quick sort Strings by length using first element as pivot in each
	 * partitioning
	 *
	 * @param words
	 *            an array of Strings
	 * @param low
	 *            start index
	 * @param high
	 *            end index
	 */
	public static void quickSortStringsByLength(String[] words, int low,
			int high) {
		int lowIndex = low;
		int highIndex = high;
		int pivot = words[low + (high - low) / 2].length();
		while (lowIndex <= highIndex) {
			while (words[lowIndex].length() < pivot) {
				lowIndex++;
			}
			while (words[highIndex].length() > pivot) {
				highIndex--;
			}
			if (lowIndex <= highIndex) {
				String temp = words[lowIndex];
				words[lowIndex] = words[highIndex];
				words[highIndex] = temp;
				lowIndex++;
				highIndex--;
			}
		}
		if (low < highIndex) {
			quickSortStringsByLength(words, low, highIndex);
		}
		if (lowIndex < high) {
			quickSortStringsByLength(words, lowIndex, high);
		}
	}
}
