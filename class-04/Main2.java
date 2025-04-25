import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main2 {
    public static void main(String[] args) {
        int port = 9005;

        new Thread(new TCPServer2(port)).start();

    }
}