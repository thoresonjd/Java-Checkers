package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ComputerPlayerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /*** Update Game ***/

    @Test
    void updateGame_DoesNothing_WhenGameIsNull() {
        Player player = new ComputerPlayer();
        player.updateGame(null);
    }

    @Test
    void updateGame_ExpectBoardChange_WhenComputerMakesMove() {
        Game game = new Game();
        Player player = new ComputerPlayer();
        String initialBoard = game.getBoard().toString();

        // Computer makes move
        player.updateGame(game);

        String boardAfterMove = game.getBoard().toString();
        assertFalse(initialBoard.equals(boardAfterMove));
    }

    @Test
    void updateGame_ExpectNoBoardChange_WhenSkipIndexIsZero() {
        Game game = new Game(null, true, 0);
        Player player = new ComputerPlayer();
        String initialBoard = game.getBoard().toString();

        // Computer makes move
        player.updateGame(game);

        String boardAfterMove = game.getBoard().toString();
        assertTrue(initialBoard.equals(boardAfterMove));
    }

    @Test
    void updateGame_ExpectNoBoardChange_WhenPlayerOneTurnIsFalse() {
        Game game = new Game();
        Player player = new ComputerPlayer();
        String initialBoard = game.getBoard().toString();

        // Set player one turn to false;
        game.setP1Turn(false);
        assertFalse(game.isP1Turn());

        // Computer makes move
        player.updateGame(game);

        String boardAfterMove = game.getBoard().toString();
        assertFalse(initialBoard.equals(boardAfterMove));
    }
}