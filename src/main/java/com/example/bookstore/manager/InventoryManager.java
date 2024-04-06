package com.example.bookstore.manager;

import java.util.concurrent.*;

public class InventoryManager {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    private ConcurrentHashMap<String, Integer> inventory = new ConcurrentHashMap<>();

    public InventoryManager() {
        // Initialize inventory with sample data
        inventory.put("Book1", 100);
        inventory.put("Book2", 150);
    }

    public void manageInventory() {
        // Start inventory monitoring and other management tasks
        startInventoryMonitoring();
        // You can add more tasks here
    }

    private void startInventoryMonitoring() {
        executorService.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // Monitoring logic
                inventory.forEach((bookId, quantity) -> {
                    if (quantity < 50) {
                        System.out.println("Low stock for " + bookId + ". Triggering restock.");
                        restock(bookId, 50);  // Restock to a specified level
                    }
                });
                try {
                    TimeUnit.MINUTES.sleep(5);  // Check stock every 5 minutes
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    public CompletableFuture<Void> processSale(String bookId, int quantity) {
        return CompletableFuture.runAsync(() -> {
            System.out.println("Processing sale for " + bookId);
            inventory.computeIfPresent(bookId, (key, val) -> val - quantity);
        }, executorService);
    }

    public CompletableFuture<Void> restock(String bookId, int quantity) {
        return CompletableFuture.runAsync(() -> {
            System.out.println("Restocking " + bookId);
            inventory.compute(bookId, (key, val) -> (val == null ? 0 : val) + quantity);
        }, executorService);
    }

    public CompletableFuture<Integer> checkAvailability(String bookId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Checking availability for " + bookId);
            return inventory.getOrDefault(bookId, 0);
        }, executorService);
    }

    public void shutdown() {
        try {
            executorService.shutdown();
            if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
