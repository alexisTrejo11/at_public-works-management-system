package com.publicworks.public_works_management.shared.response.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWrapper<T> {
    @JsonProperty("success")
    private boolean success;

    @JsonProperty("data")
    private T data;

    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private int code;

    @JsonProperty("time_stamp")
    private LocalDateTime timestamp;

    public static <T> ResponseWrapper<T> found(T data, String entity, String parameter, Object parameterValue) {
        String message = entity + " with " + parameter + " " + parameterValue + " successfully fetched";
        return new ResponseWrapper<>(
                true,
                data,
                message,
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
    }

    public static <T> ResponseWrapper<T> found(T data, String entity) {
        String message = entity + " successfully fetched";
        return new ResponseWrapper<>(
                true,
                data,
                message,
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
    }


    public static <T> ResponseWrapper<T> ok(T data, String message) {
        return new ResponseWrapper<>(
                true,
                data,
                message,
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
    }

    public static <T> ResponseWrapper<T> ok(String message) {
        return new ResponseWrapper<>(
                true,
                null,
                message,
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
    }

    public static <T> ResponseWrapper<T> created(T data, String entity) {
        String createMessage = entity + " successfully created";
        return new ResponseWrapper<>(
                true,
                data,
                createMessage,
                HttpStatus.CREATED.value(),
                LocalDateTime.now()
        );
    }

    public static <T> ResponseWrapper<T> created(String entity) {
        String createMessage = entity + " successfully created";
        return new ResponseWrapper<>(
                true,
                null,
                createMessage,
                HttpStatus.CREATED.value(),
                LocalDateTime.now()
        );
    }

    public static <T> ResponseWrapper<T> updated(T data, String entity) {
        String updateMessage = entity + " successfully updated";
        return new ResponseWrapper<>(
                true,
                data,
                updateMessage,
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
    }

    public static <T> ResponseWrapper<T> updated(String entity) {
        String updateMessage = entity + " successfully updated";
        return new ResponseWrapper<>(
                true,
                null,
                updateMessage,
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
    }

    public static <T> ResponseWrapper<T> deleted(T data, String entity) {
        String deleteMessage = entity + " successfully deleted";
        return new ResponseWrapper<>(
                true,
                data,
                deleteMessage,
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
    }

    public static <T> ResponseWrapper<T> deleted(String entity) {
        String deleteMessage = entity + " successfully deleted";
        return new ResponseWrapper<>(
                true,
                null,
                deleteMessage,
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
    }


    public static <T> ResponseWrapper<T> conflict(String message) {
        return new ResponseWrapper<>(
                false,
                null,
                message,
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );
    }

    public static <T> ResponseWrapper<T> notFound(String entity, String parameter, Object parameterValue) {
        String notFoundMessage = entity + " with " + parameter + " " + parameterValue + " not found";
        return new ResponseWrapper<>(
                false,
                null,
                notFoundMessage,
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
    }

    public static <T> ResponseWrapper<T> notFound(String notFoundMessage) {
        return new ResponseWrapper<>(
                false,
                null,
                notFoundMessage,
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
    }


    public static <T> ResponseWrapper<T> badRequest(String message) {
        return new ResponseWrapper<>(
                false,
                null,
                message,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
    }

    public static <T> ResponseWrapper<T> error(String message, int code) {
        return new ResponseWrapper<>(
                false,
                null,
                message,
                code,
                LocalDateTime.now()
        );
    }

}