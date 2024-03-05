package ma.ensa.pfaproject.controllers;


import ma.ensa.pfaproject.dtos.CustomErrorResponse;
import ma.ensa.pfaproject.exceptions.RessourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RessourceNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleResourceNotFoundException(RessourceNotFoundException ex) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(ex.getResourceType(), ex.getResourceId(), ex.getErrorMessage());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }
}
