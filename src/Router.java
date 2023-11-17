public class Router {
    private Semaphore semaphore;
    private boolean[] isConnected;

    public Router(int maxDevices) {
        isConnected = new boolean[maxDevices];
        semaphore = new Semaphore(maxDevices);
    }

    public void occupy(Device device) {
        semaphore.wait(device);
    }

    public boolean[] getIsConnected() {
        return isConnected;
    }

    public void release(Device device) {
        semaphore.signal(device, this);
    }
}
