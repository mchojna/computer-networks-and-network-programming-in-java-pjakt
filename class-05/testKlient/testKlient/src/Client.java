import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLOutput;

public class Client {

    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public String getMessage() {
        byte[] buffer = new byte[1];
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream = socket.getInputStream();

            while(inputStream.read(buffer, 0, 1) != -1) {
                String c = new String(buffer, 0, 1);

                if(c.equals("\n")){
                    break;
                }

                stringBuilder.append(c);
            }


        } catch (IOException e) {
            System.out.println("Blad przy odbieraniu");
        }

        return stringBuilder.toString();
    }

    public void sendMessage(String message) {
        byte[] buffer = message.getBytes();
        try {
            OutputStream outputStream = socket.getOutputStream();

            outputStream.write(buffer);
            outputStream.flush();

        } catch (IOException e) {
            System.out.println("Blad przy wysylaniu");
        }
    }

    public void close(){
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Blad przy zamykaniu");
        }
    }
}
