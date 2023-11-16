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
    }

    public synchronized void signal(Device device) {
        System.out.println("Connection " + device.getConnectionID() + ": " + device.getDeviceName() + " logged out");
        value++;
        if (value <= 0)
            notify();
    }
}
