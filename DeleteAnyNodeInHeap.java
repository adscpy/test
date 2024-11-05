package t1;

import java.util.Scanner;

class BinaryMaxHeapDS {

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
//		maxHeapifyStepDownRecursive(arr, nodeIndex);
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

public class DeleteAnyNodeInHeap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int nodeToDelete = sc.nextInt();

/*
 7
 20 10 60 30 50 40 70
 70

 o/p:
 70 50 60 30 10 40 20
 60 50 40 30 10 20
 */

        BinaryMaxHeapDS.buildMaxHeap(arr, n);
        BinaryMaxHeapDS.printHeap(arr);

        BinaryMaxHeapDS.deleteNode(arr, nodeToDelete);
        BinaryMaxHeapDS.printHeap(arr);

        sc.close();
    }
}
