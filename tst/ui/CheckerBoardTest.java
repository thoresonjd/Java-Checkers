package ui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ComputerPlayer;
import model.NetworkPlayer;
import model.Player;
import model.Game;

class CheckerBoardTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
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
}