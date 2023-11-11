public class Router {
    private int maxDevices;
    private boolean[] isConnected;
    private Semaphore semaphore;

    Router(int maxDevices) {
        this.maxDevices = maxDevices;
        semaphore = new Semaphore(maxDevices);
        isConnected = new boolean[maxDevices];
    }
    public synchronized void occupy(Device device) {
        for (int i = 0; i < maxDevices; i++) {
            if(!isConnected[i]){
                device.setConnectionID(i+1);
                isConnected[i] = true;
                break;
            }
        }
    }

    public synchronized void release(Device device){
        isConnected[device.getConnectionID() - 1] = false;
        notify();
        System.out.println("Connection " + device.getConnectionID() + ": " + device.getDeviceName() + " Logged out");
    }
    public synchronized void arrived(Device device){
        System.out.println("(" + device.getDeviceName() + ") " +" (" + device.getType() + ") " +" arrived");
    }
}
