package whattoplay.controllers.controllerAdvice;

import whattoplay.exceptions.NotValidPasswordException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Andrzej
 */

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = { EmptyResultDataAccessException.class})
    protected ResponseEntity<Object> handleWrongGameIdConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "No game found.";
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_PLAIN);
        //ResponseEntity<String>(bodyOfResponse, responseHeaders, HttpStatus.NOT_FOUND);
        return handleExceptionInternal(ex, bodyOfResponse, responseHeaders, HttpStatus.NOT_FOUND, request);
    }
    
    @ExceptionHandler(value = { NotValidPasswordException.class})
    protected ResponseEntity<Object> handleWrongPasswordConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Wrong password.";
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_PLAIN);
        return handleExceptionInternal(ex, bodyOfResponse, responseHeaders, HttpStatus.CONFLICT, request);
    }
    
}
