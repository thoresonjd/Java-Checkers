package network;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.*;

class ConnectionListenerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /*** Constructor ***/

    @Test
    void new_CreateConnectionListener_WhenProvideNothing(){
        ConnectionListener cl = new ConnectionListener();
    }

    @Test
    void new_CreateConnectionListener_WhenProvidePortAndConnectionHandler(){
        ActionListener connectionHandler = mock(ActionListener.class);
        ConnectionListener cl = new ConnectionListener(0, connectionHandler);
    }

    /*** Run ***/

    @Test
    void run_StartsListening_WhenServerSocketIsOpen() throws IOException {
        final ServerSocket serverSocket = mock(ServerSocket.class);
        when(serverSocket.isClosed()).thenReturn(Boolean.TRUE);
        when(serverSocket.accept()).thenReturn(new Socket());

        // Set up listener
        ConnectionListener cl = new ConnectionListener(serverSocket){
            @Override
            protected boolean ConnectHandler() throws IOException {
                return false;
            }
        };

        // Run listener
        cl.run();
    }

    @Test
    void run_CatchIOException_WhenConnectHandlerThrowIOException() throws IOException {
        final ServerSocket serverSocket = mock(ServerSocket.class);
        when(serverSocket.isClosed()).thenReturn(Boolean.FALSE).thenReturn(Boolean.FALSE).thenReturn(Boolean.TRUE);
        when(serverSocket.accept()).thenReturn(new Socket());

        // Set up listener
        ConnectionListener cl = new ConnectionListener(serverSocket){
            @Override
            protected boolean ConnectHandler() throws IOException {
                throw new IOException("IOException");
            }

            @Override
            protected void ReConnectServerSocket(){
            }
        };

        // Run listener
        cl.run();
    }

    @Test
    void run_CatchException_WhenConnectHandlerThrowException() throws Exception {
        final ServerSocket serverSocket = mock(ServerSocket.class);
        when(serverSocket.isClosed()).thenReturn(Boolean.FALSE).thenReturn(Boolean.FALSE).thenReturn(Boolean.TRUE);
        when(serverSocket.accept()).thenReturn(new Socket());

        // Set up listener
        ConnectionListener cl = new ConnectionListener(serverSocket){
            @Override
            protected boolean ConnectHandler() throws Exception {
                throw new Exception("Exception");
            }

            @Override
            protected void ReConnectServerSocket(){
            }
        };

        // Run listener
        cl.run();
    }

    /*** Listen ***/

    @Test
    public void listen_run_WhenTriggered(){
        ConnectionListener cl = new ConnectionListener();
        cl.listen();
    }

    /*** ReConnectServerSocket ***/

    @Test
    public void ReConnectServerSocket_ConnectLocalPort_WhenNoException(){
        ConnectionListener cl = new ConnectionListener();
        cl.ReConnectServerSocket();
    }

    @Test
    public void ReConnectServerSocket_CatchException_WhenException(){
        ConnectionListener cl = new ConnectionListener();
        cl.ReConnectServerSocket();
    }

    /*** ConnectHandler ***/

    @Test
    public void ConnectHandler_start_WhenCorrectTriggered() throws Exception {
        final Socket socket = mock(Socket.class);
        ConnectionListener cl = new ConnectionListener(){
            @Override
            protected ConnectionHandler getConnectionHandlerAccept() throws IOException {
                return new ConnectionHandler(this, socket);
            }
        };
        cl.ConnectHandler();
    }

    /*** stopListening ***/

    @Test
    public void stopListening_returnTrue_WhenServerSocketClosedInSpecialCases() throws IOException {
        final ServerSocket serverSocket = mock(ServerSocket.class);
        when(serverSocket.isClosed()).thenReturn(Boolean.TRUE);

        // Set up listener
        ConnectionListener cl = new ConnectionListener(serverSocket);

        cl.stopListening();
    }

    @Test
    public void stopListening_returnTrue_WhenServerSocketClosed() throws IOException {
        final ServerSocket serverSocket = mock(ServerSocket.class);
        when(serverSocket.isClosed()).thenReturn(Boolean.FALSE);

        // Set up listener
        ConnectionListener cl = new ConnectionListener(serverSocket);

        cl.stopListening();
    }

    @Test
    public void stopListening_CatchException_WhenCatchExceptionFromCloseServerSocket() throws IOException {
        final ServerSocket serverSocket = mock(ServerSocket.class);
        when(serverSocket.isClosed()).thenReturn(Boolean.FALSE);

        // Set up listener
        ConnectionListener cl = new ConnectionListener(serverSocket){
            @Override
            protected void closeServerSocket() throws IOException {
                throw new IOException("IOException");
            }
        };

        cl.stopListening();
    }

    /*** read ***/

    @Test
    public void read_ReturnEmpty_WhenSocketIsNull() {
        ConnectionListener.read(null);
    }

    @Test
    public void read_ReturnData_WhenCorrectTriggered() throws IOException {
        final Socket socket = mock(Socket.class);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[20]);
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);

        ConnectionListener.read(socket);
    }

    @Test
    public void read_IOException_WhenCatchIOEXception() throws IOException {
        final Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenThrow(IOException.class);

        ConnectionListener.read(socket);
    }

    /*** available ***/

    @Test
    public void available_returnFalse_WhenIncorrectPort(){
        ConnectionListener.available(-1);
    }

    @Test
    public void available_returnTrue_WhenIncorrectPort(){
        ConnectionListener.available(1);
    }

    @Test
    public void available_IOException_WhenCatchException() {
        ConnectionListener.available(-87);
    }

    @Test
    public void available_SecondIOException_WhenCatchException() {
        ConnectionListener.available(-69);
    }

    /*** getPort ***/

    @Test
    public void getPort_ServerSocketGetLocalPort_WhenCorrectTriggered(){
        // Set up listener
        ConnectionListener cl = new ConnectionListener();
        cl.getPort();
    }

    /*** setPort ***/

    @Test
    public void setPort_setPortEqualZero_WhenInputLessThanZero(){
        // Set up listener
        ConnectionListener cl = new ConnectionListener();
        cl.setPort(-1);
    }

    @Test
    public void setPort_IOException_WhenCatchException(){
        // Set up listener
        ConnectionListener cl = new ConnectionListener(){
            @Override
            public ServerSocket getServerSocket(int port) throws IOException {
                throw new IOException();
            }
        };
        cl.setPort(1);
    }

    /*** getServerSocket ***/

    @Test
    public void getServerSocket_getServerSocket_WhenCorrectTriggered(){
        ConnectionListener cl = new ConnectionListener();
        cl.getServerSocket();
    }

    /*** setServerSocket ***/

    @Test
    public void setServerSocket_setServerSocket_WhenCorrectTriggered(){
        ServerSocket serverSocket = mock(ServerSocket.class);
        ConnectionListener cl = new ConnectionListener();
        cl.setServerSocket(serverSocket);
    }

    /*** setConnectionHandler ***/

    @Test
    public void setConnectionHandler_setConnectionHandler_WhenCorrectTriggered(){
        ActionListener actionListener = mock(ActionListener.class);
        ConnectionListener cl = new ConnectionListener();
        cl.setConnectionHandler(actionListener);
    }

}