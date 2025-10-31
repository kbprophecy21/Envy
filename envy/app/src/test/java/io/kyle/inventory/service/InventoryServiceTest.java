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
    void testReceiveAddsStock() {};


    @Test
    void testTransferMovesStock() {};


    @Test
    void testPickReducesStock() {};

} 