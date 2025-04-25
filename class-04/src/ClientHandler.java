import java.net.Socket;

public class ClientHandler implements Runnable {
    private TCPHandler handler;

    // TODO constructor
    public ClientHandler(Socket socket) {
        this.handler = new TCPHandler(socket);
    }

    @Override
    public void run() {
        // TODO przyjmuje socket i na bazie jego tworzy sie hanlder
        // TODO client flow logic, wykorzystanie hanldera get message, send message, close

        handler.sendMessage("Server hello\n");

        System.out.println(handler.getMessage());
        System.out.println(handler.getMessage());

        handler.close();
    }
}
