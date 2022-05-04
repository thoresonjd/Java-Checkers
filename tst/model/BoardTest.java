package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.Point;
import java.util.List;
import java.util.stream.Stream;

class BoardTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /*** Copy ***/

    @Test
    void copy_BoardsShouldNotBeEqual_WhenBoardIsCopied() {
        Board board = new Board();
        Board copy = board.copy();
        assertNotEquals(board, copy);
    }

    /*** Find ***/

    @Test
    void find_FindAllTilesWithBlackCheckers_WhenNoMovesHaveBeenMade() {
        Board board = new Board();
        List<Point> points = board.find(Board.BLACK_CHECKER);

        List<Point> expected = List.of(
            new Point(1, 0), new Point(3, 0), new Point(5, 0), new Point(7, 0),
            new Point(0, 1), new Point(2, 1), new Point(4, 1), new Point(6, 1),
            new Point(1, 2), new Point(3, 2), new Point(5, 2), new Point(7, 2)
        );

        for (int i = 0; i < points.size(); i++) {
            assertEquals(points.get(i).x, expected.get(i).x);
            assertEquals(points.get(i).y, expected.get(i).y);
        }
    }

    @Test
    void find_FindAllTilesWithWhiteCheckers_WhenNoMovesHaveBeenMade() {
        Board board = new Board();
        List<Point> points = board.find(Board.WHITE_CHECKER);

        List<Point> expected = List.of(
                new Point(0, 5), new Point(2, 5), new Point(4, 5), new Point(6, 5),
                new Point(1, 6), new Point(3, 6), new Point(5, 6), new Point(7, 6),
                new Point(0, 7), new Point(2, 7), new Point(4, 7), new Point(6, 7)
        );

        for (int i = 0; i < points.size(); i++) {
            assertEquals(points.get(i).x, expected.get(i).x);
            assertEquals(points.get(i).y, expected.get(i).y);
        }
    }

    /*** To Index ***/

    /**
     * Test if point is valid (black tile) or invalid (white tile or not on board).
     * A valid point converts to an index via y * 4 + x / 2.
     * Invalid points return -1.
     */
    @ParameterizedTest
    @CsvSource({
        "-100, -100, -1", // Really low, off board
        "0, 0, -1",       // First white tile, below lower boundary
        "1, 0, 0",        // First black tile, on lower boundary
        "3, 3, -1",       // Nominal white tile, within boundary
        "3, 4, 17",       // Nominal black tile, within boundary
        "6, 7, 31",       // Last black tile, on upper boundary
        "7, 7, -1",       // Last white tile, above upper boundary
        "100, 100, -1"    // Really high, off board
    })
    void toIndex_PointToIndexConversion(int x, int y, int expected) {
        assertEquals(expected, Board.toIndex(new Point(x, y)));
    }

    /*** Set ***/

    @Test
    void set_SetTileAsBlackKingWithCoordinates_ExpectTileToBeBlackKing() {
        Board board = new Board();

        // Set board with kings
        board.set(1, 0, Board.BLACK_KING);
        board.set(3, 0, Board.BLACK_KING);
        board.set(5, 0, Board.BLACK_KING);
        board.set(7, 0, Board.BLACK_KING);

        // Assert black kings
        List<Point> bKings = board.find(Board.BLACK_KING);
        List<Point> expectedBlack = List.of(
                new Point(1, 0), new Point(3, 0), new Point(5, 0), new Point(7, 0)
        );
        for (int i = 0; i < bKings.size(); i++) {
            assertEquals(bKings.get(i).x, expectedBlack.get(i).x);
            assertEquals(bKings.get(i).y, expectedBlack.get(i).y);
        }
    }

    @Test
    void set_SetTilesEmptyWithIndex_ExpectAllTilesEmpty() {
        Board board = new Board();

        // Set board entirely empty
        for (int i = 0; i < 32; i ++) {
            board.set(i, Board.EMPTY);
        }

        // Assert all tiles empty
        List<Point> empty = board.find(Board.EMPTY);
        List<Point> expectedEmpty = List.of(
                new Point(1, 0), new Point(3, 0), new Point(5, 0), new Point(7, 0),
                new Point(0, 1), new Point(2, 1), new Point(4, 1), new Point(6, 1),
                new Point(1, 2), new Point(3, 2), new Point(5, 2), new Point(7, 2),
                new Point(0, 3), new Point(2, 3), new Point(4, 3), new Point(6, 3),
                new Point(1, 4), new Point(3, 4), new Point(5, 4), new Point(7, 4),
                new Point(0, 5), new Point(2, 5), new Point(4, 5), new Point(6, 5),
                new Point(1, 6), new Point(3, 6), new Point(5, 6), new Point(7, 6),
                new Point(0, 7), new Point(2, 7), new Point(4, 7), new Point(6, 7)
        );
        for (int i = 0; i < empty.size(); i++) {
            assertEquals(expectedEmpty.get(i).x, empty.get(i).x);
            assertEquals(expectedEmpty.get(i).y, empty.get(i).y);
        }
    }

    @Test
    void set_SetBoardWithKingsWithIndex_ExpectBlackAndWhiteKings() {
        Board board = new Board();

        // Set board with kings
        for (int i = 0; i < 12; i ++) {
            board.set(i, Board.BLACK_KING);
            board.set(31 - i, Board.WHITE_KING);
        }

        // Assert white kings
        List<Point> wKings = board.find(Board.WHITE_KING);
        List<Point> expectedWhite = List.of(
                new Point(0, 5), new Point(2, 5), new Point(4, 5), new Point(6, 5),
                new Point(1, 6), new Point(3, 6), new Point(5, 6), new Point(7, 6),
                new Point(0, 7), new Point(2, 7), new Point(4, 7), new Point(6, 7)
        );
        for (int i = 0; i < wKings.size(); i++) {
            assertEquals(wKings.get(i).x, expectedWhite.get(i).x);
            assertEquals(wKings.get(i).y, expectedWhite.get(i).y);
        }

        // Assert black kings
        List<Point> bKings = board.find(Board.BLACK_KING);
        List<Point> expectedBlack = List.of(
                new Point(1, 0), new Point(3, 0), new Point(5, 0), new Point(7, 0),
                new Point(0, 1), new Point(2, 1), new Point(4, 1), new Point(6, 1),
                new Point(1, 2), new Point(3, 2), new Point(5, 2), new Point(7, 2)
        );
        for (int i = 0; i < bKings.size(); i++) {
            assertEquals(bKings.get(i).x, expectedBlack.get(i).x);
            assertEquals(bKings.get(i).y, expectedBlack.get(i).y);
        }
    }

    @Test
    void set_SetBoardInReverseWithIndex_ExpectBlackAndWhiteToBeFlipped() {
        Board board = new Board();

        // Set board in reverse
        for (int i = 0; i < 12; i ++) {
            board.set(i, Board.WHITE_CHECKER);
            board.set(31 - i, Board.BLACK_CHECKER);
        }

        // Assert black checkers on bottom
        List<Point> black = board.find(Board.BLACK_CHECKER);
        List<Point> expectedBlack = List.of(
                new Point(0, 5), new Point(2, 5), new Point(4, 5), new Point(6, 5),
                new Point(1, 6), new Point(3, 6), new Point(5, 6), new Point(7, 6),
                new Point(0, 7), new Point(2, 7), new Point(4, 7), new Point(6, 7)
        );
        for (int i = 0; i < black.size(); i++) {
            assertEquals(black.get(i).x, expectedBlack.get(i).x);
            assertEquals(black.get(i).y, expectedBlack.get(i).y);
        }

        // Assert white checkers on top
        List<Point> white = board.find(Board.WHITE_CHECKER);
        List<Point> expectedWhite = List.of(
                new Point(1, 0), new Point(3, 0), new Point(5, 0), new Point(7, 0),
                new Point(0, 1), new Point(2, 1), new Point(4, 1), new Point(6, 1),
                new Point(1, 2), new Point(3, 2), new Point(5, 2), new Point(7, 2)
        );
        for (int i = 0; i < white.size(); i++) {
            assertEquals(white.get(i).x, expectedWhite.get(i).x);
            assertEquals(white.get(i).y, expectedWhite.get(i).y);
        }
    }

    @Test
    void set_ExerciseSetWithInvalidCheckersWithIndex_ExerciseEmptyId() {
        Board board = new Board();
        board.set(0, -1);
    }

    /*** Set Bit ***/

    @ParameterizedTest
    @CsvSource({
            "1, -1, 1",
            "2, 32, 2",
            "3, -100, 3",
            "4, 100, 4"
    })
    void setBit_SetInvalidBit_ExpectTarget(int target, int bit, int expected) {
        assertEquals(expected, Board.setBit(target, bit, true));
    }

    /*** Get ***/

    @ParameterizedTest
    @CsvSource({
        "1, 0, 6",   // Black checker
        "2, 1, 6",   // Black checker
        "0, 5, 4",   // White checker
        "3, 6, 4",   // White checker
        "0, 0, -1",  // Invalid checker
        "10, 10, -1" // Invalid checker
    })
    void get_GetTheIdOfAPoint(int x, int y, int expected) {
        Board board = new Board();
        assertEquals(expected, board.get(x, y));
    }

    /*** Get Bit ***/

    @ParameterizedTest
    @CsvSource({
        "1, -1, 0",
        "2, 32, 0",
        "3, -100, 0",
        "4, 100, 0"
    })
    void getBit_GetInvalidBit_ExpectZero(int target, int bit, int expected) {
        assertEquals(expected, Board.getBit(target, bit));
    }

    /*** Middle ***/

    @ParameterizedTest
    @CsvSource({
        "0, 9, 2, 1",     // Valid black tiles
        "23, 30, 5, 6",   // Valid black tiles
        "0, 31, -1, -1",  // Black tiles too far apart
        "-1, -1, -1, -1", // Non-existent tiles
        "0, 1, -1, -1",   // White tile, invalid
        "31, 32, -1, -1"  // White tile, invalid
    })
    void middle_GetMiddleOfTwoPoints(int index1, int index2, int x, int y) {
        Point point = Board.middle(index1, index2);
        assertEquals(x, point.x);
        assertEquals(y, point.y);
    }

    @Test
    void middle_PointIsNull_ExpectInvalidPoint() {
        Point point = Board.middle(null, null);
        assertEquals(-1, point.x);
        assertEquals(-1, point.y);
    }

    @Test
    void middle_PointIsWhiteTile_ExpectInvalidPoint() {
        Point point = Board.middle(0, 0, 4, 4);
        assertEquals(-1, point.x);
        assertEquals(-1, point.y);
    }

    /*** To String ***/

    @Test
    void toString_AssertToStringAfterBoardIsCreated_ExpectDefaultString() {
        Board board = new Board();
        String expected = "model.Board"
            + "[6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, "
            + "0, 0, 0, 0, 0, 0, 0, 0, "
            + "4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4]";
        assertEquals(expected, board.toString());
    }

    @Test
    void toString_AssertToStringAfterBoardSetWithKings_ExpectKingIDs() {
        Board board = new Board();

        // Set board with kings
        for (int i = 0; i < 12; i ++) {
            board.set(i, Board.BLACK_KING);
            board.set(31 - i, Board.WHITE_KING);
        }


        String expected = "model.Board"
                + "[7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, "
                + "0, 0, 0, 0, 0, 0, 0, 0, "
                + "5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5]";
        assertEquals(expected, board.toString());
    }

    /*** Is Valid Point ***/

    @ParameterizedTest
    @MethodSource
    void isValidPoint_ExerciseValidPoints_ExpectFalseIfNullOrWhiteTileOrOffBoard(Point point, boolean expected) {
        assertEquals(expected, Board.isValidPoint(point));
    }

    /*** Methods for providing arguments to parameterized tests ***/

    private static Stream<Arguments> isValidPoint_ExerciseValidPoints_ExpectFalseIfNullOrWhiteTileOrOffBoard() {
        return Stream.of(
            Arguments.of(null, false),                    // Null point, invalid
            Arguments.of(new Point(0, 0), false),   // White tile, invalid
            Arguments.of(new Point(4, 4), false),   // White tile, invalid
            Arguments.of(new Point(-1, -1), false), // Under lower boundary, invalid
            Arguments.of(new Point(1, 0), true),    // On lower boundary, valid
            Arguments.of(new Point(3, 4), true),    // Nominal point, valid
            Arguments.of(new Point(6, 7), true),    // On upper boundary, valid
            Arguments.of(new Point(8, 8), false)    // Above upper boundary, invalid
        );
    }
}