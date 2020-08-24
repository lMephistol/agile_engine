package com.example.agile_engine.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.TimeZone;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

public class TransactionPayloadResponse {

    @Null
    private String id;
    @NotNull
    @Pattern(regexp = "credit|debit", message = "Value must match one of ({regexp})")
    private String type;
    @NotNull
    private Integer amount;
    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'", timezone = "UTC")
    private LocalDateTime effectiveDate;

    public TransactionPayloadResponse() {

    }

    private TransactionPayloadResponse(Builder builder) {
        setId(builder.id);
        setType(builder.type);
        setAmount(builder.amount);
        setEffectiveDate(builder.effectiveDate);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(TransactionPayloadResponse copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.type = copy.getType();
        builder.amount = copy.getAmount();
        builder.effectiveDate = copy.getEffectiveDate();
        return builder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    public static final class Builder {

        private String id;
        private String type;
        private Integer amount;
        private LocalDateTime effectiveDate;

        private Builder() {
        }

        public Builder withId(String val) {
            id = val;
            return this;
        }

        public Builder withType(String val) {
            type = val;
            return this;
        }

        public Builder withAmount(Integer val) {
            amount = val;
            return this;
        }

        public Builder withEffectiveDate(LocalDateTime val) {
            effectiveDate = val;
            return this;
        }

        public TransactionPayloadResponse build() {
            return new TransactionPayloadResponse(this);
        }
    }
}
