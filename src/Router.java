public class Router {
    private Semaphore semaphore;
    private boolean[] isConnected;

    public Router(int maxDevices) {
        isConnected = new boolean[maxDevices];
        semaphore = new Semaphore(maxDevices);
    }

    public int occupy(Device device) {
        semaphore.wait(device);
        for (int i = 0; i < isConnected.length; i++) {
            if(!isConnected[i]){
                isConnected[i] = true;
                System.out.println("Connection " + (i+1) + ": (" + device.getDeviceName() + ") Occupied");
                return i + 1;
            }
        }
        return -1;
    }

    public void release(Device device) {
        isConnected[device.getConnectionID() - 1] = false;
        device.setRouter(null);
        semaphore.signal(device);
    }
}
