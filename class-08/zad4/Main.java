package zad4;

public class Main {

    private static final String HOST = "";
    private static final int PORT = 0;

    public static void main(String[] args){
        Client client = new Client(HOST, PORT);
        System.out.println(client.getMessage());
        client.close();
    }
}
