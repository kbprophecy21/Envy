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

                case IN -> {}

                case OUT -> {}

                case TRANSFER -> {}

                case ADJUST -> {}

                case CYCLE_COUNT -> {}

            }
        };

        return onHand;
    };


    


}
