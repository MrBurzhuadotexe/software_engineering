package put.io.patterns.implement;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.util.ArrayList;
import java.util.List;

public class SystemMonitor implements SystemStateObserver{

    private SystemInfo si;

    private HardwareAbstractionLayer hal;

    private OperatingSystem os;

    private SystemState lastSystemState = null;

    private List<SystemStateObserver> observers = new ArrayList<SystemStateObserver>();

    public SystemMonitor(){
        si = new SystemInfo();
        hal = si.getHardware();
        os = si.getOperatingSystem();

    }

    public static void main(){
        // tworzymy monitor
        SystemMonitor monitor = new SystemMonitor();

        // tworzymy obserwatora i dodajemy do monitora
        SystemStateObserver infObserver1 =  new SystemInfoObserver();
        SystemStateObserver infObserver2 =  new SystemCoolerObserver();
        SystemStateObserver infObserver3 =  new SystemGarbageCollectorObserver();
        SystemStateObserver infObserver4 =  new USBDeviceObserver(2);
        monitor.addSystemStateObserver(infObserver1);
        monitor.addSystemStateObserver(infObserver2);
        monitor.addSystemStateObserver(infObserver3);
        monitor.addSystemStateObserver(infObserver4);

        while (true) {
            monitor.probe();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void probe(){
        double cpuLoad = hal.getProcessor().getSystemLoadAverage(1)[0];
        double cpuTemp = hal.getSensors().getCpuTemperature();
        double memory = hal.getMemory().getAvailable() / 1000000;
        int usbDevices = hal.getUsbDevices(false).size();

        lastSystemState = new SystemState(cpuLoad, cpuTemp, memory, usbDevices);
        notifyObservers();
    }

    public void update(SystemMonitor monitor){
        monitor.getLastSystemState();
    }

    public void addSystemStateObserver(SystemStateObserver observer){
        this.observers.add(observer);
    }

    public void removeSystemStateObserver(SystemStateObserver observer){
        this.observers.remove(observer);
    }

    public void notifyObservers(){
        for(SystemStateObserver observer : this.observers){
            observer.update(this);
        }
    }


    public SystemState getLastSystemState() {
        return lastSystemState;
    }
}