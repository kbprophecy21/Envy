package io.kyle.inventory.service.inventory;

import java.beans.Transient;

import io.kyle.inventory.domain.inventory.MovementType;
import io.kyle.inventory.service.inventory.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class InventoryServiceTest {
    

    private InventoryService service;

    @BeforeEach
    public void setUp() {
        service = new InventoryService();
    }

    @Test 
    void testReceiveAddsStock() {
        service.receive("913380", "A213", 750, "Test receive");
        assertEquals(750, service.availableAt("913380", "A213"));
    };


    @Test
    void testTransferMovesStock() {
        service.receive("913380", "A213", 750, "Test receive");
        service.transfer("913380", "A213", "B456", 300, "Test transfer");
        assertEquals(450, service.availableAt("913380", "A213"));
        assertEquals(300, service.availableAt("913380", "B456"));
    };


    @Test
    void testPickReducesStock() {
        service.receive("913380", "A213", 750, "Test receive");
        service.pick("913380", "A213", 200, "Test pick");
        assertEquals(550, service.availableAt("913380", "A213"));
    };

} 