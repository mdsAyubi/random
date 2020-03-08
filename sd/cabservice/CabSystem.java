import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CabSystem {
    public static void main(String[] args) {

    }
}

class Trip {
    Customer customer;
    Cab cab;
    PaymentMethod paymentMethod;
    String id;
    Location pickUp;
    Location drop;
    TripStatus status;
}

enum TripStatus {
    NOT_STARTED, ON_THE_WAY, COMPLETED, CANCELLED
}

class Customer {
    String name;
    double rating;
    String phoneNumber;
}

enum PaymentMethod {
    CASH, CARD, UPI
}

class Cab {
    String id;
    CabType type;
    Location location;
    Driver driver;
    Trip currentTrip;
}

enum CabType {
    MINI, MICRO
}

class Driver {
    String name;
    double rating;
    String phoneNumber;
}

class Location {
    double latitude;
    double longitude;
}

class BookingService {
    CabFinder cabFinder;
    CabSelector cabSelector;
    TripManager tripManager;
    CabManager cabManager;

    public Trip bookTrip(BookingRequest bookingRequest) {
        List<Cab> cabs = cabFinder.findCabs(bookingRequest);
        Cab cab = cabSelector.selectCab(cabs);

        if (cab == null) {
            return null;
        }

        Trip trip = tripManager.createTrip(bookingRequest, cab);
        cabManager.updateCabWithTrip(cab, trip);

        return trip;
    }

    public void cancelTrip(Cab cab, Trip trip) {
        tripManager.cancelTrip(trip);
        cabManager.updateCabForCancellation(cab);
    }
}

class BookingRequest {
    Customer customer;
    CabType cabType;
    Location pickUpLocation;
    Location dropLocation;
    LocalDateTime pickUpTime;
}

class CabFinder {

    public List<Cab> findCabs(BookingRequest bookingRequest) {
        return null;
    }

}

class CabSelector {

    CabConnectionServiceManager cabServiceConnection;
    ExecutorService executor = Executors.newFixedThreadPool(10); // 10 cabs nearby maybe

    public Cab selectCab(List<Cab> cabs) {
        List<CabRequestTask> tasks = cabs.stream().map(cab -> {
            CabConnectionService cabService = cabServiceConnection.getCabService(cab);
            return new CabRequestTask(cabService, cab);
        }).collect(Collectors.toList());
        Cab cab = null;
        try {
            cab = executor.invokeAny(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return cab;
    }

}

class CabRequestTask implements Callable<Cab> {

    CabConnectionService cabService;
    Cab cab;

    public CabRequestTask(CabConnectionService cabService, Cab cab) {
        this.cabService = cabService;
        this.cab = cab;
    }

    @Override
    public Cab call() throws Exception {
        return cabService.requestCofirmation(cab);
    }

}

class TripManager {

    public Trip createTrip(BookingRequest bookingRequest, Cab cab) {
        return null;
    }

    public void cancelTrip(Trip trip) {
    }

}

class CabManager {

    CabConnectionServiceManager cabConnectionServiceManager;

    public void updateCabWithTrip(Cab cab, Trip trip) {
        CabConnectionService cabConnectionService = cabConnectionServiceManager.getCabService(cab);
        cabConnectionService.updateCabWithTrip(cab, trip);
    }

    public void updateCabForCancellation(Cab cab) {
        CabConnectionService cabConnectionService = cabConnectionServiceManager.getCabService(cab);
        cabConnectionService.updateCabForCancellation(cab);
    }

}

class CabConnectionService {

    public void updateCabWithTrip(Cab cab, Trip trip) {
        cab.currentTrip = trip;
    }

    public void updateCabForCancellation(Cab cab) {
        cab.currentTrip = null;
    }

    public Cab requestCofirmation(Cab cab) {
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // wait for the driver to confirm
        return cab;
    }

}

class CabConnectionServiceManager {

    public CabConnectionService getCabService(Cab cab) {
        return null;
    }

}