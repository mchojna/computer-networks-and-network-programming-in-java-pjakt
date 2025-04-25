import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task2Handler implements Runnable{
    private UDPHandler handler;
    private String message;
    private InetAddress addres;
    private int port;

    public Task2Handler(DatagramSocket socket, DatagramPacket packet, String message) {
        handler = new UDPHandler(socket);
        this.message = message;
        this.addres = packet.getAddress();
        this.port = packet.getPort();
    }

    @Override
    public void run() {
        String[] words = message.split(" ");
        String good = "Good 200 OK";
        String bad = "Wrong 500";
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            Message toSend = new Message(i, words[i].length(), words[i]);
            messages.add(toSend);
        }
        Collections.shuffle(messages);

        try {
            for (Message value : messages) {
                handler.sendMessage(addres, port, value);
            }
        } catch (Exception e) {
            System.out.printf("Błąd podczas wysyłania do klienta - %s\n", addres.getHostName());
        }

        try {
            Message received = handler.getMessage();
            Message answer;
            if(received.message.equals(message))
                answer = new Message(words.length, good.length(), good);
            else
                answer = new Message(words.length, bad.length(), bad);

            handler.sendMessage(addres, port, answer);
        } catch (IOException e) {
            System.out.printf("Błąd podczas obierania wiadomości od klienta - %s\n", addres.getHostName());
        } catch (Exception e) {
            System.out.printf("Błąd podczas wysyłania wiadomości do klienta - %s\n", addres.getHostName());
        }
        handler.close();
    }
}
