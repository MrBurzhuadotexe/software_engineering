package put.io.patterns.implement;

public class USBDeviceObserver implements SystemStateObserver {

    SystemState state;
    int usb;

    public void countUSB() {
        // Increase CPU cooling if the temperature is to high
        if (this.state.getUsbDevices() !=  this.usb){
            System.out.println("> Number of USB devices has been changed!");
            this.usb = this.state.getUsbDevices();
        }
    }

    public USBDeviceObserver(int usb) {
        this.usb = usb;
    }

    public void update(SystemMonitor monitor){
        this.state = monitor.getLastSystemState();
        countUSB();
    }
}
