import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer implements Runnable {
    private final DatagramSocket socket;

    public UDPServer(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        while(true){
            byte[] buffer = new byte[12];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try{
                socket.receive(packet);
                new Thread(new UDPTaskHandler(socket, packet)).start();
            } catch (IOException e) {
                System.out.printf("Błędna przesłana wiadomość od klienta - %s\n", packet.getAddress().getHostName());
            }
        }
    }
}
