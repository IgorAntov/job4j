package ru.job4j.bomberman;

import org.junit.Test;

/** Test
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class BomberManTest {

    @Test
    public void testBoardGame() throws InterruptedException {
        Board board = new Board(4,2,10);
        board.start();
    }
}
