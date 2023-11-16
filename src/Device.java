public class Device extends Thread {

    private Router router;
    private  String deviceName;
    private  String type;
    private int connectionID;

    public Device(String name, String type, Router router) {
        deviceName = name;
        this.type = type;
        this.router = router;
        connectionID = 1;
    }

    public int getConnectionID() {
        return connectionID;
    }

    @Override
    public void run() {
        try {
            connectionID = router.occupy(this);
            System.out.println("Connection " + connectionID + ": " + deviceName + " logged in");
            Thread.sleep(100);
            System.out.println("Connection " + connectionID + ": (" + deviceName + ") (" + type + ") performs online activity");
            Thread.sleep(100);
            router.release(this);
        } catch (InterruptedException e) {
        }
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getType() {
        return type;
    }
}
