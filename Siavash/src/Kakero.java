import java.text.Collator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Kakero {

	/**
	 * Merge sort for an array of integers
	 *
	 * @param numbers
	 *           an array of integers
	 */
	public int[] mergeSort(int[] numbers) {
		if (numbers.length == 1) {
			return numbers;
		}
		int[] sortedFirstHalf = mergeSort(Arrays.copyOfRange(numbers, 0, numbers.length / 2));
		int[] sortedSecondHalf = mergeSort(Arrays.copyOfRange(numbers, numbers.length / 2, numbers.length));
		int[] merged = merge(sortedFirstHalf, sortedSecondHalf);
		return merged;
	}

	/**
	 * Merge step for merge sort: merges two sorted sub arrays together
	 *
	 * @param firstHalf
	 *           an array of sorted integers
	 * @param secondHalf
	 *           an array of sorted integers
	 */
	public int[] merge(int[] firstHalf, int[] secondHalf) {
		int[] merged = new int[firstHalf.length + secondHalf.length];
		int firstHalfIndex = 0;
		int secondHalfIndex = 0;
		for (int i = 0; i < merged.length; i++) {
			if (firstHalfIndex < firstHalf.length && secondHalfIndex < secondHalf.length) {
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
	 *           an array of integers
	 * @param low
	 *           start index
	 * @param high
	 *           end index
	 */
	public void quickSort(int[] numbers, int low, int high) {
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
	 * Finds the nth largest number in an array. It's implemented based on quick sort's algorithm
	 *
	 * @param numbers
	 *           an array of integers
	 * @param low
	 *           start index
	 * @param high
	 *           end index
	 * @param order
	 *           n: the order we are looking for
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
	 * Experimental code for quick sort's partitioning
	 *
	 * @param numbers
	 *           an array of integers
	 * @param start
	 *           start index
	 * @param end
	 *           end index
	 */
	public int quickSortPartition(int[] numbers, int start, int end) {
		int partition = numbers[start];
		int i = start + 1;
		for (int j = start + 1; j <= end; j++) {
			if (numbers[j] < partition) {
				int temp = numbers[j];
				numbers[j] = numbers[i];
				numbers[i] = temp;
				i++;
			}
		}
		int temp = numbers[start];
		numbers[start] = numbers[i - 1];
		numbers[i - 1] = temp;
		return i - 1;
	}

	/**
	 * Prints out an array of integers in one line separated by comma
	 *
	 * @param array
	 *           an array of integers
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
	 *           an array of integers
	 */
	public void printArray(String[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out.println(array[array.length - 1]);
	}

	/**
	 * Rearranges elements in an array of integers to represent a max heap. Although we wont need to pass the length when
	 * making the heap, I added the parameter since I need it for heap sort
	 *
	 * @param numbers
	 *           an array of integers
	 * @param length
	 *           length of array
	 * 
	 */
	public void buildMaxHeap(int[] numbers, int length) {
		for (int i = numbers.length - 1; i >= 0; i--) {
			maxHeapify(numbers, i, length);
		}
	}

	/**
	 * Max heapifies an array. Used for buildHeap and heapSort
	 *
	 * @param numbers
	 *           an array of integers
	 * @param index
	 *           specific index that we are visiting and wanting to heapify
	 * @param length
	 *           length of array
	 * 
	 */
	public void maxHeapify(int[] numbers, int index, int length) {
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
	 * Heap sort for an array of integer
	 *
	 * @param numbers
	 *           an array of integers
	 * 
	 */
	public void heapSort(int[] numbers) {
		for (int i = numbers.length - 1; i >= 0; i--) {
			buildMaxHeap(numbers, i + 1);
			int temp = numbers[i];
			numbers[i] = numbers[0];
			numbers[0] = temp;
		}
	}

	/**
	 * Classic interview question: given an array of integers and a number, figure out if any two numbers in the array
	 * sums up to the input number. This method solves the question in O(n) runtime; however, it also uses O(n) space
	 * since I use a HashSet to store the differences
	 *
	 * @param numbers
	 *           an array of integers
	 * @param input
	 *           an integer
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
	 * Classic interview question: given an array of integers and a number, figure out if any two numbers in the array
	 * sums up to the input number. This method solves the question in O(nLogn) runtime (since I sort the array first)
	 * but it is more efficient it terms space if quick sort or some in place sorting algorithm is used.
	 *
	 * @param numbers
	 *           an array of integers
	 * @param input
	 *           an integer
	 * 
	 */
	public boolean doesTwoNumbersSumUpToInputNoDuplicate(int[] numbers, int input) {
		int j = numbers.length - 1;
		int i = 0;
		quickSort(numbers, 0, numbers.length - 1);
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
	 *           an array of integers
	 * 
	 */
	public int countInversions(int[] numbers) {
		// TODO: Implementation!
		return 0;
	}

	/**
	 * Find shortest string in the dictionary that includes all the giving strings
	 *
	 * @param word
	 *           a String
	 * @param dictionary
	 *           an array of Strings
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
	 *           an array of sorted integers
	 * @param key
	 *           key value we would like to look up
	 */
	public boolean binarySearch(int[] numbers, int key) {
		int low = 0;
		int high = numbers.length - 1;
		while (high >= low) {
			int middle = (high + low) / 2;
			if (numbers[middle] == key) {
				return true;
			} else if (numbers[middle] > key) {
				high = middle + 1;
			} else {
				low = middle - 1;
			}
		}
		return false;
	}

	/**
	 * Returns true if a given reference contains another String. The difference between this and Java's Contains
	 * function is that this doesn't consider the exact character ordering of the key
	 *
	 * @param reference
	 *           reference String
	 * @param key
	 *           key String that we want to lookup
	 */
	public boolean isStringContainsAnother(String reference, String key) {
		for (int i = 0; i < key.length(); i++) {
			int index = reference.indexOf(key.charAt(i));
			if (index == -1) {
				return false;
			}
			reference = reference.substring(0, index) + reference.substring(index + 1);
		}
		return true;
	}

	/**
	 * Quick sort Strings by length using first element as pivot in each partitioning
	 *
	 * @param words
	 *           an array of Strings
	 * @param low
	 *           start index
	 * @param high
	 *           end index
	 */
	public void quickSortStringsByLength(String[] words, int low, int high) {
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

	/**
	 * Rearranges characters in a String and sorts it alphabetically
	 *
	 * @param words
	 *           an array of Strings
	 */
	public String sortStringAlphabetically(String word) {
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
	 * Find the sentence with most number of words
	 *
	 * @param text
	 *           a String
	 */
	public int maxWords(String text) {
		int max = 0;
		String[] sentences = text.split("\\.|\\?|\\!");
		for (String sentence : sentences) {
			String[] words = sentence.trim().split("\\s+");
			if (words.length == 1 && words[0].equals("")) { // this is the only case that we can have an empty string as a word
				continue;
			} else {
				if (words.length > max) {
					max = words.length;
				}
			}
		}
		return max;
	}

	/**
	 * Can we sort the given array with only one swap
	 *
	 * @param numbers
	 *           array of integers
	 */
	public boolean oneSwap(int[] numbers) {
		int count = 0;
		int firstIndex = -1;
		int secondIndex = -1;

		for (int i = 0; i < numbers.length - 2; i++) { // note that we do length - 2 to avoid array out of bound in last loop
			if (numbers[i] > numbers[i + 1]) {
				count++;
				if (count == 1) {
					firstIndex = i;
				} else if (count == 2) {
					secondIndex = i + 1; // in this case we want the next element (smaller one)
				} else { // there are 3 or more cases of numbers[i] > numbers[i + 1] which means we definitely need more than one swap 
					System.out.println("blah");
					return false;
				}
			}
		}
		// at this point count is either 0, 1, or 2
		if (count < 2) { // for 0 array is already sorted, for 1 we do a single swap
			return true;
		} else { // exactly 2 cases, need to check whether one swap will work
			if (numbers[firstIndex] >= numbers[secondIndex - 1] && numbers[firstIndex] <= numbers[secondIndex + 1] // note that secondIndex can never be the last index so no need to check for out of bound
					&& (firstIndex == 0 || numbers[secondIndex] >= numbers[firstIndex - 1]) // doing firstIndex == 0 to avoid out of bound exception
					&& numbers[secondIndex] <= numbers[firstIndex + 1]) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Playground!
	 *
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		Kakero test = new Kakero();
		//int[] numbers = { 10, 49, 18, 7, 6, 35, 4, 3, 2, 2, 21 };
		//String[] dictionary = { "cats", "tac", "feedback", "tarzanz", "computer", "okonomiyaki", "test", "back", "cabana",
		//		"nicest", "cab" };
		//String word = "bac";
		// test.printArray(test.mergeSort(numbers));
		// test.quickSort(numbers, 0, 9);

		// System.out.println(test.nthOrderNumber(numbers, 0, 9, 1));

		// test.buildMaxHeap(numbers, numbers.length);

		// test.heapSort(numbers);

		// test.printArray(numbers);

		// test.doesTwoNumbersSumUpToInputNoDuplicate(numbers, 7);

		//System.out.println(test.shortestWordIncludingStrings(word, dictionary));

		// test.quickSort(numbers, 0, 9);
		// System.out.println(test.binarySearch(numbers, 10));

		// System.out.println(test.isStringContainsAnother("bcabz", "abbc"));

		//System.out.println(test.maxWords("We test coders. Give us a try?"));
		//System.out.println(test.maxWords("Forgot CVs..Save time . x x "));

		//int[] numbers = { 0, 5, 3, 3, 1, 7, 9 }; // normal one swap with two A[i] > A[i+1] case; should return true 
		//System.out.println(test.oneSwap(numbers));

		//int[] numbers = { 5, 3, 3, 1, 7, 9 }; // normal one swap with two A[i] > A[i+1] case and firstIndex is 0 and we covered out of bound; should return true
		//System.out.println(test.oneSwap(numbers));

		//int[] numbers = { 10, 3, 6, 4, 7 }; // two A[i] > A[i+1] case and one swap wont work; should return false
		//System.out.println(test.oneSwap(numbers));

		//int[] numbers = { 1, 3, 5, 3, 7 }; // one occurrence of A[i] > A[i+1]; should return true
		//System.out.println(test.oneSwap(numbers));

		//int[] numbers = { 1, 3, 3, 5, 7 }; // no of A[i] > A[i+1], array already sorted; should return true
		//System.out.println(test.oneSwap(numbers));

		//int[] numbers = { 1, 3, 2, 4, 3, 4, 3 }; // more than two occurrence of A[i] > A[i+1]; should return false
		//System.out.println(test.oneSwap(numbers));
	}

}
