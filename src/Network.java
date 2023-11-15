import java.util.ArrayList;
import java.util.Scanner;

public class Network {
    public static void main(String[] args) {
        int  numOfConnections, numOfDevices;
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the number of WI-FI connections? ");
        numOfConnections = scanner.nextInt();
        ArrayList<Device> devices = new ArrayList<>();
        Router router = new Router(numOfConnections);
        System.out.println("What is the number of devices Clients want to connect? ");
        numOfDevices = scanner.nextInt();
        for(int i = 0; i < numOfDevices; i++){
            String name, type;
            name = scanner.next();
            type = scanner.next();
            devices.add(new Device(name, type));
        }
        for(int i = 0; i < numOfDevices; i++){
            router.occupy(devices.get(i));
        }
    }
}
