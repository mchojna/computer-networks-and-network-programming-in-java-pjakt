import java.net.SocketException;

public class Main {
    public static void main(String[] args) {
        int portTask1 = 9000;
        int portTask2 = 9001;
        int portTask3 = 9002;

        try {
            new Thread(new Server1(portTask1)).start();
        } catch (SocketException e) {
            System.out.printf("port zajęty %d", portTask1);
        }

        try {
            new Thread(new Server2(portTask2)).start();
        } catch (SocketException e) {
            System.out.printf("port zajęty %d", portTask2);
        }

        try {
            new Thread(new Server3(portTask3)).start();
        } catch (SocketException e) {
            System.out.printf("port zajęty %d", portTask3);
        }
    }
}