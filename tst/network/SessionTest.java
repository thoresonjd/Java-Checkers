package network;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /*** constructor ***/

    @Test
    void Session_CreateObject_WhenCorrectTriggered() {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session1 = new Session(connectionListener, "", "", 0);
        Session session2 = new Session("",0,"",0);
    }

    /*** getListener ***/

    @Test
    void getListener_ReturnListener_WhenCorrectTriggered() {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", 0);
        session.getListener();
    }

    /*** setListener ***/

    @Test
    void setListener_SetListenerObject_WhenCorrectInput() {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", 0);
        session.setListener(connectionListener);
    }

    /*** getSid ***/

    @Test
    void getSid_ReturnSid_WhenCorrectTriggered() {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", 0);
        session.getSid();
    }

    /*** setSid ***/

    @Test
    void setSid_SetSidValue_WhenCorrectInput() {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", 0);
        session.setSid("");
    }

    /*** getDestinationHost ***/

    @Test
    void getDestinationHost_GetDestinationHost_WhenCorrectTriggered() {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", 0);
        session.getDestinationHost();
    }

    /*** setDestinationHost ***/

    @Test
    void setDestinationHost_GetDestinationHost_WhenCorrectInput() {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", 0);
        session.setDestinationHost("");
    }

    /*** getDestinationPort ***/

    @Test
    void getDestinationPort_GetDestinationPort_WhenCorrectTriggered() {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", 0);
        session.getDestinationPort();
    }

    /*** setDestinationPort ***/

    @Test
    void setDestinationPort_SetDestinationPort_WhenCorrectInput() {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", 0);
        session.setDestinationPort(0);
    }

    /*** getSourcePort ***/

    @Test
    void getSourcePort_GetSourcePort_WhenCorrectTriggered() {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", 0);
        session.getSourcePort();
    }

    /*** setSourcePort ***/

    @Test
    void setSourcePort_SetSourcePort_WhenCorrectInput() {
        ConnectionListener connectionListener = new ConnectionListener();
        Session session = new Session(connectionListener, "", "", 0);
        session.setSourcePort(0);
    }

}