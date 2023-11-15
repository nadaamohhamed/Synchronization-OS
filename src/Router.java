public class Router {
    private int maxDevices;
    private Device[] connections;
    private Semaphore semaphore;
    private int ptr = 0;

    Router(int maxDevices) {
        this.maxDevices = maxDevices;
        semaphore = new Semaphore(maxDevices);
        connections = new Device[maxDevices];
    }
    public void occupy(Device device) {
        System.out.println("(" + device.getDeviceName() + ") (" + device.getType() + ") arrived");
        semaphore.Wait(device);
        System.out.println("Connection " + (ptr + 1) + ": " + device.getDeviceName() + " occupied.");
        device.connect(this);
        connections[ptr] = device;
        device.setConnectionID(ptr + 1);
        ptr = (ptr + 1) % maxDevices;
        device.start();
    }
    public void release(Device device){
        connections[device.getConnectionID() - 1] = null;
        device.disconnect();
        semaphore.Signal();
    }
}
