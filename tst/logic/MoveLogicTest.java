package logic;

import model.Board;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

import model.Game;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

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
    void isValidMove_pass_startIndexEqualToEndIndexExpectFalse(){
        assertFalse(MoveLogic.isValidMove(new Board(), true, 0, 0, 0));
    }

    @Test
    void isValidMove_pass_validSkipIndexAndSkipIndexNotEqualToStartIndexExpectFalse(){
        assertFalse(MoveLogic.isValidMove(new Board(), true, 2, 0, 3));
    }
    /* -------------------------------------------------*/


    /* ---------------------------------------------------
    |  validateDistance() Tests
    |  -------------------------------------------------*/
    @Test
    void validateDistance_pass_dxDoesNotEqualdyExpectFalse(){

    }

    @Test
    void validateDistance_pass_dxIsGreaterThan2ExpectFalse(){

    }

    @Test
    void validateDistance_pass_dxIs0ExpectFalse(){

    }
    /* -------------------------------------------------*/

    /* ---------------------------------------------------
    |  isSafe() Tests
    |  -------------------------------------------------*/
    //TODO: Use parameterized tests to test different combos.
    @ParameterizedTest
    @MethodSource("nullBoardAndOrNullCheckerStream")
    void isSafe_pass_checkBoardNullAndOrCheckerNullExpectTrue(Board b, Point p){
        assertTrue(MoveLogic.isSafe(b, p));
    }

    static Stream<Object> nullBoardAndOrNullCheckerStream(){
        Object b = new Board();
        Object p = new Point(0,0);
        return Stream.of(
                arguments(null, p),
                arguments(b, null),
                arguments(null, null),
                arguments(b, p)
        );

    }

    @Test
    void isSafe_pass_boardNullCheckerNotNullExpectTrue(){
        assertTrue(MoveLogic.isSafe(null, new Point(0, 0)));
    }

    @Test
    void isSafe_pass_boardNotNullCheckerNullExpectTrue(){
        assertTrue(MoveLogic.isSafe(new Board(), null));
    }

    @Test
    void isSafe_pass_boardNullCheckerNullExpectTrue(){
        assertTrue(MoveLogic.isSafe(null, null));
    }

    @Test
    void isSafe_pass_boardNotNullCheckerNotNullExpectTrue(){
        assertTrue(MoveLogic.isSafe(new Board(), new Point(0, 0)));
    }

    //TODO: isSafe_pass_boardIDIsEmptyExpectTrue Incomplete
    @Test
    void isSafe_pass_boardIDIsEmptyExpectTrue(){
        Board b = new Board();

        //int id = b.get(Board.toIndex(p));
        assertTrue(MoveLogic.isSafe(b, new Point(1,0)));
    }

    //TODO: isSafe_pass_moveGeneratorIsValidSkipExpectFalse Incomplete
    @Test
    void isSafe_pass_moveGeneratorIsValidSkipExpectFalse(){
        Board b = new Board();
        Point p = new Point(0,0);

        //assertTrue(MoveLogic.isSafe(b, p));
    }
    /* -------------------------------------------------*/
}