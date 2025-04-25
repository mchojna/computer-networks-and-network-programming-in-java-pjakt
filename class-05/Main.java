import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            TCPClient client  = new TCPClient("172.21.40.125", 15000);
            client.sendMessage("pecarz bRe2ozNcBS\n");

            //Task 1
            String message = client.getMessage();
            String[] arr = message.split(" ");
            client.sendMessage(arr[0]+arr[1]+arr[2]+"\n");
            System.out.println(client.getMessage());


            //Task 2
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                sb.append(client.getMessage().trim());
            }
            client.sendMessage(sb.toString()+"\n");
            System.out.println(client.getMessage());

            //Task 3
            arr = new String[8];
            for (int i = 0; i < 8; i++) {
                arr[i] = client.getMessage().trim();
            }
            for (int i = 0; i < 8; i+=2) {
                client.sendMessage(arr[i]+arr[i+1]+"\n");
            }
            System.out.println(client.getMessage());

            //Task 4
            int k = Integer.parseInt(client.getMessage().trim());
            arr = new String[k];
            for (int i = 0; i < k; i++) {
                arr[i] = client.getMessage().trim();
            }
            for (int i = 0; i < k; i++) {
                String num = client.getMessage().trim();
                client.sendMessage(arr[i]+num+"\n");
            }
            System.out.println(client.getMessage());

            //Task 5
            /*for (int i = 0; i < 1000; i++) {
                client.getMessage();
                System.out.println(i);
            }*/
            k = 12;
            arr = new String[k];
            for (int i = 0; i < k; i++) {
                arr[i] = client.getMessage().trim();
            }
            for (int i = 0; i < k; i++) {
                String num = client.getMessage().trim();
                client.sendMessage(arr[i]+num+"\n");
            }
            System.out.println(client.getMessage());
            client.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}