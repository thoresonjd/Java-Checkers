package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class OptionPanelTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void setWindow_shouldSetWindow_whenPassedCheckersWindow() {
        CheckersWindow originalWindow = new CheckersWindow();
        OptionPanel optionPanel = new OptionPanel(originalWindow);

        CheckersWindow newWindow = new CheckersWindow();
        optionPanel.setWindow(newWindow);

        assertNotEquals(optionPanel.getWindow(), originalWindow);
        assertEquals(optionPanel.getWindow(), newWindow);

    }

    @ParameterizedTest
    @CsvSource({
            "true, player1 message",
            "false, player2 message"
    })
    void setNetworkWindowMessage_shouldSetMessageForSpecifiedPlayer(boolean forPlayer1, String expectedMessage) {
        OptionPanel optionPanel = new OptionPanel(new CheckersWindow());
        optionPanel.setNetworkWindowMessage(forPlayer1, expectedMessage);

        String actualMessage = forPlayer1 ?
                optionPanel.getNetworkWindow1().getMessage() :
                optionPanel.getNetworkWindow2().getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @AfterEach
    void tearDown() {
    }
}