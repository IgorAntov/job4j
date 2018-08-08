package ru.job4j.list.cycle;

public class Node<T> {
    T value;
    Node<T> next;

    public Node(T i) {
        this.value = i;
    }
}
