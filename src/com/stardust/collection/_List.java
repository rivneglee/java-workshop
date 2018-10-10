package com.stardust.collection;

import java.util.*;

public class _List<E> implements List<E> {

    private E[] elements;

    private int size = 0;

    private final static int DEFAULT_CAPACITY = 2;

    public static void main(String[] args) {
        List<String> list = new _List();
        list.add("Hello");
        list.add("World");
        list.add("My");
        list.add("Son");
        list.add("Hello");
        list.add("World");
        list.add("My");
        list.add("Son");
        list.add("Hello");
        list.add("World");
        list.add("My");
        list.add("Son");
        list.add("Hello");
        list.add("World");
        list.add("My");
        list.add("Son");
        for (String text : list) {
            System.out.println(text);
        }
    }

    public _List() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, size * 2);
        }
        elements[++size - 1] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        return elements[index] = element;
    }

    @Override
    public void add(int index, E element) {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, size * 2);
        }
        size++;
        elements[index] = element;
    }

    @Override
    public E remove(int index) {
        E e = elements[index];
        for (int i = index; i < size; i++) {
            if (i + 1 < size) {
                elements[i] = elements[i + 1];
            }
        }
        return e;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return size - currentIndex > 0;
            }

            @Override
            public E next() {
                return hasNext() ? elements[currentIndex++] : null;
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex > 0;
            }

            @Override
            public E previous() {
                return hasPrevious() ? elements[currentIndex--] : null;
            }

            @Override
            public int nextIndex() {
                return hasNext() ? currentIndex + 1 : -1;
            }

            @Override
            public int previousIndex() {
                return hasPrevious() ? currentIndex - 1 : -1;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(E e) {

            }

            @Override
            public void add(E e) {

            }
        };
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
