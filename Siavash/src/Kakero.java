import java.util.Arrays;


public class Kakero {
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
		System.out.println(highIndex);
		if (low < highIndex) {
			quickSort(numbers, low, highIndex);
		}   
        if (lowIndex < high) {
        	quickSort(numbers, lowIndex, high);
        }   
	}
	
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
		System.out.println(i - 1);
		return i - 1;
	}
	
	public void printArray(int[] array) {
		for(int i = 0; i < array.length - 1; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out.println(array[array.length - 1]);
	}
	
	public void buildMaxHeap(int[] numbers, int length) {
		for(int i = numbers.length - 1; i >= 0; i--) {
			maxHeapify(numbers, i, length);
		}
	}
	
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
	
	public void heapSort(int[] numbers) {
		for(int i = numbers.length - 1; i >= 0; i--) {
			buildMaxHeap(numbers, i + 1);
			int temp = numbers[i];
			numbers[i] = numbers[0];
			numbers[0] = temp;
		}
	}

	public static void main(String[] args) {
		Kakero test = new Kakero();
		int[] numbers = {10,49,18,7,6,35,4,3,2,21};
		//test.quickSort(numbers, 0, 9);
		//test.printArray(numbers);
		//System.out.println(test.nthOrderNumber(numbers, 0, 9, 1));
		//test.buildMaxHeap(numbers, numbers.length);
		test.heapSort(numbers);
		test.printArray(numbers);
	}

}
