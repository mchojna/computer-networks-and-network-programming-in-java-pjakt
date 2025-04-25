import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPHandler {
    private final Socket socket;

    public TCPHandler(Socket socket) {
        this.socket = socket;
    }

    public String getMessage() throws IOException {
        byte[] buffer = new byte[1];
        StringBuilder message = new StringBuilder();
        InputStream stream = this.socket.getInputStream();
        while (stream.read(buffer, 0 ,1) != -1) {
            String c = new String(buffer, 0, 1);
            message.append(c);
            if(c.equals("\n"))
                break;
        }
        return message.toString();
    }

    public void sendMessage(String message) throws IOException {
        byte[] buffer = message.getBytes();
        OutputStream stream = this.socket.getOutputStream();
        stream.write(buffer);
        stream.flush();
    }

    public void close() {
        try {
            this.socket.close();
        } catch (IOException e) {
            System.out.printf("Błąd podczas próby zakończenia połączenia - %s\n", e.getMessage());
        }
    }

    public boolean isOpened(){
        return !this.socket.isClosed();
    }
}
