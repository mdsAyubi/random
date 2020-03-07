import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class ElevatorSystem {
    public static void main(String[] args) {

    }
}

class ElevatorManager {
    List<Elevator> elevators;
    ElevatorSelector elevatorSelector;

    public ElevatorManager(List<Elevator> elevators, ElevatorSelector elevatorSelector) {
        this.elevators = elevators;
        this.elevatorSelector = elevatorSelector;
    }

    public void process(ElevatorRequest elevatorRequest) {
        Optional<Elevator> elevator = Optional.empty();
        while (elevator.isEmpty()) {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                // TODO: handle exception
            }

            elevator = elevatorSelector.selectElevator(elevators, elevatorRequest);
        }

        if (elevator.isPresent()) {
            elevator.get().addRequest(elevatorRequest);
        }
    }

    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }
}

class Elevator {
    RequestScheduler requestScheduler;
    int currentFloor;
    ElevatorState elevatorState;
    AtomicBoolean isActive;
    AtomicBoolean isGateOpen;
    List<Button> floorButtons;

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public Elevator(int currentFloor, ElevatorState elevatorState, AtomicBoolean isActive,
            RequestScheduler requestScheduler, List<Button> floorButtons, AtomicBoolean isGateOpen) {
        this.requestScheduler = requestScheduler;
        this.currentFloor = currentFloor;
        this.elevatorState = elevatorState;
        this.isActive = isActive;
        this.isGateOpen = isGateOpen;
        this.floorButtons = floorButtons;

        scheduledExecutorService.schedule(() -> {
            try {
                this.move();
            } catch (Exception e) {
            }

        }, 1, TimeUnit.SECONDS);

    }

    public void move() throws InterruptedException

    {
        if (isActive.get() && elevatorState == ElevatorState.IDLE) {
            ElevatorRequest elevatorRequest = requestScheduler.getNext();
            if (elevatorRequest != null) {
                this.openElevator();
                Thread.sleep(5 * 1000); // open gate for 5s
                this.closeElevator();
                elevatorState = currentFloor < elevatorRequest.toFloor ? ElevatorState.MOVING_UP
                        : ElevatorState.MOVING_DOWN;
                Thread.sleep(5 * 1000); // elevator moving
                currentFloor = elevatorRequest.toFloor;
                this.openElevator();
                Thread.sleep(5 * 1000); // open gate for 5s
                this.closeElevator();
            }
            elevatorState = ElevatorState.IDLE;
        }
    }

    public void addRequest(ElevatorRequest elevatorRequest) {
        requestScheduler.schedule(elevatorRequest);
    }

    public void markActive() {
        isActive.set(true);
    }

    public void markInactive() {
        isActive.set(false);
    }

    public void openElevator() {
        isGateOpen.set(true);
    }

    public void closeElevator() {
        isGateOpen.set(false);
    }

}

interface Button {
    void onButtonPress();
}

class FloorButton implements Button {
    Elevator elevator;
    int floor;

    FloorButton(Elevator elevator, int floor) {
        this.elevator = elevator;
    }

    @Override
    public void onButtonPress() {
        elevator.addRequest(new ElevatorRequest(elevator.currentFloor, floor));
    }
}

class CallElevatorButton implements Button {

    int toFloor;
    int currentFloor;
    ElevatorManager elevatorManager;

    public CallElevatorButton(int currentFloor, int toFloor, ElevatorManager elevatorManager) {
        this.toFloor = toFloor;
        this.currentFloor = currentFloor;
        this.elevatorManager = elevatorManager;
    }

    @Override
    public void onButtonPress() {
        elevatorManager.process(new ElevatorRequest(currentFloor, toFloor));
    }

}

enum ElevatorState {
    MOVING_UP, MOVING_DOWN, IDLE, OUT_OF_SERVICE
}

class ElevatorRequest {
    int toFloor;
    int fromFloor;

    ElevatorRequest(int fromFloor, int toFloor) {
        this.fromFloor = fromFloor;
        this.toFloor = toFloor;
    }
}

interface RequestScheduler {
    void schedule(ElevatorRequest elevatorRequest);

    ElevatorRequest getNext();
}

class FCFSScheduler implements RequestScheduler {

    Queue<ElevatorRequest> queue = new LinkedBlockingDeque<>();

    @Override
    public void schedule(ElevatorRequest elevatorRequest) {
        queue.offer(elevatorRequest);
    }

    @Override
    public ElevatorRequest getNext() {
        return queue.poll();
    }

}

interface ElevatorSelector {
    public Optional<Elevator> selectElevator(List<Elevator> elevators, ElevatorRequest elevatorRequest);
}

class IdleElevatorSelector implements ElevatorSelector {
    public Optional<Elevator> selectElevator(List<Elevator> elevators, ElevatorRequest elevatorRequest) {
        return elevators.stream().filter(e -> e.elevatorState == ElevatorState.IDLE).findFirst();
    }
}
