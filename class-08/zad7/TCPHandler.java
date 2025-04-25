package zad7;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPHandler {
    private Socket socket;

    public TCPHandler(Socket socket){
        this.socket = socket;
    }
    public TCPHandler(String host, int port){
        try {
            this.socket = new Socket(host, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public String getMessage(){
        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[1];
        try {
            InputStream is = socket.getInputStream();
            while(is.read(buffer) != -1){
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

    public void close(){
        try {
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
