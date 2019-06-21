package ru.job4j.quiz;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Test
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class PrefixTreeTest {
    @Test
    public void whenPutWordsFindExistOneWord() {
        PrefixTree trie = new PrefixTree();
        trie.put("холод");
        trie.put("холодильник");
        trie.put("светильник");
        assertThat(trie.find("холод"), is(true));
    }

    @Test
    public void whenPutWordsNoFindNonExistOneWord() {
        PrefixTree trie = new PrefixTree();
        trie.put("холод");
        trie.put("холодильник");
        trie.put("светильник");
        assertThat(trie.find("холоп"), is(false));
    }
}