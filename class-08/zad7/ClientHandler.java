package zad7;

import java.net.Socket;

public class ClientHandler implements Runnable {
    private TCPHandler tcpHandler;

    public ClientHandler(Socket socket){
        this.tcpHandler = new TCPHandler(socket);
    }

    @Override
    public void run() {
        this.tcpHandler.sendMessage("Server hello\n");
        this.tcpHandler.getMessage();
        this.tcpHandler.getMessage();
        this.tcpHandler.close();
    }
}
