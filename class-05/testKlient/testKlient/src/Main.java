import java.io.IOException;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
                Client client = new Client(new Socket("172.21.40.125", 15000));

        client.sendMessage("s29758 Bi5r3x4FCh\n");

        // zadanie 1
        {
            String message = "";

            message = client.getMessage().trim();
            System.out.println(message);

            String response = String.join("", message.split(" ")) + "\n";
            System.out.println(response);
            client.sendMessage(response);

            message = client.getMessage();
            System.out.println(message);
        }

        // zadanie 2
        {
            String message1 = client.getMessage().trim();
            System.out.println(message1);

            String message2 = client.getMessage().trim();
            System.out.println(message2);

            String message3 = client.getMessage().trim();
            System.out.println(message3);

            String message4 = client.getMessage().trim();
            System.out.println(message4);

            String response = String.join("", message1, message2, message3, message4, "\n");

            System.out.println(response);
            client.sendMessage(response);

            System.out.println(client.getMessage());

        }

        // zadanie 3
        {
            String[] message = new String[8];
            String[] response = new String[4];

            int j = 0;
            for(int i = 0; i < message.length; i++){
                message[i] = client.getMessage().trim();
                System.out.println(message[i]);

                if(i % 2 != 0){
                    response[j] = message[i-1] + message[i] + "\n";
                    j++;
                }

            }

            for(int i = 0; i < response.length; i++){
                System.out.println(response[i]);
                client.sendMessage(response[i]);
            }

            System.out.println(client.getMessage());
        }

        // zadanie 4
        {
            String k = client.getMessage().trim();
            System.out.println(k);
            String[] message = new String[Integer.parseInt(k) * 2];

            int j = 0;
            for(int i = 0; i < message.length; i++){
                message[i] = client.getMessage().trim();
                System.out.println(i + ": " + message[i]);

                if(i+1 > Integer.parseInt(k)){
                    String response = message[j] + message[i] + "\n";
                    System.out.println(response);
                    client.sendMessage(response);
                    j++;
                }
            }

            System.out.println(client.getMessage());
        }

        // zadanie 5
        {
//            12 odpowiedzi
//            int i = 0;
//            while(true){
//                System.out.println(i++);
//                client.getMessage();
//            }

        }

    }
}
