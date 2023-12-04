package com.example.parkingmanager;

public class Lot {
    private String name;
    private int totalSpaces;
    private int occupiedSpaces;
    private int availableSpaces;
    private double occupancyRate;

    public Lot(String name, int totalSpaces, int occupiedSpaces, int availableSpaces, double occupancyRate) {
        this.name = name;
        this.totalSpaces = totalSpaces;
        this.occupiedSpaces = occupiedSpaces;
        this.availableSpaces = availableSpaces;
        this.occupancyRate = occupancyRate;
    }

    // Add getters for properties

    public String getName() {
        return name;
    }

    public int getTotalSpaces() {
        return totalSpaces;
    }

    public int getOccupiedSpaces() {
        return occupiedSpaces;
    }

    public int getAvailableSpaces() {
        return availableSpaces;
    }

    public double getOccupancyRate() {
        return occupancyRate;
    }
}
