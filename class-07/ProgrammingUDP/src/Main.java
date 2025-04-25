import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        try {
//            DatagramSocket socket = new DatagramSocket();
//            InetAddress address = InetAddress.getByName("10.22.34.24");
//            int port = 9000;
//
//            // Sending
//            byte[] buf = "a".getBytes();
//            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
//            socket.send(packet);
//
//            // Receiving
//            buf = new byte[129];
//            packet = new DatagramPacket(buf, buf.length);
//            socket.receive(packet);
//            int id = (int) buf[0];
//            int size = buf[1];
//            System.out.println(new String(buf, 2, size));
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("10.22.34.24");
            int port = 9001;

            // Sending
            byte[] buf = "\n".getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
            socket.send(packet);
            
            // Receiving
            buf = new byte[1400];
            int finalIndex = Integer.MAX_VALUE;
            int counter = 0;
            Map<Integer, String> map = new HashMap<>();

            while (finalIndex + 1 != counter) {
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                int id = (int) buf[0];
                int size = (int) buf[1];
                String message = new String(buf, 2, size);

                if(message.endsWith("!")){
                    finalIndex = id;
                }

                counter++;
                map.put(id, message);
            }

            int desc = packet.getPort();

            StringBuilder answer = new StringBuilder();
            for(Map.Entry<Integer, String> entry : map.entrySet()) {
                answer.append(entry.getValue()).append(" ");
            }
            answer.delete(answer.length() - 1, answer.length());

            System.out.println(answer);

            buf = new byte[answer.toString().getBytes().length + 2];
            buf[0] = 9;
            buf[1] = (byte) answer.toString().getBytes().length;
            int i = 2;

            for(byte b: answer.toString().getBytes()) {
                buf[i++] = b;
            }

            packet = new DatagramPacket(buf, buf.length, address, desc);
            socket.send(packet);

//          Receiving
            buf = new byte[1400];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            int id = (int) buf[0];
            int size = buf[1];
            System.out.println(new String(buf, 2, size));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
