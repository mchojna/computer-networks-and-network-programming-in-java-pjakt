import java.net.Socket;

public class ClientHandler implements Runnable {
    private TCPHandler handler;

    public ClientHandler(Socket socket) {
        this.handler = new TCPHandler(socket);
    }

    @Override
    public void run() {
        handler.sendMessage("Server hello\n");
        System.out.println(handler.getMessage());
        System.out.println(handler.getMessage());
        handler.close();
    }
}
