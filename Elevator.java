enum Direction {
    UP, DOWN, IDLE;
}

class Request {
    int currentFloor;
    int desiredFloor;
    Direction direction;

    public Request(int currentFloor, int desiredFloor, Direction direction) {
        this.currentFloor = currentFloor;
        this.desiredFloor = desiredFloor;
        this.direction = direction;
    }
}

class Elevator {
    private int currentFloor;
    private Direction direction;
    private List<Request> requests;

    public Elevator(int currentFloor) {
        this.currentFloor = currentFloor;
        this.direction = Direction.IDLE;
        this.requests = new ArrayList<>();
    }

    public void addRequest(Request request) {
        requests.add(request);
        processRequests();
    }

    private void processRequests() {
        for (Request request : requests) {
            moveTo(request.desiredFloor);
            System.out.println("Elevator moving to floor: " + request.desiredFloor);
            currentFloor = request.desiredFloor;
        }
        requests.clear(); 
        direction = Direction.IDLE; 
    }

    private void moveTo(int floor) {
        if (floor > currentFloor) {
            direction = Direction.UP;
            System.out.println("Moving UP");
        } else if (floor < currentFloor) {
            direction = Direction.DOWN;
            System.out.println("Moving DOWN");
        }
    }

    public int getCurrentFloor() {
        return currentFloor;
    }
}

class ElevatorSystem {
    private List<Elevator> elevators;

    public ElevatorSystem(int numberOfElevators) {
        elevators = new ArrayList<>();
        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new Elevator(0)); 
        }
    }

    public void requestElevator(int fromFloor, int toFloor) {
        if (!elevators.isEmpty()) {
            Elevator elevator = elevators.get(0);
            elevator.addRequest(new Request(fromFloor, toFloor, fromFloor < toFloor ? Direction.UP : Direction.DOWN));
        } else {
            System.out.println("No elevators available.");
        }
    }
}

public class ElevatorApp {
    public static void main(String[] args) {
        ElevatorSystem elevatorSystem = new ElevatorSystem(2); 

        elevatorSystem.requestElevator(0, 5); 
        elevatorSystem.requestElevator(3, 1); 
    }
}
