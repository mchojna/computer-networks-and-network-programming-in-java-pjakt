import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // 10.22.34.8:8888
    private final int PORT = 8888;

    ServerSocket serverSocket;
    Socket socket;

    public Server() {
        try {
            this.serverSocket = new ServerSocket(PORT);
            this.socket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Blad podczas uruchamiania serwera - " + e.getMessage());
        }
    }

    public String getMessage(){
        byte[] buffer = new byte[1];
        StringBuilder sb = new StringBuilder();

        try {
            InputStream stream = this.socket.getInputStream();

            while(stream.read(buffer, 0, 1) != -1){
                String c = new String(buffer, 0, 1);
                sb.append(c);

                if(c.equals("\n")){
                    break;
                }
            }

        } catch (IOException e) {
            System.out.printf("Blad podczas odczytywania wiadomosci - %s\n", e.getMessage());
        }
        return sb.toString();
    }

    public void sendMessage(String message){
        byte[] buffer = message.getBytes();
        try {
            OutputStream stream = socket.getOutputStream();
            stream.write(buffer);
            stream.flush();
        } catch (IOException e) {
            System.out.printf("Blad podczas wysyłania wiadomosci - %s\n", e.getMessage());
        }
    }

    public void close() {
        try {
            this.socket.close();
            this.serverSocket.close();
        } catch (IOException e) {
            System.out.printf("Blad podczas proby zamykania polaczenia - %s\n", e.getMessage());
        }
    }
}
