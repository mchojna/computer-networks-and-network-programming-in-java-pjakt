import java.net.SocketException;

public class CCS {
    public static void main(String[] args) {
        int port = getPort(args);

        try {
            new Thread(new UDPServer(port)).start();
            new Thread(new TCPServer(port)).start();
        } catch (SocketException e) {
            System.out.printf("Błąd podczas uruchamiania serwera UDP - %s\n", e.getMessage());
        }
    }

    public static int getPort(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Podaj dokładnie jeden argument");
        }
        try {
            int port = Integer.parseInt(args[0]);
            if (port <= 0 || port > 65535) {
                throw new IllegalArgumentException("Numer portu musi być w zakresie 1-65535.");
            }
            return port;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Argument nie jest poprawną liczbą całkowitą.");
        }
    }
}
