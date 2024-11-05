package t4;

import java.util.Scanner;

class BinaryMaxHeap {

    public static int heapSize;

    private static int left(int index) {
        return 2 * index + 1;
    }

    private static int right(int index) {
        return 2 * index + 2;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void maxHeapifyStepDownIterative(int[] arr, int n, int i) {
        while(true) {
            int largestValueIndex = i;
            int leftChildIndex = left(i);
            int rightChildIndex = right(i);

            if (leftChildIndex < n && arr[leftChildIndex] > arr[largestValueIndex])
                largestValueIndex = leftChildIndex;

            if (rightChildIndex < n && arr[rightChildIndex] > arr[largestValueIndex])
                largestValueIndex = rightChildIndex;

            // If the largest value is still the current node, the heap property is restored
            if (largestValueIndex == i) {
                break;
            }

            // Swap the current node with the largest child
            swap(arr, i, largestValueIndex);

            // Move down to the child node
            i = largestValueIndex;
        }
    }

    private static void maxHeapifyStepDownRecursive(int[] arr, int n, int i) {
        int largestValueIndex = i; // largestValueIndex will store the index of the element which is greater
        // between parent, left child and right child.
        int leftChildIndex = left(i);
        int rightChildIndex = right(i);

        if (leftChildIndex < n && arr[leftChildIndex] > arr[largestValueIndex])
            largestValueIndex = leftChildIndex;

        if (rightChildIndex < n && arr[rightChildIndex] > arr[largestValueIndex])
            largestValueIndex = rightChildIndex;

        if (largestValueIndex != i) {
            swap(arr, i, largestValueIndex);
            maxHeapifyStepDownRecursive(arr, n, largestValueIndex);
        }
    }

    public static void buildMaxHeap(int[] arr, int n) {

        heapSize = n;

        int startIndex = (n / 2) - 1;

        for (int i = startIndex; i >= 0; i--) {
//			maxHeapifyStepDownRecursive(arr, n, i);
            maxHeapifyStepDownIterative(arr, i, 0);
        }
    }

    public static void heapSort(int[] arr, int n) {
        buildMaxHeap(arr, n);

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            maxHeapifyStepDownIterative(arr, i, 0);
        }
    }

    public static void printHeap(int[] arr) {

        if(heapSize == 0) {
            System.err.println("Oops!! heap is empty!!");
            return;
        }

        for (int i = 0; i < heapSize; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}

public class HeapSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        sc.close();

        BinaryMaxHeap.buildMaxHeap(arr, n);
        BinaryMaxHeap.printHeap(arr);

        System.out.println("\n---------------------");
        BinaryMaxHeap.heapSort(arr, n);
        for (int x : arr)
			System.out.print(x + " ");
    }
}
