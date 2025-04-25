public class Main {
    public static void main(String[] args) {
        //10.22.34.24
        TCPClient client = new TCPClient("localhost", 9007);
        System.out.println(client.getMessage());
        client.sendMessage("Hello server");
        System.out.println(client.getMessage());
        client.close();
    }
}