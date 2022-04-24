package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanPlayerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isHuman_ExpectHuman_WhenHumanPlayerIsCreated() {
        Player player = new HumanPlayer();
        assertTrue(player.isHuman());
    }

    @Test
    void updateGame_ExerciseEmptyUpdateGame() {
        Player player = new HumanPlayer();
        player.updateGame(new Game());
    }
}