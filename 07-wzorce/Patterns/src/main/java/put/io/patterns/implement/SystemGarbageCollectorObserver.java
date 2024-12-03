package put.io.patterns.implement;

public class SystemGarbageCollectorObserver implements SystemStateObserver {

    SystemState state;

    public void garbageCollector()
    {
        // Run garbage collector when out of memory
        if (state.getAvailableMemory() < 200.00){
            System.out.println("> Running garbage collector...");
        }
    }

    public void update(SystemMonitor monitor){
        this.state = monitor.getLastSystemState();
        garbageCollector();
    }
}
