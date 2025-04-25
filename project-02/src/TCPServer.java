import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable {
    private final int port;
    private final TCPStatisticsHandler statisticsHandler;

    public TCPServer(int port) {
        this.port = port;
        statisticsHandler = new TCPStatisticsHandler();
    }

    @Override
    public void run() {
        new Thread(statisticsHandler).start();
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            while(true){
                Socket socket = serverSocket.accept();
                new Thread(new TCPTaskHandler(socket, statisticsHandler)).start();
            }
        } catch (IOException e) {
            System.out.printf("Błąd podczas uruchamiania serwera TCP - %s\n", e.getMessage());
        }
    }
}
