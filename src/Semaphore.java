class Semaphore {
    private int value;
    public Semaphore(int value) {
        this.value = value;
    }
    public synchronized void wait(Device device) {
        value--;
        if (value < 0) {

        }
        else{

        }


    }
    public synchronized void signal() {
        value++;
        if (value <= 0)
            notify() ;
    }
}
