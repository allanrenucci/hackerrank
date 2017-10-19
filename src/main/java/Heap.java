import java.util.*;

/** Min Heap */
public class Heap {
    private final int[] elems;
    private int size;
    private Map<Integer, Integer> indexes = new HashMap<>();

    public Heap(int maxSize) {
        this.elems = new int[maxSize];
        this.size = 0;
    }

    public void add(int v) {
        size += 1;
        elems[size] = v;
        indexes.put(v, size);

        int index = size;

        while (index > 1) {
            int parent = index / 2;

            if (elems[parent] > elems[index]) {
                // swap
                int tmp = elems[parent];
                elems[parent] = elems[index];
                elems[index] = tmp;

                indexes.put(elems[parent], parent);
                indexes.put(elems[index], index);
            } else {
                break;
            }

            index = parent;
        }
    }

    public void delete(int v) {
        int index = indexes.remove(v);

        elems[index] = elems[size--];
        indexes.put(elems[index], index);

        while (true) {
            int leftChild = index * 2;
            int rightChild = leftChild + 1;

            int toSwap;

            if (leftChild > size) {
                // no child
                break;
            } else if (rightChild > size) {
                // no right child

                if (elems[index] <= elems[leftChild]) {
                    break;
                }
                toSwap = leftChild;

            } else /* two children */{
                if (elems[index] <= elems[leftChild] && elems[index] <= elems[rightChild]) {
                    break;

                } else if (elems[leftChild] < elems[rightChild]) {
                    // left child smaller than right child
                    toSwap = leftChild;
                } else {
                    // right child smaller than left child
                    toSwap = rightChild;
                }
            }

            int tmp = elems[index];
            elems[index] = elems[toSwap];
            elems[toSwap] = tmp;

            indexes.put(elems[index], index);
            indexes.put(elems[toSwap], toSwap);

            index = toSwap;
        }
    }

    public void printMin() {
        System.out.println(elems[1]);
    }

    @Override
    public String toString() {
        return size + " " + java.util.Arrays.toString(elems);
    }
}
