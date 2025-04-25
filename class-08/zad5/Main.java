package zad5;

public class Main {
    private static final String HOST = "";
    private static final int PORT = 0;
    private static final String MESSAGE = "";

    public static void main(String[] args){
        Client client = new Client(HOST, PORT);
        System.out.println(client.getMessage());
        client.sendMessage(MESSAGE);
        client.close();
    }
}
