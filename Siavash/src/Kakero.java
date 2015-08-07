import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Kakero {
	
	/**
	 * Merge sort for an array of integers 
	 *
	 * @param  numbers  an array of integers
	 */
	public int[] mergeSort(int[] numbers) {
		if(numbers.length == 1) {
			return numbers;
		}
		int[] firstHalf     = Arrays.copyOfRange(numbers, 0, numbers.length/2);
		int[] secondHalf    = Arrays.copyOfRange(numbers, numbers.length/2, numbers.length);
		int[] newFirstHalf  = mergeSort(firstHalf);
		int[] newSecondHalf = mergeSort(secondHalf);
		int[] merged = merge(newFirstHalf, newSecondHalf);
		return merged;
	}
	
	/**
	 * Merge step for merge sort: merges two sorted sub arrays together
	 *
	 * @param  firstHalf   an array of sorted integers
	 * @param  secondHalf  an array of sorted integers
	 */
	public int[] merge(int[] firstHalf, int[] secondHalf) {
		int[] merged        = new int[firstHalf.length + secondHalf.length];
		int firstHalfIndex  = 0;
		int secondHalfIndex = 0;
		for(int i = 0; i < merged.length; i++) {
			if(firstHalfIndex < firstHalf.length && secondHalfIndex < secondHalf.length) {
				if(firstHalf[firstHalfIndex] < secondHalf[secondHalfIndex]) {
					merged[i] = firstHalf[firstHalfIndex];
					firstHalfIndex++;
				} else {
					merged[i] = secondHalf[secondHalfIndex];
					secondHalfIndex++;
				}
			} else if(firstHalfIndex < firstHalf.length) {
				merged[i] = firstHalf[firstHalfIndex];
				firstHalfIndex++;
			} else if(secondHalfIndex < secondHalf.length) {
				merged[i] = secondHalf[secondHalfIndex];
				secondHalfIndex++;
			} else {
				break;
			}
		}
		return merged;
	}
	
	/**
	 * Quick sort using first element as pivot in each partitioning
	 *
	 * @param  numbers   an array of integers
	 * @param  low   start index
	 * @param  high   end index
	 */
	public void quickSort(int[] numbers, int low, int high) {
		int lowIndex  = low;
		int highIndex = high;
		int pivot     = numbers[low + (high - low)/2];
		while(lowIndex <= highIndex) {
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
	 * @param  numbers   an array of integers
	 * @param  low   start index
	 * @param  high   end index
	 * @param  order   n: the order we are looking for
	 */
	public int nthOrderNumber(int[] numbers, int low, int high, int order) {
		int lowIndex  = low;
		int highIndex = high;
		int pivot     = numbers[low];
		//int pivot     = numbers[low + (high - low)/2];
		while(lowIndex <= highIndex) {
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
		if(highIndex + 2 == order) {
			return numbers[highIndex + 1];
		} else if(highIndex + 2 < order) {
			return nthOrderNumber(numbers, lowIndex, high, order);
		} else {
			return nthOrderNumber(numbers, low, highIndex, order);
		}
	}
	
	/**
	 * Experimental code for quick sort's partitioning 
	 *
	 * @param  numbers   an array of integers
	 * @param  start   start index
	 * @param  end   end index
	 */
	public int quickSortPartition(int[] numbers, int start, int end) {
		int partition = numbers[start];
		int i = start + 1;
		for(int j = start + 1; j <= end; j++) {
			if(numbers[j] < partition) {
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
	 * @param  numbers   an array of integers
	 */
	public void printArray(int[] array) {
		for(int i = 0; i < array.length - 1; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out.println(array[array.length - 1]);
	}
	
	/**
	 * Rearranges elements in an array of integers to represent a max heap.
	 * Although we wont need to pass the length when making the heap, I added the parameter since I need it for heap sort
	 *
	 * @param  numbers   an array of integers
	 * @param  length   length of array
	 *  
	 */
	public void buildMaxHeap(int[] numbers, int length) {
		for(int i = numbers.length - 1; i >= 0; i--) {
			maxHeapify(numbers, i, length);
		}
	}
	
	/**
	 * Max heapifies an array. Used for buildHeap and heapSort
	 *
	 * @param  numbers   an array of integers
	 * @param  index   specific index that we are visiting and wanting to heapify
	 * @param  length   length of array
	 *  
	 */
	public void maxHeapify(int[] numbers, int index, int length) {
		int left   = 2 * index + 1;
		int right  = 2 * index + 2;
		int parent = index;
		if(left < length && numbers[left] > numbers[parent]) {
			parent = left;
		}
		if(right < length && numbers[right] > numbers[parent]) {
			parent = right;
		}
		if(parent != index) {
			int temp = numbers[parent];
			numbers[parent] = numbers[index];
			numbers[index] = temp;
			maxHeapify(numbers, parent, length);
		}
	}
	
	/**
	 * Heap sort for an array of integer
	 *
	 * @param  numbers   an array of integers
	 *  
	 */
	public void heapSort(int[] numbers) {
		for(int i = numbers.length - 1; i >= 0; i--) {
			buildMaxHeap(numbers, i + 1);
			int temp = numbers[i];
			numbers[i] = numbers[0];
			numbers[0] = temp;
		}
	}
	
	/**
	 * Classic interview question: given an array of integers and a number, figure out if any two numbers in the array sums up to the input number.
	 * This method solves the question in O(n) runtime; however, it also uses O(n) space since I use a HashSet to store the differences
	 *
	 * @param  numbers   an array of integers
	 * @param  input   an integer 
	 *  
	 */
	public boolean doesTwoNumbersSumUpToInputUsingHash(int[] numbers, int input) {
		Set difference = new HashSet();
		for (int number : numbers) {
			difference.add(input - number);
		}
		for (int number : numbers) {
			if(difference.contains(number)) {
				System.out.println(number);
				System.out.println(input - number);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Classic interview question: given an array of integers and a number, figure out if any two numbers in the array sums up to the input number.
	 * This method solves the question in O(nLogn) runtime (since I sort the array first) but it is more efficient it terms space if quick sort or some in place sorting algorithm is used.
	 *
	 * @param  numbers   an array of integers
	 * @param  input   an integer 
	 *  
	 */
	public boolean doesTwoNumbersSumUpToInputNoDuplicate(int[] numbers, int input) {
		int j = numbers.length - 1;
		quickSort(numbers, 0, numbers.length - 1);
		for(int i = 0; i < j;) {
			if(numbers[i] + numbers[j] == input) {
				System.out.println(numbers[i]);
				System.out.println(numbers[j]);
				return true;
			} else if(numbers[i] + numbers[j] > input) {
				j--;
			} else {
				i++;
			}
		}
		return false;
	}
	
	public int countInversions(int[] numbers) {
		return 0;
	}

	/**
	 * Playground!
	 *
	 * @param  args
	 *  
	 */
	public static void main(String[] args) {
		Kakero test = new Kakero();
		int[] numbers = {10,49,18,7,6,35,4,3,2,21};
		//test.quickSort(numbers, 0, 9);
		
		//System.out.println(test.nthOrderNumber(numbers, 0, 9, 1));
		
		//test.buildMaxHeap(numbers, numbers.length);
		
		//test.heapSort(numbers);
		
		//test.printArray(numbers);
		
		test.doesTwoNumbersSumUpToInputNoDuplicate(numbers, 7);
	}

}
