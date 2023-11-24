import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

class Router{
    public static volatile Queue<Integer>connections;

    public Router(int numberOfConnections){
       connections = new LinkedList<>();
       for(int i = 1; i <= numberOfConnections; i++){
           connections.add(i);
       }
    }

    public synchronized int occupy() throws Exception {
       if(connections.isEmpty()){
           throw new Exception("No Connections Left!");
       }else{
           return connections.remove();
       }
    }

    public synchronized void release(int connection){
       connections.add(connection);
    }

}

class Semaphore {
    protected int value = 0;

    protected Semaphore() {
        value = 0;
    }

    protected Semaphore(int initial) {
        value = initial;
    }

    public synchronized void P(String deviceName, String deviceType) {
        String field = "(" + deviceName + ") (" + deviceType + ")";
        value--;
        if (value < 0) {
            System.out.println(field + " arrived and waiting");
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println(field + " arrived");
        }
    }

    public synchronized void V() {
        value++;
        if (value <= 0) {
            notify();
        }
    }
}

class Device extends Thread {
    private String name;
    private String type;
    private Semaphore semaphore;
    private int workingTime;
    private Router router;

    public Device(String name, String type, Semaphore semaphore, Router router, int workingTime) {
        this.name = name;
        this.type = type;
        this.semaphore = semaphore;
        this.router = router;
        this.workingTime = workingTime;
    }

    @Override
    public void run() {
        int connectionNumber = -1;
        semaphore.P(name, type);
        try {
            connectionNumber = router.occupy();
            System.out.println("Connection " + connectionNumber + ": " + name + " Occupied");
            System.out.println("Connection " + connectionNumber + ": " + name + " Login");
            Thread.sleep(1000);
            System.out.println("Connection " + connectionNumber + ": " + name + " performs online activity");
            Thread.sleep(workingTime);
            System.out.println("Connection " + connectionNumber + ": " + name + " logged out");
        } catch (Exception e) {
            e.printStackTrace();
        }
        semaphore.V();
        router.release(connectionNumber);
    }
}


public class Network {
    public static void main(String[] args) {

        int  numOfConnections, numOfDevices;
        Scanner input = new Scanner(System.in);

        System.out.println("What is number of WI-FI Connections?");
        numOfConnections = input.nextInt();

        System.out.println("What is number of devices Clients want to connect?");
        numOfDevices = input.nextInt();

        Router router = new Router(numOfConnections);
        Semaphore semaphore = new Semaphore(numOfConnections);
        ArrayList<Device> devices = new ArrayList<>();

        for (int i = 0; i < numOfDevices; i++) {
            String name = input.next(), type = input.next();
            devices.add(new Device(name, type, semaphore, router, new Random().nextInt(5000 - 2000) + 2000));
        }
        try {
            System.setOut(new PrintStream(new BufferedOutputStream((new FileOutputStream("output.txt"))), true));
        }
        catch (FileNotFoundException e){

        }
        for (int i = 0; i < numOfDevices; i++) {
            devices.get(i).start();
        }
    }
}