package zad7;

public class Main {
    private static final int PORT = 0;
    public static void main(String[] args){
        new Thread(new Server(PORT)).start();
    }
}
