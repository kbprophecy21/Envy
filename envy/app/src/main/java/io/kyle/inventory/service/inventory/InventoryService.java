package io.kyle.inventory.service.inventory;

import java.util.HashMap;
import java.util.Map;

import io.kyle.inventory.domain.inventory.Movement;
import io.kyle.inventory.domain.inventory.MovementType;

public class InventoryService {


    private final List<Movement> ledger = new ArrayList<>();

    public Movement movement;
    


    public void receive(String itemNumber, String toLocation, int qty, String note) {};


    public void pick(String itemNumber, String fromLocation, int qty, String note) {}; // Must throw error if not enough.  

    public void transfer(String itemNumber, String fromLocation, String toLocation, int qty, String note) {}; // Must throw error if not enough.

    public void adjust(String itemNumber, String location, int delta, String note) {}; // delta can +/-.

    public void cycleCount(String itemNumber, String location, int countedQty, String note) {}; // Writes the difference.

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



    private static void add(Map<String, Integer> locMap, String location, int delta) {
        if (location == null || location.isBlank() || delta == 0) return;
        locMap.merge(location, delta, Integer::sum);
    };

    private static String target(Movement m) {
        String to = m.getToLocation();
        return (to != null && !to.isBlank()) ? to : m.getFromLocation();
    }


    


}
