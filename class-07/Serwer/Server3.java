import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Random;

public class Server3 implements Runnable{
    private DatagramSocket socket;

    public Server3(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true){
            int number = random.nextInt(7)+2;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < number; i++) {
                int word = random.nextInt(arr.length);
                sb.append(arr[word]).append(" ");
            }
            sb.append("!");
            byte[] buffer = new byte[1];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(packet);
                if(new String(buffer, 0, 1).equals("\n")) {
                    DatagramSocket clientSocket = getSocket();
                    if(clientSocket != null)
                        new Thread(new Task3Handler(clientSocket, packet, sb.toString().trim())).start();
                }
            } catch (IOException e) {
                System.out.printf("Błędna przesłana wiadomość od klienta - %s\n", packet.getAddress().getHostName());
            }
        }
    }

    private DatagramSocket getSocket(){
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.setSoTimeout(300);
            return datagramSocket;
        } catch (SocketException e) {
            System.out.printf("Błąd podczas tworzenia socket - %s\n", e.getMessage());
        }
        return null;
    }

    private String[] arr = {
            "apple",
            "banana",
            "cherry",
            "dragonfruit",
            "elephant",
            "falcon",
            "grapefruit",
            "honeydew",
            "igloo",
            "jungle",
            "kangaroo",
            "lemon",
            "mountain",
            "nectarine",
            "orange",
            "penguin",
            "quartz",
            "raspberry",
            "strawberry",
            "tiger",
            "umbrella",
            "violet",
            "watermelon",
            "xylophone",
            "yacht",
            "zebra"
    };
}
