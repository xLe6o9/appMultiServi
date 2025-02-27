package xfactor.org.app.aop.errors;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class CustomResponseDTO {

    private final HttpStatus status;
    private final String error;
    private final String message;
    private final String details;
    private final LocalDateTime date;

//    CONSTRUCTOR
    public CustomResponseDTO(HttpStatus status, String error, String details, String message) {
        this.status = status;
        this.error = error;
        this.details = details.replace("uri=", "");
        this.message = message;
        this.date = LocalDateTime.now();
    }

    //    GET & SET
    public HttpStatus getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
