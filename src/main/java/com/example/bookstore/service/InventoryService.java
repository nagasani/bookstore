package com.example.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bookstore.manager.InventoryManager;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;


@Service
public class InventoryService {

    @Autowired
    private InventoryManager inventoryManager;

    @PostConstruct
    public void startInventoryManagement() {
        inventoryManager.manageInventory();
    }

    @PreDestroy
    public void stopInventoryManagement() {
        inventoryManager.shutdown();
    }
}
