import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    private Socket socket;

    public TCPClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
    }

    public String getMessage(){
        StringBuilder sb = new StringBuilder();
        byte[] bytes = new byte[1];
        try {
            InputStream input = socket.getInputStream();
            while (input.read(bytes) != -1) {
                String message = new String(bytes);
                if (message.equals("\n"))
                    break;
                sb.append(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public void sendMessage(String message){
        byte[] bytes = message.getBytes();
        try {
            OutputStream output = socket.getOutputStream();
            output.write(bytes);
            output.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close(){
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
