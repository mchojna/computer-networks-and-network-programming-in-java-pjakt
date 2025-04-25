import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class Task3Handler implements Runnable{
    private UDPHandler handler;
    private String message;
    private InetAddress addres;
    private int port;

    public Task3Handler(DatagramSocket socket, DatagramPacket packet, String message) {
        handler = new UDPHandler(socket);
        this.message = message;
        this.addres = packet.getAddress();
        this.port = packet.getPort();
    }

    @Override
    public void run() {
        int sum = 0;
        int index = 0;
        Random random = new Random();
        String good = "Good 200 OK";
        String bad = "Wrong 500";
        List<Message> messagesToSend = new ArrayList<Message>();
        while (sum != message.length()){
            int range = random.nextInt(message.length() - sum) + 1;
            Message toSend = new Message(index, range, message.substring(sum, sum+range));
            messagesToSend.add(toSend);
            index++;
            sum+=range;
            if(sum != message.length()) {
                String randomString = generateRandomString(random.nextInt(120) + 1);
                toSend = new Message(index, randomString.length(), randomString);
                messagesToSend.add(toSend);
                index++;
            }
        }
        try {
            for (Message message : messagesToSend) {
                handler.sendMessage(addres, port, message);
            }
        } catch (Exception e) {
            System.out.printf("Błąd podczas wysyłania wiadomości do klienta - %s\n", addres.getHostName());
        }

        TreeMap<Integer, String> messages = new TreeMap<>();
        for (int i = 0; i < (index+1)/2; i++) {
            try {
                Message received = handler.getMessage();
                messages.put(received.index, received.message);
            }catch (IOException e) {
                System.out.printf("Błąd podczas obierania wiadomości od klienta - %s\n", addres.getHostName());
            }
        }

        StringBuilder sb = new StringBuilder();
        for(String mess : messages.values()){
            sb.append(mess);
        }

        try {
            Message answer;
            if(sb.toString().equals(message))
                answer = new Message(index, good.length(), good);
            else
                answer = new Message(index, bad.length(), bad);

            handler.sendMessage(addres, port, answer);
        } catch (Exception e) {
            System.out.printf("Błąd podczas wysyłania wiadomości do klienta - %s\n", addres.getHostName());
        }
        handler.close();
    }

    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        String CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHAR_SET.length());
            sb.append(CHAR_SET.charAt(randomIndex));
        }
        return sb.toString();
    }
}
