package utils;
import java.util.Arrays;

import java.util.Arrays;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class MyArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }


    public MyArrayList(MyArrayList<T> otherList) {
        this.size = otherList.size;
        this.array = Arrays.copyOf(otherList.array, otherList.size);
    }

    public void add(T element) {
        ensureCapacity();
        array[size++] = element;
    }

    public void remove(int index) {
        validateIndex(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
    }

    public void remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                remove(i);
                return;
            }
        }
    }


    public T get(int index) {
        validateIndex(index);
        return (T) array[index];
    }

    public int size() {
        return size;
    }

    public void forEach(Consumer<? super T> action) {
        for (int i = 0; i < size; i++) {
            action.accept((T) array[i]);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }


            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[currentIndex++];
            }
        };
    }

    private void ensureCapacity() {
        if (size == array.length) {
            int newCapacity = array.length * 2;
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}


