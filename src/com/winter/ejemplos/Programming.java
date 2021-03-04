package com.winter.ejemplos;

public class Programming {

	static void minValue(int arr[]) {
		int min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (min > arr[i]) {
				min = arr[i];
			}
		}
		System.out.println(min);
	}

	static void fidTheBlank(int theNumbers[]) {
		int sumOfAllNumbers = 0;
		int sumOfNumbersPresent = 0;
		int blankSpace = 0;
		for (int i = 0; i < theNumbers.length; i++) {
			sumOfAllNumbers += i + 1;
			sumOfNumbersPresent += theNumbers[i];
			if (theNumbers[i] == 0) {
				blankSpace = i;
			}
		}
		int missingNumber = sumOfAllNumbers - sumOfNumbersPresent;
		System.out.println("Missing number = " + missingNumber + " at location " + blankSpace + " of the array");
	}

	static int binarySearch(int arr[], int l, int r, int x) {
		if (r >= 1) {
			int mid = l + (r - l) / 2;
			if (arr[mid] == x)
				return mid;
			if (arr[mid] > x)
				return binarySearch(arr, l, mid - 1, x);
			return binarySearch(arr, mid + 1, r, x);
		}
		return -1;
	}

	static void bubbleSort(int[] arr) {
		int n = arr.length;
		int temp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				if (arr[j - 1] > arr[j]) {
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int numbers[] = { 12, -2, 10, 53, 30 };
		int n = numbers.length;
		int x = 10;
		int result = binarySearch(numbers, 0, n - 1, x);
		
		if (result == -1)
			System.out.println("Element " + x + " not present");
		else
			System.out.println("Element " + x + " found at index " + result);
		
		System.out.println("Array Before Bubble Sort");
		printArray(numbers);
		bubbleSort(numbers);
		System.out.println("Array After Bubble Sort");
		printArray(numbers);
	}

}
