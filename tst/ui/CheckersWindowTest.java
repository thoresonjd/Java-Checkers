package ui;

import model.ComputerPlayer;
import model.HumanPlayer;
import model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class CheckersWindowTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /*** Window Creation ***/

    @Test
    void getBoard_ExpectBoardNotNull_WhenWindowIsCreated() {
        CheckersWindow window = new CheckersWindow();
        assertTrue(window.getBoard() != null);
    }

    @Test
    void getPlayers_ExpectPlayersAsProvided_WhenWindowIsCreatedWithPlayers() {
        Player p1 = new HumanPlayer();
        Player p2 = new ComputerPlayer();
        CheckersWindow window = new CheckersWindow(p1, p2);
        assertTrue(window.getBoard().getPlayer1().toString().equals("HumanPlayer[isHuman=true]"));
        assertTrue(window.getBoard().getPlayer2().toString().equals("ComputerPlayer[isHuman=false]"));
    }

    /*** Restart ***/

    @Test
    void restart() {
        Player p1 = new ComputerPlayer();
        Player p2 = new ComputerPlayer();
        CheckersWindow window = new CheckersWindow(p1, p2);

        // Player 1 makes a move
        String initialBoard = window.getBoard().getGame().getBoard().toString();
        p1.updateGame(window.getBoard().getGame());
        String boardAfterMove = window.getBoard().getGame().getBoard().toString();
        assertFalse(initialBoard.equals(boardAfterMove));

        // Board is reset
        window.restart();
        String boardAfterRestart = window.getBoard().getGame().getBoard().toString();
        assertTrue(initialBoard.equals(boardAfterRestart));
    }

    /*** Set Game State ***/

    @ParameterizedTest
    @MethodSource
    void setGameState_ExpectGameSkipIndex_WhenStateIsSetViaCheckersWindow(String state, int expected) {
        CheckersWindow window = new CheckersWindow();
        window.setGameState(state);
        assertEquals(expected, window.getBoard().getGame().getSkipIndex());
    }


    /*** Session ***/

    @Test
    void getSession_ExpectSessionDestinationPort_WhenWindowIsActive() {
        CheckersWindow window = new CheckersWindow();
        assertEquals(window.getSession1().getDestinationPort(), -1);
        assertEquals(window.getSession2().getDestinationPort(), -1);
    }

    /*** Methods for providing arguments to parameterized tests ***/

    private static Stream<Arguments> setGameState_ExpectGameSkipIndex_WhenStateIsSetViaCheckersWindow() {
        return Stream.of(
            Arguments.of("0000000000000000000000000000000000", 0),
            Arguments.of("0000000000000000000000000000000001", 1),
            Arguments.of("0000000000000000000000000000000005", 5),
            Arguments.of("0000000000000000000000000000000009", 9),
            Arguments.of("000000000000000000000000000000000-1", -1),
            Arguments.of("0000000000000000000000000000000001000", 1000)
        );
    }
}