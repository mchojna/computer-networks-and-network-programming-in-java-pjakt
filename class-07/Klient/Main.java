import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            UDPHandler handler = new UDPHandler(socket);

            //Task 1
            init(9000, socket);
            System.out.println(handler.getMessage().message);

            //Task 2
            init(9001, socket);
            boolean flag = true;
            boolean exclamation = false;
            Set<Integer> requiredKeys = new HashSet<>();
            TreeMap<Integer, String> messages = new TreeMap<>();
            int port = -1;
            while (flag){
                Message message = handler.getMessage();
                port = message.port;
                messages.put(message.index, message.message);
                if(message.message.endsWith("!")){
                    exclamation = true;
                    requiredKeys = IntStream.rangeClosed(0, message.index).boxed().collect(Collectors.toSet());
                }
                if(exclamation)
                    if(messages.keySet().containsAll(requiredKeys))
                        flag = false;
            }

            StringBuilder sb = new StringBuilder();
            for (String mess : messages.values())
                sb.append(mess).append(" ");

            String toSend = sb.toString().trim();
            Message message = new Message(0, toSend.length(), toSend);
            handler.sendMessage(InetAddress.getByName("localhost"), port, message);
            System.out.println(handler.getMessage().message);


            //Task3
            init(9002, socket);
            flag = true;
            exclamation = false;
            requiredKeys = new HashSet<>();
            messages = new TreeMap<>();
            port = -1;
            while (flag){
                message = handler.getMessage();
                port = message.port;
                if(message.index % 2 == 0) {
                    messages.put(message.index, message.message);
                    if (message.message.endsWith("!")) {
                        exclamation = true;
                        requiredKeys = IntStream.rangeClosed(0, message.index).filter(i -> i % 2 == 0).boxed().collect(Collectors.toSet());
                    }
                }
                if (exclamation)
                    if (messages.keySet().containsAll(requiredKeys))
                        flag = false;
            }

            int index = 0;
            for (String mess : messages.values()) {
                message = new Message(index, mess.length(), mess);
                handler.sendMessage(InetAddress.getByName("localhost"), port, message);
                index++;
            }
            System.out.println(handler.getMessage().message);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw  new RuntimeException(e);
        }
    }

    private static void init(int port, DatagramSocket socket){
        byte[] buffer = "\n".getBytes();
        try {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), port);
            socket.send(packet);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}