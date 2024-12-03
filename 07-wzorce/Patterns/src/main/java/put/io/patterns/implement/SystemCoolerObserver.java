package put.io.patterns.implement;

public class SystemCoolerObserver implements SystemStateObserver {

    SystemState state;

    public void runCooler()
    {
        // Increase CPU cooling if the temperature is to high
        if (state.getCpuTemp() > 60.00){
            System.out.println("> Increasing cooling of the CPU...");
        }
    }

    public void update(SystemMonitor monitor){
        this.state = monitor.getLastSystemState();
        runCooler();
    }
}