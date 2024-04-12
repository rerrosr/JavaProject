package com.example.univerproject.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

/** The type Exceptions handler. */
@RestControllerAdvice
@Slf4j
public class ExceptionsHandler {
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
  /**
   * Handle internal server error error response.
   *
   * @param ex the ex
   * @return the error response
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(RuntimeException.class)
  public ErrorResponse handleInternalServerError(RuntimeException ex) {
<<<<<<< Updated upstream
    log.error("ERROR, 500 CODE");
    return new ErrorResponse(ex.getMessage());
=======
    log.error("(500) Internal Server Error", ex);
    return new ErrorResponse(
        "500: Internal Server Error. Please try again later or contact support.");
>>>>>>> Stashed changes
  }

  /**
   * Handle bad request exception error response.
   *
   * @param ex the ex
   * @return the error response
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
<<<<<<< Updated upstream
          HttpClientErrorException.class,
          HttpMessageNotReadableException.class,
          MethodArgumentNotValidException.class,
          MissingServletRequestParameterException.class,
          ConstraintViolationException.class
  })
  public ErrorResponse handleBadRequestException(Exception ex) {
    log.error("ERROR, 400 CODE");
    return new ErrorResponse("400 ERROR, BAD REQUEST");
=======
    HttpClientErrorException.class,
    HttpMessageNotReadableException.class,
    MethodArgumentNotValidException.class,
    MissingServletRequestParameterException.class,
    ConstraintViolationException.class
  })
  public ErrorResponse handleBadRequestException(Exception ex) {
    log.error("(400) Bad Request", ex);
    return new ErrorResponse(
        "400: Bad Request. Please check your request parameters and try again.");
>>>>>>> Stashed changes
  }

  /**
   * Handle method not allowed error response.
   *
   * @param ex the ex
   * @return the error response
   */
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
<<<<<<< Updated upstream
  public ErrorResponse handleMethodNotAllowed(Exception ex) {
    log.error("ERROR, 405 CODE");
    return new ErrorResponse("405 ERROR, METHOD NOT ALLOWED");
  }

  /**
   * Handler found exception error response.
=======
  public ErrorResponse handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
    log.error("(405) Method Not Allowed", ex);
    return new ErrorResponse(
        "405: Method Not Allowed. Supported methods: " + ex.getSupportedHttpMethods());
  }

  /**
   * Handle not found exception error response.
>>>>>>> Stashed changes
   *
   * @param ex the ex
   * @return the error response
   */
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoHandlerFoundException.class)
<<<<<<< Updated upstream
  public ErrorResponse handlerFoundException(Exception ex) {
    log.error("ERROR, 404 CODE");
    return new ErrorResponse("404 ERROR, NOT FOUND");
  }
}
=======
  public ErrorResponse handleNotFoundException(NoHandlerFoundException ex) {
    log.error("(404) Not Found", ex);
    return new ErrorResponse("404: Resource Not Found. Please check the URL and try again.");
  }
}
>>>>>>> Stashed changes
