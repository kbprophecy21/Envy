package io.kyle.inventory.service.inventory;

import java.util.ArrayList;
import java.util.List;

import io.kyle.inventory.domain.inventory.Movement;

public class InventoryService {


    private final List<Movement> ledger = new ArrayList<>();




    public void receive(String itemNumber, String toLocation, int qty, String note) {};


    public void pick(String itemNumber, String fromLocation, int qty, String note) {}; // Must throw error if not enough.  

    public void transfer(String itemNumber, String fromLocation, String toLocation, int qty, String note) {}; // Must throw error if not enough.

    public void adjust(String itemNumber, String location, int delta, String note) {}; // delta can +/-.

    public void cycleCount(String itemNumber, String location, int countedQty, String note) {}; // Writes the difference.

    public int availableAt(String itemNumber, String location) {};

    public int onHandFor(String itemNumber) {};

    public Map<String, Map<String,Integer>> currentOnHand() {};


    


}
