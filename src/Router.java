import java.util.LinkedList;
import java.util.Queue;

public class Router {
    private int maxDevices;
    private Device[] connections;
    private Queue<Device> waitingDevices;
    private Semaphore semaphore;
    private int ptr = 0;

    Router(int maxDevices) {
        this.maxDevices = maxDevices;
        semaphore = new Semaphore(maxDevices);
        connections = new Device[maxDevices];
        waitingDevices = new LinkedList<>();
    }

    public Queue<Device> getWaitingDevices() {
        return waitingDevices;
    }
    public void occupy(Device device) {
        if(device == null)
            return;

        if(semaphore.getValue() == 0){
            System.out.println("(" + device.getDeviceName() + ") (" + device.getType() + ") arrived and waiting.");
            waitingDevices.add(device);
        }
        else{
            System.out.println("(" + device.getDeviceName() + ") (" + device.getType() + ") arrived.");
            run(device);
        }
    }
    public void run(Device device){
        semaphore.Wait();
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
        if(!waitingDevices.isEmpty())
            run(waitingDevices.poll());
    }
}
