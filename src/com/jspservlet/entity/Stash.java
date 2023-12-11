package com.jspservlet.entity;

import java.util.Map;
import java.util.Set;

public class Stash {
    private String name;
    private Map<Book, Integer> inventory;
    private String locationCity; // city
    private String locationStreet;
    private Set<String> shipAddress;

    public Stash() {
    }

    public Stash(String name, Map<Book, Integer> inventory,
                 String locationCity, String locationStreet, Set<String> shipAddress) {
        this.name = name;
        this.inventory = inventory;
        this.locationCity = locationCity;
        this.locationStreet = locationStreet;
        this.shipAddress = shipAddress;
    }

    public Map<Book, Integer> getInventory() {
        return this.inventory;
    }

    public String getLocation() {
        return this.locationCity + this.locationStreet;
    }

    public String getName() {
        return this.name;
    }

    public void setInventory(Map<Book, Integer> inventory) {
        this.inventory = inventory;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public void setLocationStreet(String locationStreet) {
        this.locationStreet = locationStreet;
    }

    public String distribute(String deliveryAddress) {
        for (String canShip : this.shipAddress) {
            if (canShip.equals(deliveryAddress)) {
                return this.name;
            }
        }
        return "not this stash";
    }
}
