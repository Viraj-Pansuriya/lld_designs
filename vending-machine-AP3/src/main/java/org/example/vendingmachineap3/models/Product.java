package org.example.vendingmachineap3.models;

public enum Product {
    PEPSI(20 , "Pepsi") ,
    COCA_COLA(25 , "Coca Cola"),
    SPRITE(25 , "Sprite"),
    WATER(10 , "Water"),
    MILK(30 , "Milk"),
    CHOCOLATE(50 , "Chocolate"),
    JUICE(40, "Juice"),
    SODA(15, "Soda"),
    PROTEIN_SHAKE(70 , "Protein Shake"),;

     public final int price;
     public final String name;

    Product(int price, String name) {
        this.name = name;
        this.price = price; // Default price for all items except Pepsi
    }
}
