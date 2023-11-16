class Semaphore {
    private int value;
    public Semaphore(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public synchronized void Wait () {
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
            notify();
    }
}
