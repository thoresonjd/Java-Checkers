package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NetworkPlayerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isHuman_ExpectNotHuman_WhenNetworkPlayerIsCreated() {
        Player player = new NetworkPlayer();
        assertFalse(player.isHuman());
    }

    @Test
    void updateGame_ExerciseEmptyUpdateGame() {
        Player player = new NetworkPlayer();
        player.updateGame(new Game());
    }
}