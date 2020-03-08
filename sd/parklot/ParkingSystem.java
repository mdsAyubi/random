import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ParkingSystem {
    public static void main(String[] args) {

    }
}

class ParkingManager {
    CostCalculator costCalculator;
    SlotManager slotManager;
    TicketManager ticketManager;
    PaymentManager paymentManager;

    public ParkingTicket park(Vehicle vehicle) {
        Slot slot = slotManager.getSlot(vehicle);
        ParkingTicket ticket = ticketManager.getTicket(slot, vehicle);
        return ticket;
    }

    public void unpark(int ticketId) {
        ParkingTicket ticket = ticketManager.getTicketForId(ticketId);
        double amount = costCalculator.getAmountForTicket(ticket);

        paymentManager.payAmount(ticket, amount);
        slotManager.freeSlot(ticket.slot);
        ticketManager.releaseTicket(ticket);
    }
}

class Vehicle {
    String license;
    VehicleType type;
}

class ParkingTicket {

    int ticketId;
    Vehicle vehicle;
    LocalDateTime parkingTime;
    Slot slot;
    boolean isActive;

    public ParkingTicket(int ticketId, Vehicle vehicle, LocalDateTime parkingTime, Slot slot, boolean isActive) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingTime = parkingTime;
        this.slot = slot;
        this.isActive = isActive;
    }

}

enum VehicleType {
    SEDAN, HATCH_BACK
}

class CostCalculator {

    public double getAmountForTicket(ParkingTicket ticket) {
        long hours = ChronoUnit.HOURS.between(ticket.parkingTime, LocalDateTime.now());
        return hours * 25;
    }

}

class SlotManager {

    List<Slot> slots = new ArrayList<>();

    public Slot getSlot(Vehicle vehicle) {
        return null;
    }

    public void freeSlot(Slot slot) {
        slot.vehicle = null;
    }

}

class Slot {
    int id;
    Location Location;
    Vehicle vehicle;

    public Slot(int id, Location location, Vehicle vehicle) {
        this.id = id;
        Location = location;
        this.vehicle = vehicle;
    }

    public boolean isFree() {
        return vehicle == null;
    }
}

class Location {
    int floor;
    String block;
    int spot;

    public Location(int floor, String block, int spot) {
        this.floor = floor;
        this.block = block;
        this.spot = spot;
    }

}

class TicketManager {
    public ParkingTicket getTicket(Slot slot, Vehicle vehicle) {
        return null;
    }

    public void releaseTicket(ParkingTicket ticket) {
        ticket.isActive = false;
    }

    public ParkingTicket getTicketForId(int ticketId) {
        // call Db, get the ticket for id
        return null;
    }
}

class PaymentManager {

    public void payAmount(ParkingTicket ticket, double amount) {

    }

}