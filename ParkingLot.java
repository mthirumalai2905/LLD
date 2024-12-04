import java.util.*;

class ParkingLot {
    private int totalSlots;
    private Map<String, Vehicle> parkedVehicles;
    private PriorityQueue<Integer> availableSlots;
    private Map<VehicleType, Integer> typeCapacity;

    public ParkingLot(int slots) {
        totalSlots = slots;
        parkedVehicles = new HashMap<>();
        availableSlots = new PriorityQueue<>();
        typeCapacity = new HashMap<>();
        
        for (int i = 1; i <= slots; i++) {
            availableSlots.offer(i);
        }
        
        typeCapacity.put(VehicleType.CAR, slots / 2);
        typeCapacity.put(VehicleType.TRUCK, slots / 4);
        typeCapacity.put(VehicleType.BIKE, slots / 4);
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        if (!hasAvailableSlot(vehicle.getType())) {
            throw new ParkingFullException("No slots available for " + vehicle.getType());
        }

        int slotNumber = availableSlots.poll();
        Ticket ticket = new Ticket(vehicle.getNumber(), slotNumber);
        parkedVehicles.put(vehicle.getNumber(), vehicle);
        
        return ticket;
    }

    public void unparkVehicle(Ticket ticket) {
        if (!parkedVehicles.containsKey(ticket.getVehicleNumber())) {
            throw new InvalidTicketException("Invalid ticket");
        }

        parkedVehicles.remove(ticket.getVehicleNumber());
        availableSlots.offer(ticket.getSlotNumber());
    }

    private boolean hasAvailableSlot(VehicleType type) {
        return availableSlots.size() > 0 && 
               parkedVehicles.values().stream()
               .filter(v -> v.getType() == type).count() < typeCapacity.get(type);
    }
}

class Vehicle {
    private String number;
    private VehicleType type;

    public Vehicle(String number, VehicleType type) {
        this.number = number;
        this.type = type;
    }

    public String getNumber() { return number; }
    public VehicleType getType() { return type; }
}

class Ticket {
    private String vehicleNumber;
    private int slotNumber;
    private long entryTime;

    public Ticket(String vehicleNumber, int slotNumber) {
        this.vehicleNumber = vehicleNumber;
        this.slotNumber = slotNumber;
        this.entryTime = System.currentTimeMillis();
    }

    public String getVehicleNumber() { return vehicleNumber; }
    public int getSlotNumber() { return slotNumber; }
}

enum VehicleType {
    CAR, TRUCK, BIKE
}

class ParkingFullException extends RuntimeException {
    public ParkingFullException(String message) {
        super(message);
    }
}

class InvalidTicketException extends RuntimeException {
    public InvalidTicketException(String message) {
        super(message);
    }
}
