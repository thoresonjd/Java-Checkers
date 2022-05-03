package ui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ComputerPlayer;
import model.NetworkPlayer;
import model.Player;
import model.Game;

import java.awt.*;

class CheckerBoardTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /*** setGameState ***/

    @Test
    void setGameState_ExpectSuccess_WhenBoardIsFlipped() {
        Player player1 = new NetworkPlayer();
        Player player2 = new NetworkPlayer();
        CheckersWindow window = new CheckersWindow();
        Game game = new Game();
        CheckerBoard board = new CheckerBoard(window, game, player1, player2);

        // Black checker = 6, empty tile = 0, white checker = 4
        String initialState = "666666666666000000004444444444441-1";
        String newState = "444444444444000000006666666666661-1";
        boolean success = board.setGameState(false, newState, initialState);

        assertTrue(success);
        assertEquals(newState, game.getGameState());
    }

    @Test
    void setGameState_ExpectFailure_WhenInitialStateIsNotExpectedState() {
        Player player1 = new NetworkPlayer();
        Player player2 = new NetworkPlayer();
        CheckersWindow window = new CheckersWindow();
        Game game = new Game();
        CheckerBoard board = new CheckerBoard(window, game, player1, player2);

        // Black checker = 6, empty tile = 0, white checker = 4
        String initialState = "444444444444000000006666666666661-1";
        String newState = "666666666666000000004444444444441-1";
        assertFalse(board.setGameState(true, newState, initialState));
    }

    /*** setLightTile ***/

    @Test
    void setLightTile_ExpectWhite_WhenLightTileIsNull() {
        // Set up
        Player player1 = new NetworkPlayer();
        Player player2 = new NetworkPlayer();
        CheckersWindow window = new CheckersWindow();
        Game game = new Game();
        CheckerBoard board = new CheckerBoard(window, game, player1, player2);

        // Set light tile
        board.setLightTile(null);
        Color expected = Color.WHITE;
        Color actual = board.getLightTile();
        assertEquals(expected, actual);
    }

    /*** update ***/

    @Test
    void update_Exercise_WhenPlayersAreNetworkPlayers() {
        Player player1 = new NetworkPlayer();
        Player player2 = new NetworkPlayer();
        CheckersWindow window = new CheckersWindow();
        Game game = new Game();
        CheckerBoard board = new CheckerBoard(window, game, player1, player2);
        board.update();
    }

    @Test
    void update_Exercise_WhenPlayersAreComputerPlayers() {
        Player player1 = new ComputerPlayer();
        Player player2 = new ComputerPlayer();
        CheckersWindow window = new CheckersWindow();
        Game game = new Game();
        CheckerBoard board = new CheckerBoard(window, game, player1, player2);
        board.update();
    }

    /*** updateGame ***/

    @Test
    void updateNetwork_Exercise_SessionsDoNotConnect() {
        Player player1 = new NetworkPlayer();
        Player player2 = new NetworkPlayer();
        CheckersWindow window = new CheckersWindow();
        Game game = new Game();
        CheckerBoard board = new CheckerBoard(window, game, player1, player2);
        board.updateNetwork();
    }
}