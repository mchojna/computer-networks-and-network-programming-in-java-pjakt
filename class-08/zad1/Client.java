package zad1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;

    public Client() {
        try {
            this.socket = new Socket("172.21.40.125", 15000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMessage() {
        StringBuffer sb = new StringBuffer();
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

    public void sendMessage(String message) {
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
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
