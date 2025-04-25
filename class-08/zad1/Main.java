package zad1;

public class Main {
    public static void main(String[] args){
        Client client = new Client();
        client.sendMessage("s29758 SvNnmxAoSP\n");
        // zad 1
        {
            String s = client.getMessage().trim();
            client.sendMessage(s.replace(" ", "") + "\n");

            System.out.println(client.getMessage());
        }

        // zad 2
        {
            String[] s = new String[4];
            for(int i = 0; i < 4; i++){
                s[i] = client.getMessage().trim();
            }
            client.sendMessage(String.join("", s) + "\n");

            System.out.println(client.getMessage());
        }

        // zad 3
        {
            String[] s = new String[4];
            for(int i = 0 ; i < 4; i++){
                s[i] = client.getMessage().trim() + client.getMessage().trim();
            }
            for(int i = 0 ; i < 4; i++){
                client.sendMessage(s[i] + "\n");
            }

            System.out.println(client.getMessage());
        }

        // zad 4
        {
            int k = 2 * Integer.parseInt(client.getMessage().trim());
            String[] s = new String[k];
            for(int i = 0 ; i < k; i++){
                s[i] = client.getMessage().trim();
            }
            for(int i = 0 ; i < k; i++){
                s[i] = s[i] + client.getMessage().trim();
                client.sendMessage(s[i] + "\n");
            }

            System.out.println(client.getMessage());
        }

        // zad 5
        {
            int k = 12;
            String[] s = new String[k];
            for(int i = 0 ; i < k; i++){
                s[i] = client.getMessage().trim();
            }
            for(int i = 0 ; i < k; i++){
                s[i] = s[i] + client.getMessage().trim();
                client.sendMessage(s[i] + "\n");
            }

            System.out.println(client.getMessage());
        }
        client.close();
    }
}
