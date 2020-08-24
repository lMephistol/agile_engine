package com.example.agile_engine.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;

@JsonInclude(Include.NON_NULL)
public class ApiError implements Serializable {

    private HttpStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'", timezone = "UTC")
    private LocalDateTime timestamp;
    private Integer code;
    private String message;
    private String path;
    private List<ApiValidationError> errorList;

    private ApiError(Builder builder) {
        setStatus(builder.status);
        setTimestamp(builder.timestamp);
        setCode(builder.code);
        setMessage(builder.message);
        setPath(builder.path);
        setErrorList(builder.errorList);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ApiError copy) {
        Builder builder = new Builder();
        builder.status = copy.getStatus();
        builder.timestamp = copy.getTimestamp();
        builder.code = copy.getCode();
        builder.message = copy.getMessage();
        builder.path = copy.getPath();
        builder.errorList = copy.getErrorList();
        return builder;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<ApiValidationError> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ApiValidationError> errorList) {
        this.errorList = errorList;
    }


    public static final class Builder {

        private HttpStatus status;
        private LocalDateTime timestamp;
        private Integer code;
        private String message;
        private String path;
        private List<ApiValidationError> errorList;

        private Builder() {
        }

        public Builder withStatus(HttpStatus val) {
            status = val;
            return this;
        }

        public Builder withTimestamp(LocalDateTime val) {
            timestamp = val;
            return this;
        }

        public Builder withCode(Integer val) {
            code = val;
            return this;
        }

        public Builder withMessage(String val) {
            message = val;
            return this;
        }

        public Builder withPath(String val) {
            path = val;
            return this;
        }

        public Builder withErrorList(List<ApiValidationError> val) {
            errorList = val;
            return this;
        }

        public ApiError build() {
            return new ApiError(this);
        }
    }
}
