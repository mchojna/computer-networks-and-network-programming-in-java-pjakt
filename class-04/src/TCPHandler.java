import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//obsluga protokolu tcp
public class TCPHandler {
    private Socket socket;

    // TODO copy TCP protocol implementation akcepacja, get message, sent message
    public TCPHandler(Socket socket){
        this.socket = socket;
    }

    public void sendMessage(String message){
        byte[] buffer = message.getBytes();
        try {
            OutputStream stream = this.socket.getOutputStream();
            stream.write(buffer);
            stream.flush();
        } catch (IOException e) {
            System.out.println("Blad podczas wysyłania wiadomosci: " + e.getMessage());
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
            System.out.println("Blad podczas odczytywania wiadomosci: " + e.getMessage());
        }
        return sb.toString();
    }

    public void close(){
        try {
            this.socket.close();
        } catch (IOException e) {
            System.out.println("Blad podczas zmykania gniazda: " + e.getMessage());
        }
    }
}
