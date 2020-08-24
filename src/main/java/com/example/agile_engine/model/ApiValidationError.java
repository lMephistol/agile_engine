package com.example.agile_engine.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"object", "field", "rejectedValue", "message"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiValidationError{
    private String message;
    private String object;
    private String field;
    private Object rejectedValue;

    public ApiValidationError(String object, String field, Object rejectedValue, String message) {
        this.message = message;
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
