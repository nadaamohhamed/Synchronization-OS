public class Device extends Thread{
    private Router router;
    private  String deviceName;
    private  String type;
    private  int connectionID;

    public Device(String name, String type, Router router) {
        deviceName = name;
        this.type = type;
        this.router = router;
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


    public void activity(){

    }
}
