package ui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    /*** updateGame ***/

    @Test
    void updateGame_Exercise() {
        Player player1 = new NetworkPlayer();
        Player player2 = new NetworkPlayer();
        CheckersWindow window = new CheckersWindow();
        Game game = new Game();
        CheckerBoard board = new CheckerBoard(window, game, player1, player2);
        board.updateNetwork();
    }
}