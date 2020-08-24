package com.example.agile_engine.services;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

@Service
public class AmountService {

    private final AtomicInteger amount;

    public AmountService() {
        this.amount = new AtomicInteger(0);
    }

    public AtomicInteger getAmount() {
        return amount;
    }
}
