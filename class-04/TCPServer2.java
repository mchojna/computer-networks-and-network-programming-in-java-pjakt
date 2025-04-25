import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer2 implements Runnable {
    private int port;

    public TCPServer2(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ClientHandler(socket)).start();
            }

        } catch (IOException e) {
            System.out.printf("Błąd podczas uruchamiania serwera - %s\n", e.getMessage());
        }
    }
}
