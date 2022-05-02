package network;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /*** Get Listener ***/

    @ParameterizedTest
    @CsvSource({"1", "2", "3", "4000"})
    void getListener_ExpectProvidedPortFromListener_WhenPortIsValid(int port) {
        ConnectionHandler handler = new ConnectionHandler(new ConnectionListener(port), new Socket());
        ConnectionListener listener = handler.getListener();
        assertEquals(port, listener.getPort());
    }


}