package io.kyle.inventory.service.inventory;

import java.util.HashMap;
import java.util.Map;

import io.kyle.inventory.domain.inventory.Movement;
import io.kyle.inventory.domain.inventory.MovementType;

public class InventoryService {


    private final List<Movement> ledger = new ArrayList<>();

    public Movement movement;
    


    public void receive(String itemNumber, String toLocation, int qty, String note) {
        requireNonBlank(itemNumber, "Item number is required");
        requireNonBlank(toLocation, "To location is required");
        requirePositive(qty, "Quantity must be positive");
        ledger.add(new Movement(itemNumber, MovementType.IN, null, toLocation, qty, note));
    };


    public void pick(String itemNumber, String fromLocation, int qty, String note) {
        requireNonBlank(itemNumber, "Item number is required");
        requireNonBlank(toLocation, "To location is required");
        requirePositive(qty, "Quantity must be positive");
        if (availableAt < qty) throw new IllegalArgumentException("Not enough stock to pick"); // update string later for clarity
    }; // Must throw error if not enough.

    public void transfer(String itemNumber, String fromLocation, String toLocation, int qty, String note) {
        requireNonBlank(itemNumber, "Item number is required");
        requireNonBlank(fromLocation, "From location is required");
        requireNonBlank(toLocation, "To location is required");
        requirePositive(qty, "Quantity must be positive");
        int available = availableAt(itemNumber, fromLocation);
        if (available < qty) throw new IllegalArgumentException("Not enough stock to transfer"); // update string later for clarity
        ledger.add(new Movement(itemNumber, MovementType.TRANSFER, fromLocation, toLocation, qty, note));

    }; // Must throw error if not enough.

    public void adjust(String itemNumber, String location, int delta, String note) {
        requireNonBlank(itemNumber, "Item number is required");
        requireNonBlank(location, "Location is required");
        if (delta == 0) throw new IllegalArgumentException("Adjustment delta cannot be zero");
        ledger.add(new Movement(itemNumber, MovementType.ADJUST, location, location, delta, note));
    }; // delta can +/-.

    public void cycleCount(String itemNumber, String location, int countedQty, String note) {
        requireNonBlank(itemNumber, "Item number is required");
        requireNonBlank(location, "Location is required");
        if (countedQty < 0) throw new IllegalArgumentException("Counted quantity cannot be negative");
        int currentQty = availableAt(itemNumber, location);
        int delta = countedQty - currentQty;
        if (delta != 0) {
            ledger.add(new Movement(itemNumber, MovementType.CYCLE_COUNT, location, location, difference, note));
        }
    }; // Writes the difference.

    public int availableAt(String itemNumber, String location) {
        return currentOnHand().getorDefault(itemNumber, Map.of()).getOrDefault(location, 0);
    };

    public int onHandFor(String itemNumber) {
        return currentOnHand().getOrDefault(itemNumber, Map.of()).values().stream().mapToInt(Integer::intValue).sum();
    };

    public Map<String, Map<String,Integer>> currentOnHand() 
    {

        Map<String, Map<String,Integer>> onHand = new Hashmap<>();

        for (Movement movement : ledger) {

            String itemNumber = movement.getItemNumber();

            onHand.conputeIfAbsent(itemNumber, k -> new HashMap<>());
            
            Map<String, Integer> byLocation = onHand.get(itemNumber);

            switch(movement.getType()) {

                case IN -> {
                    add(byLocation, movement.getToLocation(), movement.getQuantity());
                }

                case OUT -> {
                    add(byLocation, movement.getFromLocation(), movement.getQuantity());
                }

                case TRANSFER -> {
                    add(byLocation, movement.getFromLocation(), -movement.getQuantity());
                    add(byLocation, movement.getToLocation(), movement.getQuantity());
                }

                case ADJUST, CYCLE_COUNT -> {
                    add(byLocation, target(m), movement.getQuantity());
                    
                }

                

            }
        };

        return onHand;
    };


//------------------------Helper Class Methods------------------------//
    private static void add(Map<String, Integer> locMap, String location, int delta) {
        if (location == null || location.isBlank() || delta == 0) return;
        locMap.merge(location, delta, Integer::sum);
    };

    private static String target(Movement m) {
        String to = m.getToLocation();
        return (to != null && !to.isBlank()) ? to : m.getFromLocation();
    };

    private static void requireNonBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        };
    };

    private static void requirePositive(int value, String message) {
        if (value <= 0) {
            throw new IllegalArgumentException(message);
        };
    };


}
