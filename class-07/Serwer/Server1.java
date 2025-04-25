import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server1 implements Runnable{
    private DatagramSocket socket;

    public Server1(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        while (true){
            byte[] buffer = new byte[1];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(packet);
                new Thread(new Task1Handler(socket, packet, "Good Answer")).start();
            } catch (IOException e) {
                System.out.printf("Błędna przesłana wiadomość od klienta - %s\n", packet.getAddress().getHostName());
            }
        }
    }
}
