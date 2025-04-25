import java.io.IOException;
import java.net.Socket;

public class TCPTaskHandler implements Runnable {
    private final String address;
    private final String port;

    private final TCPHandler handler;
    private final TCPStatisticsHandler statisticsHandler;
    private static final String pattern = "^(ADD|SUB|MUL|DIV) -?\\d+ -?\\d+$";

    public TCPTaskHandler(Socket socket, TCPStatisticsHandler statistics) {
        this.address = socket.getInetAddress().getHostAddress();
        this.port = String.valueOf(socket.getPort());
        this.handler = new TCPHandler(socket);
        this.statisticsHandler = statistics;

        System.out.println(address + " " + port);
    }

    @Override
    public void run() {
        statisticsHandler.changeClientStatistics(this.address, this.port);
        try {
            while (handler.isOpened()) {
                String received = handler.getMessage().trim();
                if(received.isEmpty()){
                    throw new IOException();
                }
                String toSend = "";
                if (received.matches(pattern)) {
                    try {
                        String operation = received.split(" ")[0];
                        int argument1 = Integer.parseInt(received.split(" ")[1]);
                        int argument2 = Integer.parseInt(received.split(" ")[2]);
                        switch (operation) {
                            case "ADD": {
                                statisticsHandler.changeStatistics(operation, 1);
                                toSend = String.valueOf(argument1 + argument2);
                                break;
                            }
                            case "SUB": {
                                statisticsHandler.changeStatistics(operation, 1);
                                toSend = String.valueOf(argument1 - argument2);
                                break;
                            }
                            case "MUL": {
                                statisticsHandler.changeStatistics(operation, 1);
                                toSend = String.valueOf(argument1 * argument2);
                                break;
                            }
                            case "DIV": {
                                statisticsHandler.changeStatistics(operation, 1);
                                toSend = String.valueOf(argument1 / argument2);
                                break;
                            }
                            default: {
                                toSend = "ERROR";
                            }
                        }
                    } catch (NumberFormatException | ArithmeticException e1) {
                        toSend = "ERROR";
                    }
                } else {
                    toSend = "ERROR";
                }

                if(toSend.equals("ERROR")){
                    statisticsHandler.changeStatistics("INVALID_OPERATIONS", 1);
                } else {
                    statisticsHandler.changeStatistics("VALID_OPERATIONS", 1);
                    statisticsHandler.changeStatistics("SUM", Integer.parseInt(toSend));
                }

                handler.sendMessage(toSend + "\n");
                System.out.println("[RECEIVED]: " + received + "\t[SENT]: " + toSend);
            }
        } catch (IOException ignored) {
        } finally {
            handler.close();
        }
    }
}
