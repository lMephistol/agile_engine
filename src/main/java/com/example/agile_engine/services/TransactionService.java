package com.example.agile_engine.services;

import com.example.agile_engine.model.TransactionPayloadResponse;
import com.example.agile_engine.utils.Util;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.xml.bind.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final ConcurrentHashMap<String, TransactionPayloadResponse> holder;
    private final AmountService amountService;

    @Autowired
    public TransactionService(AmountService amountService) {
        holder = new ConcurrentHashMap<>();
        this.amountService = amountService;
    }


    public Collection<TransactionPayloadResponse> getAllTransaction() {
        return holder.values();
    }

    public TransactionPayloadResponse getTransaction(String transactionId) {
        return holder.get(transactionId);
    }

    public TransactionPayloadResponse commitTransaction(TransactionPayloadResponse transaction)
            throws ValidationException {
        Integer payloadAmount = transaction.getAmount();
        String operationType = transaction.getType();
        String uuid = UUID.randomUUID().toString();
        AtomicInteger amount = amountService.getAmount();
        while (true) {
            int currentAmount = amount.get();
            if (StringUtils.equals(operationType, Util.CREDIT_OPERATION)) {
                int newAmount = currentAmount + payloadAmount;
                if (amount.compareAndSet(currentAmount, newAmount)) {
                    return buildAndSafeResponse(transaction, uuid);
                }
            } else {
                int newAmount = currentAmount - payloadAmount;
                if (newAmount < 0) {
                    throw new ValidationException(
                            "Сan not decrease the value of amount below zero. currentAmount = " + currentAmount
                                    + " requestedAmount = " + payloadAmount);
                }
                if (amount.compareAndSet(currentAmount, newAmount)) {
                    return buildAndSafeResponse(transaction, uuid);
                }
            }
        }
    }

    private TransactionPayloadResponse buildAndSafeResponse(TransactionPayloadResponse transaction, String uuid) {
        TransactionPayloadResponse response = TransactionPayloadResponse.newBuilder(transaction)
                .withEffectiveDate(LocalDateTime.now(Clock.systemUTC()))
                .withId(uuid)
                .build();
        holder.put(uuid, response);
        return response;
    }
}
