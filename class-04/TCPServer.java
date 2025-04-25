import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private Socket socket;
    private ServerSocket serverSocket;

    public TCPServer(String host, int port) {
        try {
            this.socket = new Socket(host, port);
        } catch (IOException e) {
            System.out.printf("Błąd podczas nawiązywania połączenia - %s\n", e.getMessage());
        }
    }

    public TCPServer(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            this.socket = serverSocket.accept();
        } catch (IOException e) {
            System.out.printf("Błąd podczas uruchamiania serwera - %s\n", e.getMessage());
        }
    }

    public String getMessage() {
        byte[] buffer = new byte[1];
        StringBuilder sb = new StringBuilder();
        try {
            InputStream stream = socket.getInputStream();
            while (stream.read(buffer, 0 ,1) != -1) {
                String c = new String(buffer, 0, 1);
                sb.append(c);
                if(c.equals("\n"))
                    break;
            }
        } catch (IOException e) {
            System.out.printf("Błąd podczas odczytywania wiadomości - %s\n", e.getMessage());
        }
        return sb.toString();
    }

    public void sendMessage(String message) {
        byte[] buffer = message.getBytes();
        try {
            OutputStream stream = socket.getOutputStream();
            stream.write(buffer);
            stream.flush();
        } catch (IOException e) {
            System.out.printf("Błąd podczas wysyłania wiadomości - %s\n", e.getMessage());
        }
    }

    public void close() {
        try {
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.printf("Błąd podczas próby zakończenia połączenia - %s\n", e.getMessage());
        }
    }
}
