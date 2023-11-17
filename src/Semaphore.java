class Semaphore {
    private int value;

    Semaphore(int value) {
        this.value = value;
    }

    public synchronized void wait(Device device) {
        value--;
        if (value < 0) {
            try {
                System.out.println("(" + device.getDeviceName() + ") (" + device.getType() + ") arrived and waiting");
                wait();
            } catch (InterruptedException e) {
            }
        }
        else
            System.out.println("(" + device.getDeviceName() + ") " + "(" + device.getType() + ") arrived");

        for (int i = 0; i < device.getRouter().getIsConnected().length; i++) {
            if(!device.getRouter().getIsConnected()[i]){
                device.getRouter().getIsConnected()[i] = true;
                device.setConnectionID(i+1);
                System.out.println("Connection " + (i+1) + ": (" + device.getDeviceName() + ") Occupied");
                break;
            }
        }
    }

    public synchronized void signal(Device device, Router router) {
        router.getIsConnected()[device.getConnectionID() - 1] = false;
        System.out.println("Connection " + device.getConnectionID() + ": " + device.getDeviceName() + " logged out");
        value++;
        if (value <= 0)
            notify();
    }
}
