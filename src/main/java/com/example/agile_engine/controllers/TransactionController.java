package com.example.agile_engine.controllers;

import com.example.agile_engine.model.TransactionPayloadResponse;
import com.example.agile_engine.services.TransactionService;
import com.example.agile_engine.utils.Util;
import java.util.Collection;
import javax.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<Collection<TransactionPayloadResponse>> getAllTransactions() {
        return ResponseEntity.ok(service.getAllTransaction());
    }

    @PostMapping
    public ResponseEntity<TransactionPayloadResponse> commitTransaction(
            @Validated @RequestBody TransactionPayloadResponse body) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(service.commitTransaction(body));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionPayloadResponse> getTransaction(
            @PathVariable("transactionId") @Pattern(regexp = Util.UUID_PATTERN, message = "TransactionId incorrect format")
                    String transactionId) {
        TransactionPayloadResponse transaction = service.getTransaction(transactionId);
        if (transaction == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transaction);
    }


}
