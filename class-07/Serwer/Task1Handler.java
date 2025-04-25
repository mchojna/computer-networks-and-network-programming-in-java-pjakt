import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Task1Handler implements Runnable{
    private UDPHandler handler;
    private String message;
    private InetAddress addres;
    private int port;

    public Task1Handler(DatagramSocket socket, DatagramPacket packet, String message) {
        handler = new UDPHandler(socket);
        this.message = message;
        this.addres = packet.getAddress();
        this.port = packet.getPort();
    }

    @Override
    public void run() {
        Message toSend = new Message(0, message.length(), message);
        try {
            handler.sendMessage(addres, port, toSend);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.printf("Błąd podczas wysyłania zapytania do klienta %s\n", addres.getHostName());
        }
    }
}
