package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;

public class UserMapTest {

    static final int hash(Object key) {
        return (key == null) ? 0 : (key.hashCode()) ^ (key.hashCode() >>> 16);
    }

    static final int getIndex(int hash, int size) {
        int n;
        return  ((size - 1) & hash);
    }

    @Test
    public void userMapTest() {

        HashMap<User, String> hashMap = new HashMap<>();

        User user1 = new User("Ivan", 2, new GregorianCalendar(1980, 01, 01));
        hashMap.put(user1, "1");
        System.out.println("hash=" + UserMapTest.hash(user1.hashCode()) + " = (bin) " +  Integer.toBinaryString(UserMapTest.hash(user1.hashCode()))  + " size = " + hashMap.size() + " index= " + getIndex(UserMapTest.hash(user1.hashCode()), 16));

        User user2 = new User("Oleg", 2, new GregorianCalendar(1985, 01, 01));
        hashMap.put(user2, "2");
        System.out.println("hash=" + UserMapTest.hash(user2.hashCode()) + " = (bin) " +  Integer.toBinaryString(UserMapTest.hash(user2.hashCode()))  + " size = " + hashMap.size() + " index= " + getIndex(UserMapTest.hash(user2.hashCode()), 16));


        User user3 = new User("Lena", 2, new GregorianCalendar(1990, 01, 01));
        hashMap.put(user3, "3");
        System.out.println("hash=" + UserMapTest.hash(user3.hashCode()) + " = (bin) " +  Integer.toBinaryString(UserMapTest.hash(user3.hashCode()))  + " size = " + hashMap.size() + " index= " + getIndex(UserMapTest.hash(user3.hashCode()), 16));

        System.out.println(hashMap);
    }
}
