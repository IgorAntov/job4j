package ru.job4j.tree;

import java.util.Optional;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<Node<E>> {

    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);
}
