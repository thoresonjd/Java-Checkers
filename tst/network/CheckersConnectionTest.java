package network;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;

class CheckersConnectionTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /*** actionPerformed ***/

    @Test
    void actionPerformed_ExerciseThisMethodBecauseItIsEmpty_WhenTriggered() {
        CheckersConnection checkersConnection = new CheckersConnection();
        checkersConnection.actionPerformed(new ActionEvent(0, 0, ""));
    }

}