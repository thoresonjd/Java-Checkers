package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /*** Constructors ***/

    @Test
    void constructor_ExpectToGetStartAndEndIndices_WhenIndicesAreProvidedViaConstructor() {
        Move move = new Move(0, 31);
        assertEquals(0, move.getStartIndex());
        assertEquals(31, move.getEndIndex());
    }

    @Test
    void constructor_ExpectToGetStartAndEndIndices_WhenPointsProvidedViaConstructor() {
        Move move = new Move(new Point(1, 0), new Point(6,7));
        assertEquals(0, move.getStartIndex());
        assertEquals(31, move.getEndIndex());
    }

    /*** Set Indices ***/

    @Test
    void setStartIndex_ExpectNewStartIndex_WhenStartIndexChanges() {
        Move move = new Move(0, 31);
        assertEquals(0, move.getStartIndex());

        // Change start index
        move.setStartIndex(1);
        assertEquals(1, move.getStartIndex());
    }

    @Test
    void setEndIndex_ExpectNewEndIndex_WhenEndIndexChanges() {
        Move move = new Move(0, 31);
        assertEquals(31, move.getEndIndex());

        // Change start index
        move.setEndIndex(30);
        assertEquals(30, move.getEndIndex());
    }

    /*** Set and Get Starting Point ***/

    @ParameterizedTest
    @CsvSource({
        "0, -1, -1, -1", // Below lower boundary, invalid
        "1, 0, 1, 0",    // On lower boundary, valid
        "3, 4, 3, 4",    // Nominal value, valid
        "6, 7, 6, 7",    // On upper boundary, valid
        "8, 8, -1, -1",  // Above upper boundary, invalid
        "0, 0, -1, -1"   // White tile, invalid
    })
    void setStart_ExpectToGetStartingPoint_AfterSettingStartingPoint(int x, int y, int expX, int expY) {
        Move move = new Move(0, 31);
        move.setStart(new Point(x, y));
        Point start = move.getStart();
        assertEquals(expX, start.x);
        assertEquals(expY, start.y);
    }

    /*** Set and Get End Point ***/

    @ParameterizedTest
    @CsvSource({
        "0, -1, -1, -1", // Below lower boundary, invalid
        "1, 0, 1, 0",    // On lower boundary, valid
        "3, 4, 3, 4",    // Nominal value, valid
        "6, 7, 6, 7",    // On upper boundary, valid
        "8, 8, -1, -1",  // Above upper boundary, invalid
        "0, 0, -1, -1"   // White tile, invalid
    })
    void setEnd_ExpectToGetEndPoint_AfterSettingEndPoint(int x, int y, int expX, int expY) {
        Move move = new Move(0, 31);
        move.setEnd(new Point(x, y));
        Point end = move.getEnd();
        assertEquals(expX, end.x);
        assertEquals(expY, end.y);
    }

    /*** Set, Get, and Change Weight ***/

    @ParameterizedTest
    @CsvSource({"1.1", "2.2", "3.3", "4.4", "5.5", "6.6"})
    void setWeight_ExpectToGetWeight_AfterSettingWeight(double weight) {
        Move move = new Move(0, 31);
        move.setWeight(weight);
        assertEquals(weight, move.getWeight());
    }

    @ParameterizedTest
    @CsvSource({
        "1.1, 1.5, 2.6",
        "3.7, 6.2, 9.9",
        "4.4, 10.9, 15.3",
        "7.0, 13.7, 20.7"
    })
    void changeWeight_ExpectNewWeight_WhenWeightIsChanged(double weight, double delta, double expected) {
        Move move = new Move(0, 31);

        // Set weight
        move.setWeight(weight);
        assertEquals(weight, move.getWeight());

        // Change weight
        move.changeWeight(delta);
        assertEquals(expected, move.getWeight());
    }

    /*** To String ***/
    @ParameterizedTest
    @MethodSource
    void toString_ExpectStringWithCorrectStartEndAndWeight(int sIndex, int eIndex, double wt, String exp) {
        Move move = new Move(sIndex, eIndex);
        move.setWeight(wt);
        String result = move.toString();
        assertEquals(exp, result);
    }

    /*** Methods for providing arguments to parameterized tests ***/

    private static Stream<Arguments> toString_ExpectStringWithCorrectStartEndAndWeight() {
        return Stream.of(
            Arguments.of(0, 31, 1.1, "Move[startIndex=0, endIndex=31, weight=1.1]"),
            Arguments.of(1, 30, 2.2, "Move[startIndex=1, endIndex=30, weight=2.2]"),
            Arguments.of(2, 29, 3.3, "Move[startIndex=2, endIndex=29, weight=3.3]"),
            Arguments.of(3, 28, 4.4, "Move[startIndex=3, endIndex=28, weight=4.4]"),
            Arguments.of(4, 27, 5.5, "Move[startIndex=4, endIndex=27, weight=5.5]"),
            Arguments.of(5, 26, 6.6, "Move[startIndex=5, endIndex=26, weight=6.6]"),
            Arguments.of(6, 25, 7.7, "Move[startIndex=6, endIndex=25, weight=7.7]")
        );
    }
}