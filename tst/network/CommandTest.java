package network;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class CommandTest {

    @BeforeEach
    void setUp(){
    }

    @AfterEach
    void tearDown() {
    }

    /*** send ***/

    @Test
    public void send_ReturnStringResponse_WhenCorrectTriggered() throws IOException {
        // Using Mockito
        final Socket socket = mock(Socket.class);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[20]);

        when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);

        Command command = new Command("","") {
            @Override
            protected Socket createSocket() {
                return socket;
            }
        };
        String response = command.send("localhost",1234);
    }

    @Test
    public void send_UnknownHostException_WhenIncorrectTriggered() throws IOException {
        // Using Mockito
        final Socket socket = mock(Socket.class);

        when(socket.getOutputStream()).thenThrow(UnknownHostException.class);

        Command command = new Command("","") {
            @Override
            protected Socket createSocket() {
                return socket;
            }
        };
        String response = command.send("localhost",1234);
    }

    @Test
    public void send_IOException_WhenIncorrectTriggered() throws IOException {
        // Using Mockito
        final Socket socket = mock(Socket.class);

        when(socket.getOutputStream()).thenThrow(IOException.class);

        Command command = new Command("","") {
            @Override
            protected Socket createSocket() {
                return socket;
            }
        };
        String response = command.send("localhost",1234);
    }

    /*** createSocket ***/

    @Test
    void createSocket_ReturnSocket_WhenCorrectTriggered() {
        Command command = new Command("","");
        Socket s = command.createSocket();
        assertTrue(s.toString().equals("Socket[unconnected]"));
    }

    @Test
    void createSocket_CatchConnectException_WhenServerConnectionRefused(){
        Command command = new Command("","");
        Throwable exception = assertThrows(
            ConnectException.class,
            () -> command.createSocket("localhost",1234)
        );
    }

    /*** getOutput ***/

    @Test
    void getOutput_ReturnDataContent_WhenCorrectTriggered() {
        Command command = new Command("","");
        String data = command.getOutput();
        assertTrue(data.equals("\n"));
    }

    @Test
    void getOutput_BreakForLoop_WhenNullBetweenParameter() {
        Command command = new Command("","a", null, "c");
        String data = command.getOutput();
        assertTrue(data.equals("\na"));
    }


    /*** getCommand ***/

    @Test
    void getCommand_ReturnCommand_WhenCorrectTriggered() {
        Command command = new Command("command","");
        assertTrue(command.getCommand().equals("command"));
    }


    /*** setCommand ***/

    @Test
    void setCommand_SetCommand_WhenCorrectInput() {
        Command command = new Command("","");
        command.setCommand("command");
        assertTrue(command.getCommand().equals("command"));
    }


    /*** getData ***/

    @Test
    void getData_ReturnDataArray_WhenCorrectTriggered() {
        Command command = new Command("","data1", "data2", "data3");
        String[] data = command.getData();
        assertEquals(data.length, 3);
        assertTrue(data[0].equals("data1"));
        assertTrue(data[1].equals("data2"));
        assertTrue(data[2].equals("data3"));
    }


    /*** setData ***/

    @Test
    void setData_SetData_WhenCorrectInput() {
        Command command = new Command("","");
        command.setData(new String[]{"data1", "data2", "data3"});
        String[] data = command.getData();
        assertEquals(data.length, 3);
        assertTrue(data[0].equals("data1"));
        assertTrue(data[1].equals("data2"));
        assertTrue(data[2].equals("data3"));
    }
}