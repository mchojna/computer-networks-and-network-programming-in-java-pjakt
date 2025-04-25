import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPTaskHandler implements Runnable {
    private final UDPHandler handler;
    private final InetAddress address;
    private final int port;
    private final DatagramPacket packet;

    public UDPTaskHandler(DatagramSocket socket, DatagramPacket packet) {
        handler = new UDPHandler(socket);
        this.address = packet.getAddress();
        this.port = packet.getPort();
        this.packet = packet;
    }

    @Override
    public void run() {
        try {
            String received = new String(packet.getData(), 0, packet.getLength());
            if(received.equals("CCS DISCOVER")){
                byte[] toSend = ("CCS FOUND").getBytes();
                handler.sendMessage(address, port, toSend);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
