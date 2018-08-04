package ru.job4j.generic;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class UserStor extends AbstractStore<User> {

       public UserStor(int size) {
       super( new SimpleArray<>(size));
          }

}
