package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @MethodSource
    void toString_AssertToStringForIsHumanPlayerStatus(Player player, String expected) {
        assertEquals(expected, player.toString());
    }

    /*** Methods for providing arguments to parameterized tests ***/

    private static Stream<Arguments> toString_AssertToStringForIsHumanPlayerStatus() {
        return Stream.of(
            Arguments.of(new HumanPlayer(), "HumanPlayer[isHuman=true]"),
            Arguments.of(new NetworkPlayer(), "NetworkPlayer[isHuman=false]"),
            Arguments.of(new ComputerPlayer(), "ComputerPlayer[isHuman=false]")
        );
    }
}