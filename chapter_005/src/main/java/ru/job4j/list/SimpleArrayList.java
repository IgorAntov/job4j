package ru.job4j.list;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleArrayList<E> {

    private int size;
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }


    /**
     * Реализовать метод удаления первого элемент в списке.
     */
    public E delete() {
        Node<E> returnNode = null;
        Node<E> result = this.first;
        if (first != null) {
            for (int i = 0; i < size - 2; i++) {
                result = result.next;
            }
            returnNode = first;
            this.first = result.next.next;
            size--;
        }
        return returnNode.date;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }

}
