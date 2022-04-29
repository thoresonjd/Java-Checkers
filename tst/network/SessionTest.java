package network;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /*** getListener ***/

    @Test
    void getListener_ExpectProvidedListener_WhenListenerProvidedViaConstructor() {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", 0);
        ConnectionListener res = session.getListener();
        assertEquals(res, connectionListener);

        // Stop listening at port to prevent tests from bleeding into one another
        connectionListener.stopListening();
    }

    @Test
    void getListener_ExpectProvidedListener_WhenListenerProvidedViaSetter() {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", 0);
        session.setListener(connectionListener);
        ConnectionListener res = session.getListener();
        assertEquals(res, connectionListener);

        // Stop listening at port to prevent tests from bleeding into one another
        connectionListener.stopListening();
    }

    @Test
    void getListener_ExpectNonNullListener_WhenNoListenerProvided() {
        Session session = new Session("", 0, "", 0);
        assertNotNull(session.getListener());
    }

    /*** getSid ***/

    @ParameterizedTest
    @CsvSource({ "1", "2", "3", "4", "5"})
    void getSid_ExpectProvidedSid_WhenSidProvidedViaConstructor(String sid) {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, sid, "", 0);
        assertTrue(session.getSid().equals(sid));

        // Stop listening at port to prevent tests from bleeding into one another
        connectionListener.stopListening();
    }

    @ParameterizedTest
    @CsvSource({ "1", "2", "3", "4", "5"})
    void getSid_ExpectProvidedSid_WhenSidProvidedViaSetter(String sid) {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", 0);
        session.setSid(sid);
        assertTrue(session.getSid().equals(sid));

        // Stop listening at port to prevent tests from bleeding into one another
        connectionListener.stopListening();
    }

    /*** getDestinationHost ***/

    @ParameterizedTest
    @CsvSource({ "1", "2", "3", "4", "5"})
    void getDestinationHost_ExpectProvidedHost_WhenHostProvidedViaConstructor(String destHost) {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", destHost, 0);
        assertTrue(session.getDestinationHost().equals(destHost));

        // Stop listening at port to prevent tests from bleeding into one another
        connectionListener.stopListening();
    }


    @ParameterizedTest
    @CsvSource({ "1", "2", "3", "4", "5"})
    void getDestinationHost_ExpectProvidedHost_WhenHostProvidedViaSetter(String destHost) {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", 0);
        session.setDestinationHost(destHost);
        assertTrue(session.getDestinationHost().equals(destHost));

        // Stop listening at port to prevent tests from bleeding into one another
        connectionListener.stopListening();
    }

    /*** getDestinationPort ***/

    @ParameterizedTest
    @CsvSource({ "1000", "2000", "3000", "4000", "5000"})
    void getDestinationPort_ExpectProvidedPort_WhenPortProvidedViaConstructor(int destPort) {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", destPort);
        assertEquals(session.getDestinationPort(), destPort);

        // Stop listening at port to prevent tests from bleeding into one another
        connectionListener.stopListening();
    }

    @ParameterizedTest
    @CsvSource({ "1000", "2000", "3000", "4000", "5000"})
    void getDestinationPort_ExpectProvidedPort_WhenPortProvidedViaSetter(int destPort) {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", 0);
        session.setDestinationPort(destPort);
        assertEquals(session.getDestinationPort(), destPort);

        // Stop listening at port to prevent tests from bleeding into one another
        connectionListener.stopListening();
    }

    /*** getSourcePort ***/

    @ParameterizedTest
    @CsvSource({"1000", "2000", "3000", "4000", "5000"})
    void getSourcePort_ExpectProvidedPort_WhenPortProvided(int port) {
        ConnectionListener connectionListener = new ConnectionListener(port);
        Session session = new Session(connectionListener, "", "", 0);
        assertEquals(port, session.getSourcePort());

        // Stop listening at port to prevent tests from bleeding into one another
        connectionListener.stopListening();
    }

    @ParameterizedTest
    @CsvSource({"-1000", "-2", "-1", "0"})
    void getSourcePort_ExpectAutoGeneratedPort_WhenInvalidPortProvided(int port) {
        ConnectionListener connectionListener = new ConnectionListener(port);
        Session session = new Session(connectionListener, "", "", 0);
        assertNotEquals(port, session.getSourcePort());

        // Stop listening at port to prevent tests from bleeding into one another
        connectionListener.stopListening();
    }

    @Test
    void getSourcePort_ExpectInvalidPort_WhenListenerIsNull() {
        Session session = new Session(null, "", "", 0);
        assertEquals(-1, session.getSourcePort());
    }

    @ParameterizedTest
    @CsvSource({ "1000", "2000", "3000", "4000", "5000"})
    void getSourcePort_ExpectProvidedPort_WhenPortProvidedViaSetter(int srcPort) {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", 0);
        session.setSourcePort(srcPort);
        assertEquals(srcPort, session.getSourcePort());

        // Stop listening at port to prevent tests from bleeding into one another
        connectionListener.stopListening();
    }

}