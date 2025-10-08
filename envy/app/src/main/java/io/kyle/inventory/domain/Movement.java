package io.kyle.inventory.domain;

import java.time.Instant;
import java.util.UUID;

public class Movement {

    // --- data members ---//
    private String id;
    private String sku;
    private MovementType type;
    private String fromLocation;
    private String toLocation;
    private int quantity;
    private String note;
    private Instant timestamp;
   //-------------------------//
   
   // --- constructors ---//
   public Movement() {}


   public Movement(String sku, MovementType type, String fromLocation, String toLocation, int quantity, String note) {
       this.id = UUID.randomUUID().toString();
       this.sku = sku;
       this.type = type;
       this.fromLocation = fromLocation;
       this.toLocation = toLocation;
       this.quantity = quantity;
       this.note = note;
       this.timestamp = Instant.now();
   }


   //---Getters and Setters---//
   public String getId(){return id;}
   public void setId(String id){this.id = id;}

    public String getSku(){return sku;}
    public void setSku(String sku){this.sku = sku;}

    public MovementType getType(){return type;}
    public void setType(MovementType type){this.type = type;}

    public String getFromLocation(){return fromLocation;}
    public void setFromLocation(String fromLocation){this.fromLocation = fromLocation;}

    public String getToLocation(){return toLocation;}
    public void setToLocation(String toLocation){this.toLocation = toLocation;}


    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity = quantity;}

    public String getNote(){return note;}
    public void setNote(String note){this.note = note;}

    public Instant getTimestamp(){return timestamp;}
    public void setTimestamp(Instant timestamp){this.timestamp = timestamp;}
    //------------------------------------------------------------------------------------//

    @Override
    public String toString() {
        return "Movement{" +
                "id='" + id + '\'' +
                ", sku='" + sku + '\'' +
                ", type=" + type +
                ", fromLocation='" + fromLocation + '\'' +
                ", toLocation='" + toLocation + '\'' +
                ", quantity=" + quantity +
                ", note='" + note + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    
}
