class Semaphore {
    private int value;
    public Semaphore(int value) {
        this.value = value;
    }
    public synchronized void Wait (Device device) {
        value--;
        if (value < 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
    }
    public synchronized void Signal() {
        value++;
        if (value <= 0)
            notify() ;
    }
}
