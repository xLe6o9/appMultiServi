package xfactor.org.app.aop.errors;

import org.apache.coyote.BadRequestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

//  PERSONALIZAR ERROR 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponseDTO> handlerException(Exception e, WebRequest webRequest) {
        LOGGER.error("handlerException - message {}", e.getMessage());
        CustomResponseDTO customResDTO = new CustomResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR: GlobalExceptionHanlder -> handlerException -> Exception.", webRequest.getDescription(false), e.getMessage());
        return ResponseEntity.status(500).body(customResDTO);
    }

//  PERSONALIZAR ERROR 404
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomResponseDTO> notFoundException(NotFoundException nfe, WebRequest webRequest) {
        LOGGER.error("notFoundException - message {}", nfe.getMessage());
        CustomResponseDTO customResDTO = new CustomResponseDTO(HttpStatus.NOT_FOUND, "ERROR: GlobalExceptionHanlder -> handlerException -> NotFoundException.", webRequest.getDescription(false), nfe.getMessage());
        return ResponseEntity.status(404).body(customResDTO);
    }

//    @ExceptionHandler(BadRequestException.class)
//    public ResponseEntity<CustomResponseDTO> badRequestHandler(BadRequestException bre) {
//        LOGGER.error("badRequestHandler - message {}", bre.getMessage());
//        CustomResponseDTO customResDTO = new CustomResponseDTO("BadRequestException ERROR", bre.getMessage(), 400, bre.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customResDTO);
//    }


//    @ExceptionHandler(value = NullPointerException.class)
//    public ResponseEntity<CustomResponseDTO> handlerNullPointerException(NullPointerException npe) {
//        LOGGER.error("NullPointerException - message {}", npe.getMessage());
//        CustomResponseDTO customResDTO = new CustomResponseDTO("NullPointerException ERROR", npe.getMessage(), 500, npe.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResDTO);
//    }

}
