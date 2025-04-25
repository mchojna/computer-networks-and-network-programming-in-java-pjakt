import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient3 {
    private Socket socket;

    public TCPClient3(String host, int port) {
        try {
            this.socket = new Socket(host, port);
        } catch (IOException e) {
            System.out.printf("Blad podczas nawiazywania polaczenia - %s\n", e.getMessage());
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

                if(sb.toString().contains("File sent, disconnecting...")){
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
        } catch (IOException e) {
            System.out.printf("Blad podczas proby zamykania polaczenia - %s\n", e.getMessage());
        }
    }
}
