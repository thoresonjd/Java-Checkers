package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void copy_shouldReturnCopy() {
        Game game = new Game();
        Game gameCopy = game.copy();

        assertNotEquals(game, gameCopy);
    }

    @Test
    void restart_shouldResetGameState() {
        Game game = new Game(new Board(), true, -1);
        String initialState = game.getGameState();
        game.setGameState("6666666666660000000044444444444410");

        game.restart();

        assertEquals(initialState, game.getGameState());
    }

    @ParameterizedTest
    @MethodSource
    void move_expectFalse_whenStartOrEndIsNull(Point start, Point end) {
        Game game = new Game();
        assertFalse(game.move(start, end));
    }

    @Test
    void move_expectFalse_whenBoardIsNull() {
        Game game = new Game(null, true, -1);
        assertFalse(game.move(new Point(), new Point()));
    }

    @Test
    void move_expectTrue_whenMoveToOpenSquare() {
        Game game = new Game();
        boolean result = game.move(new Point(1, 2), new Point(2, 3));
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({
            "4, 0, false", // White checker
            "26, 31, true" // Black checker
    })
    void move_expectCheckerToBecomeKing_whenMovedToEmptySquareOnOppositeSideOfBoard(
            int startIndex, int endIndex, boolean isBlackTurn) {
        Board board = new Board();

        // Place the checker in position where it can move to become king
        board.set(startIndex, isBlackTurn ? Board.BLACK_CHECKER : Board.WHITE_CHECKER);
        board.set(endIndex, Board.EMPTY);
        Game game = new Game(board, isBlackTurn, -1);

        // Move it to the edge of the board
        game.move(startIndex, endIndex);

        // Assert that it became a king
        int king = isBlackTurn ? Board.BLACK_KING : Board.WHITE_KING;
        assertEquals(king, game.getBoard().get(endIndex));
    }

    @ParameterizedTest
    @MethodSource
    void move_expectSkipIndexSetToEndIndex_whenCheckerIsCapturedAndAnotherCouldBeCaptured(
            int startIndex,
            int midIndex,
            int endIndex,
            int possibleSkipIndex,
            boolean isBlackTurn
    ) {
        Board board = new Board();

        // Prepare opposite color checker to be captured at midIndex
        board.set(midIndex, isBlackTurn ? Board.WHITE_CHECKER : Board.BLACK_CHECKER);
        // Open up landing square
        board.set(endIndex, Board.EMPTY);
        // Prepare another opposite color checker that could be captured
        board.set(possibleSkipIndex, isBlackTurn ? Board.WHITE_CHECKER : Board.BLACK_CHECKER);

        Game game = new Game(board, isBlackTurn, -1);

        // Capture
        game.move(startIndex, endIndex);

        assertEquals(endIndex, game.getSkipIndex());
    }

    @Test
    void getBoard_shouldReturnCopyOfBoard() {
        Board originalBoard = new Board();
        Game game = new Game(originalBoard, true, -1);
        assertNotEquals(originalBoard, game.getBoard());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 11",
            "20, 31",
    })
    void isGameOver_expectTrue_whenAllCheckersOfOneColorAreRemoved(int fromIndex, int toIndex) {
        Board board = new Board();
        for (int index = fromIndex; index <= toIndex; index++) {
            board.set(index, Board.EMPTY);
        }

        Game game = new Game(board, true, -1);

        assertTrue(game.isGameOver());
    }

    @Test
    void isGameOver_expectTrue_whenBoardIsEmpty() {
        Game game = new Game("00000000000000000000000000000000");
        assertTrue(game.isGameOver());
    }

    @Test
    void isGameOver_expectFalse_whenGameBegins() {
        Game game = new Game();
        assertFalse(game.isGameOver());
    }

    @ParameterizedTest
    @CsvSource({
            "444400000006000000000000000006666-1, true", // Black checker in the middle of the board
            "444400000000000000400000000006666-1, false", // White checker in the middle of the board
            "000000000000000040000000000600000-1, true", // Black checker one square away from edge
            "000040000000000060000000000000000-1, false", // White checker one square away from edge
    })
    void isGameOver_expectFalse_whenAtLeastOneOfTheRemainingCheckersHasOpenSquareToMoveTo(
            String state, boolean isP1Turn) {
        Game game = new Game(state);
        game.setP1Turn(isP1Turn);
        assertFalse(game.isGameOver());
    }

    @ParameterizedTest
    @CsvSource({
            "000000000000000000000000000000061-1, true", // Black checkers reached edge of board
            "000000000000000000000000000000661-1, true",
            "000000000000000000000000000006661-1, true",
            "000000000000000000000060606060601-1, true",

            "400000000000000000000000000000000-1, false", // White checkers reached edge of board
            "440000000000000000000000000000000-1, false",
            "444000000000000000000000000000000-1, false",
            "404040404040000000000000000000000-1, false",

            "444400000000000000000000000006666-1, true", // Black and white checkers both still on the
            "444400000000000000000000000006666-1, false" // board, both reached edge of board
    })
    void isGameOver_expectTrue_whenRemainingCheckersHaveNoOpenSquareToMoveTo(String state, boolean isP1Turn) {
        Game game = new Game(state);
        game.setP1Turn(isP1Turn);
        assertTrue(game.isGameOver());
    }

    @Test
    void isP1Turn_expectTrue_whenP1TurnSetToTrue() {
        Game game = new Game();
        game.setP1Turn(true);
        assertTrue(game.isP1Turn());
    }

    @Test
    void isP1Turn_expectFalse_whenP1TurnSetToTrue() {
        Game game = new Game();
        game.setP1Turn(false);
        assertFalse(game.isP1Turn());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "000000000000000000000000000000001",    // On boundary:         exactly 33 characters
            "0000000000000000000000000000000010",   // Just above boundary: 34 characters
            "0000000000000000000000000000000010000" // Above boundary:      37 characters
    })
    void setGameState_shouldSetP1TurnToTrue_whenStateLengthIsGreaterThan32AndChar32Is1(String state) {
        Game game = new Game(new Board(), true, -1);
        game.setGameState(state);
        System.out.println(game.getGameState());

        assertTrue(game.isP1Turn());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "000000000000000000000000000000000",
            "0000000000000000000000000000000000",
            "0000000000000000000000000000000000000"
    })
    void setGameState_shouldSetP1TurnToFalse_whenStateLengthIsGreaterThan32AndChar32IsNot1(String state) {
        Game game = new Game(new Board(), true, -1);
        game.setGameState(state);
        System.out.println(game.getGameState());

        assertFalse(game.isP1Turn());
    }

    @ParameterizedTest
    @MethodSource
    void setGameState_shouldSetSkipToIntegerStartingAtIndex33_whenIndex33IsAnInteger(String state, int expectedResult) {
        Game game = new Game(new Board(), true, -1);
        game.setGameState(state);

        assertEquals(expectedResult, game.getSkipIndex());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "000000000000000000000000000000000x",
            "000000000000000000000000000000000x",
            "000000000000000000000000000000000x000"
    })
    void setGameState_shouldSetSkipIndexToNegativeOne_whenIndex33IsNotAnInteger(String state) {
        Game game = new Game(new Board(), true, -1);
        game.setGameState(state);

        assertEquals(-1, game.getSkipIndex());
    }

    /*** Methods for providing arguments to parameterized tests ***/

    private static Stream<Arguments> setGameState_shouldSetSkipToIntegerStartingAtIndex33_whenIndex33IsAnInteger() {
        return Stream.of(
                Arguments.of("0000000000000000000000000000000000", 0),
                Arguments.of("0000000000000000000000000000000001", 1),
                Arguments.of("0000000000000000000000000000000005", 5),
                Arguments.of("0000000000000000000000000000000009", 9),
                Arguments.of("000000000000000000000000000000000-1", -1),
                Arguments.of("0000000000000000000000000000000001000", 1000)
        );
    }

    private static Stream<Arguments> move_expectFalse_whenStartOrEndIsNull() {
        return Stream.of(
                Arguments.of(null, new Point()),
                Arguments.of(new Point(), null),
                Arguments.of(null, null)
        );
    }

    private static Stream<Arguments> move_expectSkipIndexSetToEndIndex_whenCheckerIsCapturedAndAnotherCouldBeCaptured() {
        return Stream.of(
                Arguments.of(0, 5, 9, 14, true),    // Black checker captures white
                Arguments.of(31, 26, 22, 17, false) // White checker captures black
        );
    }

    @AfterEach
    void tearDown() {
    }
}