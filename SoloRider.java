import java.util.*;

class Passenger {
    String name;
    int x, y;

    Passenger(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}

class Vehicle {
    String vid;
    int x, y;

    Vehicle(String vid, int x, int y) {
        this.vid = vid;
        this.x = x;
        this.y = y;
    }
}

public class SoloRider {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        List<Passenger> passengers = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String name = scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            passengers.add(new Passenger(name, x, y));
        }

        for (int i = 0; i < M; i++) {
            String vid = scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            vehicles.add(new Vehicle(vid, x, y));
        }

        int totalDistance = calculateMinDistance(passengers, vehicles);
        System.out.println(totalDistance);
    }

    private static int calculateMinDistance(List<Passenger> passengers, List<Vehicle> vehicles) {
        int totalDistance = 0;

        Collections.sort(passengers, Comparator.comparing(p -> p.name));

        for (Passenger passenger : passengers) {
            Vehicle closestVehicle = findClosestVehicle(passenger, vehicles);
            totalDistance += calculateDistance(closestVehicle, passenger);
            vehicles.remove(closestVehicle);
        }

        return totalDistance;
    }

    private static Vehicle findClosestVehicle(Passenger passenger, List<Vehicle> vehicles) {
        Vehicle closest = null;
        int minDistance = Integer.MAX_VALUE;

        for (Vehicle vehicle : vehicles) {
            int distance = calculateDistance(vehicle, passenger);
            if (distance < minDistance || (distance == minDistance && vehicle.vid.compareTo(closest.vid) < 0)) {
                minDistance = distance;
                closest = vehicle;
            }
        }

        return closest;
    }

    private static int calculateDistance(Vehicle vehicle, Passenger passenger) {
        return Math.abs(vehicle.x - passenger.x) + Math.abs(vehicle.y - passenger.y);
    }
}