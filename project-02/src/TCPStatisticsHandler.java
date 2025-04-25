import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class TCPStatisticsHandler implements Runnable{
    private final ConcurrentHashMap<String, Integer> allStatistics;
    private final ConcurrentHashMap<String, Integer> lastStatistics;

    private final ConcurrentSkipListSet<String> allUniqueAddress;
    private final ConcurrentSkipListSet<String> lastUniqueAddress;

    private final String[] keys;

    public TCPStatisticsHandler(){
        allStatistics = new ConcurrentHashMap<>();
        lastStatistics = new ConcurrentHashMap<>();
        keys = new String[]{"CLIENTS", "VALID_OPERATIONS", "ADD", "SUB", "MUL", "DIV", "INVALID_OPERATIONS", "SUM"};

        for(String key: keys){
            allStatistics.putIfAbsent(key, 0);
            lastStatistics.putIfAbsent(key, 0);
        }

        allUniqueAddress = new ConcurrentSkipListSet<>();
        lastUniqueAddress = new ConcurrentSkipListSet<>();
    }

    public synchronized void changeClientStatistics(String address, String port){
        String client = address + ":" + port;
        allUniqueAddress.add(client);
        lastUniqueAddress.add(client);

        allStatistics.put("CLIENTS", allUniqueAddress.size());
        lastStatistics.put("CLIENTS", lastUniqueAddress.size());
    }

    public synchronized void changeStatistics(String key, int value){
        allStatistics.merge(key, value, Integer::sum);
        lastStatistics.merge(key, value, Integer::sum);
    }

    public synchronized void resetLastStatistics(){
        lastUniqueAddress.clear();
        for(String key: keys){
            lastStatistics.put(key, 0);
        }
    }

    public synchronized void printStatistics(){
        StringBuilder statisticsString = new StringBuilder();
        statisticsString.append("\nALL STATISTICS:\n");
        for(String key: keys){
            statisticsString.append("[").append(key).append("]: ").append(allStatistics.get(key)).append("\n");
        }
        statisticsString.append("\nLAST STATISTICS:\n");
        for(String key: keys){
            statisticsString.append("[").append(key).append("]: ").append(lastStatistics.get(key)).append("\n");
        }
        System.out.println(statisticsString);
    }

    @Override
    public void run() {
        while(true){
            try {
                this.resetLastStatistics();
                Thread.sleep(10000);
                this.printStatistics();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
