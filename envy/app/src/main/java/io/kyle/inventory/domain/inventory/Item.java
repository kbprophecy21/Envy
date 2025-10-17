package io.kyle.inventory.domain.inventory;

public class Item {


    //=====Fields ======//

    private String itemNumber;
    private String itemName;
    private String itemCategory;
    private String unit;
    private int reorderPoint;
    private String itemDescription;
    private String itemImage; // !!!Not sure about the data type here



    // ---------Constructors-----------------//

    public Item() {}; // No arg contructor for testing I believe.


    public Item(String itemNumber, String itemName, String itemCategory, String unit, int reorderPoint, String itemDescription) 
    {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.unit = unit;
        this.reorderPoint = reorderPoint;
        this.itemDescription = itemDescription;
    };
    //---------------------------------------------------------------------------//

    //----------------Getter & Setters ------------------------------//

    public String getItemNumber() {return itemNumber;};
    public void setItemNumber(String itemNumber){this.itemNumber = itemNumber;};

    public String getItemName() {return itemName;};
    public void setItemName(String itemName) {this.itemName = itemName;};

    public String getItemCategory() {return itemCategory;};
    public void setItemCategory(String itemCategory){this.itemCategory = itemCategory;};

    public String getUnit(){return unit;};
    public void setUnit(String unit) {this.unit = unit;};

    public int getReorderPoint(){return reorderPoint;};
    public void setReorderPoint(int reorderPoint){this.reorderPoint = reorderPoint;};

    public String getItemDescription(){return itemDescription;};
    public void setItemDescription(String itemDescription){this.itemDescription = itemDescription;};

    //---------------------------------------------------------------//


    







}
