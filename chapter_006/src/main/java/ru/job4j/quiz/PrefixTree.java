package ru.job4j.quiz;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class PrefixTree {
    static class Node {
        Map<Character, Node> children = new TreeMap<Character, Node>();
        boolean leaf;
    }

    Node root = new Node();

    public void put(String s) {
        Node v = root;
        for (char ch : s.toLowerCase().toCharArray()) {
            if (!v.children.containsKey(ch)) {
                v.children.put(ch, new Node());
            }
            v = v.children.get(ch);
        }
        v.leaf = true;
    }

    public boolean find(String s) {
        Node v = root;
        for (char ch : s.toLowerCase().toCharArray()) {
            if (!v.children.containsKey(ch)) {
                return false;
            } else {
                v = v.children.get(ch);
            }
        }
        return true;
    }
}
