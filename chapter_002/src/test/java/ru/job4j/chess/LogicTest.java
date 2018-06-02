package ru.job4j.chess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.*;
import ru.job4j.chess.figures.white.*;

import java.security.PublicKey;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */

public class LogicTest {

    /**
     * Test BishopBlack Logic.
     * Way choosing.
     */

    private final Logic logic = new Logic();

    @Before
    public void chessInit() {
        this.buildBlackTeam();
        this.buildWhiteTeam();
    }

    @After
    public void donetest() {
        System.out.println("test is done");

    }

    private void buildBlackTeam() {
        logic.add(new PawnBlack(Cell.A7));
        logic.add(new PawnBlack(Cell.B7));
        logic.add(new PawnBlack(Cell.C7));
        logic.add(new PawnBlack(Cell.D7));
        logic.add(new PawnBlack(Cell.E7));
        logic.add(new PawnBlack(Cell.F7));
        logic.add(new PawnBlack(Cell.G7));
        logic.add(new PawnBlack(Cell.H7));
        logic.add(new RookBlack(Cell.A8));
        logic.add(new KnightBlack(Cell.B8));
        logic.add(new BishopBlack(Cell.C8));
        logic.add(new QeenBlack(Cell.D8));
        logic.add(new KingBlack(Cell.E8));
        logic.add(new BishopBlack(Cell.F8));
        logic.add(new KnightBlack(Cell.G8));
        logic.add(new RookBlack(Cell.H8));
    }

    public void buildWhiteTeam() {
        logic.add(new PawnWhite(Cell.A2));
        logic.add(new PawnWhite(Cell.B2));
        logic.add(new PawnWhite(Cell.C2));
        logic.add(new PawnWhite(Cell.D2));
        logic.add(new PawnWhite(Cell.E2));
        logic.add(new PawnWhite(Cell.F2));
        logic.add(new PawnWhite(Cell.G2));
        logic.add(new PawnWhite(Cell.H2));
        logic.add(new RookWhite(Cell.A1));
        logic.add(new KnightWhite(Cell.B1));
        logic.add(new BishopWhite(Cell.C1));
        logic.add(new QeenWhite(Cell.D1));
        logic.add(new KingWhite(Cell.E1));
        logic.add(new BishopWhite(Cell.F1));
        logic.add(new KnightWhite(Cell.G1));
        logic.add(new RookWhite(Cell.H1));
    }

    @Test
    public void bishopBlackWhenWrongWayWasChoosedF8F6() {
        logic.move(Cell.F7, Cell.F6);
        assertThat(logic.move(Cell.F8, Cell.F7), is(false));
    }

    @Test
    public void whenWrongWayWasChoosedF8G6() {
        assertThat(logic.move(Cell.F8, Cell.G5), is(false));
    }

    @Test
    public void whenWrongWayWasChoosedF8H6() {
        assertThat(logic.move(Cell.F8, Cell.H6), is(false));
    }

    @Test
    public void whenRightWayWasChoosedF8H6() {
        logic.move(Cell.G7, Cell.G6);
        assertThat(logic.move(Cell.F8, Cell.H6), is(true));
    }


}
