import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPHandler {
    private final DatagramSocket socket;

    public UDPHandler(DatagramSocket socket) {
        this.socket = socket;
    }

    public void sendMessage(InetAddress address, int port, byte[] message) throws Exception {
        DatagramPacket packet = new DatagramPacket(message, message.length, address, port);
        socket.send(packet);
    }
}
