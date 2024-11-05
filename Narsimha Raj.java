1. Write a program to build in-place a HEAP of n elements given in an array

public class Main{
    int[] heap;
    int n;
    
    Main(int[] array){
        this.heap = array;
        this.n = array.length;
        mainheap();
    }
    
    private void mainheap(){
        for(int i = n/2 -1; i>=0; i--){
            heapify(i);
        }
    }
    
    private void heapify(int index){
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        
        if (left < n && heap[left] > heap[largest])
            largest = left;
        if (right < n && heap[right] > heap[largest])
            largest = right;
        if (largest!=index){
            swap(largest, index);
            heapify(largest);
        }
    }
    
    private void swap(int a, int b){
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }
    
    public void printl(){
        for (int value : heap){
            System.out.print(value + " ");
        }
        System.out.println();
    }
    
    public static void main(String args[]){
        int[] array = {3, 5, 1, 10, 2, 7};
        Main hh = new Main(array);
        hh.printl();
    }
}


2. Write a program to sort a list of n elements using HEAP SORT

public class HeapSort{
    
    public void sort(int[] arr){
        int n = arr.length;
        for (int i=n/2-1; i>=0; i--){
            heapify(arr, n, i);
        }
        
        for (int i = n-1; i>=0; i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            
            heapify(arr,i,0);
        }
    }
    
    public void heapify(int[] arr, int n, int i){
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr[left] > arr[largest])
            largest = left;
        if (right < n && arr[right] > arr[largest])
            largest = right;
        if (largest!=i){
            int temp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = temp;
            
            heapify(arr,n,largest);
        }
    }
    
    public void printl(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
    public static void main(String args[]){
        int[] arr = {22,13,17,11,10,14,12};
        HeapSort hs = new HeapSort();
        hs.sort(arr);
        hs.printl(arr);
    }
}

3. Write a program to find the Kth largest element using Priority Queue

import java.util.*;

public class KLargest {

    public static int kthLargestEle(int arr[], int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (pq.peek() < arr[i]) {
                pq.remove();
                pq.add(arr[i]);
            }
        }
        return pq.peek();
    }

    public static void main(String args[]) {
        int arr[] = { 20, 10, 60, 30, 50, 40 };

        int k = 2;
        int output = kthLargestEle(arr, k);

        System.out.println(k + "th largest ele in the array is: " + output);
    }
}

4. Given a Priority Queue implemented as a heap write a program to delete any arbitrary node in the heap.

public class Cheap {
    int[] heap;
    int size;

    Cheap(int[] arr) {
        this.heap = arr;
        this.size = arr.length;
        buildheap();
    }

    void buildheap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapifydown(i);
        }
    }

    void heapifydown(int index) {
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < size && heap[left] > heap[largest]) {
                largest = left;
            }

            if (right < size && heap[right] > heap[largest]) {
                largest = right;
            }

            if (largest == index) {
                break;
            }

            int temp = heap[index];
            heap[index] = heap[largest];
            heap[largest] = temp;

            index = largest;
        }
    }

    public void delete(int data) {
        int ind = find(data);
        if (ind == -1)
            return;
        heap[ind] = heap[size - 1];
        size--;
        heapifydown(ind);
    }

    public int find(int item) {
        for (int i = 0; i < size; i++) {
            if (heap[i] == item)
                return i;
        }
        return -1;
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        int arr[] = {20, 10, 60, 30, 50, 40};
        Cheap ch = new Cheap(arr);
        ch.printHeap();
        ch.delete(30);
        ch.printHeap();
    }
}

5. Program to implement Double Ended HEAP with search, insertion and delete_MIN and delete_MAX operations.

public class buildheap{
    int[] heap;
    int size;
    buildheap(int high){
        this.heap = new int[high];
        this.size = 0;
    }
    
    public void insert(int item){
        if(size==heap.length){
            return;
        }
        
        heap[size] = item;
        size++;
        heapifyup(size-1);
    }
    
    public void heapifyup(int index){
        int parent;
        while(index>0){
            parent = (index-1)/2;
            if(heap[parent] < heap[index]){
                swap(parent,index);
                index = parent;
            }
            else{
                break;
            }
        }
    }
    
    public int deletemin(){
        if (size==0){
            System.out.print("Message");
        }
        int minindex = findminindex(); 
        int min = heap[minindex];
        heap[minindex] = heap[size-1];
        size--;
        
        if (minindex < size){
            heapifyup(minindex);
            heapifydown(minindex);
        }
        return min;
        
    }
    
    public int deletemax(){
        if (size==0){
            System.out.print("Message");
        }
        int max = heap[0];
        heap[0] = heap[size-1];
        size--;
        heapifydown(0);
        return max;
    }
    
    public void heapifydown(int index){
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        
        while(true){
            if (left < size && heap[left] > heap[largest])
                largest = left;
            if (right < size && heap[right] > heap[largest])
                largest = right;
            if(largest!=index){
                swap(largest,index);
                index = largest;
            }
            else{
                break;
            }
        }
    }
    
    
    public int findminindex(){
        int min = 0;
        for(int i=1;i<size;i++){
            if(heap[i] < heap[min]){
                min = i;
            }
        
        }
        return min;
    }
    
    
    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
    
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
    
    public boolean search(int value) {
        for (int i = 0; i < size; i++) {
            if (heap[i] == value) {
                return true;
            }
        }
        return false;
    }

    
    public static void main(String args[]){
        buildheap heap = new buildheap(10);
        heap.insert(50);
        heap.insert(60);
        heap.insert(61);
        heap.insert(52);
        heap.print();
        System.out.println(heap.deletemin());
        System.out.println(heap.deletemax());
        
    }
}

6. Write a program to decrease the priority of an element in Priority Queue implemented as HEAP.

public class PriorityQueueHeap {
    private int[] heap;
    private int size;

    public PriorityQueueHeap(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    public void insert(int value) {
        if (size >= heap.length) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size] = value;
        size++;
        heapifyUp(size - 1);
    }

    public int deleteMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return min;
    }

    public void decreaseKey(int oldValue, int newValue) {
        if (newValue >= oldValue) {
            throw new IllegalArgumentException("New value must be less than old value");
        }
        
        int index = find(oldValue);
        if (index == -1) {
            return;
        }
        heap[index] = newValue;
        heapifyUp(index);
    }

    private int find(int value) {
        for (int i = 0; i < size; i++) {
            if (heap[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private void heapifyUp(int index) {
        while (index > 0 && heap[parent(index)] > heap[index]) {
            swap(parent(index), index);
            index = parent(index);
        }
    }

    private void heapifyDown(int index) {
        while (leftChild(index) < size) {
            int smallerChildIndex = leftChild(index);
            if (rightChild(index) < size && heap[rightChild(index)] < heap[leftChild(index)]) {
                smallerChildIndex = rightChild(index);
            }
            if (heap[index] <= heap[smallerChildIndex]) {
                break;
            }
            swap(index, smallerChildIndex);
            index = smallerChildIndex;
        }
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PriorityQueueHeap heap = new PriorityQueueHeap(10);
        heap.insert(10);
        heap.insert(20);
        heap.insert(5);

        System.out.print("Heap before deleteMin: ");
        heap.printHeap();

        System.out.println("Deleted min: " + heap.deleteMin());

        System.out.print("Heap after deleteMin: ");
        heap.printHeap();

        heap.decreaseKey(20, 3);

        System.out.print("Heap after decreaseKey: ");
        heap.printHeap();
    }
}
