package put.io.patterns.implement;

public class SystemInfoObserver implements SystemStateObserver {

    SystemState state;

    public void printMonitor()
    {
        System.out.println("============================================");
        System.out.println(String.format("CPU Load: %2.2f%%", this.state.getCpu()));
        System.out.println(String.format("CPU temperature: %.2f C", this.state.getCpuTemp()));
        System.out.println(String.format("Available memory: %.2f MB", this.state.getAvailableMemory()));
        System.out.println(String.format("USB devices: %d", this.state.getUsbDevices()));
    }

    public void update(SystemMonitor monitor){
        this.state = monitor.getLastSystemState();
        printMonitor();
    }
}
