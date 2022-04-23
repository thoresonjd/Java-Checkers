package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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
    void getBoard_shouldReturnCopyOfBoard() {
        Board originalBoard = new Board();
        Game game = new Game(originalBoard, true, -1);
        assertNotEquals(originalBoard, game.getBoard());
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

    @AfterEach
    void tearDown() {
    }
}