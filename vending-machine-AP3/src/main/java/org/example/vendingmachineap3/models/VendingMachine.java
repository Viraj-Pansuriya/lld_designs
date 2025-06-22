package org.example.vendingmachineap3.models;

import org.example.vendingmachineap3.service.StockService;

public class VendingMachine {

    private final StockService stockService;

    public VendingMachine(StockService stockService) {
        this.stockService = stockService;
    }


}
