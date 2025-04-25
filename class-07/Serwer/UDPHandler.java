import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPHandler {
    private DatagramSocket socket;

    public UDPHandler(DatagramSocket socket) {
        this.socket = socket;
    }

    public Message getMessage() throws IOException {
        Message message;
        byte[] buffer = new byte[129];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        int index = Byte.toUnsignedInt(buffer[0]);
        int size = Byte.toUnsignedInt(buffer[1]);
        message = new Message(index, size, new String(buffer, 2, size));
        return message;
    }

    public void sendMessage(InetAddress address, int port, Message message) throws Exception {
        if(message.size > 127)
            throw new Exception("Incorrect size");
        if(message.index > 127)
            throw new Exception("Incorrect index");

        byte[] buffer = new byte[message.size+2];
        buffer[0] = (byte) message.index;
        buffer[1] = (byte) message.size;
        byte[] chars = message.message.getBytes();
        for (int i = 2; i < buffer.length; i++) {
            buffer[i] = chars[i-2];
        }

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
    }

    public void close(){
        socket.close();
    }
}
