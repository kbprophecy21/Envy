package io.kyle.inventory.service.inventory;

public class InventoryService {







    public void recieve(String itemNumber, String toLocation, int qty, String note) {};


    public void pick(String itemNumber, String fromLocation, int qty, String note) {}; // Must throw error if not enough.  

    public void transfer(String itemNumber, String fromLocation, String toLocation, int qty, String note) {}; // Must throw error if not enough.

    public void adjust(String itemNumber, String location, int delta, String note) {}; // delta can +/-.

    public void cycleCount(String itemNumber, String location, int countedQty, String note) {}; // Writes the difference.

    public void availableAt(String itemNumber, String location) { 
        // needs to change void to integer(int). this functions needs to return the quanity of that item at that location. 
    };

    public void onHandFor(String itemNumber) {
        // needs to change void to integer(int). this functions needs to return what is on hand for that item number. 
    };

    


}
