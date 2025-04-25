package zad6;

public class Main {
    public static final int PORT = 0;

    public static void main(String[] args){
        Server server = new Server(PORT);
        System.out.println(server.getMessage());
        server.close();
    }
}
