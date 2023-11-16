import java.util.ArrayList;
import java.util.Scanner;

public class Network {
    public static void main(String[] args) {
        int  numOfConnections, numOfDevices;
        Scanner input = new Scanner(System.in);

        System.out.println("What is number of WI-FI Connections?");
        numOfConnections = input.nextInt();

        System.out.println("What is number of devices Clients want to connect?");
        numOfDevices = input.nextInt();

        Router router = new Router(numOfConnections);
        ArrayList<Device> devices = new ArrayList<>();

        for (int i = 0; i < numOfDevices; i++) {
            String name = input.next(), type = input.next();
            devices.add(new Device(name, type, router));
        }
        for (int i = 0; i < numOfDevices; i++) {
            devices.get(i).start();
        }
    }
}