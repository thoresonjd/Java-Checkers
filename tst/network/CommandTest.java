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
        assertEquals("Socket[unconnected]", s.toString());
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
        assertEquals("\n", data);
    }

    @Test
    void getOutput_BreakForLoop_WhenNullBetweenParameter() {
        Command command = new Command("","a", null, "c");
        String data = command.getOutput();
        assertEquals("\na", data);
    }


    /*** getCommand ***/

    @Test
    void getCommand_ReturnCommand_WhenCorrectTriggered() {
        Command command = new Command("command","");
        assertEquals("command", command.getCommand());
    }


    /*** setCommand ***/

    @Test
    void setCommand_SetCommand_WhenCorrectInput() {
        Command command = new Command("","");
        command.setCommand("command");
        assertEquals("command", command.getCommand());
    }


    /*** getData ***/

    @Test
    void getData_ReturnDataArray_WhenCorrectTriggered() {
        Command command = new Command("","data1", "data2", "data3");
        String[] data = command.getData();
        assertEquals(data.length, 3);
        assertEquals("data1", data[0]);
        assertEquals("data2", data[1]);
        assertEquals("data3", data[2]);
    }


    /*** setData ***/

    @Test
    void setData_SetData_WhenCorrectInput() {
        Command command = new Command("","");
        command.setData(new String[]{"data1", "data2", "data3"});
        String[] data = command.getData();
        assertEquals(data.length, 3);
        assertEquals("data1", data[0]);
        assertEquals("data2", data[1]);
        assertEquals("data3", data[2]);
    }
}