package com.example.agile_engine.controllers;

import com.example.agile_engine.services.AmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/default")
public class DefaultController {
    private final AmountService amountService;

    @Autowired
    public DefaultController(AmountService amountService) {
        this.amountService = amountService;
    }

    @GetMapping
    public ResponseEntity<Integer> getAmount() {
        return ResponseEntity.ok(amountService.getAmount().get());
    }
}
