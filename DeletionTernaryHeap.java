package t8;

import java.util.Scanner;

class TernaryMaxHeapDS {

    public static int heapSize;

    private static int parent(int index) {
        return ((index - 1) / 3);
    }

    private static int left(int index) {
        return 3 * index + 1;
    }

    private static int middle(int index) {
        return 3 * index + 2;
    }

    private static int right(int index) {
        return 3 * index + 3;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void maxHeapifyStepDownIterative(int[] arr, int i) {
        while(true) {
            int largestValueIndex = i;
            int leftChildIndex = left(i);
            int middleChildIndex = middle(i);
            int rightChildIndex = right(i);

            if (leftChildIndex < heapSize && arr[leftChildIndex] > arr[largestValueIndex]) {
                largestValueIndex = leftChildIndex;
            }

            if (middleChildIndex < heapSize && arr[middleChildIndex] > arr[largestValueIndex]) {
                largestValueIndex = middleChildIndex;
            }

            if (rightChildIndex < heapSize && arr[rightChildIndex] > arr[largestValueIndex]) {
                largestValueIndex = rightChildIndex;
            }

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

    public static void buildMaxHeap(int[] arr, int n) {

        heapSize = n;

        int startIndex = (n - 1) / 3;

        for (int i = startIndex; i >= 0; i--) {
            maxHeapifyStepDownIterative(arr, i);
        }
    }

    private static int findNodeIndex(int[] arr, int n, int node) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == node) {
                return i;
            }
        }

        return -1;
    }

    public static void deleteNode(int[] arr, int node) {
        if (heapSize == 0) {
            throw new IllegalStateException("Heap is empty!");
        }

        int nodeIndex = findNodeIndex(arr, heapSize, node);
        if (nodeIndex == -1) {
            throw new IllegalArgumentException("This node " + node + " is not found in the heap!!");
        }

        arr[nodeIndex] = arr[heapSize - 1];
        heapSize--;

        maxHeapifyStepDownIterative(arr, nodeIndex);
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


public class DeletionTernaryHeap {
    public static void main(String[] args) throws java.lang.Exception {
        // your code goes here
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        TernaryMaxHeapDS.buildMaxHeap(arr, n);
        TernaryMaxHeapDS.printHeap(arr);

        TernaryMaxHeapDS.deleteNode(arr, 45);
        TernaryMaxHeapDS.printHeap(arr);

        sc.close();
    }
}