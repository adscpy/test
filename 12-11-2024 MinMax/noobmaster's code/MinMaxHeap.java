import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MinMaxHeap {
    private ArrayList<Integer> heap;

    public MinMaxHeap() {
        heap = new ArrayList<>();
        heap.add(null);
    }

    public void insert(int value) {
        heap.add(value);
        bubbleUp(heap.size() - 1);
    }

    private void bubbleUp(int index) {
        if (index == 1)
            return;

        int parentIndex = index / 2;

        if (isMinLevel(index)) {
            if (heap.get(index) > heap.get(parentIndex)) {
                swap(index, parentIndex);
                bubbleUpMax(parentIndex);
            } else {
                bubbleUpMin(index);
            }
        } else {
            if (heap.get(index) < heap.get(parentIndex)) {
                swap(index, parentIndex);
                bubbleUpMin(parentIndex);
            } else {
                bubbleUpMax(index);
            }
        }
    }

    private void bubbleUpMin(int index) {
        int grandparent = index / 4;
        if (grandparent > 0 && heap.get(index) < heap.get(grandparent)) {
            swap(index, grandparent);
            bubbleUpMin(grandparent);
        }
    }

    private void bubbleUpMax(int index) {
        int grandparent = index / 4;
        if (grandparent > 0 && heap.get(index) > heap.get(grandparent)) {
            swap(index, grandparent);
            bubbleUpMax(grandparent);
        }
    }

    public int findMin() {
        if (heap.size() <= 1)
            throw new NoSuchElementException("Heap is empty");
        return heap.get(1);
    }

    public int findMax() {
        if (heap.size() <= 1)
            throw new NoSuchElementException("Heap is empty");
        if (heap.size() == 2)
            return heap.get(1);
        if (heap.size() == 3)
            return heap.get(2);
        return Math.max(heap.get(2), heap.get(3));
    }

    public int deleteMin() {
        if (heap.size() <= 1)
            throw new NoSuchElementException("Heap is empty");

        int min = heap.get(1);
        heap.set(1, heap.remove(heap.size() - 1));
        trickleDown(1);
        return min;
    }

    public int deleteMax() {
        if (heap.size() <= 1)
            throw new NoSuchElementException("Heap is empty");

        int maxIndex;
        if (heap.size() == 2) {
            maxIndex = 1;
        } else if (heap.size() == 3) {
            maxIndex = 2;
        } else {
            maxIndex = (heap.get(2) > heap.get(3)) ? 2 : 3;
        }

        int max = heap.get(maxIndex);
        heap.set(maxIndex, heap.remove(heap.size() - 1));
        trickleDown(maxIndex);
        return max;
    }

    private void trickleDown(int index) {
        if (isMinLevel(index)) {
            trickleDownMin(index);
        } else {
            trickleDownMax(index);
        }
    }

    private void trickleDownMin(int index) {
        int smallest = index;

        int left = 2 * index;
        int right = 2 * index + 1;

        if (left < heap.size() && heap.get(left) < heap.get(smallest))
            smallest = left;
        if (right < heap.size() && heap.get(right) < heap.get(smallest))
            smallest = right;

        for (int child : new int[] { 2 * left, 2 * left + 1, 2 * right, 2 * right + 1 }) {
            if (child < heap.size() && heap.get(child) < heap.get(smallest))
                smallest = child;
        }

        if (smallest != index) {
            swap(index, smallest);
            if (smallest >= left && smallest <= right && heap.get(smallest) > heap.get(index / 2)) {
                swap(smallest, index / 2);
            }
            trickleDownMin(smallest);
        }
    }

    private void trickleDownMax(int index) {
        int largest = index;

        int left = 2 * index;
        int right = 2 * index + 1;

        if (left < heap.size() && heap.get(left) > heap.get(largest))
            largest = left;
        if (right < heap.size() && heap.get(right) > heap.get(largest))
            largest = right;

        for (int child : new int[] { 2 * left, 2 * left + 1, 2 * right, 2 * right + 1 }) {
            if (child < heap.size() && heap.get(child) > heap.get(largest))
                largest = child;
        }

        if (largest != index) {
            swap(index, largest);
            if (largest >= left && largest <= right && heap.get(largest) < heap.get(index / 2)) {
                swap(largest, index / 2);
            }
            trickleDownMax(largest);
        }
    }

    private boolean isMinLevel(int index) {
        return ((int) (Math.log(index) / Math.log(2))) % 2 == 0;
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void printHeap() {
        System.out.println("Heap: " + heap.subList(1, heap.size()));
    }

    public static void main(String[] args) {
        MinMaxHeap minMaxHeap = new MinMaxHeap();
        
        Scanner sc = new Scanner(System.in);
        
        while(true) {
            System.out.println("Please enter the choice of operation to be performed.");
            System.out.print("1. Insert \t 2. DeleteMin \t 3. DeleteMax \t 4. FindMin \t 5. FindMax \t 6. Print Heap: 7. Exit :");
            int option = sc.nextInt();
            switch(option) {
                case 1:
                    System.out.print("Please enter the element to be inserted: ");
                    int element = sc.nextInt();
                    minMaxHeap.insert(element);
                    break;
                case 2:
                    System.out.println("Deleted min: " + minMaxHeap.deleteMin());
                    break;
                case 3:
                    System.out.println("Deleted max: " + minMaxHeap.deleteMax());
                    break;
                case 4:
                    System.out.println("Min: " + minMaxHeap.findMin());
                    break;
                case 5:
                    System.out.println("Max: " + minMaxHeap.findMax());
                    break;
                case 6:
                    minMaxHeap.printHeap();
                    break;
                case 7:
                    System.out.println("Exiting out of the program");
                    System.exit(0);
            }
        }

        // minMaxHeap.insert(8);
        // minMaxHeap.insert(35);
        // minMaxHeap.insert(27);
        // minMaxHeap.insert(12);
        // minMaxHeap.insert(28);
        // minMaxHeap.insert(14);
        // minMaxHeap.insert(9);
        // minMaxHeap.insert(26);
        // minMaxHeap.insert(22);
        // minMaxHeap.insert(30);
        // minMaxHeap.insert(50);
        // minMaxHeap.insert(5);
        
        // minMaxHeap.insert(12);
        // minMaxHeap.insert(35);
        // minMaxHeap.insert(30);
        // minMaxHeap.insert(9);
        // minMaxHeap.insert(27);
        // minMaxHeap.insert(14);
        // minMaxHeap.insert(26);
        // minMaxHeap.insert(28);
        // minMaxHeap.insert(22);
        // minMaxHeap.insert();
        // minMaxHeap.insert();
        // minMaxHeap.insert();
        
        

        // minMaxHeap.printHeap();
        
    }
}