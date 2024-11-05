package t2;

import java.util.Scanner;

class BinaryMaxHeapDS {

    public static int heapSize;

    private static int parent(int index) {
        return (index - 1) / 2;
    }

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

    private static void maxHeapifyStepUp(int[] arr, int i) {
        while (i > 0 && arr[parent(i)] < arr[i]) {
            swap(arr, i, parent(i));
            i = parent(i);
        }
    }

    private static void maxHeapifyStepDownIterative(int[] arr, int i) {
        while(true) {
            int largestValueIndex = i;
            int leftChildIndex = left(i);
            int rightChildIndex = right(i);

            if (leftChildIndex < heapSize && arr[leftChildIndex] > arr[largestValueIndex])
                largestValueIndex = leftChildIndex;

            if (rightChildIndex < heapSize && arr[rightChildIndex] > arr[largestValueIndex])
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

    private static void maxHeapifyStepDownRecursive(int[] arr, int i) {
        int largestValueIndex = i; // largestValueIndex will store the index of the element which is greater
        // between parent, left child and right child.
        int leftChildIndex = left(i);
        int rightChildIndex = right(i);

        if (leftChildIndex < heapSize && arr[leftChildIndex] > arr[largestValueIndex])
            largestValueIndex = leftChildIndex;

        if (rightChildIndex < heapSize && arr[rightChildIndex] > arr[largestValueIndex])
            largestValueIndex = rightChildIndex;

        if (largestValueIndex != i) {
            swap(arr, i, largestValueIndex);
            maxHeapifyStepDownRecursive(arr, largestValueIndex);
        }
    }

    public static void buildMaxHeap(int[] arr, int n) {

        heapSize = n;

        int startIndex = (n / 2) - 1;

        for (int i = startIndex; i >= 0; i--) {
//			maxHeapifyStepDownRecursive(arr, i);
            maxHeapifyStepDownIterative(arr, i);
        }
    }

    public static void decreasePriorityOfKey(int[] arr, int n, int i, int key) {
        if(i >= n || arr[i] <= key) {
            System.out.println("Invalid Operations!!");
            return;
        }

        arr[i] = key; // or arr[i] -= key; // This can also be implemented.
        if (i > 0 && arr[parent(i)] < arr[i]) {
            System.out.println("Going to step up!!"); // stepping up if new key is too high
            maxHeapifyStepUp(arr, i);
        }else {
            System.out.println("Going to step down!!"); // stepping down if new key is <= parent element i.e., too low.
            maxHeapifyStepDownIterative(arr, i);
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

public class DecPriorityInHeap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        BinaryMaxHeapDS.buildMaxHeap(arr, n);
        BinaryMaxHeapDS.printHeap(arr);

        int index = sc.nextInt(), key = sc.nextInt();
        BinaryMaxHeapDS.decreasePriorityOfKey(arr, n, index, key);
        BinaryMaxHeapDS.printHeap(arr);

        sc.close();
    }
}
