package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MinBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V, P>> _heap;

    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MinBinHeapER() {
        _heap = new ArrayList<>();
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO: overloaded constructor
    public MinBinHeapER(Prioritized<V, P>[] initialEntries) {
        _heap = new ArrayList<>();
        for (int i = initialEntries.length - 1; i <= 0; i--) {
            _heap.add(initialEntries[i]);
            bubbledown(i);
        }
    }


    @Override
    public int size() {
        return _heap.size();
    }

    // TODO: enqueue
    @Override
    public void enqueue(V value, P priority) {
        Patient patient = new Patient(value, priority);
        _heap.add(patient);
        bubbleup(size() - 1);
    }

    // TODO: enqueue
    public void enqueue(V value) {
        Patient patient = new Patient(value);
        _heap.add(patient);
        bubbleup(size() - 1);
    }

    // TODO: dequeue
    @Override
    public V dequeue() {
        if (size() == 0) {
            return null;
        } else if (size() == 1) {
            V temp = _heap.get(0).getValue();
            _heap.remove(0);
            return(temp);
        } else {
            V value = _heap.get(0).getValue();
            _heap.set(0, _heap.get(size() - 1));
            bubbledown(0);
            _heap.remove(size() - 1);
            return value;
        }
    }

    // TODO: getMin
    @Override
    public V getMin() {
        if (size() == 0) {
            return null;
        } else {
            return _heap.get(0).getValue();
        }
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V, P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }

    public List bubbleup(int index) {
        if (index == 0) {
            return _heap;
        } else {
            Prioritized child = _heap.get(index);
            Prioritized parent = _heap.get((index - 1) / 2);
            if (child.getPriority().compareTo(parent.getPriority()) >= 0) {
                return _heap;
            } else {
                _heap.set(((index - 1) / 2), child);
                _heap.set(index, parent);
                return bubbleup(((index - 1) / 2));
            }
        }
    }

    public void bubbledown(int index) {
        int right = (2 * index) + 2;
        int left = (2 * index) + 1;
        Prioritized parent = _heap.get(index);
        if (right > size() && left >= size()) {
            return;
        } else if (right >= size()) {
            Prioritized leftchild = _heap.get(left);
            if (leftchild.getPriority().compareTo(_heap.get(index).getPriority()) < 0) {
                _heap.set(index, leftchild);
                _heap.set(left, parent);
                bubbledown(left);
            }
        } else {
            Prioritized leftchild = _heap.get(left);
            Prioritized rightchild = _heap.get(right);
            if ((leftchild.getPriority().compareTo(parent.getPriority()) < 0) || (rightchild.getPriority().compareTo(parent.getPriority()) < 0)) {
                if (leftchild.getPriority().compareTo(rightchild.getPriority()) < 0) {
                    _heap.set(index, leftchild);
                    _heap.set(left, parent);
                    bubbledown(left);
                } else {
                    _heap.set(index, rightchild);
                    _heap.set(right, parent);
                    bubbledown(right);
                }
            }
        }
    }
}
