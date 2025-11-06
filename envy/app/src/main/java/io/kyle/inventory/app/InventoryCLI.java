package io.kyle.inventory.app;

import io.kyle.inventory.service.InventoryService;
import io.kyle.inventory.domain.inventory.MovementType;

import java.util.Scanner;


public class InventoryCLI {

    private final InventoryService service;

    private final Scanner in = new Scanner(System.in);



    public InventoryCLI(InventoryService service) {
        this.service = service;
    };


    public void start() {
        println("=====Envy The Warehouse Management System======");
        help();
        while (true) {
            print("\n");
            String line = in.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");
            String cmd = parts[0].toLowerCase();
            try {
                switch (cmd) {
                    case "help" -> help();
                    case "quit", "exit" -> {println("Closing Envy..."); return;}
                    case "receive" -> cmdReceive(parts);
                    case "move" -> cmdMove(parts);
                    case "ship" -> cmdShip(parts);
                    case "stock" -> cmdStock(parts);
                    case "items" -> cmdItems();
                    case "movements" -> cmdMovements();
                    default -> println("Unknown command: " + cmd + ". Type 'help' for a list of commands.");       
                    };
                } catch (IllegalArgumentException e) {
                    println("Error: " + e.getMessage());
            } catch (Exception e) {
                    println("An unexpected error occurred: " + e.getMessage());
                    e.printStackTrace();
                }

        };
    };


    private void help() {
        println("Available commands:");
        println("  help                     - Show this help message");
        println("  receive <item> <qty>     - Receive new stock");
        println("  move <item> <qty>        - Move stock within the warehouse");
        println("  ship <item> <qty>        - Ship stock out of the warehouse");
        println("  stock <item>             - Check stock level for an item");
        println("  items                    - List all items in inventory");
        println("  movements                - List all inventory movements");
        println("  quit | exit              - Exit the application");
    };


    private void cmdReceive(String[] parts) {
        if (parts.length < 4) {
            throw new IllegalArgumentException("Usage: receive <Item Number> <Location> <Quantity> [notes]");
        }
        String itemNumber = parts[1];
        String location = parts[2];
        int qty = parseQuantity(parts[3]);
        String note = (parts.length > 4) ? joinFrom(parts, 4) : "CLI Receive";
        service.receive(itemNumber, location, qty, note);
        println("Received " + qty + " of item " + itemNumber + " to location " + location + "."); 
    };

}
