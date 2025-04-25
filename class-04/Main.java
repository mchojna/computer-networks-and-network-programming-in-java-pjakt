import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        int port = 9005;

        TCPServer server = new TCPServer(port);
        System.out.println(server.getMessage());
        server.close();


    }
}