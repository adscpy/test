package t5;

import java.util.PriorityQueue;
import java.util.Scanner;

public class KthLargestElementUsingPQ {
    private static int KthLargestElement(int[] arr, int n, int K) {

        if (n == 0 || K > n) {
            throw new IllegalArgumentException("Array is empty or K is greater than the number of elements.");
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for(int i = 0; i < n; i++) {
            priorityQueue.add(arr[i]);

            if(priorityQueue.size() > K) {
                priorityQueue.poll();
            }
        }

        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), K = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        sc.close();

        System.out.println(KthLargestElement(arr, n, K));
    }
}