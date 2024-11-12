import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MinMaxHeap {
    private ArrayList<Integer> heap;

    public MinMaxHeap() {
        heap = new ArrayList<>();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private boolean isMinLevel(int index) {
        return (int)(Math.log(index + 1) / Math.log(2)) % 2 == 0;
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

    public void insert(int value) {
        heap.add(value);
        int index = heap.size() - 1;
        bubbleUp(index);
    }

    private void bubbleUp(int index) {
        if (index == 0) return;
        int parentIndex = parent(index);
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
        int grandparent = parent(parent(index));
        if (index > 2 && heap.get(index) < heap.get(grandparent)) {
            swap(index, grandparent);
            bubbleUpMin(grandparent);
        }
    }

    private void bubbleUpMax(int index) {
        int grandparent = parent(parent(index));
        if (index > 2 && heap.get(index) > heap.get(grandparent)) {
            swap(index, grandparent);
            bubbleUpMax(grandparent);
        }
    }

    public int deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Heap is empty.");
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            heap.set(0, last);
            trickleDown(0);
        }
        return min;
    }

    public int deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Heap is empty.");
        int maxIndex;
        if (heap.size() == 1) {
            return heap.remove(0);
        } else if (heap.size() == 2) {
            return heap.remove(1);
        } else {
            maxIndex = (heap.get(1) > heap.get(2)) ? 1 : 2;
        }
        int max = heap.get(maxIndex);
        int last = heap.remove(heap.size() - 1);
        if (maxIndex < heap.size()) {
            heap.set(maxIndex, last);
            trickleDown(maxIndex);
        }
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
        while (leftChild(index) < heap.size()) {
            int minIndex = findMinIndexAmongChildrenAndGrandchildren(index);
            if (minIndex == -1) break;
            if (minIndex > rightChild(index)) {
                if (heap.get(minIndex) < heap.get(index)) {
                    swap(minIndex, index);
                    int parentIndex = parent(minIndex);
                    if (heap.get(minIndex) > heap.get(parentIndex)) {
                        swap(minIndex, parentIndex);
                    }
                    index = minIndex;
                } else {
                    break;
                }
            } else {
                if (heap.get(minIndex) < heap.get(index)) {
                    swap(minIndex, index);
                }
                break;
            }
        }
    }

    private void trickleDownMax(int index) {
        while (leftChild(index) < heap.size()) {
            int maxIndex = findMaxIndexAmongChildrenAndGrandchildren(index);
            if (maxIndex == -1) break;
            if (maxIndex > rightChild(index)) {
                if (heap.get(maxIndex) > heap.get(index)) {
                    swap(maxIndex, index);
                    int parentIndex = parent(maxIndex);
                    if (heap.get(maxIndex) < heap.get(parentIndex)) {
                        swap(maxIndex, parentIndex);
                    }
                    index = maxIndex;
                } else {
                    break;
                }
            } else {
                if (heap.get(maxIndex) > heap.get(index)) {
                    swap(maxIndex, index);
                }
                break;
            }
        }
    }

    private int findMinIndexAmongChildrenAndGrandchildren(int index) {
        int minIndex = -1;
        int left = leftChild(index);
        int right = rightChild(index);
        if (left < heap.size()) minIndex = left;
        if (right < heap.size() && heap.get(right) < heap.get(minIndex)) minIndex = right;

        int leftLeft = leftChild(left);
        int leftRight = rightChild(left);
        int rightLeft = leftChild(right);
        int rightRight = rightChild(right);

        if (leftLeft < heap.size() && heap.get(leftLeft) < heap.get(minIndex)) minIndex = leftLeft;
        if (leftRight < heap.size() && heap.get(leftRight) < heap.get(minIndex)) minIndex = leftRight;
        if (rightLeft < heap.size() && heap.get(rightLeft) < heap.get(minIndex)) minIndex = rightLeft;
        if (rightRight < heap.size() && heap.get(rightRight) < heap.get(minIndex)) minIndex = rightRight;

        return minIndex;
    }

    private int findMaxIndexAmongChildrenAndGrandchildren(int index) {
        int maxIndex = -1;
        int left = leftChild(index);
        int right = rightChild(index);
        if (left < heap.size()) maxIndex = left;
        if (right < heap.size() && heap.get(right) > heap.get(maxIndex)) maxIndex = right;

        int leftLeft = leftChild(left);
        int leftRight = rightChild(left);
        int rightLeft = leftChild(right);
        int rightRight = rightChild(right);

        if (leftLeft < heap.size() && heap.get(leftLeft) > heap.get(maxIndex)) maxIndex = leftLeft;
        if (leftRight < heap.size() && heap.get(leftRight) > heap.get(maxIndex)) maxIndex = leftRight;
        if (rightLeft < heap.size() && heap.get(rightLeft) > heap.get(maxIndex)) maxIndex = rightLeft;
        if (rightRight < heap.size() && heap.get(rightRight) > heap.get(maxIndex)) maxIndex = rightRight;

        return maxIndex;
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // New method to print the heap structure
    public void printHeap() {
        System.out.println("Heap:" + heap);
    }

    public static void main(String[] args) {
        MinMaxHeap minMaxHeap = new MinMaxHeap();

        minMaxHeap.insert(50);
        minMaxHeap.insert(3);
        minMaxHeap.insert(2);
        minMaxHeap.insert(47);
        minMaxHeap.insert(6);
        minMaxHeap.insert(49);
        minMaxHeap.printHeap();
       

      
    }
}