package org.example.models;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.PriorityQueue;

@Slf4j
@Data
public class Elevator {
    private Long id;
    private State state;
    private Direction direction;
    private int currentFloor;
    private int capacity;
    private int numberOfFloors;
    private final PriorityQueue<Request> requestQueueForUP = new PriorityQueue<>(Comparator.comparingInt(Request::getDestinationFloor));

    private final PriorityQueue<Request> requestQueueForDOWN = new PriorityQueue<>((o1, o2) -> o2.getDestinationFloor() - o1.getDestinationFloor());

    public Elevator(Long id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.currentFloor = 0;
        this.state = State.IDLE;
        this.direction = Direction.UP;
        this.numberOfFloors = 10;
    }

    public synchronized void addRequest(Request request) {
        log.info("Thread " + Thread.currentThread().getName() + " is processing request from floor " +
                request.getSourceFloor() + " to floor " + request.getDestinationFloor());


        boolean requestAdded = this.currentFloor > request.getDestinationFloor() ?
                requestQueueForDOWN.add(request) : requestQueueForUP.add(request);

        if (!requestAdded) {
            throw new IllegalStateException("Error while adding request to the Queue");
        }
        notifyAll(); // Notify the thread waiting on the queue
    }

    public void run() {
        processRequests();
    }

    private void processRequests() {
        while (true) {
            synchronized (this) {
                while (requestQueueForDOWN.isEmpty() && requestQueueForUP.isEmpty()) {
                    try {
                        System.out.println("Queue is empty. Elevator is idle at floor " + this.currentFloor);
                        wait(); // Wait until a new request is added
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.err.println("Elevator thread interrupted: " + e.getMessage());
                        return;
                    }
                }
            }

            Request request = requestQueueForUP.poll();
            boolean isUp = true;
            synchronized (this) {
                if(this.direction == Direction.UP && !requestQueueForUP.isEmpty()){
                    request = requestQueueForUP.poll();
                }
                else if(!requestQueueForDOWN.isEmpty()){
                    request = requestQueueForDOWN.poll();
                    isUp = false;
                }
                log.info("request fetched from queue : " + request);
            }

            assert request != null;
            processRequest(request , (isUp ? requestQueueForUP : requestQueueForDOWN));

        }
    }

    private void processRequest(Request request , PriorityQueue<Request> requestQueue) {
        int startFloor = this.currentFloor;
        int endFloor = request.getDestinationFloor();

        this.direction = (startFloor <= endFloor) ? Direction.UP : Direction.DOWN;
        this.state = State.MOVING;

        while (this.currentFloor != endFloor) {
            sleepThread(1000); // Simulate 1 second delay while moving between floors

            this.currentFloor  = this.direction == Direction.UP ? this.currentFloor+1 : this.currentFloor-1;
            System.out.println("Elevator " + this.id + " is at floor " + this.currentFloor);

            synchronized (this) {
                Request topRequest = requestQueue.peek();
                if ((topRequest != null && topRequest.getDestinationFloor() == this.currentFloor)) {
                    requestQueue.poll(); // Remove the processed request
                    log.info("request removed from queue insider : " + topRequest);
                    processCurrentFloorRequest();
                }
            }
        }

        synchronized (this) {
            processCurrentFloorRequest();
        }

        this.state = State.IDLE; // Set state to IDLE after completing the request
        System.out.println("Elevator " + this.id + " has arrived at floor " + this.currentFloor);
    }

    private void processCurrentFloorRequest() {
        System.out.println("Elevator " + this.id + " door is opening at floor " + this.currentFloor);
        sleepThread(3000); // Simulate door opening time
        System.out.println("Elevator " + this.id + " door is closing at floor " + this.currentFloor);
    }

    private void sleepThread(int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
