package network;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import model.Game;
import model.NetworkPlayer;
import model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.CheckersWindow;
import ui.CheckerBoard;
import ui.NetworkWindow;
import ui.OptionPanel;

import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

class CheckersNetworkHandlerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /*** actionPerformed ***/

    @Test
    public void actionPerformed_return_WhenActionEventIsNull(){
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);

        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op);
        cnh.actionPerformed(null);
    }

    @Test
    public void actionPerformed_return_WhenCheckersWindowIsNull() throws IOException {
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final ConnectionHandler handler = mock(ConnectionHandler.class);
        final ActionEvent e = mock(ActionEvent.class);
        final ConnectionListener cl = mock(ConnectionListener.class);
        final Socket sk = mock(Socket.class);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[20]);
        final Session s = mock(Session.class);

        when(e.getSource()).thenReturn(new ConnectionHandler(cl, sk));
        when(handler.getSocket()).thenReturn(sk);
        when(sk.getInputStream()).thenReturn(byteArrayInputStream);
        when(sk.isClosed()).thenReturn(true);
        when(cw.getSession1()).thenReturn(s);
        when(cw.getSession2()).thenReturn(s);


        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, null, cb, op);
        cnh.actionPerformed(e);
    }

    @Test
    public void actionPerformed_start_WhenPlayerOneAndCorrectActionEvent() throws IOException {
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final ConnectionHandler handler = mock(ConnectionHandler.class);
        final ActionEvent e = mock(ActionEvent.class);
        final ConnectionListener cl = mock(ConnectionListener.class);
        final Socket sk = mock(Socket.class);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[20]);
        final Session s = mock(Session.class);

        when(e.getSource()).thenReturn(new ConnectionHandler(cl, sk));
        when(handler.getSocket()).thenReturn(sk);
        when(sk.getInputStream()).thenReturn(byteArrayInputStream);
        when(sk.isClosed()).thenReturn(true);
        when(cw.getSession1()).thenReturn(s);
        when(cw.getSession2()).thenReturn(s);


        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op){
            @Override
            public ConnectionHandler getEventSource(ActionEvent e){
                return handler;
            }
        };
        cnh.actionPerformed(e);
    }

    @Test
    public void actionPerformed_start_WhenPlayerTwoAndCorrectActionEvent() throws IOException {
        boolean isPlayer1 = false;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final ConnectionHandler handler = mock(ConnectionHandler.class);
        final ActionEvent e = mock(ActionEvent.class);
        final ConnectionListener cl = mock(ConnectionListener.class);
        final Socket sk = mock(Socket.class);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[20]);
        final Session s = mock(Session.class);

        when(e.getSource()).thenReturn(new ConnectionHandler(cl, sk));
        when(handler.getSocket()).thenReturn(sk);
        when(sk.getInputStream()).thenReturn(byteArrayInputStream);
        when(sk.isClosed()).thenReturn(true);
        when(cw.getSession1()).thenReturn(s);
        when(cw.getSession2()).thenReturn(s);


        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op){
            @Override
            public ConnectionHandler getEventSource(ActionEvent e){
                return handler;
            }
        };
        cnh.actionPerformed(e);
    }

    @Test
    public void actionPerformed_start_WhenPlayerOneAndCommandEqualUpdate() throws IOException {
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final ConnectionHandler handler = mock(ConnectionHandler.class);
        final ActionEvent e = mock(ActionEvent.class);
        final ConnectionListener cl = mock(ConnectionListener.class);
        final Socket sk = mock(Socket.class);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[20]);
        final Session s = mock(Session.class);

        when(e.getSource()).thenReturn(new ConnectionHandler(cl, sk));
        when(handler.getSocket()).thenReturn(sk);
        when(sk.getInputStream()).thenReturn(byteArrayInputStream);
        when(sk.isClosed()).thenReturn(true);
        when(cw.getSession1()).thenReturn(s);
        when(cw.getSession2()).thenReturn(s);


        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op){
            @Override
            public ConnectionHandler getEventSource(ActionEvent e){
                return handler;
            }

            @Override
            public String getCMD(String[] lines){
                return "UPDATE";
            }

            @Override
            public String getSID(String[] lines){
                return "1";
            }
        };
        cnh.actionPerformed(e);
    }

    @Test
    public void actionPerformed_start_WhenPlayerOneAndCommandEqualConnect() throws IOException {
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final ConnectionHandler handler = mock(ConnectionHandler.class);
        final ActionEvent e = mock(ActionEvent.class);
        final ConnectionListener cl = mock(ConnectionListener.class);
        final Socket sk = mock(Socket.class);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[20]);
        final Session s = mock(Session.class);
        final InetAddress ia = mock(InetAddress.class);
        final NetworkWindow nw = mock(NetworkWindow.class);

        when(e.getSource()).thenReturn(new ConnectionHandler(cl, sk));
        when(handler.getSocket()).thenReturn(sk);
        when(sk.getInputStream()).thenReturn(byteArrayInputStream);
        when(sk.isClosed()).thenReturn(true);
        when(cw.getSession1()).thenReturn(s);
        when(cw.getSession2()).thenReturn(s);
        when(sk.getInetAddress()).thenReturn(ia);
        when(ia.getHostAddress()).thenReturn("127.0.0.1");


        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op){
            @Override
            public ConnectionHandler getEventSource(ActionEvent e){
                return handler;
            }

            @Override
            public String getCMD(String[] lines){
                return "CONNECT";
            }

            @Override
            public String getSID(String[] lines){
                return "1";
            }

            @Override
            public NetworkWindow getWin(){
                return nw;
            }


        };
        cnh.actionPerformed(e);
    }

    @Test
    public void actionPerformed_start_WhenPlayerTwoAndCommandEqualConnect() throws IOException {
        boolean isPlayer1 = false;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final ConnectionHandler handler = mock(ConnectionHandler.class);
        final ActionEvent e = mock(ActionEvent.class);
        final ConnectionListener cl = mock(ConnectionListener.class);
        final Socket sk = mock(Socket.class);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[20]);
        final Session s = mock(Session.class);
        final InetAddress ia = mock(InetAddress.class);
        final NetworkWindow nw = mock(NetworkWindow.class);

        when(e.getSource()).thenReturn(new ConnectionHandler(cl, sk));
        when(handler.getSocket()).thenReturn(sk);
        when(sk.getInputStream()).thenReturn(byteArrayInputStream);
        when(sk.isClosed()).thenReturn(true);
        when(cw.getSession1()).thenReturn(s);
        when(cw.getSession2()).thenReturn(s);
        when(sk.getInetAddress()).thenReturn(ia);
        when(ia.getHostAddress()).thenReturn("127.0.0.1");


        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op){
            @Override
            public ConnectionHandler getEventSource(ActionEvent e){
                return handler;
            }

            @Override
            public String getCMD(String[] lines){
                return "CONNECT";
            }

            @Override
            public String getSID(String[] lines){
                return "1";
            }

            @Override
            public NetworkWindow getWin(){
                return nw;
            }
        };
        cnh.actionPerformed(e);
    }

    @Test
    public void actionPerformed_start_WhenPlayerOneAndCommandEqualCommandGetAndMatch() throws IOException {
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final ConnectionHandler handler = mock(ConnectionHandler.class);
        final ActionEvent e = mock(ActionEvent.class);
        final ConnectionListener cl = mock(ConnectionListener.class);
        final Socket sk = mock(Socket.class);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[20]);
        final Session s = mock(Session.class);
        final InetAddress ia = mock(InetAddress.class);
        final NetworkWindow nw = mock(NetworkWindow.class);
        final Game g = mock(Game.class);

        when(e.getSource()).thenReturn(new ConnectionHandler(cl, sk));
        when(handler.getSocket()).thenReturn(sk);
        when(sk.getInputStream()).thenReturn(byteArrayInputStream);
        when(sk.isClosed()).thenReturn(true);
        when(cw.getSession1()).thenReturn(s);
        when(cw.getSession2()).thenReturn(s);
        when(s.getSid()).thenReturn("1");
        when(sk.getInetAddress()).thenReturn(ia);
        when(ia.getHostAddress()).thenReturn("127.0.0.1");
        when(cb.getGame()).thenReturn(g);
        when(g.getGameState()).thenReturn("111");


        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op){
            @Override
            public ConnectionHandler getEventSource(ActionEvent e){
                return handler;
            }

            @Override
            public String getCMD(String[] lines){
                return "GET-STATE";
            }

            @Override
            public String getSID(String[] lines){
                return "1";
            }

            @Override
            public NetworkWindow getWin(){
                return nw;
            }
        };
        cnh.actionPerformed(e);
    }

    @Test
    public void actionPerformed_start_WhenPlayerOneAndCommandEqualCommandGetAndNotMatch() throws IOException {
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final ConnectionHandler handler = mock(ConnectionHandler.class);
        final ActionEvent e = mock(ActionEvent.class);
        final ConnectionListener cl = mock(ConnectionListener.class);
        final Socket sk = mock(Socket.class);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[20]);
        final Session s = mock(Session.class);
        final InetAddress ia = mock(InetAddress.class);
        final NetworkWindow nw = mock(NetworkWindow.class);
        final Game g = mock(Game.class);

        when(e.getSource()).thenReturn(new ConnectionHandler(cl, sk));
        when(handler.getSocket()).thenReturn(sk);
        when(sk.getInputStream()).thenReturn(byteArrayInputStream);
        when(sk.isClosed()).thenReturn(true);
        when(cw.getSession1()).thenReturn(s);
        when(cw.getSession2()).thenReturn(s);
        when(s.getSid()).thenReturn("1");
        when(sk.getInetAddress()).thenReturn(ia);
        when(ia.getHostAddress()).thenReturn("127.0.0.1");
        when(cb.getGame()).thenReturn(g);
        when(g.getGameState()).thenReturn("111");


        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op){
            @Override
            public ConnectionHandler getEventSource(ActionEvent e){
                return handler;
            }

            @Override
            public String getCMD(String[] lines){
                return "GET-STATE";
            }

            @Override
            public String getSID(String[] lines){
                return "2";
            }

            @Override
            public NetworkWindow getWin(){
                return nw;
            }
        };
        cnh.actionPerformed(e);
    }


    @Test
    public void actionPerformed_start_WhenPlayerOneAndCommandEqualDisconnect() throws IOException {
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final ConnectionHandler handler = mock(ConnectionHandler.class);
        final ActionEvent e = mock(ActionEvent.class);
        final ConnectionListener cl = mock(ConnectionListener.class);
        final Socket sk = mock(Socket.class);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[20]);
        final Session s = mock(Session.class);
        final InetAddress ia = mock(InetAddress.class);
        final NetworkWindow nw = mock(NetworkWindow.class);
        final Game g = mock(Game.class);

        when(e.getSource()).thenReturn(new ConnectionHandler(cl, sk));
        when(handler.getSocket()).thenReturn(sk);
        when(sk.getInputStream()).thenReturn(byteArrayInputStream);
        when(sk.isClosed()).thenReturn(true);
        when(cw.getSession1()).thenReturn(s);
        when(cw.getSession2()).thenReturn(s);
        when(s.getSid()).thenReturn("1");
        when(sk.getInetAddress()).thenReturn(ia);
        when(ia.getHostAddress()).thenReturn("127.0.0.1");
        when(cb.getGame()).thenReturn(g);
        when(g.getGameState()).thenReturn("111");
        when(op.getNetworkWindow1()).thenReturn(nw);


        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op){
            @Override
            public ConnectionHandler getEventSource(ActionEvent e){
                return handler;
            }

            @Override
            public String getCMD(String[] lines){
                return "DISCONNECT";
            }

            @Override
            public String getSID(String[] lines){
                return "1";
            }

            @Override
            public NetworkWindow getWin(){
                return nw;
            }
        };
        cnh.actionPerformed(e);
    }

    @Test
    public void actionPerformed_start_WhenPlayerTwoAndCommandEqualDisconnect() throws IOException {
        boolean isPlayer1 = false;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final ConnectionHandler handler = mock(ConnectionHandler.class);
        final ActionEvent e = mock(ActionEvent.class);
        final ConnectionListener cl = mock(ConnectionListener.class);
        final Socket sk = mock(Socket.class);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[20]);
        final Session s = mock(Session.class);
        final InetAddress ia = mock(InetAddress.class);
        final NetworkWindow nw = mock(NetworkWindow.class);
        final Game g = mock(Game.class);

        when(e.getSource()).thenReturn(new ConnectionHandler(cl, sk));
        when(handler.getSocket()).thenReturn(sk);
        when(sk.getInputStream()).thenReturn(byteArrayInputStream);
        when(sk.isClosed()).thenReturn(true);
        when(cw.getSession1()).thenReturn(s);
        when(cw.getSession2()).thenReturn(s);
        when(s.getSid()).thenReturn("1");
        when(sk.getInetAddress()).thenReturn(ia);
        when(ia.getHostAddress()).thenReturn("127.0.0.1");
        when(cb.getGame()).thenReturn(g);
        when(g.getGameState()).thenReturn("111");
        when(op.getNetworkWindow2()).thenReturn(nw);


        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op){
            @Override
            public ConnectionHandler getEventSource(ActionEvent e){
                return handler;
            }

            @Override
            public String getCMD(String[] lines){
                return "DISCONNECT";
            }

            @Override
            public String getSID(String[] lines){
                return "1";
            }

            @Override
            public NetworkWindow getWin(){
                return nw;
            }
        };
        cnh.actionPerformed(e);
    }

    @Test
    public void actionPerformed_start_WhenPlayerOneAndCommandEqualDisconnectAndNotMatch() throws IOException {
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final ConnectionHandler handler = mock(ConnectionHandler.class);
        final ActionEvent e = mock(ActionEvent.class);
        final ConnectionListener cl = mock(ConnectionListener.class);
        final Socket sk = mock(Socket.class);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[20]);
        final Session s = mock(Session.class);
        final NetworkWindow nw = mock(NetworkWindow.class);

        when(e.getSource()).thenReturn(new ConnectionHandler(cl, sk));
        when(handler.getSocket()).thenReturn(sk);
        when(sk.getInputStream()).thenReturn(byteArrayInputStream);
        when(sk.isClosed()).thenReturn(true);
        when(cw.getSession1()).thenReturn(s);
        when(cw.getSession2()).thenReturn(s);
        when(s.getSid()).thenReturn("1");


        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op){
            @Override
            public ConnectionHandler getEventSource(ActionEvent e){
                return handler;
            }

            @Override
            public String getCMD(String[] lines){
                return "DISCONNECT";
            }

            @Override
            public String getSID(String[] lines){
                return "2";
            }

            @Override
            public NetworkWindow getWin(){
                return nw;
            }
        };
        cnh.actionPerformed(e);
    }


    /*** handleUpdate ***/

    @Test
    public void handleUpdate_ResponseDenied_WhenInputIsNull(){
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);

        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op);
        cnh.testHandleUpdate("");
    }

    @Test
    public void handleUpdate_UpdateBoard_WhenPlayerIsNotHuman(){
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final Player p1 = mock(Player.class);

        when(cb.getCurrentPlayer()).thenReturn(p1);
        when(p1.isHuman()).thenReturn(false);

        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op);
        cnh.testHandleUpdate("111");
    }

    @Test
    public void handleUpdate_SendGameState_WhenPlayerOneAndPlayerTwoIsNetworkPlayer(){
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final Player p1 = mock(Player.class);
        final NetworkPlayer p2 = mock(NetworkPlayer.class);

        when(cb.getPlayer2()).thenReturn(p2);

        when(cb.getCurrentPlayer()).thenReturn(p1);
        when(p1.isHuman()).thenReturn(false);

        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op);
        cnh.testHandleUpdate("111");
    }

    @Test
    public void handleUpdate_SendGameState_WhenPlayerTwoAndPlayerOneIsNetworkPlayer(){
        boolean isPlayer1 = false;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final Player p2 = mock(Player.class);
        final NetworkPlayer p1 = mock(NetworkPlayer.class);

        when(cb.getPlayer1()).thenReturn(p1);

        when(cb.getCurrentPlayer()).thenReturn(p2);
        when(p2.isHuman()).thenReturn(false);

        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op);
        cnh.testHandleUpdate("111");
    }


    /*** handleConnect ***/
    //@Test
    public void handleConnect_ResponseDenied_WhenPlayerOneIsFalse(){
        boolean isPlayer1 = false;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final Session s = mock(Session.class);
        when(cw.getSession1()).thenReturn(s);
        when(cw.getSession2()).thenReturn(s);
        when(s.getSid()).thenReturn("1");

        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op);
        cnh.testHandleConnect(null, 0, false);
    }

    @Test
    public void handleConnect_ResponseDenied_WhenPlayerOneAndSid1(){
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final Session s = mock(Session.class);
        final Socket sk = mock(Socket.class);
        when(cw.getSession1()).thenReturn(s);
        when(cw.getSession2()).thenReturn(s);
        when(s.getSid()).thenReturn("1");
        when(s.getSourcePort()).thenReturn(1);

        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op);
        cnh.testHandleConnect(sk, 1, false);
    }

    @Test
    public void handleConnect_ResponseDenied_WhenPlayerOneIsRemote(){
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final Session s = mock(Session.class);
        final Socket sk = mock(Socket.class);
        when(cw.getSession1()).thenReturn(s);
        when(cw.getSession2()).thenReturn(s);
        when(s.getSid()).thenReturn(null);
        when(s.getSourcePort()).thenReturn(1);

        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op);
        cnh.testHandleConnect(null, 1, true);
    }

    @Test
    public void handleConnect_ResponseDenied_WhenPlayerOneNotRemoteAndHostEqual127001(){
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final Session s = mock(Session.class);
        final Socket sk = mock(Socket.class);
        final InetAddress ia = mock(InetAddress.class);
        when(cw.getSession1()).thenReturn(s);
        when(cw.getSession2()).thenReturn(s);
        when(s.getSid()).thenReturn("");
        when(s.getSourcePort()).thenReturn(1);
        when(sk.getInetAddress()).thenReturn(ia);
        when(ia.getHostAddress()).thenReturn("127.0.0.1");

        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op);
        cnh.testHandleConnect(sk, 1, false);
    }

    @Test
    public void handleConnect_ResponseDenied_WhenPlayerOneNotRemoteAndHostNotEqual127001(){
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final Session s = mock(Session.class);
        final Socket sk = mock(Socket.class);
        final InetAddress ia = mock(InetAddress.class);
        final NetworkWindow nw = mock(NetworkWindow.class);

        when(cw.getSession1()).thenReturn(s);
        when(cw.getSession2()).thenReturn(s);
        when(s.getSid()).thenReturn("");
        when(s.getSourcePort()).thenReturn(1);
        when(sk.getInetAddress()).thenReturn(ia);
        when(ia.getHostAddress()).thenReturn("127.6.9.1");

        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op){
            @Override
            public NetworkWindow getWin(){
                return nw;
            }
        };
        cnh.testHandleConnect(sk, 1, false);
    }


    /*** getWin ***/

    @Test
    public void getWin_returnWin_WhenTriggered(){
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op);
        cnh.getWin();
    }


    /*** sendResponse ***/
    @Test
    public void sendResponse_Return_WhenConnectionHandlerIsNull(){
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final ConnectionHandler ch = mock(ConnectionHandler.class);
        final Socket sk = mock(Socket.class);
        when(sk.isClosed()).thenReturn(true);
        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op);
        cnh.testSendResponse(null,"test");
    }

    @Test
    public void sendResponse_Return_WhenSocketIsClosed(){
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final ConnectionHandler ch = mock(ConnectionHandler.class);
        final Socket sk = mock(Socket.class);
        when(sk.isClosed()).thenReturn(true);
        when(ch.getSocket()).thenReturn(sk);
        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op);
        cnh.testSendResponse(ch,"test");
    }

    @Test
    public void sendResponse_SetEmptyResponse_WhenResponseIsNull() throws IOException {
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final ConnectionHandler ch = mock(ConnectionHandler.class);
        final Socket sk = mock(Socket.class);
        final OutputStream os = mock(OutputStream.class);
        when(sk.isClosed()).thenReturn(false);
        when(ch.getSocket()).thenReturn(sk);
        when(sk.getOutputStream()).thenReturn(os);
        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op);
        cnh.testSendResponse(ch,null);
    }

    @Test
    public void sendResponse_CatchIOException_WhenGetOutputStreamFailed() throws IOException {
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final ConnectionHandler ch = mock(ConnectionHandler.class);
        final Socket sk = mock(Socket.class);
        final OutputStream os = mock(OutputStream.class);
        when(sk.isClosed()).thenReturn(false);
        when(ch.getSocket()).thenReturn(sk);
        when(sk.getOutputStream()).thenThrow(IOException.class);
        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op);
        cnh.testSendResponse(ch,"test");
    }

    @Test
    public void sendResponse_CatchIOException_WhenSocketCloseFailed() throws IOException {
        boolean isPlayer1 = true;
        final CheckersWindow cw = mock(CheckersWindow.class);
        final CheckerBoard cb = mock(CheckerBoard.class);
        final OptionPanel op = mock(OptionPanel.class);
        final ConnectionHandler ch = mock(ConnectionHandler.class);
        final Socket sk = mock(Socket.class);
        final OutputStream os = mock(OutputStream.class);
        when(sk.isClosed()).thenReturn(false);
        when(ch.getSocket()).thenReturn(sk);
        when(sk.getOutputStream()).thenReturn(os);
        doThrow(new IOException()).when(sk).close();
        CheckersNetworkHandler cnh = new CheckersNetworkHandler(isPlayer1, cw, cb, op);
        cnh.testSendResponse(ch,null);
    }
//    /*** Action Performed ***/
//
//    @Test
//    void actionPerformed() {
//        CheckersWindow window = new CheckersWindow();
//        CheckerBoard board = new CheckerBoard(window);
//        OptionPanel panel = new OptionPanel(window);
//        CheckersNetworkHandler handler = new CheckersNetworkHandler(
//            true, window, board, panel
//        );
//
//        handler.actionPerformed(new ActionEvent(0, 0, ""));
//    }
}