public class  Device extends Thread{
    private Router router;
    private  String deviceName;
    private  String type;
    private  int connectionID;
    public Device(String name, String type) {
        deviceName = name;
        this.type = type;
        connectionID = 1;
    }
    public String getDeviceName() {
        return deviceName;
    }

    public String getType() {
        return type;
    }
    public int getConnectionID() {
        return connectionID;
    }
    public void setConnectionID(int connectionID) {
        this.connectionID = connectionID;
    }
    public void run(){
        // idle for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.out.println("Connection " + this.getConnectionID() + ": " + this.getDeviceName() + " Logged in");
        activity();
        System.out.println("Connection " + this.getConnectionID() + ": " + this.getDeviceName() + " Logged out");
        router.release(this);
    }
    public void connect(Router router){
        this.router = router;
    }
    public void disconnect(){
        this.router = null;
    }
    public void activity(){
        // idle for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.out.println("- Connection " + this.getConnectionID() + ": " + this.getDeviceName() + " performs online activity");
        // idle for 1 second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
    }
}
