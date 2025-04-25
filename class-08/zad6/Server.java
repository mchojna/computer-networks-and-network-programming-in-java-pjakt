package zad6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;

    public Server(int PORT){
        try {
            this.serverSocket = new ServerSocket(PORT);
            this.socket = serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMessage(){
        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[1];
        try {
            InputStream os = socket.getInputStream();
            while(os.read(buffer) != -1){
                String c = new String(buffer);
                if(c.equals("\n")){
                    break;
                }
                sb.append(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public void sendMessage(String message){
        byte[] buffer = message.getBytes();
        try {
            OutputStream os = socket.getOutputStream();
            os.write(buffer);
            os.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close(){
        try {
            this.socket.close();
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
