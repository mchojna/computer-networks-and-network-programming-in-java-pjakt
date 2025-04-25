import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
//        Server1 server = new Server1();
//        System.out.println(server.getMessage());
//        server.close();
        int port = 8888;
        new Thread(new TCPServer(port)).start();

    }
}