package logic;

import model.Board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.List;

class MoveGeneratorTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /*** Get Moves ***/

    @Test
    void getMoves_ExpectEmptyList_WhenBoardIsNull() {
        List<Point> moves = MoveGenerator.getMoves(null, null);
        assertTrue(moves.isEmpty());
    }

    /*** Get Skips ***/

    @Test
    void getSkips_ExpectEmptyList_WhenBoardIsNull() {
        List<Point> skips = MoveGenerator.getSkips(null, null);
        assertTrue(skips.isEmpty());
    }

    /*** Is Valid Skip ***/

    @Test
    void isValidSkip_ExpectFalse_WhenBoardIsNull() {
        boolean isValidSkip = MoveGenerator.isValidSkip(null, 0, 0);
        assertFalse(isValidSkip);
    }

    @Test
    void isValidSkip_ExpectFalse_WhenStartIndexIsInvalidAndEndIndexIsEmpty() {
        boolean isValidSkip = MoveGenerator.isValidSkip(new Board(), -1, 12);
        assertFalse(isValidSkip);
    }
}