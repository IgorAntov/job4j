package ru.job4j.list.cycle;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class IsCycleTest {

   @Test
   public void isCycleTest() {

       IsCycle iscycle = new IsCycle();

       Node<Integer> first = new Node<>(1);
       Node<Integer> two = new Node<>(2);
       Node<Integer> third = new Node<>(3);
       Node<Integer> four = new Node<>(4);

       first.next = two;
       two.next = third;
       third.next = four;
       four.next = first;

       assertThat(iscycle.hasCycle(first), is(true));

       four.next = null;

       assertThat(iscycle.hasCycle(first), is(false));
       }

}
