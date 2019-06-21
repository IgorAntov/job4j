package ru.job4j.tree;

import java.util.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;
    Tree(E value) {
        this.root = new Node<>(value);
    }

    /**
     * Method add Child-Node into the tree
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result;
        Optional<Node<E>> e;
        e = findBy(parent);
        if (e.isPresent()) {
            e.get().add(new Node<>(child));
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Method finds Node in the Tree
     * @param value
     * @return
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Method defines is Tree Binary or not
     * if Childes in all parent  <= 2 then tree is binary
     * @return
     */

    public boolean isBinary() {

        Iterator<Node<E>> it = this.iterator();
        boolean result;

        result =  it.hasNext();

        while (it.hasNext()) {
            List<Node<E>> lp = it.next().leaves();
            if (lp.size() > 2) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public Iterator<Node<E>> iterator() {
        return new Iterator<Node<E>>() {

            Queue<Node<E>> data = new LinkedList<>();
            boolean run = false;

            @Override
            public boolean hasNext() {
                boolean result;
                if (!run && root.leaves() != null) {
                    data.offer(root);
                    result = true;
                    run = true;
                } else {
                    if (!data.isEmpty()) {
                        result = true;
                    } else {
                        result = false;
                    }
                }
                return result;
            }

            @Override
            public Node<E> next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> el = data.poll();
                for (Node<E> child : el.leaves()) {
                    data.offer(child);
                }
                return el;
            }
        };
    }
}
