package logic;

import model.Board;
import static org.mockito.Mockito.*;

import model.Game;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.Point;
import java.util.List;
import java.util.stream.Stream;

class MoveLogicTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /* ---------------------------------------------------
    |  isValidMove() Tests
    |  -------------------------------------------------*/
    @Test
    void isValidMove_pass_gameIsNullExpectFalse(){
        Game game = null;
        assertFalse(MoveLogic.isValidMove(null, 0, 0));
    }

    @Test
    void isValidMove_pass_gameBoardIsNullExpectFalse(){
        Board b = null;
        assertFalse(MoveLogic.isValidMove(b, true, 0, 0, 0));
    }

    @ParameterizedTest(name = "startIndex = {0}, endIndex = {1}")
    @CsvSource({
            "-1, 0", //invalid startIndex
            "0, -1" //invalid endIndex
    })
    void isValidMove_pass_invalidIndexExpectFalse(int start, int end){
        assertFalse(MoveLogic.isValidMove(new Board(), true, start, end, 0));
    }

    @Test
    void isValidMove_pass_startIndexEqualToEndIndex(){
        assertFalse(MoveLogic.isValidMove(new Board(), true, 0, 0, 0));
    }

    @Test
    void isValidMove_pass_validSkipIndexAndSkipIndexNotEqualToStartIndex(){
        assertFalse(MoveLogic.isValidMove(new Board(), true, 2, 0, 3));
    }
}